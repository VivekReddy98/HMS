package utilities;
import java.sql.*;
import java.util.*;
import java.io.*;
import utilities.*;

public class TreatedPatient{

	public TreatedPatient() {

        StaticFunctions.Initialise();
    }

    public void MainView(String eid) throws Exception{

    	int choice;
        ArrayList<Integer> vid_list = new ArrayList<Integer>();
        int i = 0;
        int vid = 1;
        int fid = 0;
    	String query1 = "Select f_id from Facility_has_Dept where code = (Select primary_dept from Staff where designation = 'M' and e_id = '"
    					 + eid +"')";
    	//System.out.println(query1);
    	

    	SQLExec db = new SQLExec();
    	db.connect();
        ResultSet rs1 = db.execQuery(query1);
        try {
            while(rs1.next()) {
            	fid = rs1.getInt("f_id");
            }
        }
        catch (Exception e) {
            System.out.println("Unable to fetch records:" + e);
        }


        //--------------Display Patient List----------------

        String query2 = "Select v_id from Checks_In where treatment = 'True' and f_id = " + fid;
        ResultSet rs2 = db.execQuery(query2);
       // System.out.println(query2);
        System.out.println("\t\tList of treated patients:");

        try {
            while(rs2.next()) {
            	++i;
            	System.out.println(Integer.toString(i) + ". " + rs2.getString("v_id"));
                vid_list.add(rs2.getInt("v_id"));
            }
        }

        catch (Exception e) {
            System.out.println("Unable to fetch records:" + e);
        }

        System.out.println("Enter the number corresponding to the patient for check out:");
        System.out.println("i:" + i);
        do{
        	choice = StaticFunctions.nextInt();
        	StaticFunctions.nextLine();
        	if(choice < 1 || choice > i) {
        		System.out.println("Invalid Choice");
    		}
    		else{
    			vid = vid_list.get(choice - 1);
    		}
    	}while(choice < 1 || choice > i);




    	//-----------------Display Menu-------------------------

    	System.out.println("\t\tMenu");
    	System.out.println("1. Check out");
    	System.out.println("2. Go Back");

    	do{
        	choice = StaticFunctions.nextInt();
        	StaticFunctions.nextLine();
        	if(choice != 1 && choice != 2) {
        		System.out.println("Invalid Choice");
    		}

    		else if (choice == 1){
    			//Call checkout(vid);
    			System.out.println("Call checkout(" + vid + "),  parameter: vid");
    		}

    		else{
    			//return;
    			System.out.println("Back to Staff menu.");
    			return;
    		}

    	}while(choice != 1 && choice != 2);
    	
    }


    public static void main(String[] args) throws Exception
    {
        TreatedPatient tp = new TreatedPatient();
        tp.MainView("89001");
    }

}