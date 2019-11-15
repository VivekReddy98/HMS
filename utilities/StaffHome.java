
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffHome{
    private String eid;
    private String f_name;
    private String l_name, dob, city;
    public StaffHome() {

    }
    boolean autheticate() throws Exception{
        ResultSet rs = null;
        String query = "Select e_id, fname from Staff where lname = '" + l_name + "' and dob = TO_DATE('" +
                dob +"', 'MM/dd/yyyy') and city = '"+ city +"' and designation = 'M'";
        SQLExec db = new SQLExec();
        db.connect();

        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
        }
        if (!rs.next())
        {
            return false;
        }
        else
        {
            eid = rs.getString("e_id");
            f_name = rs.getString("fname");
        }
        db.terminate();
        return true;
    }
    public void signIn() throws Exception{
        boolean invalidDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        System.out.println("\n\t\tMedical Staff Sign In");
        System.out.print("Enter Last Name: ");
        l_name = StaticFunctions.nextLine();
        System.out.print("Enter Date of Birth (mm/dd/yyyy): ");
        do {
            dob = StaticFunctions.next();
            StaticFunctions.nextLine();
            invalidDate = false;
            try{
                dateFormat.parse(dob.trim());
            }
            catch (Exception pe) {
                System.out.print("Invalid Date. Enter again: ");
                invalidDate = true;
            }
        } while(invalidDate);
        System.out.print("Enter City: ");
        city = StaticFunctions.nextLine();
    }
    public void MainView() throws Exception{
        int choice;
        signIn();
        if(!autheticate()){
            return;
        }
        else{
            System.out.println("EID: "+eid);
        }
        do{
            System.out.println("\n\t\tStaff Menu");
            System.out.println("1. Checked-in patients' list");
            System.out.println("2. Treated patients' list");
            System.out.println("3. Add symptoms");
            System.out.println("4. Add severity scale");
            System.out.println("5. Add assessment rule");
            System.out.println("6. Go Back");
            do {
                System.out.print("Enter Choice (1-6): ");
                choice = StaticFunctions.nextInt();
                StaticFunctions.nextLine();
                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid Choice");
                }
            } while (choice < 1 || choice > 6);
            switch(choice) {
                case 1:
                    StaffProcessPatient spp = new StaffProcessPatient(eid);
                    spp.MainView();
                    break;
                case 2:
                    System.out.print("Treated patients' list");
                    System.out.print("Staff checkout patient page");
                    break;
                case 3:
                    System.out.print("Add symptoms page");
                    break;
                case 4:
                    System.out.print("Add severity scale");
                    break;
                case 5:
                    System.out.print("Add assessment rule");
                    break;
                case 6:
                    System.out.print("Function for choice 6");
                    break;
            };
        }while(choice != 6);
    }

    public static void main(String[] args) throws Exception
    {

    }
}