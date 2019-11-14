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

    public void displayPatients() throws Exception{
        int choice;
        int choice_opt;
        int i;
        Vector listofpatients = new Vector();   //To pass code of selected disease
        String query = "SELECT * FROM Checks_In";
        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
        db.connect();
        ResultSet rs = db.execQuery(query);

//        if (!rs.next()){
//            return "No New Patients";
//        }

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
            System.out.println("No Patients");
            return;
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

        ////////////////////////////////////////////////////
        System.out.println("\n1. Enter Vitals");
        System.out.println("2. Treat Patient");
        System.out.println("3. Go Back");


        do {
            System.out.print("\nEnter the ID of patient to select :");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if (choice == 3){
                System.out.println("Go Back Function");
                return;
            }
            if (listofpatients.contains(Integer.toString(choice))) {
                break;
            }
            else{
                System.out.println("Enter Correct Patient ID");
            }
        }while (true);
        do{
            System.out.print("\nEnter Choice (1-3): ");
            choice_opt = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if(choice_opt != 1 && choice_opt != 2 && choice_opt != 3)
            {
                System.out.println("Invalid Choice");
            }
        }while(choice_opt != 1 && choice_opt != 2 && choice_opt !=3);
        switch(choice_opt) {
            case 1:
                executeChoice_opt(choice, choice_opt);
                break;
            case 2:
                executeChoice_opt(choice, choice_opt);
                break;
            case 3:
                System.out.println("Bye");
                return;
        };

    };



    public void executeChoice_opt(int v_id, int choice) throws Exception{
        switch(choice) {
            case 1:
                System.out.println("Remaining - Go to Enter Vitals Page with v_id: "+Integer.toString(v_id));
                break;
            case 2:
                System.out.println("Remaining - Check auth and treat patient Function");
                if (!treatPatient(v_id)){
                    System.out.println("Inadequate Privilege");
                    displayPatients();
                }
                break;
            case 3:
                return;
        };
    }

    public boolean treatPatient(int v_id) throws  Exception{
        return false;
    }

    public void mainView() throws Exception{
        System.out.println("\nList of checked in patients");
        displayPatients();

    }

    public static void main(String[] args) throws Exception
    {
        System.out.println("Remaining - if user is medical staff");
        StaffProcessPatient ob = new StaffProcessPatient();
        ob.mainView();
    }
}