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

public class PatientCheckIn{
    private int v_id;
    private int other_flag = 0;
    public PatientCheckIn(int vid) {
        v_id = vid;
    }
    public void MainView() throws Exception {
        String temp = "";
        int choice,i;
        ResultSet rs = null;
        Vector listofsymptoms = new Vector();   //To pass code of selected disease
        SQLExec db = new SQLExec();
        db.connect();
        String query = "SELECT * FROM Symptoms";

        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            //db.terminate();
            return;
        }
        System.out.print("\n\t\tSymptoms");
        while(rs.next()) {
            if (rs.getString("name").equals("Other")){
                temp = rs.getString("code");
                continue;
            }
            listofsymptoms.add(rs.getString("code"));
        }
        listofsymptoms.add(temp);
        do{
            db.terminate();
            db.connect();
            System.out.println();
            try {
                rs = db.execQuery(query);
            }
            catch(Exception e){
                System.out.println("Error retrieving data from the DB: "+e);
                //db.terminate();
                return;
            }
            i = 0;

            while(rs.next()) {
                i++;
                if (rs.getString("name").equals("Other")){
                    i--;
                    continue;
                }
                System.out.println(Integer.toString(i)+". "+rs.getString("name"));
            }
            System.out.println(Integer.toString(i+1) +". Other");
            System.out.println(Integer.toString(i+2) +". Done");

            System.out.print("Enter Symptom Number: ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if(choice> i+2) {
                System.out.println("Invalid Choice");
            }
            else if(choice == i+1){
//                String temp1 = listofsymptoms.get(choice-1);
                otherSymptom((String)listofsymptoms.get(choice-1));
            }
            else if(choice == i+2){
                //System.out.println("Function for Done choice");
            }
            else{
                //System.out.println("Call Sanket's Function with CODE "+ listofsymptoms.get(choice-1));
                PatientSymptom symp = new PatientSymptom(v_id);
                symp.MainView(String.valueOf(listofsymptoms.get(choice-1)));
            }
        }while(choice != i+2);

        if (choice == i+2){
            System.out.println("Inserting start time into checks in table");
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String current_time;
            current_time = formatter.format(date);
            //current_time = "TO_DATE('"+current_time+"', 'mm/dd/yyyy hh24:mi:ss')";
            current_time = "STR_TO_DATE('"+current_time+"', '%m/%d/%Y %T')";
            String update_start_time_query = "UPDATE Checks_In SET checkin_start_time = " +current_time+" WHERE v_id ="+v_id+"";
            System.out.println(update_start_time_query);
//            System.out.println(update_start_time_query);
            try{
                db.execCommand(update_start_time_query);
            }
            catch(Exception e){
                System.out.println("Error updating the DB: "+e);
                //db.terminate();
                return;
            }
            //Validate
            System.out.println("Check In complete. Logging Out.");
            TimeUnit.SECONDS.sleep(2);
        }
        db.terminate();
    }

    public void otherSymptom(String s_code){
        SQLExec db = new SQLExec();
        db.connect();
        String description;

        if (other_flag == 1){
            System.out.println("Please select from available symptoms");
            return;
        }
        System.out.print("Enter Description: ");
        description = StaticFunctions.nextLine();
        String query = ("INSERT INTO Affected_Info VALUES ("+v_id+", '"+s_code+"', 'OTH000', NULL, NULL, NULL, '"+description+"', NULL)");
        try {
            db.execCommand(query);
        }catch (Exception e) {
            System.out.println("Error updating the DB: "+e);
//            db.terminate();
            return;
        }
        other_flag = 1;
        //db.terminate();
    }

    public static void main(String[] args) throws Exception
    {
        // PatientCheckIn pc = new PatientCheckIn();
        // pc.MainView(1);
    }
}