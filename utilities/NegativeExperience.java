package utilities;

import java.sql.*;
import java.util.*;
import java.io.*;
import utilities.*;

public class NegativeExperience{


    public NegativeExperience() {

        StaticFunctions.Initialise();
    }

    public void MainView(ReportDS r_obj) throws Exception{
        String query = "";
        int choice;
        // SQLExec db = new SQLExec();
        // db.connect();

        do{
            System.out.println("\n\t\tAdd Negative Experience");
            System.out.println("1. Negative Experience Code");
            System.out.println("2. Go Back");

            System.out.print("Enter Choice (1/2): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            switch(choice) {

                case 1:

                	int choice1 = 3; 
                	String desc = "";
                	

            		do{

            			System.out.println("Select the Code(1/2) corresponding to the negative experience:");
	                	System.out.println("1. Misdiagnosis");
	            		System.out.println("2. Patient acquired an infection during hospital stay.");
	            		choice1 = StaticFunctions.nextInt();
	            		StaticFunctions.nextLine();

            		}while(choice1 != 1 && choice1 != 2);

            		System.out.println("Enter a description for this negative experience:");
            		desc = StaticFunctions.nextLine();

                    query = "Update Checks_In set neg_code = " + choice1 + " , neg_exp = '" + desc + "' where v_id = " + r_obj.vid;

                    r_obj.n_code = choice1;
                    r_obj.n_description = desc;
                    r_obj.Q_negex = query;


                    // try{
                    //     db.execCommand(query);
                    // }catch (Exception e) {
                    //     System.out.println("Could not update entry: "+e);
                    // }
                    // System.out.println(query);

                    // db.terminate();

                    break;

                case 2:

                    return;

                default:
                    System.out.print("Invalid Choice, Please try again");
                    break;
            };

        }while(choice != 2);

        return ;
    }

    public static void main(String[] args) throws Exception
    {
        NegativeExperience ne = new NegativeExperience();
        ReportDS r_obj = new ReportDS();
        ne.MainView(r_obj);
        System.out.println(r_obj.n_description);
    }
}