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

    public String displayPatients() throws Exception{
        int choice;
        Vector listofpatients = new Vector();   //To pass code of selected disease
        String query = "SELECT * FROM ChecksIn";
        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
        FileReader pathSchema = new FileReader(userWindows + "sql/populateChecksIn.sql");
        db.connect();
        //To insert dummy data... run once
//        db.execCommandScript(pathSchema);
        ResultSet rs = db.execQuery(query);
        int i;
        try {
            while(rs.next()) {
                listofpatients.add(rs.getString("v_id"));
//                System.out.println(listofpatients);
            }
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Unable to fetch records1");
        }
        try {
            rs = db.execQuery(query);
            i = 0;
            try {
                while (rs.next()) {
                    ++i;
                    System.out.println(Integer.toString(i) + ". " + rs.getString("v_id"));
                }
            } catch (Exception e) {
                System.out.println("Unable to fetch records");
            }
        }catch (Exception e) {}

        choice = StaticFunctions.nextInt();
        StaticFunctions.nextLine();

//        return listofpatients.get(choice-1);
        return "Some ID";
    }

    public void displayMenu(String v_id){
        int choice;
        System.out.println("SelectedPatiend: "+v_id);
        System.out.println("1. Enter Vitals");
        System.out.println("2. Treat Patient");
        System.out.println("3. Go Back");
        do{
            System.out.print("Enter Choice (1-3): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if(choice != 1 && choice != 2 && choice != 3)
            {
                System.out.println("Invalid Choice");
            }
        }while(choice != 1 && choice != 2 && choice !=3);
        switch(choice) {
            case 1:
                System.out.println("Go to Enter Vitals Page with v_id");
                break;
            case 2:
                System.out.println("Check auth and treat patient Function");
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
        StaffProcessPatient ob = new StaffProcessPatient();
        ob.mainView();
    }
}