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
    private static String staff_id = "88001";
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
//                if(rs.getString("checkin_start_time") != null && rs.getString("treatment").equals("False") ) {
                if(rs.getString("checkin_start_time") != null) {
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
//                    if(rs.getString("checkin_start_time") != null && rs.getString("treatment").equals("False") ) {
                    if(rs.getString("checkin_start_time") != null) {
                        ++i;
                        System.out.println(Integer.toString(i) + ". " + rs.getString("v_id"));
                    }
                }
            } catch (Exception e) {
                System.out.println("Unable to fetch records");
            }
        }catch (Exception e) {}

        ////////////////////////////////////////////////////

        do {
            System.out.print("\nEnter the ID of patient to select: ");
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

        
        System.out.println("\n1. Enter Vitals");
        System.out.println("2. Treat Patient");
        System.out.println("3. Go Back");


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
    db.terminate();
    };



    public void executeChoice_opt(int v_id, int choice) throws Exception{
        switch(choice) {
            case 1:
                System.out.println("Remaining - Go to Enter Vitals Page with v_id: "+Integer.toString(v_id));
                break;
            case 2:
//                System.out.println("Remaining - Check auth and treat patient Function");
                if (!treatPatient(v_id)) {
                    System.out.println("Inadequate Privilege");
                    displayPatients();
                }
                else{
                    SQLExec db = new SQLExec();
                    db.connect();
                    String query_toUpdateTreatmentDone = "UPDATE Checks_In SET treatment = 'True' WHERE v_id = "+v_id+"";
                    db.execCommand(query_toUpdateTreatmentDone);
                    System.out.println("TreatmentDone");
                    db.terminate();
                }
                break;
            case 3:
                return;
        };
    }

    public boolean treatPatient(int v_id) throws  Exception{
        SQLExec db = new SQLExec();
        db.connect();
        String query_getBodyPartsFromAffected = "SELECT b_code FROM Affected_Info WHERE v_id ="+v_id+"";
//        System.out.println(query_getBodyPartsFromAffected);
        ResultSet rs = db.execQuery(query_getBodyPartsFromAffected);
        while (rs.next()){
            String b_code = rs.getString("b_code");
//            System.out.println(b_code);
            String query_getDeptId1 = "SELECT * FROM Staff, Specialized_For SF WHERE Staff.e_id ='"+staff_id+"' AND Staff.primary_dept = SF.s_code AND SF.b_code ='"+b_code+"'";
//            System.out.println(query_getDeptId1);
            ResultSet rs_getDeptId1 = db.execQuery(query_getDeptId1);

            String query_getDeptId2 = "SELECT * FROM Secondary_Works_Dept, Specialized_For SF WHERE Secondary_Works_Dept.e_id ='"+staff_id+"' AND Secondary_Works_Dept.code = SF.s_code AND SF.b_code ='"+b_code+"'";
//            System.out.println(query_getDeptId2);
            ResultSet rs_getDeptId2 = db.execQuery(query_getDeptId2);
            if (rs_getDeptId1.next() || rs_getDeptId2.next()){
//                System.out.println("RERURNING TRUE");
                db.terminate();
                return true;
            }

        }
//        System.out.println("RERURNING FALSE");
        db.terminate();
        return false;
    }

    public boolean checkIfStaff(String staff_id) throws Exception{
        SQLExec db = new SQLExec();
        db.connect();

        String query = "SELECT * FROM Staff WHERE e_id='"+staff_id+"' AND designation = 'M'";
        ResultSet rs = db.execQuery(query);
        if (!rs.next()){
            db.terminate();
            return false;
        }
        else {
            db.terminate();
            return true;
        }

    }

    public void mainView() throws Exception{
        System.out.println("\nList of checked in patients");
        displayPatients();

    }

    public static void main(String[] args) throws Exception
    {
        StaffProcessPatient ob = new StaffProcessPatient();
        if (ob.checkIfStaff(staff_id)) {
            ob.mainView();
        }
        else{
            System.out.println("Sorry! Only Medical Staff Allowed");
        }
    }
}