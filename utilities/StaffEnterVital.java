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

public class StaffEnterVital{
    public int v_id;
    private int temperature;
    private int bp_systolic;
    private int bp_diastolic;
    public StaffEnterVital() {
    	//StaticFunctions.Initialise();
    }

    public void enterDetails() throws Exception{
        System.out.println("\nEnter Following Details for Patient with ID: "+v_id);
        System.out.print("A. Temperature: ");
        temperature = StaticFunctions.nextInt();
        System.out.print("B. Systolic blood pressure: ");
        bp_systolic = StaticFunctions.nextInt();
        System.out.print("C. Diastolic blood pressure: ");
        bp_diastolic = StaticFunctions.nextInt();
    }

    public void showMenu()throws Exception{
        do{
            System.out.println("\n\t\tActions");
            System.out.println("1. Record");
            System.out.println("2. Go Back");
            System.out.print("\nEnter Choice: ");
            System.out.println("vid:"+v_id);
            int choice = StaticFunctions.nextInt();
            if (choice == 1){
                assessment();
                System.out.println("\nSuccessfully Recorded! Redirecting to Staff Menu.");
                TimeUnit.SECONDS.sleep(2);
                return;
            }
            if (choice == 2){
                return;
            }
            if (choice != 1 && choice != 2){
                System.out.println("Invalid Choice");
            }
        }while(true);
    }
    public void mainView() throws Exception{
        enterDetails();
        showMenu();

    }

    public void assessment() throws Exception{
        SQLExec db = new SQLExec();
        db.connect();
        String priority = "Normal";;
        ///////////////////////////////////////////
        //ENTER ASSESSMENT LOGIC and put Priority in priority variable
        System.out.println("Remaining - Assessment Func");
        priority = setPriority(v_id);
        ///////////////////////////////////////////

        System.out.println("Priority: "+priority);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String current_time;
        current_time = formatter.format(date);
        // current_time = "TO_DATE('"+current_time+"', 'mm/dd/yyyy hh24:mi:ss')";
        current_time = "STR_TO_DATE('"+current_time+"', '%m/%d/%Y %T')";

        String update_Checks_In = "UPDATE Checks_In SET checkin_end_time = " +current_time+", temp = "+ temperature+", bp_systolic = "+bp_systolic+", bp_diastolic = "+bp_diastolic+", priority = '"+priority+"' WHERE v_id ="+v_id+"";
        try {
            db.execCommand(update_Checks_In);
        }catch (Exception e){
            System.out.println("Error updating the database: "+e);
            return;
        }

    }

    // public int getVid()throws Exception {
    //     ResultSet rs = null;
    //     SQLExec db = new SQLExec();
    //     db.connect();
    //     String query = "Select v_id from Checks_In where p_id = "+pid+" and f_id = "+fid+" and acknowledged = 'no'";
    //     try{
    //         rs = db.execQuery(query);
    //     }
    //     catch(Exception e){
    //         System.out.println("Error retrieving data from the DB: "+e);
    //         db.terminate();
    //         return -1;
    //     }
    //     rs.next();
    //     return rs.getInt("v_id");
    // }


    public boolean isValid(String comp, String sev_val, String sev_val2) throws Exception{
        if(sev_val.matches("[0-9]+") && sev_val2.matches("[0-9]+")){
            if(comp.equals(">")){
                return Integer.parseInt(sev_val2) > Integer.parseInt(sev_val);
            }
            else if(comp.equals("=")){
                return Integer.parseInt(sev_val2) == Integer.parseInt(sev_val);
            }
            else if(comp.equals("<")){
                return Integer.parseInt(sev_val2) < Integer.parseInt(sev_val);
            }
            else if(comp.equals(">=")){
                return Integer.parseInt(sev_val2) >= Integer.parseInt(sev_val);
            }
            else if(comp.equals("<=")){
                return Integer.parseInt(sev_val2) <= Integer.parseInt(sev_val);
            }
            else if(comp.equals("!=")){
                return Integer.parseInt(sev_val2) != Integer.parseInt(sev_val);
            }
        }
        return sev_val.equalsIgnoreCase(sev_val2);
    }


    public String setPriority(int v_id)throws Exception{
        ResultSet rs, rs2 = null;
        int temp_id;
        String priority ="Normal";
        List<Integer> ar_id = new ArrayList<>();
        HashMap<Integer,Integer> ar_map = new HashMap<>();
        SQLExec db = new SQLExec();
        db.connect();
        String query = "Select s_code, b_code, sev_value from Affected_Info where v_id = "+v_id;
        
        try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
            db.terminate();
            return priority;
        }

        while(rs.next())
        {
            query = "Select *  from Assessment_Rules where s_code = '"+rs.getString("s_code")+
                            "' and b_code = '"+rs.getString("b_code") + "'";

            try{
                rs2 = db.execQuery(query);
            }
            catch(Exception e){
                System.out.println("Error retrieving data from the DB: "+e);
                db.terminate();
                return priority;
            }
            while(rs2.next())
            {
            	System.out.println("In loop");
                if(isValid(rs2.getString("comparison"), rs2.getString("severity_val"),
                            rs.getString("sev_value")))
                {

                    temp_id = rs2.getInt("ar_id");
                    //ar_id.add(rs2.getInt("ar_id"));
                    if(ar_map.containsKey(temp_id)){
                        ar_map.put(temp_id,(int)ar_map.get(temp_id)+1);
                    }
                    else{
                        ar_map.put(temp_id,1);
                    }
                }
            }
        }

            rs = null; rs2 = null;
            System.out.println("map:" + ar_map);
            if (ar_map.isEmpty())
            {
            	System.out.println("Empty map");
            	return priority;
            }
            for(int key:ar_map.keySet()){
            	System.out.println("HULLA0");
                query = "Select count(*) from Assessment_Rules where ar_id = "+key;
                
                try{
                    rs = db.execQuery(query);
                    System.out.println("HULLA");
                }
                catch(Exception e){
                    System.out.println("Error retrieving data from the DB: "+e);
                    db.terminate();
                    return priority;
                }
                rs.next();
                if(rs.getInt(1) == ar_map.get(key))
                {
                    query = "Select * from Rule_Priority where asn_id = "+key;
                    try{
                        rs2 = db.execQuery(query);
                        System.out.println("HULLA2");
                    }
                    catch(Exception e){
                        System.out.println("Error retrieving data from the DB: "+e);
                        db.terminate();
                        return priority;
                    }
                    if(rs2.next()){
                    	System.out.println("HULLA3");
                        priority = rs2.getString("priority");
                        
                    }
                }
            }
        
        return priority;

    }

    public static void main(String[] args) throws Exception
    {
    	StaffEnterVital sev = new StaffEnterVital();
        sev.v_id = 5;
        sev.mainView();
    }
}