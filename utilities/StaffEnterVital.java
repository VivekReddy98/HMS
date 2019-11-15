// Needs Visit_ID to update the end time
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StaffEnterVital{
    int v_id;
    private int temperature;
    private int bp_systolic;
    private int bp_diastolic;
    public StaffEnterVital() {

    }

    public void enterDetails(){
        System.out.println("\nEnter Following Details for Patient with ID: "+v_id);
        System.out.print("A. Temperature: ");
        temperature = StaticFunctions.nextInt();
        System.out.print("B. Systolic blood pressure: ");
        bp_systolic = StaticFunctions.nextInt();
        System.out.print("C. Diastolic blood pressure: ");
        bp_diastolic = StaticFunctions.nextInt();
    }

    public void showMenu()throws Exception{
        do{
            System.out.println("\n\t\tActions");
            System.out.println("1. Record");
            System.out.println("2. Go Back");
            System.out.print("\nEnter Choice: ");
            int choice = StaticFunctions.nextInt();
            if (choice == 1){
                assessment();
                System.out.println("\nSuccessfully Recorded! Redirecting to Staff Menu.");
                TimeUnit.SECONDS.sleep(2);
                return;
            }
            if (choice == 2){
                return;
            }
            if (choice != 1 && choice != 2){
                System.out.println("Invalid Choice");
            }
        }while(true);
    }
    public void mainView() throws Exception{
        enterDetails();
        showMenu();

    }

    public void assessment(){
        SQLExec db = new SQLExec();
        db.connect();
        String priority;
        ///////////////////////////////////////////
        //ENTER ASSESSMENT LOGIC and put Priority in priority variable
        System.out.println("Remaining - Assessment Func");
        priority = "High";
        ///////////////////////////////////////////

        System.out.println("Priority: "+priority);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String current_time;
        current_time = formatter.format(date);
        current_time = "TO_DATE('"+current_time+"', 'mm/dd/yyyy hh24:mi:ss')";

        String update_Checks_In = "UPDATE Checks_In SET checkin_end_time = " +current_time+", temp = "+ temperature+", bp_systolic = "+bp_systolic+", bp_diastolic = "+bp_diastolic+", priority = '"+priority+"' WHERE v_id ="+v_id+"";
        try {
            db.execCommand(update_Checks_In);
        }catch (Exception e){
            System.out.println("Error updating the database: "+e);
            return;
        }

    }

    public static void main(String[] args) throws Exception
    {
    }
}