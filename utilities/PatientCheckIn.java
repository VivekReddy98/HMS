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
    public PatientCheckIn() {

        StaticFunctions.Initialise();
    }
    public void showSymptops() throws Exception
    {
        //TEMP
        String visit_id = "1111AA";


        int choice;
        Vector listofsymptoms = new Vector();   //To pass code of selected disease
        String query = "SELECT * FROM Symptoms";
        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
        FileReader pathSchema = new FileReader(userWindows + "sql/addSymptoms.sql");
        db.connect();
        //To insert dummy data... run once
            //db.execCommandScript(pathSchema);
        ResultSet rs = db.execQuery(query);
        int i;
        try {
            while(rs.next()) {
                listofsymptoms.add(rs.getString("code"));
            }
        }
        catch (Exception e) {
            System.out.println("Unable to fetch records");
        }
        listofsymptoms.add("other");
        listofsymptoms.add("exit");
        System.out.println(""+ listofsymptoms);

        do{
            rs = db.execQuery(query);
            i = 0;
            try {
                while(rs.next()) {
                    ++i;
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
                System.out.println("Function for other choice");
            }
            else{
                System.out.println("Call Sanket's Function with CODE "+ listofsymptoms.get(choice-1));
            }
        }while(choice != 5);

        if (choice == 5){
            System.out.println("Insert time into checks in table");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String current_time;
            current_time = formatter.format(date);
            System.out.println("query => UPDATE Checks_In SET checkin_end_time =" +current_time+" WHERE v_id ='"+visit_id+"'");
            //String update_end_time_query = "UPDATE Checks_In SET checkin_end_time =" +current_time+" WHERE v_id ='"+visit_id+"'";
            System.out.println("--What is validate");
            System.out.println("--Call Logout");

        }
    }

    public static void main(String[] args) throws Exception
    {
        PatientCheckIn ob = new PatientCheckIn();
        ob.showSymptops();
    }
}