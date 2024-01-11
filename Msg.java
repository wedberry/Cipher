package assignment2;

public class Msg implements Cipherable{
	
	//Data Members
	private String contents, source, destination;
	private char type;
	
	// Constructors
	
	//-----------------------------------------------------------------------------------------------------

	public Msg() { 
	//no args constructor
		
	}
	
	public Msg(String c) {
		//constructor with contents as an argument
		contents = c;
	}
	//-----------------------------------------------------------------------------------------------------

	//Methods
	//Getters
	
	public String getContents() {
		return contents;
	}
	
	public String getSource() {
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public char getType() {
		return type;
	}
	//-----------------------------------------------------------------------------------------------------

	//Setters
	
	public void setContents(String c) {
		contents = c;
	}
	
	public void setSource(String s) {
		source = s;
	}

	public void setDestination(String d) {
		destination = d;
	}
	
	public void setType(char t) {
		type = t;
	}
	//-----------------------------------------------------------------------------------------------------

	//Other Methods
	
	public double costOfTransfer() {
		//Type = email
		if (type == 'e') {
			return 0.00;
			
		//type = text
		} else if (type == 't') {
			return 0.25;
			
		//type = fax
		} else if (type == 'f') {
			return 1.50;
			
		//invalid char for message type
		} else {
			System.out.println("Invalid Message Type");
			return -1;
		}
	}
	//-----------------------------------------------------------------------------------------------------

	public String encrypt() {
		
		String encrypted = ""; // to build finished encrypted string
		
		String[] tokenizer = this.contents.split(" "); //split strings by word
		
		for(int i = 0; i<tokenizer.length; i++) { //loop through array of words, first to last
			
			char[] word = tokenizer[i].toCharArray(); 
			
			for(int k = word.length-1; k >= 0; k+=-1) { //loop through letters in each word, last to first
				encrypted += word[k]; //add letter to final string, this will build a list of words, as they appear, but spelling reversed
			}

		encrypted = encrypted + " ";//adds spaces between reversed words

		}
		
		this.contents = encrypted; //modify contents
		return encrypted; //return encrypted contents
	}
	//-----------------------------------------------------------------------------------------------------

	public String decrypt() {
		
		String decrypted = ""; // to build finished decrypted string
		
		String[] tokenizer = this.contents.split(" "); //divide string into array of words
		
		for(int i = 0; i<tokenizer.length; i++) { //loop through words in order of first to last
			
			char[] word = tokenizer[i].toCharArray();
			
			for(int k = word.length-1; k >= 0; k+=-1) { //reverse each word
				decrypted += word[k]; //builds reversed words in final decrypted string
			}

		decrypted = decrypted + " "; //add spaces between each word

		}
		
		this.contents = decrypted; //modify contents
		return decrypted; // return decrypted string
	}
}
