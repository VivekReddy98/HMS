package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class PatientCheckIn{
    public PatientCheckIn() {

        StaticFunctions.Initialise();
    }
    public void showSymptops() throws Exception
    {
        int choice;
        Vector listofsymptoms = new Vector();
        String query = "SELECT * FROM Symptoms";
        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
        FileReader pathSchema = new FileReader(userWindows + "sql/addSymptoms.sql");
        db.connect();
//        db.execCommandScript(pathSchema);
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
            else{
                System.out.println("Call Sanket's Function");
            }
        }while(choice != 5);

    }

    public static void main(String[] args) throws Exception
    {
        PatientCheckIn ob = new PatientCheckIn();
        ob.showSymptops();
    }
}