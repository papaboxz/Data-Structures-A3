/*
 * Class: 			Directory
 * Purpose:			Represents a directory of linked lists that hold Email Addresses.
 * 					Also facilitates the operations required to manipulate the directory.
 * Author: 			Emmett Janssens
 * Data Members: 	
 * 					directory - ArrayList to hold the LinkedList objects
 * Methods: 
 * 					constructor
 * 
 * 					addList             (Scanner) 	  		  - Creates a new EmailList object using the Scanner passed in from the main method and
 * 															  	adds it to the directory in sorted order. 
 * 
 * 					validFile			(Scanner)             - Takes in a Scanner object that is a file and checks to see if the file exists and if it
 * 																does then calls readFromFile. 
 * 
 * 					readFromFile	    (Scanner) 	  		  - Uses the Scanner passed in from main to read in the name of a file to process. Then 
 * 														      	the method processes the file reading in and calling functions to create Email Lists
 * 														      	and Email Addresses adding those to the directory and related Email List respectively. 
 * 														      	Also performs all error checking on the format of the data in the file. 
 * 
 * 					addEmail			(Scanner, EmailList)  - Searches for the entered EmailList in the directory and if it is found calls the method
 * 																in EmailList to add a new Email Address to it, passing the Scanner from main to it so the
 * 																method can read in a new Email Address.
 * 
 * 					deleteEmail			(Scanner, EmailList)  - Searches for the entered EmailList in the directory and if it is found calls the method
 * 																in EmailList to remove an Email Address from, passing the Scanner from main to it so the
 * 																method can get an index from the user. 
 * 
 * 					displayList			(EmailList)			  - Searches for the entered EmailList in the directory and if it is found prints the EmailList
 * 
 * 					toString			()					  - Creates a String representation of this object.
 * 				
 * 					binSearch			(EmailList)			  - Returns the index of the entered EmailList in the directory in the most efficient way possible.													
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; 

public class Directory 
{
	private ArrayList <EmailList> directory; 
	
	public Directory ()
	{
		directory = new ArrayList <EmailList> (); 
	}
	
	public void addList (Scanner reader)
	{	
		EmailList newList = new EmailList (reader);
		directory.add(binSearch(newList), newList);
	}
	
	public void validFile (Scanner reader)
	{
		Scanner inFile = null; 
		String fileName = ""; 
		
		System.out.print("Enter the name of the file you want to read from: ");
		fileName = reader.next(); 
		
		File file = new File(fileName);
		try 
		{
			if (file.exists()) 
			{
				inFile = new Scanner(file);
			}
			else
			{
				System.out.println("-- ERROR: File Does Not Exist --");
				return;
			}
		} 
		catch (IOException e) 
		{
			System.out.println("-- ERROR: Invalid File Format --");
			return; 
		}
		
		readFromFile(inFile); 
	}
	
	public void readFromFile (Scanner inFile)
	{	 
		int numLists = 0; 
		String listName = ""; 
		EmailList newList; 
		
		int numEmails = 0; 
		
		while (inFile.hasNext())
		{
			if (!inFile.hasNextInt())
			{
				System.out.println("-- ERROR: Expecting an Integer Value for the Number of Email Lists to Process --");
				return; 
			}
			else
			{
				numLists = inFile.nextInt(); 
				
				for (int i = 0; i < numLists; i++)
				{	
					listName = inFile.next(); 
					newList = new EmailList (listName);
					directory.add(binSearch(newList), newList);
					
					if (!inFile.hasNextInt())
					{
						System.out.println("-- ERROR: Expecting an Integer Value for the Number of Emails to Read --");
						return; 
					}
					else
					{
						numEmails = inFile.nextInt(); 
						
						for (int j = 0; j < numEmails; j++)
						{
							if (!inFile.hasNext())
							{
								System.out.println("-- ERROR: Less Email Addresses than Expected");
								return; 
							}
							
							directory.get(binSearch(newList) - 1).addEmail(inFile, "n");
						}
					}
					
					if (i + 1 == numLists && inFile.hasNext())
					{
						return; 
					}
					
					if (!inFile.hasNext() && i + 1 < numLists)
					{
						System.out.println("-- ERROR: There are less email lists than expected --");
						return; 
					}
				}
			}
		}
		
		inFile.close();
	}
	
	public void addEmail (Scanner reader, EmailList listName)
	{	
			if (directory.isEmpty())
			{
				System.out.println("-- ERROR: The Directory is Empty --");
				return; 
			}
			
			if (listName.compareLists(directory.get(binSearch(listName) - 1)) == 0)
			{
				directory.get(binSearch(listName) - 1).addEmail(reader, "y");
			}
			else
			{
				System.out.println("-- ERROR: The Requested Email List Does Not Exist in this Directory --");
			}
	}
	
	public void deleteEmail (Scanner reader, EmailList listName)
	{
		if (directory.isEmpty())
		{
			System.out.println("-- The Directory is Empty --");
			return; 
		}
		
		if (listName.compareLists(directory.get(binSearch(listName) - 1)) == 0)
		{
			directory.get(binSearch(listName) - 1).deleteEmail(reader);
		}
		else
		{
			System.out.println("-- ERROR: The Requested Email List Does Not Exist in this Directory --");
		}
	}
	
	public void displayList (EmailList displayList)
	{
		if (directory.isEmpty())
		{
			System.out.println("-- ERROR: The Directory is Empty --");
			return; 
		}
		
		if (displayList.compareLists(directory.get(binSearch(displayList) - 1)) == 0)
		{
			System.out.print(directory.get(binSearch(displayList) - 1));
		}
		else
		{
			System.out.println("-- ERROR: The Requested Email List Does Not Exist in this Directory --");
		}
	}
	
	public String toString ()
	{
		String string = ""; 
		
		string += "\nThe email lists are: \n";
		
		for (int i = 0; i < directory.size(); i++)
		{
			string += directory.get(i);
		}
		
		return string;
	}
	
	public int binSearch (EmailList listName)
	{
		int left = 0; 
		int right = directory.size() - 1; 
		int middle = (left + right)/2;
		
		while (left <= right)
		{
			middle = (left + right)/2;
			
			if (directory.get(middle).compareLists(listName) <= 0)
			{
				left = middle + 1; 
			}
			else
			{
				right = middle - 1; 
			}
		}
		
		return left;  
	}
}	

