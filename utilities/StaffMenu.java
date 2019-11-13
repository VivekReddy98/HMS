// Needs Visit_ID to update the end time
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffMenu{
    public StaffMenu() {

        StaticFunctions.Initialise();
    }
    public void showStaffMenu() throws Exception{
        int choice;
        do{
            System.out.println("\n");
            System.out.println("\n\t\tStaff Menu");
            System.out.println("1. Checked-in patients' list");
            System.out.println("2. Treated patients' list");
            System.out.println("3. Add symptoms");
            System.out.println("4. Add severity scale");
            System.out.println("5. Add assessment rule");
            System.out.println("6. Go Back");

            System.out.print("Enter Choice (1-6): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Checked-in patients' list");
                    System.out.print("Staff process patient page");
                    break;
                case 2:
                    System.out.print("Treated patients' list");
                    System.out.print("Staff checkout patient page");
                    break;
                case 3:
                    System.out.print("Add symptoms page");
                    break;
                case 4:
                    System.out.print("Add severity scale");
                    break;
                case 5:
                    System.out.print("Add assessment rule");
                    break;
                case 6:
                    System.out.print("Function for choice 6");
                    break;
                default:
                    System.out.print("Invalid");
                    break;
            };
        }while(choice != 6);
        return;
    }

    public static void main(String[] args) throws Exception
    {
        StaffMenu ob = new StaffMenu();
        ob.showStaffMenu();
    }
}