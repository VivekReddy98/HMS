// Needs Visit_ID to update the end time
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StaffProcessPatient{
    private String staff_id;
    public StaffProcessPatient(String e_id) {
        staff_id = e_id;
        //StaticFunctions.Initialise();
    }

    public void displayPatients() throws Exception{
        int choice;
        ResultSet rs = null;
        int choice_opt;
        int i;
        int fid = 0;
        Vector listofpatients = new Vector();   //To pass code of selected disease
        Vector namesofpatients = new Vector();

        SQLExec db = new SQLExec();
        String userWindows = System.getenv("HMSPATH");
        db.connect();

        String q1 = "Select f.f_id from Facility_has_Dept f, Staff s where s.primary_dept = f.code and s.e_id = '" + staff_id + "'";

        try{
            rs = db.execQuery(q1);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            return;
        }
        if(rs.next()){
            fid = rs.getInt("f_id");
        }

        String query = "SELECT * FROM Checks_In c, Patient p where c.p_id = p.p_id and c.f_id =" + fid + " and c.treatment = 'false'";
        System.out.println(query);
        
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            return;
        }

        while(rs.next()) {
            if(rs.getString("checkin_start_time") != null) {
                listofpatients.add(rs.getInt("v_id"));
                namesofpatients.add(rs.getString("fname") +" "+ rs.getString("lname"));
            }
        }
        if (listofpatients.isEmpty()){
            System.out.println("\n\tNo Checked In Patients. Redirecting to Menu.");
            TimeUnit.SECONDS.sleep(2);
            return;
        }
        try {
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            return;
        }

        i = 0;
        while (rs.next()) {
//          if(rs.getString("checkin_start_time") != null && rs.getString("treatment").equals("False") ) {
            if(rs.getString("checkin_start_time") != null) {
                i++;
                System.out.println(Integer.toString(i) + ". " + String.valueOf(listofpatients.get(i-1)) + "   " + namesofpatients.get(i-1)) ;
            }
        }

        do {
            System.out.print("\nEnter your choice: ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            
            try{
                if (listofpatients.contains(listofpatients.get(choice-1))){
                    break;
                }

            }  
                
            catch (Exception e){
                System.out.println("Incorrect Entry.");
            }
        }while (true);

        System.out.println("\n\t\tActions");
        System.out.println("1. Enter Vitals");
        System.out.println("2. Treat Patient");
        System.out.println("3. Go Back");
        do{
            System.out.print("Enter Choice (1-3): ");
            choice_opt = StaticFunctions.nextInt();
            StaticFunctions.nextLine();
            if(choice_opt != 1 && choice_opt != 2 && choice_opt != 3)
            {
                System.out.println("Invalid Choice");
            }
        }while(choice_opt != 1 && choice_opt != 2 && choice_opt !=3);

        //System.out.println(listofpatients.get(choice-1).getClass());
        switch(choice_opt) {
            case 1:
                executeChoice_opt((int)listofpatients.get(choice-1), choice_opt);
                break;
            case 2:
                executeChoice_opt((int)listofpatients.get(choice-1), choice_opt);
                break;
            case 3:
                return;
        };
    db.terminate();
    }

    public void executeChoice_opt(int v_id, int choice) throws Exception{
        switch(choice) {
            case 1:
                //Connect to StaffEnterVital.java
                StaffEnterVital sev = new StaffEnterVital();
                sev.v_id = v_id;
                sev.mainView();
                break;
            case 2:
                if (!treatPatient(v_id)) {
                    System.out.println("Inadequate Staff Privilege Level");
                    displayPatients();
                }
                else{
                    SQLExec db = new SQLExec();
                    db.connect();
                    String query_toUpdateTreatmentDone = "UPDATE Checks_In SET treatment = 'True' WHERE v_id = "+v_id+"";
                    try {
                        db.execCommand(query_toUpdateTreatmentDone);
                    }
                    catch(Exception e){
                        System.out.println("Error updating the DB: "+e);
                        return;
                    }
                    System.out.println("Patient marked as treated");
                    db.terminate();
                }
                break;
            case 3:
                return;
        };
    }

    public boolean treatPatient(int v_id) throws  Exception{
        ResultSet rs = null;
        ResultSet rs_getDeptId1 = null;
        ResultSet rs_getDeptId2 = null;
        SQLExec db = new SQLExec();
        db.connect();
        String query_getBodyPartsFromAffected = "SELECT b_code FROM Affected_Info WHERE v_id ="+v_id+"";

        try {
            rs = db.execQuery(query_getBodyPartsFromAffected);
        }
        catch (Exception e){
            System.out.println("Error fetching data from the DB: "+e);
            return false;
        }
        while (rs.next()){
            try{
                String b_code = rs.getString("b_code");
                String query_getDeptId1 = "SELECT * FROM Staff, Specialized_For SF WHERE Staff.e_id ='"+staff_id+"' AND Staff.primary_dept = SF.s_code AND SF.b_code ='"+b_code+"'";
                rs_getDeptId1 = db.execQuery(query_getDeptId1);

                String query_getDeptId2 = "SELECT * FROM Secondary_Works_Dept, Specialized_For SF WHERE Secondary_Works_Dept.e_id ='"+staff_id+"' AND Secondary_Works_Dept.code = SF.s_code AND SF.b_code ='"+b_code+"'";
                rs_getDeptId2 = db.execQuery(query_getDeptId2);
            }
            catch (Exception e){
                System.out.println("Error fetching data from the DB: "+e);
                return false;
            }
            if (rs_getDeptId1.next() || rs_getDeptId2.next()){
                db.terminate();
                return true;
            }

        }
//        System.out.println("RERURNING FALSE");
        db.terminate();
        return false;
    }

    public void MainView() throws Exception{
        System.out.println("\nList of checked in patients");
        displayPatients();

    }

    public static void main(String[] args) throws Exception
    {
        StaffProcessPatient spp = new StaffProcessPatient("88001");
        spp.MainView();
    }
}