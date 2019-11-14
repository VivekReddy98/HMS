package utilities;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.MessageFormat;
import utilities.*;

/// Usage: Just initialize this Object and call MainView() Method

public class AddRule {

    public ArrayList<RuleDS> Rules;

    public void createRule(){

    }

	public void MainView() throws Exception {
		int choice;
		boolean dont_terminate = true;
		Rules = new ArrayList<RuleDS>();
		System.out.println("\n\t\t Menu to add a New Assessment Rules ");
		System.out.println("1. Add a New Rule");
		System.out.println("2. Go Back");

		while (dont_terminate) {	
			System.out.print("Enter Choice (1-2): ");
			choice = StaticFunctions.nextInt();
			StaticFunctions.nextLine();

			if (choice == 1) {
				createRule();
		    }
	        else if (choice == 2) {
	        	dont_terminate = false;
	        }
	        else{
	        	System.out.println("Invalid Choice, Plese Re-enter");
	        }

	    };
	}

 	public static void main(String[] args) throws Exception {

    }
}

// Class Representing a tuple in Rule Class
public class RuleDS {
	
	public ArrayList<String> s_code;
	public ArrayList<String> b_code;
	public ArrayList<String> comparison; 
	public ArrayList<String> severity_val; 

	s_code = new ArrayList<String>();
	b_code = new ArrayList<String>();
	comparison = new ArrayList<String>();
    severity_val = new ArrayList<String>();

}