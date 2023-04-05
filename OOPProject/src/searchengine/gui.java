package searchengine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileFilter;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class gui extends JFrame implements ActionListener{

//static ArrayList<Person> people =new ArrayList<Person>();
static JFrame frame;
static JTextField sterm;
static JButton Search;
static JButton Clear;
static JPanel panel1;
static JTextArea textarea;

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

public void searchFileForTerm(String filename, List<String> searchTerms, JTextArea textarea) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        int termCount = 0;
        int totalCount = 0;
        
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length - searchTerms.size() + 1; i++) {
                boolean match = true;
                for (int j = 0; j < searchTerms.size(); j++) {
                    if (!words[i + j].equalsIgnoreCase(searchTerms.get(j))) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    termCount++;
                }
                totalCount++;
            }
        }
        
        double percent = (double)termCount / totalCount * 100.0;
        
        if (termCount > 0) {
            textarea.append(String.format("Term: %s found in %s %.2f%% of the time\n", searchTerms, filename, percent));
        } else {
            textarea.append(String.format("Term: %s not found in %s\n", searchTerms, filename));
        }
    }
}


public void actionPerformed(ActionEvent e)
{
	if(e.getSource() == Search)
	{		
		String searchInput = sterm.getText();
		List<String> searchTerms = Arrays.asList(searchInput.split("\\s+"));
//		if (searchTerms.size() == 0) {
//			JOptionPane.showMessageDialog(frame,  "Please enter at least one term");
//			return;
//		}
		
		int option = JOptionPane.showOptionDialog(frame,
                "Please select a range to search within",
                "Search Option",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Search within 3 specified files", "Select a specific file on device", "Cancel"},
                null);
		
        if (option == JOptionPane.YES_OPTION) {
            try {
                String[] filenames = {"base.txt", "base2.txt", "base3.txt"};
                for (String filename : filenames) {
                    searchFileForTerm(filename, searchTerms, textarea);
                }
            } catch (IOException er) {
                System.err.println("Error reading file : " + er.getMessage());
            }
        } else if (option == JOptionPane.NO_OPTION) {
            JFileChooser fileChooser = new JFileChooser(File.listRoots()[0]);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(filter);
            
            int fileOption = fileChooser.showOpenDialog(frame);
            if (fileOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (!file.exists()) {
                    JOptionPane.showMessageDialog(frame, "File not found" + "\n" + "A full file path is required");
                    return;
                }
                try {
                    searchFileForTerm(file.getAbsolutePath(), searchTerms, textarea);
                } catch (IOException er) {
                    System.err.println("Error reading file : " + er.getMessage());
                }
            }
        }
	}
	
	if(e.getSource() == Clear) {
		textarea.setText("");
	}
}
}