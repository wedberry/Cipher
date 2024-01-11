import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;




public class Driver {
	
	public static void readFile(String fileName) {
		try { 
		      File file = new File(fileName); //open new file
		      Scanner read = new Scanner(file);	//create a new scanner to read the grades txt file
		      
		      //read in first line of the file to create a report card for the first student
		      while(read.hasNextLine()) {
		    	  String data = read.nextLine();
		      
		    	  System.out.println(data);
		      }
		      
		      read.close(); //close file scanner

		      
		    } catch (FileNotFoundException e) {  //this is for if the file is not found :(
		      System.out.println("File not found");
		      e.printStackTrace();
		    }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		FileObject paul = new FileObject();
		FileObject chris = new FileObject();
		FileObject steve = new FileObject();
		
		paul.setContents("top-secret\nWhat the world needs now");
		paul.setName("top-s");
		
		//chris.setContents("The lazy fat cat sat");
		//chris.setName("top-secret");
		
		//steve.setContents("Spring Break is here");
		//steve.setName("sdjg");
		
		String fileName = paul.encrypt();
		System.out.println("Echo: ");
		paul.echo();
		System.out.println("From File: ");
		readFile(fileName);

		
		
		fileName = paul.decrypt();
		System.out.println("Echo: ");
		paul.echo();
		System.out.println("From File: ");

		readFile(fileName);

		
		Msg message = new Msg("hello World\nI am contents");
		
		System.out.println(message.encrypt());
		
		System.out.println(message.decrypt());
		
		
		
		
		//System.out.println(chris.encrypt());
		
		//System.out.println(steve.encrypt());
		
		//System.out.println(e.search("world"));
		//System.out.println(e.search("I am"));
		
		
		
	}

}
