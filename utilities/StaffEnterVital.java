// Needs Visit_ID to update the end time
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffEnterVital{
    int v_id = 1;
    private int temperature;
    private int bp_systolic;
    private int bp_diastolic;
    public StaffEnterVital() {
        StaticFunctions.Initialise();
    }

    public void enterDetails(){
        System.out.println("Enter Following Details");
        System.out.print("\nA. Temperature: ");
        temperature = StaticFunctions.nextInt();
        System.out.print("B. Systolic blood pressure: ");
        bp_systolic = StaticFunctions.nextInt();
        System.out.print("C. Diastolic blood pressure: ");
        bp_diastolic = StaticFunctions.nextInt();
    }

    public void showMenu(){
        do{
            System.out.println("\n1. Record");
            System.out.println("2. Go Back");
            System.out.print("\nEnter Choice: ");
            int choice = StaticFunctions.nextInt();
            if (choice == 1){
                assessment();
                System.out.println("\nSuccessfully Recorded! \nGoing Back");
                choice = 2;
            }
            if (choice == 2){
                System.out.println("Remaining - Trigger GoBack Func");
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
        String userWindows = System.getenv("HMSPATH");
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
            System.out.println(e);
        }

    }

    public static void main(String[] args) throws Exception
    {
        StaffEnterVital ob = new StaffEnterVital();
        ob.mainView();
    }
}