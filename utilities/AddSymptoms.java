package utilities;
import java.sql.*;
import java.util.*;
import java.io.*;
import utilities.*;

public class AddSymptoms{

	public AddSymptoms() {

        StaticFunctions.Initialise();
    }


    public String bodyPartMenu() throws Exception{

    	String bPartCode = "";
    	int choice;
        ArrayList<String> bparts = new ArrayList<String>();
        int i = 0;
    	String query = "Select * from Body_Parts";

    	SQLExec db = new SQLExec();
    	db.connect();
        ResultSet rs = db.execQuery(query);

        try {
            while(rs.next()) {
            	++i;
            	System.out.println(Integer.toString(i) + ". " + rs.getString("name"));
                bparts.add(rs.getString("code"));
            }
        }

        catch (Exception e) {
            System.out.println("Unable to fetch records");
        }


        do{
        	choice = StaticFunctions.nextInt();
        	StaticFunctions.nextLine();
        	if(choice < 1 || choice > i) {
        		System.out.println("Invalid Choice");
    		}
    		else{
    			bPartCode = bparts.get(choice - 1);
    		}

    	}while(choice < 1 || choice > i);

    	return bPartCode;
    }


    public int severityMenu() throws Exception{

    	int sevType = 1;
    	int choice;
        ArrayList<Integer> scales = new ArrayList<Integer>();
        int i = 0;
    	String query = "Select * from Severity";

    	SQLExec db = new SQLExec();
    	db.connect();
        ResultSet rs = db.execQuery(query);

        try {
            while(rs.next()) {
            	++i;
            	System.out.println(Integer.toString(i) + ". " + rs.getString("type"));
                scales.add(rs.getInt("s_id"));
            }
        }

        catch (Exception e) {
            System.out.println("Unable to fetch records");
        }

        do{
        	choice = StaticFunctions.nextInt();
        	StaticFunctions.nextLine();
        	if(choice < 1 || choice > i) {
        		System.out.println("Invalid Choice");
    		}
    		else{
    			sevType = scales.get(choice - 1);
    		}
    		System.out.println(choice);
    	}while(choice < 1 || choice > i);

    	return sevType;
    }



    public void insertIntoSymptoms(String symCode, String symName, String bPartCode, int sevType) throws Exception{

    	String query = "Insert into Symptoms (code, b_code, name, severity_type) values ('" + symCode + "','" + bPartCode + "','" + symName + "', " + sevType + ")";
    	//String query = "Insert into Symptoms (code, b_code, name, severity_type) values ('SYM999','" + bPartCode + "','" + symName + "', " + sevType + ")";
        System.out.println(""+query);

  /**      SQLExec db = new SQLExec();
        db.connect();

        try{
                db.execCommand(query);
        }
        
        catch (Exception e) {

                System.out.println("Could not insert data into the DB: "+e);
        }**/

    }


    public void MainView() throws Exception{
    	String symName = "";
    	String bPartCode = "";
    	int sevType = 0;
    	int choice = 0;
    	String symCode = "";

    	do{

    		System.out.println("Enter the new symptom name:");
    		symName = StaticFunctions.nextLine();

    		if (symName.length() == 0){
    			System.out.println("Please actually enter something!");
    		}

    	}while(symName.length() == 0);

    	System.out.println("Enter the body part associated with this symptom:\n(Choose \"Nothing Specific\" if no body part is associated)");
    	bPartCode = bodyPartMenu();

    	System.out.println("Enter the severity scale associated with this symptom:");
    	sevType = severityMenu();

    	//Clear the screen
    	System.out.print("\033[H\033[2J");  
   		System.out.flush();

    	//Show Menu
    	do{
    		System.out.println("\t\tMenu");
    		System.out.println("1. Record new symptom into database");
    		System.out.println("2. Go Back ( Discard changes )");
    		choice = StaticFunctions.nextInt();
    		StaticFunctions.nextLine();
    		if(choice != 1 && choice != 2){
    			System.out.println("Invalid Choice, Please Try again.");
    		}
    	}while(choice != 1 && choice != 2);


    	String query = "Select * from Symptoms order by code asc";

    	SQLExec db = new SQLExec();
    	db.connect();
        ResultSet rs = db.execQuery(query);

        try {

            while(rs.next()) {

  				symCode = rs.getString("code");
  				
			}
        }

        catch (Exception e) {
            System.out.println("Unable to fetch max symptom: " + e);
        }

    	if(choice == 1){
    		int temp = Integer.parseInt(symCode.substring(3, 6)) + 1;
    		symCode = String.format("%s%03d", symCode.substring(0, 3),temp);
    		insertIntoSymptoms(symCode, symName, bPartCode, sevType);
    	}

    	db.terminate();

    	return;
    }


    public static void main(String[] args) throws Exception
    {
        AddSymptoms as = new AddSymptoms();
        as.MainView();
        System.out.println("Back to Staff menu");
    }
}