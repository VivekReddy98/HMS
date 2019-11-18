package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PatientLogin{
    private int pid;
    private String f_name;
    private String l_name, dob, city;
    public PatientLogin() {

    }

    boolean autheticate() throws Exception{
        ResultSet rs = null;
        // String query = "Select p_id, fname from Patient where lname = '" + l_name + "' and dob = TO_DATE('" +
        //         dob +"', 'MM/dd/yyyy') and city = '"+ city +"'";

        String query = "Select p_id, fname from Patient where lname = '" + l_name + "' and dob = STR_TO_DATE('" +
                dob +"', '%m/%d/%Y') and city = '"+ city +"'";
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
            pid = rs.getInt("p_id");
            f_name = rs.getString("fname");
        }
        db.terminate();
        return true;
    }

    public void signIn() throws Exception{
        boolean invalidDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);

        System.out.println("\n\t\tPatient Sign In");
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
        System.out.println("\n\t\tSign In");
        System.out.println("1. Sign In");
        System.out.println("2. Go Back");
        do{
            System.out.print("Enter Choice (1-2): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if(choice != 1 && choice != 2)
            {
                System.out.println("Invalid Choice");
            }
        }while(choice != 1 && choice != 2);
        switch(choice) {
            case 1:
                if(autheticate())
                {
                    Patient p = new Patient(pid,f_name);
                    p.MainView();
                }
                else
                {
                    System.out.println("Invalid Credentials. Try again.");
                    TimeUnit.SECONDS.sleep(2);
                    MainView();
                }
                break;
            case 2:
                return;
        };
    }

    public static void main(String[] args) throws Exception
    {

    }
}