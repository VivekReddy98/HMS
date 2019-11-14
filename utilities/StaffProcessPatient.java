// Needs Visit_ID to update the end time
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffProcessPatient{
    public StaffProcessPatient() {

        StaticFunctions.Initialise();
    }

    public int displayPatients() throws Exception{
        int choice;
        Vector listofpatients = new Vector();   //To pass code of selected disease
        String query = "SELECT * FROM Checks_In";
        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
//        FileReader pathSchema = new FileReader(userWindows + "sql/populateChecksIn.sql");
        db.connect();
        //To insert dummy data... run once
//        db.execCommandScript(pathSchema);
            ResultSet rs = db.execQuery(query);

//        if (!rs.next()){
//            return "No New Patients";
//        }
        int i;
        try {
            while(rs.next()) {
                if(rs.getString("checkin_start_time") != null && rs.getString("treatment").equals("False") ) {
                    listofpatients.add(rs.getString("v_id"));
                }
//                System.out.println(listofpatients);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Unable to fetch records1");
        }
        if (listofpatients.isEmpty()){
            return -1;
        }
        try {
            rs = db.execQuery(query);
            i = 0;
            try {
                while (rs.next()) {
                    if(rs.getString("checkin_start_time") != null && rs.getString("treatment").equals("False") ) {
                        ++i;
                        System.out.println(Integer.toString(i) + ". " + rs.getString("v_id"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Unable to fetch records");
            }
        }catch (Exception e) {}
        System.out.println("\n Enter the ID of patient to select");
        do {
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if (choice == 0){
                return -2;
            }
            if (listofpatients.contains(Integer.toString(choice))) {
                return choice;
            }
            else{
                System.out.println("Enter Correct Patient ID or Enter 0 to go back");
            }
        }while (true);

    }

    public void displayMenu(int v_id){
        if (v_id == -1){
            System.out.println("\nNo New Patients");
            System.out.println("Logging out");
            return;
        }
        if (v_id == -2) {
            System.out.println("\nThank You");
            System.out.println("Logging out");
            return;
        }
        int choice;
        System.out.println("\nSelected Patiend ID: "+Integer.toString(v_id));
        System.out.println("1. Enter Vitals");
        System.out.println("2. Treat Patient");
        System.out.println("3. Go Back");
        do{
            System.out.print("\nEnter Choice (1-3): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if(choice != 1 && choice != 2 && choice != 3)
            {
                System.out.println("Invalid Choice");
            }
        }while(choice != 1 && choice != 2 && choice !=3);
        switch(choice) {
            case 1:
                System.out.println("Go to Enter Vitals Page with v_id: "+Integer.toString(v_id));
                break;
            case 2:
                System.out.println("Remaining - Check auth and treat patient Function");
                break;
            case 3:
                return;
        };
    }



    public void mainView() throws Exception{
        System.out.println("List of checked in patients");
        displayMenu(displayPatients());

    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Remaining - if user is medical staff");
        StaffProcessPatient ob = new StaffProcessPatient();
        ob.mainView();
    }
}