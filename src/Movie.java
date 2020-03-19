package Movie;
import java.util.Arrays; 
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Movie {
	//releaseYear, eleCount, and movieNamesNonQuote are static because multiple classes will be utilizing them.
	private static String[] releaseYear; //For the release year of the movie.
	public static int eleCount; //The length of the movie array as well as the array's length for releaseYear.
	public static String[] movieNamesNonQuotes; //The array for the movie's names.
	public static void main(String[] args) throws IOException{
	BufferedReader movieNumber = new BufferedReader(new java.io.FileReader("movies.csv")); //Will be necessary to count the amount of movies in the list.
	BufferedReader moviesReader = new BufferedReader(new java.io.FileReader("movies.csv")); //This reader will be used to read all the movie titles.
	//The purposes of the BufferedReaders will be expressed through the scanners. 
	Scanner movieCount = new Scanner(movieNumber);
	Scanner file = new Scanner(moviesReader); 
	int skipLine = 0; 
	//We need to skip the beginning line, since it's not an entry for a movie.
	while(skipLine < 1)  {
		movieCount.nextLine();
		skipLine++;
	}
	skipLine = 0;
	int lineCount = 0;
	//Gathering the elementCount for the arrays.
	while(movieCount.hasNext()) { 
		movieCount.nextLine();
		lineCount++;
		if(!(movieCount.hasNext()))
		eleCount = lineCount;
	}
	
	movieCount.close();
	//Skipping the beginning line for the movieReader scanner ("file")
	while(skipLine < 1) {
		file.nextLine();
		skipLine++;
	}
	//An array that will store the lines and will be used by another array that will provide proper presentation of the movie names (Such as removing quotation marks and separating the release year.)
	String[] movieNames = new String[eleCount];
	
	for(int i = 0; i < eleCount;i++) {
		String movieLine = file.nextLine();
			//This split doesn't separate the commas inside the element.
		
			String[] movieLineArray =  movieLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			//"[1]" is where the movie is located
			movieNames[i] = movieLineArray[1];
	}
	file.close();
	Arrays.sort(movieNames,String.CASE_INSENSITIVE_ORDER);
	//Now, the arrays have the same length.
	movieNamesNonQuotes = new String[eleCount];
	releaseYear = new String[eleCount];
	//Converting movieNames' elements into strings, modifying the strings to separate and gather the movie's name and it's release year
	for(int i = 0;i<movieNames.length;i++){
		//Turning element into a String
		String strLine = movieNames[i].toString();
		//Using the beginning position to see if it's a double quote.
		String strPositionNum = strLine.substring(0,1);
		String endPositionNum = strLine.substring(strLine.length()-1,strLine.length());
		//Beginning double quote checker. 
		if(strPositionNum.startsWith("\"")) {
			//Checks if the Beginning of the movie name starts with quotations. 
			if(strLine.charAt(strLine.length()-1) == ' ') {
				//This if statement checks if there is any white space at the end of strLine. If it is present, then the beginning of substring has to subtract StrLine.length() by one less
				//The reason for this is because the whitespace could prevent us from cutting off unnecessary characters 
				//(When we print "Quest for Fire (Guerre du feu, La)" from movieNamesNonQuotes, for example, we want the releaseYear to be (1981) as opposed to (1981)"
				releaseYear[i] = strLine.substring(strLine.length()-8,strLine.length());
				//We need to mark the substring from the beginning of the title (Without quotation marks) to the left parenthesis mark that encapsulates the year. All we need to do is use the beginIndex for releaseYear
				//and use that as movieNamesNonQuotes' endIndex
				//With that, we will have our movie name.
				movieNamesNonQuotes[i] = strLine.substring(1, strLine.length()-8);
				continue;
			}
			
			releaseYear[i] = strLine.substring(strLine.length()-7,strLine.length()-1);
			movieNamesNonQuotes[i] = strLine.substring(1, strLine.length()-7);
		}
		//If there are no quotes, then the song's name is included without the need of a substring.
		else {
			if(strLine.charAt(strLine.length()-1) == ' ') {
				//For movie names without quotation marks, releaseYears[i] takes a smaller substring compared to movie names with them.
				releaseYear[i] = strLine.substring(strLine.length()-7,strLine.length());
				movieNamesNonQuotes[i] = strLine.substring(0, strLine.length()-7);
				continue;
			} //There are movie titles that do not have the releaseYear next to them. If they don't, we can just add them to the list with further modification.
			String endPositionQuotes = strLine.substring(strLine.length()-1,strLine.length());
			if(!((endPositionQuotes.startsWith(")")))){
					movieNamesNonQuotes[i] = strLine;
			continue;
			}
			//For the movie and it's release year that doesn't contain blank space and is not surrounded by double quotation marks.
			releaseYear[i] = strLine.substring(strLine.length()-6,strLine.length());
			movieNamesNonQuotes[i] = strLine.substring(0,strLine.length()-7);
		}
	}
	System.out.println("--The Full Movie List---");
	System.out.println();
	for(int f = 0; f< movieNamesNonQuotes.length;f++) {
		System.out.println(movieNamesNonQuotes[f]);
	}
	System.out.println("--End of List--");
	
	System.out.println("--Binary Tree--");
	Tree tree = new Tree();
	Tree tree2 = new Tree();
	Tree tree3 = new Tree();
	Tree tree4 = new Tree();
	Tree tree5 = new Tree();
	Tree treeMultiple = new Tree();
	//We will add asterisks to the text that explains the actions of the tree objects
	//in order to indicate text that's not part of the object.
	tree.insert("Toy Story","Toy Story 3");
	System.out.println("***Travelling through Toy Story to Story 3***");
	tree.displayResults(tree.root);
	
	tree2.insert("Toy Story 3","Toy Story");
	System.out.println();
	System.out.println("***This tree prevents you from travelling in descending order. For example, this is what displays when you travel from Toy Story 3 to Toy Story***");
	tree2.displayResults(tree2.root);
	System.out.println();
	
	tree3.insert("Young Guns II","Z");
	System.out.println("***Going through a bigger sub set***");
	tree3.displayResults(tree3.root);
	System.out.println();
	
	tree4.insert("Toy Story","Dof 2");
	System.out.println("***This is what displays when one or both elements of start and end are not on the list***");
	tree4.displayResults(tree4.root);
	System.out.println();
	
	System.out.println("***A subset that includes a movie with no release year as is startIndex***");
	tree5.insert("Black Mirror", "Black Narcissus");
	tree5.displayResults(tree5.root);
	System.out.println();
	
	System.out.println("***Keep in mind, this tree class also allows you to go through multiple subset nodes. Let's add the (Toy Story to Toy Story 3) subset and (Young Guns II to Z) subset onto a new tree.***");
	treeMultiple.insert("Toy Story","Toy Story 3");
	treeMultiple.insert("Young Guns II","Z");
	treeMultiple.displayResults(treeMultiple.root);
	}
	static class Node{
		
		Node(){
			startIndex = -1;
			endIndex = -1;
			setElementArray();
		}
		public String startName; //The subset will start from here 
		public String endName; //After the subset reaches the this String, the subset will stop printing.
		//The constructor will initialize startIndex and endIndex to -1. 
		//Since the lowest index value a csv file can be is 0 when it is inserted into an array (Indicating "Item 1" of the file, assuming we skipped the introductory line,)
		//"-1" will be the indication that the text in startName and/or endName is not found on the array. If it were found, then the startIndex and/or endIndex will reflect the position the string is on the array.
		
		private int startIndex; 
		private int endIndex; 
		private boolean existingValue = false; //Boolean meant to indicate that the text has been found on the array.
		private boolean firstValueBigger = false; /*This boolean will check if the startIndex is bigger than the endIndex. 
		*Since this list does not travel in descending order, we want to make sure the startIndex is not bigger.
		*If it is, then the boolean will change to true, preventing the for loop that prints the movie's name and release year from operating for that node.*/
		public static final String[] presentMovieNames = new String[movieNamesNonQuotes.length];
		Node left;
		//We want to make sure the object's array contains all the elements from the static Movie Names array that belongs to the Movie class. 
		public void setElementArray() {
			for(int i = 0; i < presentMovieNames.length;i++) {
				presentMovieNames[i] = movieNamesNonQuotes[i];
			}
		}
	}
	static class Tree{
		private Node root;
		public Tree(){
			root = null;
		}
		//As the left node and this method will indicate, the nodes of the tree will be one sided (Only pertaining to left nodes.)
		public void insert(String startName,String endName) {
			Node newNode = new Node();
			newNode.startName = startName;
			newNode.endName = endName;
			if(root == null) root = newNode;
			else {
				Node current = root;
				Node parent;
				while(true) {
				parent = current;
				current = current.left;
				//The left node will hold the node containing the parameter's values, assuming the node is null.
				//If the left node of the parent equals null, it will be assigned to that new node, then exit out of the while loop.
				if(current == null)
				{
					parent.left = newNode;
					return;
				}
			}
			}
		}
		
		  public void displayResults(Node node) {
			    if (node == null) {
			      return;
			    }
			    //if the node's startName string matches a string in the movieNames array, 
			    //then it's startIndex variable will take the position number of that string in the movieNamesNonQuotes array.
			   
			    for (int i = 0; i < movieNamesNonQuotes.length;i++) {
					if(movieNamesNonQuotes[i].toString().equals(node.startName))
						node.startIndex = i;
				}
			    //The same situation applies to the node's endIndex.
			    for (int j = 0; j < movieNamesNonQuotes.length;j++) {
					if(movieNamesNonQuotes[j].toString().equals(node.endName))
						node.endIndex = j;
				}
			    //If the startIndex and endIndex is not -1, that means the values are on the array. Therefore, the node's existingValue will be flagged as true.
			    if(node.startIndex > -1 && node.endIndex > -1) node.existingValue = true;
			    //node.existingValue is part of the if statement in the case that both startName and endName are part of the array but the startName appears after endName on the movieNames array.
			    if((node.startIndex > node.endIndex) && (node.existingValue)) node.firstValueBigger = true;
			    
			    //If the node's firstValueBigger is set to true, that means they will receive this message. This will be the only message that will be presented when the method is used. 
			    if(node.firstValueBigger) {
			    	System.out.println("The movie at the start of the subset is alphabetically further than the movie at the end of the subset.");    	
	    	 }
			    
			    if((node.existingValue) && node.firstValueBigger == false){
			    	//The array will travel from the startIndex to the endIndex to display the sublist of the node. This will include the startIndex and the endIndex.
			    	//Any movie before the startIndex and any movie beyond the endIndex will not be printed.
			    for (int f = node.startIndex; f <= node.endIndex; f++) {
			    	if(releaseYear[f] == null) {
			    		//For the movies that do not have a releaseYear, it will print the below line then continue through the loop.
					System.out.println("Movie: " +movieNamesNonQuotes[f].toString() + " ||" + " No year recorded for this film."); 
					continue;
					}
			    	//This prints the name of the movie as well as it's release year.
			    	System.out.println("Movie: " +movieNamesNonQuotes[f].toString() + " ||" + " Year of Release: " +releaseYear[f]);
				}
			    
			    }
			    //This if statement will only be active if b
			    if(node.startIndex == -1 || node.endIndex==-1)
			    	System.out.println("The first and/or last movie(s) of the subset is not on the list.");
			
			    displayResults(node.left);
			  }
	}
}
