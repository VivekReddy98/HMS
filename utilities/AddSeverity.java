package utilities;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.MessageFormat;
import utilities.*;

/// Usage: Just initialize this Object and call MainView() Method

public class AddSeverity {
    
    public ArrayList<String> SeveLevels;
    
    public AddSeverity() {
        StaticFunctions.Initialise();
    }

    public void AddSev() {
    	System.out.println("Enter a discrete level for the scale you are entering!!");
	 	SeveLevels.add(StaticFunctions.nextLine());
	}

	public boolean EndSev() {
		System.out.println("Thanks for your inputs");
		return false;
	}

	public void InsertData() throws Exception{
		String output = "";
		int id = -1;
		for (String i : SeveLevels)
		{
			if(output.equals(""))
				output = i;
			else
				output = output + ',' + i;
		}
		//System.out.println(output);
		SQLExec db = new SQLExec();
		db.connect();
		ResultSet rs = db.execQuery("SELECT MAX(s_id) FROM Severity");
		if (rs.next())
		{
			id = rs.getInt("MAX(s_id)");
		}
		//System.out.println(id);
		String query = MessageFormat.format("INSERT INTO Severity VALUES ({0}, ''{1}'')", id+1, output);
		//System.out.println(query);
		db.execCommand(query);
		db.terminate();
	}

	public void MainView() throws Exception {
		int choice;
		boolean dont_terminate = true;
		SeveLevels = new ArrayList<String>();

		System.out.println("\n\t\t Menu to add a New Severity Scale");
		System.out.println("1. Add a Level for this scale");
		System.out.println("2. There are no more levels for this scale");
		System.out.println("3. Go Back");

		while (dont_terminate) {
			
			System.out.print("Enter Choice (1-3): ");
			choice = StaticFunctions.nextInt();
			StaticFunctions.nextLine();

		        if (choice == 1) {
		            AddSev();
		        }
		        else if (choice == 2) {
		        	InsertData();
		        	dont_terminate = EndSev();
		        }
		        else if (choice == 3){
		        	dont_terminate = false;
		        }
		        else{
		        	System.out.println("Invalid Choice, Plese Re-enter");
		        }
	    
	        };
	    System.out.println("Exiting back!!");
	}


 public static void main(String[] args) throws Exception
    {
        AddSeverity ob = new AddSeverity();
        ob.MainView();
    }
}

