// Author: Joe Mulholland
// Student Number: C21404162
// Date: 07/04/2023
// Program Description: A search engine with a GUI. Has the option to search between 3 files that are created when ran, or any file on device.
// Results are displayed with percent match that shows how close the searched term matches the terms in the txt file
// Program also features clear button to clear results

package searchengine;

//Packages
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//GUI Class
public class gui extends JFrame implements ActionListener{

//GUI Framework
static JFrame frame;
static JTextField sterm;
static JButton Search;
static JButton Clear;
static JPanel panel1;
static JTextArea textarea;

//GUI variables
public void gui()
{
	frame = new JFrame("Search Engine");
	frame.setSize(600, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	sterm = new JTextField(20);
	Search = new JButton("Search");
	Clear = new JButton("Clear Results");
	panel1 = new JPanel();
	panel1.add(sterm);
	panel1.add(Search);
	panel1.add(Clear);
	textarea = new JTextArea();
	frame.getContentPane().add(BorderLayout.NORTH, panel1);
	frame.getContentPane().add(BorderLayout.CENTER, textarea);
	frame.setVisible(true);
	Search.addActionListener(this);
	Clear.addActionListener(this);
}

//Search algorithm
public void searchFileForTerm(String filename, List<String> searchTerms, JTextArea textarea) throws IOException {
    //Read through file/s
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        int totalScore = 0;
        int totalCharacters = 0;

        //Read file loop
        while ((line = reader.readLine()) != null) {
            int score = 0;
            int characters = line.length();
            for (String searchTerm : searchTerms) {
                for (int i = 0; i <= line.length() - searchTerm.length(); i++) {
                    String substring = line.substring(i, i + searchTerm.length());
                    if (substring.equalsIgnoreCase(searchTerm)) {
                        score += searchTerm.length();
                    }
                }
            }
            totalScore += score;
            totalCharacters += characters;
        }

        //Percent match calculation
        double percent = (double) totalScore / totalCharacters * 100.0;

        //Search results
        if (totalScore > 0) {
            textarea.append(String.format("Terms: %s found in %s Percentage: (%.2f%%)\n", searchTerms, filename, percent));
        } else {
            textarea.append(String.format("Terms: %s not found in %s. Terms might be misspelled.\n", searchTerms, filename));
        }
    }
}


//Menu within GUI
public void actionPerformed(ActionEvent e)
{
	
	//If 'Search' is selected
	if(e.getSource() == Search)
	{		
		String searchInput = sterm.getText();
		List<String> searchTerms = Arrays.asList(searchInput.split("\\s+"));
		
		//Pop-up menu when 'Search' selected
		int option = JOptionPane.showOptionDialog(frame,
                "Please select a range to search within",
                "Search Option",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Search within 3 specified files", "Select a specific file on device", "Cancel"},
                null);
		
		//Pop-up menu response IF yes
        if (option == JOptionPane.YES_OPTION) {
            try {
            	
            	//Read through 3 files specified
                String[] filenames = {"base.txt", "base2.txt", "base3.txt"};
                for (String filename : filenames) {
                    searchFileForTerm(filename, searchTerms, textarea);
                }
            } catch (IOException er) {
                System.err.println("Error reading file : " + er.getMessage());
            }
            
        //Pop-up menu response IF no
        } else if (option == JOptionPane.NO_OPTION) {
        	
        	//Filter to only allow .txt files to be selected
            JFileChooser fileChooser = new JFileChooser(File.listRoots()[0]);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);
            
            //FileChooser
            int fileOption = fileChooser.showOpenDialog(frame);
            if (fileOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                
                //If input is not recognized
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(frame, "File not found" + "\n" + "A full file path is required");
                    return;
                }
                
                //Search for term in file using path specified
                try {
                    searchFileForTerm(file.getAbsolutePath(), searchTerms, textarea);
                } catch (IOException er) {
                    System.err.println("Error reading file : " + er.getMessage());
                }
            }
        }
	}
	
	//Option to clear results
	if(e.getSource() == Clear) {
		textarea.setText("");
	}
}
}