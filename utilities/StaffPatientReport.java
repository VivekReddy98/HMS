package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class StaffPatientReport{

    public StaffPatientReport() {

        StaticFunctions.Initialise();
    }

    public void MainView(int vid) throws Exception{
    	String trmt_desc = "";
        int choice;
        SQLExec db = new SQLExec();
        db.connect();

        do{
            System.out.println("\n");
            System.out.println("\n\t\tStaff Patient Report");
            System.out.println("1. Discharge Status");
            System.out.println("2. Referral Status");
            System.out.println("3. Treatment");
            System.out.println("4. Negative Experience");
            System.out.println("5. Go Back");
            System.out.println("6. Submit");

            System.out.print("Enter Choice (1-6): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Discharge Status page");
                    break;
                case 2:
                    System.out.print("Referral Status page");
                    break;
                case 3:

                    System.out.print("Enter treatment description:");
                    trmt_desc = StaticFunctions.nextLine();
                    String query = "Update Checks_In set trmt_description = '" + trmt_desc + "' where v_id = " + vid;
			        try{
			                db.execCommand(query);
			        }
			        
			        catch (Exception e) {

			                System.out.println("Could not update entry: "+e);
			        }

                    break;
                    
                case 4:
                    System.out.print("Negative Experience page");
                    break;
                case 5:
                    return;
                case 6:
                    System.out.print("Submit, Staff Patient report confirmation page.");
                    break;
                default:
                    System.out.print("Invalid Choice, Please try again");
                    break;
            };
        }while(choice != 5);

        return;
    }

    public static void main(String[] args) throws Exception
    {
        StaffPatientReport spr = new StaffPatientReport();
        spr.MainView(1);
    }
}
