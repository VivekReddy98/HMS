package utilities;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.util.Map.Entry;
import java.util.Set;

public class DemoQueries {

	public ResultSet rs;
	public String query;
	public SQLExec db = new SQLExec();
	public Hashtable<String,String> preDefQueries = new Hashtable<String,String>();
	public ArrayList<String> col_names;
	public int choice;

    public DemoQueries() throws Exception {
        StaticFunctions.Initialise();
    }

    public void demoQuery(String query) throws Exception {
    	db.connect();
    	try{
    		rs = db.execQuery(query);
    		ResultSetMetaData rsmd = rs.getMetaData();
    		int columnsNumber = rsmd.getColumnCount();
    		while (rs.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
			        String columnValue = rs.getString(i);
			        System.out.print(columnValue + " " + rsmd.getColumnName(i));
			    }
    		System.out.println("");
    	    }
    	} catch (Exception e) {
    		System.out.println("Not able to fire the Query" + e);
    	}
    	db.terminate();
    	return;
    }

    public void demoCommand(String query) throws Exception {
    	db.connect();
    	try{
    		db.execCommand(query);
    	} catch (Exception e) {
    		System.out.println("Not able to fire the Query" + e);
    	}
    	db.terminate();
    	return;
    }

    public void preDefined() throws Exception {
    	System.out.println("Build in Progress, Wait Patiently !!!!!!!")
    	return;
    }	

    public void MainView() throws Exception{
    	while (true)
    	{
    		System.out.println("\n\t\t Demo Queries Menu");
            System.out.println("1. Try a New Query");
            System.out.println("2. Try a New Command (Which does changes inside your DB but doesnt return anything)");
            System.out.println("3. Want to see pre-defined Queries");
            System.out.println("4. Go Back");
            System.out.print("Enter Choice (1-4): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if (choice==1) {
            	System.out.println("Enter Your Query here : ");
    			query = StaticFunctions.nextLine();
    			//System.out.println(query);
            	demoQuery(query);
            }
            else if (choice==2)
            {   
            	System.out.println("Enter Your Command String : ");
    			query = StaticFunctions.nextLine();
    			//System.out.println(query);
            	demoCommand(query);
            }
            else if (choice==3) {
            	preDefined();
            }
            else if (choice==4) {
            	StaticFunctions.closeScanner();
            	break;
            }
            else{
            	System.out.println("Invalid Entry!!!!!! Try Again");
            	continue;
            }
    	}
    }

    public static void main(String[] args) throws Exception
    {
    	DemoQueries dq = new DemoQueries();
    	dq.MainView();  
    }
}