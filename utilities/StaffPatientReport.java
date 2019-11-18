package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class StaffPatientReport{


    ReportDS r_obj = new ReportDS();

    public StaffPatientReport() {

        StaticFunctions.Initialise();
    }

    public void MainView(int vid) throws Exception{
    	String trmt_desc = "";
        int choice;
        SQLExec db = new SQLExec();
        db.connect();
        r_obj.vid = vid;

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
                    //System.out.print("Discharge Status page");

                    DischargeStatus spr = new DischargeStatus();
                    spr.MainView(r_obj);
                    //System.out.println("Test:\t" + r_obj.Q_discharge);
                    break;
                case 2:
                   // System.out.print("Referral Status page");
                    ReferralStatus rst = new ReferralStatus();
                    rst.MainView(r_obj);
                    //System.out.println("Test:\t" + r_obj.Q_Ref_to);
                    break;
                case 3:

                    System.out.println("Enter treatment description:");
                    trmt_desc = StaticFunctions.nextLine();
                    String query = "Update Checks_In set trmt_description = '" + trmt_desc + "' where v_id = " + vid;
			        // try{
			        //         db.execCommand(query);
			        // }
			        
			        // catch (Exception e) {

			        //         System.out.println("Could not update entry: "+e);
			        // }

                    r_obj.trmt_description = trmt_desc;
                    r_obj.Q_trmt = query;

                    break;
                    
                case 4:
                   // System.out.print("Negative Experience page");

                    NegativeExperience ne = new NegativeExperience();
                    ne.MainView(r_obj);
                    break;
                case 5:
                    return;
                case 6:

                    System.out.print("\033[H\033[2J");  
                    System.out.flush();


                    System.out.println("-----------------------------------Report--------------------------------");
                    System.out.println("Discharge Status:\t" + r_obj.discharge_status);
                    System.out.println("Referral Status:\t");
                    System.out.println("Treatment given:\t" + r_obj.trmt_description);
                    System.out.println("Negative Experience Code\t:" + r_obj.n_code);
                    System.out.println("Negative Experience description\t:" + r_obj.n_description);
                    System.out.println("-------------------------------------------------------------------------");


                    System.out.println("Confirm?");

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
        // StaffPatientReport spr = new StaffPatientReport();
        // spr.MainView(1);
    }
}
