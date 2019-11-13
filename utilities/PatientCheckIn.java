// Needs Visit_ID to update the end time
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientCheckIn{
    public int v_id = 1;
    public int other_flag = 0;
    public PatientCheckIn() {

        StaticFunctions.Initialise();
    }
    public void showSymptops() throws Exception
    {
        //TEMP


        String temp = "";
        int choice;
        Vector listofsymptoms = new Vector();   //To pass code of selected disease
        String query = "SELECT * FROM Symptoms";
        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
        FileReader pathSchema = new FileReader(userWindows + "sql/addSymptoms.sql");
        db.connect();
        //To insert dummy data... run once
//            db.execCommandScript(pathSchema);
        ResultSet rs = db.execQuery(query);
        int i;
        try {
            while(rs.next()) {
                if (rs.getString("name").equals("Other")){
                    temp = rs.getString("code");
                    continue;
                }
                listofsymptoms.add(rs.getString("code"));
            }
        }
        catch (Exception e) {
            System.out.println("Unable to fetch records");
        }
        listofsymptoms.add(temp);
//        listofsymptoms.add("exit");
//        System.out.println(""+ listofsymptoms);

        do{
            System.out.println();
            rs = db.execQuery(query);
            i = 0;
            try {
                while(rs.next()) {
                    ++i;
                    if (rs.getString("name").equals("Other")){
                        i--;
                        continue;
                    }
                    System.out.println(Integer.toString(i)+". "+rs.getString("name"));
                }
            }
            catch (Exception e) {
                System.out.println("Unable to fetch records");
            }
            System.out.println(Integer.toString(i+1) +". Other");
            System.out.println(Integer.toString(i+2) +". Done\n");

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
                System.out.println("Function for Done choice");
            }
            else{
                System.out.println("Call Sanket's Function with CODE "+ listofsymptoms.get(choice-1));
            }
        }while(choice != i+2);

        if (choice == i+2){
            System.out.println("Inserting time into checks in table");
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            String current_time;
            current_time = formatter.format(date);
            current_time = "TO_DATE('"+current_time+"', 'mm/dd/yyyy hh24:mi:ss')";
            String update_end_time_query = "UPDATE Checks_In SET checkin_start_time = " +current_time+" WHERE v_id ="+v_id+"";
//            System.out.println(update_end_time_query);
            db.execCommand(update_end_time_query);

            System.out.println("--What is validate");
            System.out.println("--Call Logout");

        }
    }

    public void otherSymptom(String s_code){
        if (other_flag == 1){
            System.out.println("Please select from available symptoms");
            return;
        }
        SQLExec db = new SQLExec();
        db.connect();
        String description;
        System.out.println("Enter Description: ");
        description = StaticFunctions.nextLine();
        String query = ("INSERT INTO Affected_Info VALUES ("+v_id+", '"+s_code+"', 'OTH000', NULL, NULL, NULL, '"+description+"', NULL)");
        try {
            db.execCommand(query);
        }catch (Exception e) {
            System.out.println(e);
        }
        other_flag = 1;
    }

    public static void main(String[] args) throws Exception
    {
        PatientCheckIn ob = new PatientCheckIn();
        ob.showSymptops();
    }
}