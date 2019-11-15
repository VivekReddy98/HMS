package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class ReferralStatus{

    public ReferralStatus() {

        StaticFunctions.Initialise();
    }

    public void MainView(int vid) throws Exception{
        String query = "";
        int choice;
        SQLExec db = new SQLExec();
        db.connect();

        do{

            System.out.println("\n\t\tStaff Patient Report");
            System.out.println("Selected Patient: "+vid);
            System.out.println("\n1. Facility id");
            System.out.println("2. Referrer id");
            System.out.println("3. Add reason");
            System.out.println("4. Go Back");

            System.out.print("Enter Choice (1-4): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            switch(choice) {
                case 1:
                    int f_id;
                    System.out.println("Enter Facility ID: ");
                    f_id = StaticFunctions.nextInt();
                    StaticFunctions.nextLine();
                    query = "INSERT INTO Referred_To (f_id, v_id) VALUES ("+f_id+"," + vid+")";
                    try{
                        db.execCommand(query);
                    }catch (Exception e) {
                        query = "UPDATE Referred_to SET f_id = "+f_id+" WHERE v_id =" + vid+"";
                        try {
                            db.execCommand(query);
                        }catch(Exception f){
                            System.out.println(f);
                        }
                    }
                    System.out.println("Updated Referred_to Successfully");
                    break;
                case 2:
                    String r_id;
                    System.out.println("Enter Referrer ID: ");
                    r_id = StaticFunctions.nextLine();
                    query = "INSERT INTO Referred_to (e_id, v_id) VALUES ('"+r_id+"'," + vid+")";
                    try{
                        db.execCommand(query);
                    }catch (Exception e) {
                        query = "UPDATE Referred_to SET e_id = '"+r_id+"' WHERE v_id =" + vid+"";
                        try {
                            db.execCommand(query);
                        }catch(Exception f){
                            System.out.println(f);
                        }
                    }
                    System.out.println("Updated Referred_By Successfully");
                    break;
                case 3:
                    System.out.println("Remaining - Goto referral reasons page");
                    return;

                case 4:
                    System.out.print("BYE");
                    return;

                default:
                    System.out.print("Invalid Choice, Please try again");
                    break;
            };
        }while(choice != 4);

        return;
    }

    public static void main(String[] args) throws Exception
    {
        ReferralStatus spr = new ReferralStatus();
        spr.MainView(1);
    }
}
