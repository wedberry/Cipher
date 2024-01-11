import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;


public class FileObject  implements Cipherable{

		// Data Members
		private String name, creator, contents;
		private int size;
		
		//-------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------

		// Constructor
		public FileObject() {
			
		}
		//-------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------

		// Methods
		
		// Getters
		//-------------------------------------------------------------------------------------------------------

		public String getName() {
			return this.name;
		}
		
		//-------------------------------------------------------------------------------------------------------
	
		public String getCreator() {
			return this.creator;
		}
		
		//-------------------------------------------------------------------------------------------------------
		
		public int getSize() {
			return this.size;
		}
		
		//-------------------------------------------------------------------------------------------------------

		public String getContents() {
			return this.contents;
		}
		
		//-------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------
		// Setters
		
		public void setName(String n) {
			if(n.length() > 20) { //ensures file name is less than 20 characters
				System.out.println("File Name Too Long");
			}else {
				this.name = n;
			}
		}
		
		//-------------------------------------------------------------------------------------------------------
		
		public void setCreator(String c) {
			this.creator = c;
		}
		
		//-------------------------------------------------------------------------------------------------------
		
		public void setSize(int s) {
			this.size = s;
		}
		
		public void setContents(String c) {
			this.contents = c;
		}
		//-------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------
		// Other Methods
		
		public void echo() {
			System.out.println(this.contents); //prints contents of file to the console
		}
		
		//-------------------------------------------------------------------------------------------------------

		public boolean search(String expression) {
			//search for the expression in the contents of the file
			
			String regex = "(.*)" + expression + "(.*)";  //build regex string for matches method
			String[] tokenizer = this.contents.split("\n"); //delimit on new lines, matches() does not work otherwise
			
			for(int i = 0; i < tokenizer.length; i++) { 
				
				if(tokenizer[i].matches(regex)) { //if expression found
					return true;
				}
			}
			return false; //if expression not found
		}
		
		//-------------------------------------------------------------------------------------------------------

		
		public int countOccurrence(char c) {
			int count = 0; //stores occurrances of the character
			
			for (int i = 0; i < contents.length(); i++) {
				if (contents.charAt(i) == c) {
					count++;
				}
			}
			return count;
		}
		
		//-------------------------------------------------------------------------------------------------------

		public int wordCount() {
			
			int count = 0; //to store total numbers
			
			StringTokenizer tokenizer = new StringTokenizer(contents, " \n");
			
			while(tokenizer.hasMoreTokens()) {
				if(tokenizer.nextToken().length() > 1) {
					count++;
				}
			}
			return count;
			
		}
		//-----------------------------------------------------------------------------------------------------

		public String encrypt() {
			
			String encrypted = ""; //to store final encrypted contents
			
			
			if(this.name.matches("(.*)top-secret(.*)") || this.search("top-secret") ) { //if file contains top-secret key word in name or contents
				
				String backwards = ""; //string that reverse lines will be added to
				
				String[] lines = this.contents.split("\n"); // string array delimited by line
				
				for(int i = 0; i < lines.length; i++) { //loops through array
					
					String reversed = ""; //build reversed line
					
					char[] line = lines[i].toCharArray();
				
					for(int j = line.length - 1; j >=0; j+=-1) { //reverses lines
						reversed += line[j];
					}
					if(i < lines.length-1) {
						backwards = reversed + "\n"; //add reversed line to the reversed string

					} else {
						backwards += reversed; //if last line in contents dont add newline
					}
				}
				
				
				if(this.countOccurrence('t')%2 == 0) { //even # of t's
					
					char[] charArr = backwards.toCharArray(); //take reversed contents
					
					for(int k = 0; k < charArr.length; k++) { //change every t to *
						if(charArr[k] == 't') { //replace t with *
							encrypted += '*';
						} else {
							encrypted += charArr[k]; //add non t chars to string
						}
					}
					
				} else { //odd # of t's
					
					char[] charArr = backwards.toCharArray();
					
					for(int k = 0; k < charArr.length; k++) { //change every t to @
						if(charArr[k] == 't') { //replace t with @
							encrypted+= '@';
						} else {
							encrypted += charArr[k]; // add non t chars to string
						}
					}
				}
				
			} else { //no top-secret key word
				
				String[] tokenizer = this.contents.split(" ");
				
				for(int i = 0; i<tokenizer.length; i++) { //loop through array of words
					if(i % 2 == 1) { //if it is the word at an odd index
						char[] word = tokenizer[i].toCharArray();
						
						for(int k = word.length-1; k >= 0; k+=-1) { //reverse that word
							encrypted += word[k]; //add reversed word
						}
						encrypted += " "; // add a space
					
					
					} else { //word is at even index
						
						encrypted += tokenizer[i] + " "; //add unreversed word and a space
					}
				}
				
			}
			
			try { //Write to file, create file if it doesnt exist
				
				File myFile = new File("encrypted.txt"); //create new file object for encrypted.txt: file to send encrypted contents
				myFile.createNewFile(); //create  new File if and only if encrypted.txt does not exist
				
				FileWriter writer = new FileWriter("encrypted.txt");
				writer.write(encrypted);  //write encrypted contents to the file
				this.contents = encrypted; //store encrypted contents in the FileObject
				
				writer.close();
				
			} catch (IOException e) { //Catch errors with File IO process
			      System.out.println("Error");
			      e.printStackTrace();
			    }
		
			
			return "encrypted.txt"; //return name of destination file
		}
		
		//--------------------------------------------------------------------------------------------------------------------
		

		public String decrypt() {
			String decrypted = ""; //to store final encrypted contents
			
			if(this.name.matches("(.*)top-secret(.*)") || this.search("*erces-po*") || this.search("@erces-po@") || this.search("top-secret")) { //if file contains top-secret key word
				
				String forwards = ""; //string that reverse lines will be added to
				
				String[] lines = this.contents.split("\n"); // string array delimited by line
				
				for(int i = 0; i < lines.length; i++) { //loops through array
					String reversed = ""; 
					
					char[] line = lines[i].toCharArray();
				
					for(int j = line.length - 1; j >=0; j+=-1) { //reverses lines
						reversed += line[j];
					}
					if(i < lines.length-1) {
						forwards = reversed + "\n"; //contents with each line reversed

					} else {
						forwards += reversed;
					}
				}
				
				
				if(this.countOccurrence('*') > 0) { //has *'s
					
					char[] charArr = forwards.toCharArray();
					
					for(int k = 0; k < charArr.length; k++) { //change every t to *
						if(charArr[k] == '*') { //replace * with t
							decrypted += 't';
						} else {
							decrypted += charArr[k];
						}
					}
					
				} else { //has @'s
					
					char[] charArr = forwards.toCharArray();
					
					for(int k = 0; k < charArr.length; k++) { //change every t to @
						if(charArr[k] == '@') { //replace @ with t
							decrypted+= 't';
						} else {
							decrypted += charArr[k];
						}
					}
				}
				
			} else { //no top-secret key word
				
				String[] tokenizer = this.contents.split(" ");
				
				for(int i = 0; i<tokenizer.length; i++) { //loop through array of words
					if(i % 2 == 1) { //if it is the word at an odd index
						char[] word = tokenizer[i].toCharArray();
						
						for(int k = word.length-1; k >= 0; k+=-1) { //reverse that word
							decrypted += word[k];
						}
						decrypted += " "; // add a space
					} else {
						
						decrypted += tokenizer[i] + " "; //add unreversed word and a space
					}
				}
				
			}
			try {
				File myFile = new File("unencrypted.txt"); //create new File object to store decrypted contents
				myFile.createNewFile(); //create new File if and only if unencrypted.txt does no already exist
				
				FileWriter writer = new FileWriter("unencrypted.txt");
				writer.write(decrypted); //write decrypted contents to new file
				this.contents = decrypted; //store unencrypted contents in the file object
				
				writer.close();
				
			} catch (IOException e) { //handle IO errors 
			      System.out.println("Error");
			      e.printStackTrace();
			    }
		
			
			return "unencrypted.txt"; //return name of a new file to which the decrypted contents have been sent
		}
}
