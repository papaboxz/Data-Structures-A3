/*
 * Class: 				EmailList
 * Purpose: 			To represent a collection of Email Addresses
 * Author: 				Emmett Janssens
 * Data Members:
 * 						name 	  - String to hold the name of the EmailList
 * 						emailList - The Linked List to hold the Email Address objects
 * Methods: 
 * 						constructor 		  (Scanner) 		- Creates a new EmailList and adds EmailAddresses to it using a Scanner 
 * 													 		      from directory passed to it from main.
 * 
 * 						constructor           (String)			- Creates a new empty EmailList with only a name passed in from a String
 * 
 * 						addEmail              (Scanner, String) - Takes in a Scanner and String object to pass to the addEmail method of the EmalAddress
 * 																  class, initializing a new EmailAddress and adds it to the EmailList.
 * 
 * 						deleteEmail			  (Scanner)			- Displays the Email Addresses in the EmailList and prompts the user for an index
 * 																  to delete from and deletes at the index if the index was valid. 
 * 
 * 						compareLists		  (EmailList)		- Compares the value of the names of this EmailList object and another, returning
 * 																  the integer result. 
 * 
 * 						toString			  ()				- Returns a String representation of this object.
 *										  
 */

import java.util.LinkedList;
import java.util.Scanner; 

public class EmailList 
{
	private String name; 
	private LinkedList <EmailAddress> emailList;
	
	public EmailList (Scanner reader)
	{
		emailList = new LinkedList <EmailAddress> ();
		
		String prompt = "y"; 
		 
		System.out.print("Enter name of the list: ");
		name = reader.next(); 
		
		EmailAddress newAddress = new EmailAddress (); 
		
		while (!prompt.equals("n"))
		{
			newAddress.addAddress(reader, "y");
			emailList.addLast(newAddress); 
			System.out.print("Would you like to add another email? (y/n): ");
			prompt = reader.next(); 
		}
	}
	
	public EmailList (String name)
	{
		emailList = new LinkedList <EmailAddress> (); 
		
		this.name = name; 
	}
	
	public void addEmail (Scanner input, String prompt)
	{
		EmailAddress newAddress = new EmailAddress(); 
		newAddress.addAddress(input, prompt);
		emailList.addLast(newAddress);
	}
	
	public void deleteEmail (Scanner reader)
	{
		int index = -1; 
		
		System.out.println(name);
		for (int i = 0; i < emailList.size(); i++)
		{
			System.out.println(i + " " + emailList.get(i));
		}
		
		System.out.print("Enter index to delete from: ");
		while (index < 0 || index >= emailList.size())
		{
			while (!reader.hasNextInt())
			{
				System.out.print("-- ERROR: You need to enter a valid integer index --: ");
				reader.next();
			}
			
			index = reader.nextInt();
			
			if (index < 0 || index >= emailList.size())
			{
				System.out.print("--ERROR: You need to enter a valid index --: ");
			}
		}
		
		if (index == 0)
		{
			emailList.removeFirst();
		}
		else if (index == emailList.size() - 1)
		{
			emailList.removeLast(); 
		}
		else
		{
			emailList.remove(index);
		}
	}
	
	public int compareLists (EmailList rightEmailList)
	{
		return this.name.compareTo(rightEmailList.name);
	}
	
	public String toString ()
	{
		String string = ""; 
		
		if (!emailList.isEmpty())
		{
			string += "\n" + name + ": ["; 
			
			for (int i = 0; i < emailList.size(); i++)
			{
				string += emailList.get(i);
				if (i < emailList.size() - 1)
				{
					string += ", ";
				}
			}
			
			string += "] \n\n";
		}

		return string; 
	}
}
