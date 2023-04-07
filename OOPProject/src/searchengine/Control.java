package searchengine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Control extends gui{

		  public static void main(String[] args) {

			    File base= new File("base.txt");
			    try {
					if (base.createNewFile())
						System.out.println("The file is created");
					else
						System.out.println("The file already exists");
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    try {
					FileWriter writer= new FileWriter("base.txt");
					writer.write("Wizard" + "Author " +  "Janitor " +  "Doctor " +  "Teacher " +  "Scientist " +  "Professor " +  "Hitman " +  "Mechanic " +  "Wizard ");
					System.out.println("The file is written");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    File base2= new File("base2.txt");
			    try {
					if (base2.createNewFile())
						System.out.println("The file is created");
					else
						System.out.println("The file already exists");
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    try {
					FileWriter writer= new FileWriter("base2.txt");
					writer.write("Sad " + "Angry " + "Happy " + "Jealous " + "Excited " + "Deranged " + "Motivated " + "Confused " + "Scared ");
					System.out.println("The file is written");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    File base3= new File("base3.txt");
			    try {
					if (base3.createNewFile())
						System.out.println("The file is created");
					else
						System.out.println("The file already exists");
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
			    try {
					FileWriter writer= new FileWriter("base3.txt");
					writer.write("John " + "James " + "Ron " + "Joe " + "Nick " + "Sarah " + "Molly " + "Jean " + "Paula ");
					System.out.println("The file is written");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			    
				gui Interface = new gui();
				Interface.gui();
				}
}
