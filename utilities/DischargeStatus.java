package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class DischargeStatus{

    public DischargeStatus() {

        StaticFunctions.Initialise();
    }

    public void MainView(ReportDS r_obj) throws Exception{
        String query = "";
        int choice;
        // SQLExec db = new SQLExec();
        // db.connect();

        do{
            System.out.println("\n\t\tStaff Patient Report");
            System.out.println("1. Successful treatment");
            System.out.println("2. Deceased");
            System.out.println("3. Referred");
            System.out.println("4. Go Back");

            System.out.print("Enter Choice (1-4): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            switch(choice) {
                case 1:
                    query = "Update Checks_In set dis_status = 'Treated Successfully' where v_id = " + r_obj.vid;
                    // try{
                    //     db.execCommand(query);
                    // }catch (Exception e) {
                    //     System.out.println("Could not update entry: "+e);
                    // }
                    System.out.println("Updated Treated Successfully");
                    r_obj.Q_discharge = query; 
                    r_obj.discharge_status = "Treated Successfully";
                    return;
                case 2:
                    query = "Update Checks_In set dis_status = 'Deceased' where v_id = " + r_obj.vid;
                    // try{
                    //     db.execCommand(query);
                    // }catch (Exception e) {
                    //     System.out.println("Could not update entry: "+e);
                    // }
                    System.out.println("Updated Deceased");
                    r_obj.Q_discharge = query;
                    r_obj.discharge_status = "Deceased"; 
                    return;
                case 3:
                    query = "Update Checks_In set dis_status = 'Referred' where v_id = " + r_obj.vid;
                    // try{
                    //     db.execCommand(query);
                    // }catch (Exception e) {
                    //     System.out.println("Could not update entry: "+e);
                    // }
                    System.out.println("Updated Referred");
                    r_obj.Q_discharge = query; 
                    r_obj.discharge_status = "Referred";
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
        DischargeStatus spr = new DischargeStatus();
        ReportDS r_obj = new ReportDS();
        spr.MainView(r_obj);
        //System.out.println(r_obj.Q_discharge);
    }
}
