package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Patient{
    int pid;
    String f_name;
    int fid;
    public Patient() {

    }
    public Patient(int pid,String f_name) {
        this.pid = pid;
        this.f_name = f_name;
    }
    public void getMedicalFacility() throws Exception{
        ResultSet rs = null;
        String address = ""; int new_fid;
        List<Integer> fid_list = new ArrayList<>();
        List<Integer> fid_list2 = new ArrayList<>();
        boolean invalid = false;

        SQLExec db = new SQLExec();
        db.connect();

        String query = "Select f_id from Checks_In where p_id = "+pid+" and acknowledged = 'no'";
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            return;
        }

        while(rs.next())
        {
            fid_list.add(rs.getInt("f_id"));
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
        System.out.println("\nID \t\t Name \t\t\t Address: ");
        while(rs.next())
        {
            address = Integer.toString(rs.getInt("numb")) + ", " + rs.getString("street") +
                    ", " + rs.getString("city") + ", " + rs.getString("state") +  ", " +
                    rs.getString("country");
            fid_list2.add(rs.getInt("f_id"));
            System.out.println("\n"+rs.getInt("f_id")+"\t"+rs.getString("name")+"\t\t"+address);
        }
        if (!fid_list.isEmpty())
        {
            System.out.println("\nFacilities already checked in: "+ Arrays.toString(fid_list.toArray()));
        }
        do {
            System.out.print("\nEnter facility ID to check in: ");
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

    public int checkIn()throws Exception{
        ResultSet rs = null;
        SQLExec db = new SQLExec();
        db.connect();
        String query = "Select * from Checks_In where p_id = "+pid+" and f_id = "+fid+" and acknowledged = 'no'";
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            db.terminate();
            return -1;
        }
        if(rs.next())
        {
            db.terminate();
            return 0;
        }
        query = "Insert into Checks_In(p_id, f_id, acknowledged)" +
                        "Values("+ pid +", "+ fid + ", 'no')";
        try {
            db.execCommand(query);
        }
        catch (Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            db.terminate();
            return -1;
        }
        db.terminate();
        return 1;
    }

    public int getVid()throws Exception {
        ResultSet rs = null;
        SQLExec db = new SQLExec();
        db.connect();
        String query = "Select v_id from Checks_In where p_id = "+pid+" and f_id = "+fid+" and acknowledged = 'no'";
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            db.terminate();
            return -1;
        }
        rs.next();
        return rs.getInt("v_id");
    }

    public int checkOut()throws Exception{
        ResultSet rs = null;
        SQLExec db = new SQLExec();
        db.connect();
        String query = "Update Checks_In set acknowledged = 'yes' where p_id = "+pid+" and f_id = "+fid;
        try{
            db.execCommand(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            db.terminate();
            return -1;
        }
        db.terminate();
        return 1;
    }

    public void MainView() throws Exception{
        int choice; int result;
        getMedicalFacility();

        do {
            System.out.print("\nHello, " +f_name);
            System.out.println("\n\t\tPatient Menu");
            System.out.println("1. Check In");
            System.out.println("2. Check Out Acknowledgement");
            System.out.println("3. Back");
            System.out.println("4. Logout");
            do {
                System.out.print("Enter Choice (1-4): ");
                choice = StaticFunctions.nextInt();
                StaticFunctions.nextLine();
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid Choice");
                }
            } while (choice < 1 || choice > 4);
            switch (choice) {
                case 1:
                    result = checkIn();
                    if(result == 1) {
                        int vid = getVid();
                        PatientCheckIn pc = new PatientCheckIn(vid);
                        pc.MainView();
                        return;
                    }
                    else if(result == 0){
                        System.out.println("\nYou have already checked in this facility.");
                        TimeUnit.SECONDS.sleep(2);
                    }
                    else{
                        //Do Nothing
                    }
                    break;
                case 2:
                    result = checkOut();
                    if(result == 1) {
                        System.out.println("Check Out Successful!");
                        TimeUnit.SECONDS.sleep(2);
                    }
                    else{
                        //Do Nothing
                    }
                    break;
                case 3:
                    getMedicalFacility();
                    break;
                case 4:
                    return;
            };
        }while(true);
    }

    public static void main(String[] args) throws Exception
    {
        Patient ob = new Patient();
        ob.MainView();
    }
}