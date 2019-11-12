package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Login{
    private int pid;
    private String f_name;
    private int fid;

    public Login() {

        StaticFunctions.Initialise();
    }

    boolean autheticate(String l_name, String dob, String city) throws Exception{
        ResultSet rs = null;
        String query = "Select p_id, fname from Patient where lname = '" + l_name + "' and dob = TO_DATE('" +
                dob +"', 'dd/MM/yyyy') and city = '"+ city +"'";
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

    public void getMedicalFacility() throws Exception{
        ResultSet rs = null;
        String address = ""; int new_fid;
        List<Integer> fid_list = new ArrayList<>();
        List<Integer> fid_list2 = new ArrayList<>();
        boolean invalid = false;

        SQLExec db = new SQLExec();
        db.connect();

        String query = "Select f_id from Checks_In where p_id = "+pid;
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            return;
        }

        while(rs.next())
        {
            fid_list.add(rs.getInt(fid));
        }

        query = "Select * from Medical_Facility";
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            return;
        }
        System.out.println("\n\t\tMedical Facilities: ");
        System.out.println("\nID \t Name \t\t\t Address: ");
        while(rs.next())
        {
            System.out.println("nmmn");
            address = Integer.toString(rs.getInt("numb")) + ", " + rs.getString("street") +
                      ", " + rs.getString("city") + ", " + rs.getString("state") +  ", " +
                      rs.getString("country");
            fid_list2.add(rs.getInt("f_id"));
            System.out.println("\n"+rs.getInt("f_id")+"\t"+rs.getString("name")+"\t"+address);
        }
        if (!fid_list.isEmpty())
        {
            System.out.println("\nFacilities already checked in: "+ Arrays.toString(fid_list.toArray()));
        }
        do {
            System.out.print("Enter facility ID to check in: ");
            new_fid = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            invalid = false;
            if(!fid_list2.contains(new_fid)){
                System.out.println("Invalid facility Id. Enter again. ");
                invalid = true;
            }
            else{
                fid = new_fid;
            }
        }while(invalid);
        db.terminate();
    }

    public void signIn() throws Exception{
        String l_name, dob, city;
        boolean invalidDate;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        System.out.println("\n\t\tPatient Sign In");
        System.out.print("Enter Last Name: ");
        l_name = StaticFunctions.nextLine();
        System.out.print("Enter Date of Birth (dd/mm/yyyy): ");
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
        if(autheticate(l_name,dob,city))
        {
            System.out.println("\nHello, "+f_name);
            getMedicalFacility();
        }
        else
        {
            System.out.println("\nInvalid Credentials. Try again.");
            TimeUnit.SECONDS.sleep(3);
            signIn();
        }
    }

    public void MainView() throws Exception{
        int choice;

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
                signIn();
                break;
            case 2:
                return;
        };
    }

    public static void main(String[] args) throws Exception
    {
        Login ob = new Login();
        ob.MainView();
        //String query = "insert into Medical_Facility(f_id, name, capacity, classification, numb, street, city, state, country) values (1000, 'Wolf Hospital', 300, 3, 2650, 'Wolf Village', 'Raleigh','NC','USA')";
        //SQLExec db = new SQLExec();
        //db.connect();

        //db.execCommand(query);

        //db.terminate();
    }
}