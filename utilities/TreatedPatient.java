package utilities;
import java.sql.*;
import java.util.*;
import java.io.*;
import utilities.*;

public class TreatedPatient{

	public TreatedPatient() {

    }

    public void MainView(String eid) throws Exception{
    	int choice;
        ArrayList<Integer> vid_list = new ArrayList<Integer>();
        ArrayList<String> pat_list = new ArrayList<String>();
        ResultSet rs1 = null;
        int i = 0;
        int vid = 1;
        int fid = 0;
    	String query1 = "Select f_id from Facility_has_Dept where code = (Select primary_dept from Staff where designation = 'M' and e_id = '"
    					 + eid +"')";

    	SQLExec db = new SQLExec();
    	db.connect();
    	try{
            rs1 = db.execQuery(query1);
        }
        catch (Exception e) {
            System.out.println("Unable to fetch records: " + e);
        }
    	while(rs1.next()) {
            	fid = rs1.getInt("f_id");
            }

      //--------------Display Patient List----------------

        String query2 = "Select c.v_id, p.fname, p.lname from Checks_In c, Patient p where c.treatment = 'True' and c.p_id = p.p_id and c.f_id = " + fid;
        ResultSet rs2 = null;
        try{
            rs2 = db.execQuery(query2);
        }
        catch (Exception e) {
            System.out.println("Unable to fetch records:" + e);
        }
        
        System.out.println("\t\tList of treated patients in your facility: ");

        while(rs2.next()) {
            i++;
            System.out.println(Integer.toString(i) + ". " + rs2.getString("fname") + " " + rs2.getString("lname"));
            vid_list.add(rs2.getInt("v_id"));
            pat_list.add(rs2.getString("fname") + " " + rs2.getString("lname"));
        }

        System.out.println("Enter the number corresponding to the patient for check out:");

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
    	System.out.println("1. Check out patient: " + pat_list.get(choice - 1));
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
    			return;
    		}

    	}while(choice != 1 && choice != 2);

    	db.terminate();
    	
    }


    public static void main(String[] args) throws Exception
    {

    }

}