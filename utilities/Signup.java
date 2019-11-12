package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
public class Signup{
    public Signup() {
        StaticFunctions.Initialise();
    }
    public void addPatient(String f_name, String l_name, int addr_num, String street, String city, String state,
                           String country, String dob, String phone_no) throws Exception
    {
        String tab_name = "Patient";
        String col_fname = "fname";
        String col_lname = "lname";
        String col_dob = "dob";
        String col_phone_no = "phone_no";
        String col_addr_num = "numb";
        String col_street = "street";
        String col_city = "city";
        String col_state = "state";
        String col_country = "country";

        String query = "Select p_id from "+ tab_name + " where " +
                col_fname + " = '" + f_name + "' and " + col_lname + " = '" + l_name + "' and " +
                col_dob + " = TO_DATE('" + dob +"', 'dd/MM/yyyy') and " +
                col_addr_num + " = " + addr_num + " and " + col_street + " = '" + street + "' and " +
                col_state + " = '" + state + "' and " + col_city + " = '" + city + "'";

        SQLExec db = new SQLExec();
        db.connect();
        ResultSet rs = db.execQuery(query);
        if (!rs.next())
        {
            query = "Insert into "+ tab_name + "(" + col_fname + ", " + col_lname + ", " + col_addr_num + ", " +
                    col_street + ", " + col_state + ", " + col_city + ", " + col_dob + ") values ('" + f_name +
                    "', '" + l_name + "', " + addr_num + ", '" + street + "', '" + state + "', '" + city +
                    "', TO_DATE('" + dob +"','dd/MM/yyyy'))";
            try{
                db.execCommand(query);
            }
            catch (Exception e) {

                System.out.println("Could not insert data into the DB: "+e);
            }
        }
        else
        {
            System.out.println("Patient already registered. Please Log In");
        }
        db.terminate();
    }
    public void MainView() throws Exception{
        String f_name, l_name, dob, street, city, state, country, phone_no;
        int addr_num;
        boolean invalidDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        System.out.println("\n\t\tNew Patient Registration");
        System.out.print("Enter First Name: ");
        f_name = StaticFunctions.next();
        StaticFunctions.nextLine();
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
        System.out.println("\nAddress Details ");
        System.out.print("Enter Address Number: ");
        addr_num = StaticFunctions.nextInt();
        StaticFunctions.nextLine();
        System.out.print("Enter Street: ");
        street = StaticFunctions.nextLine();
        System.out.print("Enter City: ");
        city = StaticFunctions.nextLine();
        System.out.print("Enter State: ");
        state = StaticFunctions.nextLine();
        System.out.print("Enter Country: ");
        country = StaticFunctions.nextLine();
        System.out.print("Enter Phone No: ");
        phone_no = StaticFunctions.nextLine();

        addPatient(f_name, l_name, addr_num, street, city, state, country, dob, phone_no);
    }

    public static void main(String[] args) throws Exception
    {
        Signup ob = new Signup();
        ob.MainView();
    }
}