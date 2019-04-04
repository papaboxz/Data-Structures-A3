/*
 * Class: 		Assignment 3
 * Purpose: 	To use an ArrayList to store LinkedLists that store email addresses.
 * 				This setup would be used to represent a CC chain of addresses to send 
 * 				mail to. 
 * Author:		Emmett Janssens
 * Methods: 
 * 				main - Presents the user with a menu of options to manipulate the directory of 
 * 					   linked lists. 
 */

import java.util.Scanner; 

public class Assign3 
{
	public static void main (String [] args)
	{
		Directory directory = new Directory (); 
		String input = ""; 
		String listName = ""; 
		Scanner reader = new Scanner (System.in);
		
		System.out.println("Assignment 3 Menu:");
		
		while (!input.equalsIgnoreCase("q"))
		{
			System.out.println("c to create a new list");
			System.out.println("p to display all lists");
			System.out.println("a to add an entry to a list");
			System.out.println("d to delete from a list");
			System.out.println("l to display a list");
			System.out.println("f to load lists from file");
			System.out.println("q to quit");
			System.out.print("Enter Command: ");
			input = reader.next(); 
			
			if (input.equalsIgnoreCase("c"))
			{
				directory.addList(reader);
			}
			else if (input.equalsIgnoreCase("p"))
			{
				System.out.println(directory);
			}
			else if (input.equalsIgnoreCase("a"))
			{
				System.out.print("Enter name of the list to add to: ");
				listName = reader.next();
				
				EmailList iter = new EmailList (listName); 
				
				directory.addEmail(reader, iter);
			}
			else if (input.equalsIgnoreCase("d"))
			{
				System.out.print("Enter name of the list to delete from: ");
				listName = reader.next(); 
				
				EmailList iter = new EmailList (listName); 
				
				directory.deleteEmail(reader, iter);
			}
			else if (input.equalsIgnoreCase("l"))
			{
				System.out.print("Enter name of the list to display: ");
				listName = reader.next(); 
				
				EmailList iter = new EmailList (listName); 
				
				directory.displayList(iter);
			}
			else if (input.equalsIgnoreCase("f"))
			{
				directory.validFile(reader);
			}
			else if (input.equalsIgnoreCase("q")) {}
			else
			{
				System.out.println("-- ERROR: Unrecognized Command --");
			}
		}
	}
}
