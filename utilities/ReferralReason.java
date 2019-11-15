package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class ReferralReason{

    public ReferralReason() {

        StaticFunctions.Initialise();
    }

    public void MainView(int vid) throws Exception{
        String query = "";
        int choice;
        SQLExec db = new SQLExec();
        db.connect();

        do{

            System.out.println("\n\t\tReferral Reason");
            System.out.println("Selected Patient: "+vid);
            System.out.println("\n1. Reason");
            System.out.println("2. Go back");

            System.out.print("Enter Choice (1-4): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            switch(choice) {
                case 1:

                    return;
                case 2:
                    return;
                default:
                    System.out.print("Invalid Choice, Please try again");
                    break;
            };
        }while(choice != 2);

        return;
    }

    public static void main(String[] args) throws Exception
    {
        ReferralReason spr = new ReferralReason();
        spr.MainView(1);
    }
}
