package utilities;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.util.Map.Entry;
import java.util.Set;
import java.text.SimpleDateFormat;

public class DemoQueries {

	public ResultSet rs;
	public String query;
	public SQLExec db = new SQLExec();
	public Hashtable<String,String> preDefQueries = new Hashtable<String,String>();
	public ArrayList<String> col_names;
	public int choice;
    public String start_time;
    public String end_time;

    public DemoQueries() throws Exception {
        StaticFunctions.Initialise();
    }

    public void demoQuery(String query) throws Exception {
    	db.connect();
    	try {
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
    	System.out.println("Enter a number between 1-6: (7 to Go Back)");
        choice = StaticFunctions.nextInt();
        StaticFunctions.nextLine();
        if (choice > 7 || choice < 0) {
            System.out.println("Invalid Choice!!!!!! Please Re-enter");
            preDefined();
            return;
        } 
        else if (choice == 7) {
            return;
        }
        else if (choice == 2) {
            Q2();
            preDefined();
            return;
        }
        else if (choice == 1) {
            query = "SELECT p.fname, p.lname, c.f_id, c.checkin_end_time, c.discharge_time, c.neg_exp from Patient p, Checks_In c WHERE p.p_id = c.p_id AND c.dis_status IS NOT NULL AND c.neg_code <> 0";
            demoQuery(query);
            preDefined();
            return;
        }
        else if (choice == 4) {
            query = "SELECT f_id FROM Checks_In WHERE neg_exp IS NULL AND v_id = (SELECT aff.v_id  FROM Affected_Info aff, Symptoms symp WHERE aff.s_code = symp.code AND symp.name = 'cardiac');";
            demoQuery(query);
            preDefined();
            return;
        }
        else if (choice == 5) {
            query = "SELECT MAX(M.count_val) AS Maximum FROM (SELECT COUNT(neg_code) AS count_val, f_id FROM Checks_In C WHERE C.neg_code<>0 GROUP BY f_id) AS M";
            demoQuery(query);
            preDefined();
            return; 
        }
        else {
            System.out.println("Wait Patiently");
            preDefined();
            return; 
        }
    }	

    public void Q2() throws Exception{
        System.out.print("Enter the Start Time: (yyyy-MM-dd-dd format)");
        start_time = StaticFunctions.nextLine();
        System.out.print("Enter the End Time: (yyyy-MM-dd-dd format)");
        String end_time = StaticFunctions.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
                dateFormat.parse(start_time.trim());
                 dateFormat.parse(end_time.trim());
        } catch (Exception pe) {
                System.out.print("Invalid Date. Enter again: ");
                Q2();
                return;
        }
        query = MessageFormat.format("SELECT DISTINCT f_id FROM Checks_In C WHERE C.discharge_time > ''{0}'' AND C.discharge_time < ''{1}'' AND C.neg_code=0", start_time, end_time);
        System.out.println(query);
        demoQuery(query);
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
            else {
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