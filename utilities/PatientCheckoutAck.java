
package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.text.MessageFormat;
import java.util.Date;

public class PatientCheckoutAck{
    private String eid;
    private String dis_status;
    private String l_name, dob, city;
    public String query;
    public ResultSet rs;
    public SQLExec db = new SQLExec();
    public ReportDS r_obj = new ReportDS();
    public ArrayList<String> choices;

    public PatientCheckoutAck() {

    }

    public void MainView(int fid, int pid) throws Exception{

   		query = MessageFormat.format("Select * from Checks_In where p_id = {0} and f_id = {1} and acknowledged = ''no''", String.valueOf(pid), String.valueOf(fid));
   		System.out.println(query);
   		System.out.println(fid);
   		db.connect();
   		try{
            rs = db.execQuery(query);
        }
        catch(Exception e){
            System.out.println("Error retrieving data from the DB: "+e);
        }
        if(rs.next()){
        	r_obj.vid = rs.getInt("v_id");
        	r_obj.discharge_status = rs.getString("dis_status");
        	r_obj.trmt_description  = rs.getString("trmt_description");
        	r_obj.n_code = rs.getInt("neg_code");
        	r_obj.n_description = rs.getString("neg_exp");

        }
        else{
        	System.out.println("There are no unacknowledged reports for you, sorry");
        	db.terminate();
        	return;
        }

        if(r_obj.discharge_status.equals("Referred")){
        	query = MessageFormat.format("Select f_id, e_id from Referred_to where v_id = {0}", r_obj.vid);
        	try{
            	rs = db.execQuery(query);
        	}
        	catch(Exception e){
           		System.out.println("Error retrieving data from the DB: "+e);
        	}

        	if(rs.next()){
        		r_obj.f_id = rs.getInt("f_id");
        		r_obj.e_id = rs.getString("e_id");
        	}

        	query = MessageFormat.format("Select rf.r_id, rf.description, S.name from Referral_Reason rf, Services S where rf.v_id = {0} and rf.s_code = S.code", r_obj.vid);
        	try{
            	rs = db.execQuery(query);
        	}
        	catch(Exception e){
           		System.out.println("Error retrieving data from the DB: "+e);
        	}

        	while(rs.next()){
        		choices = new ArrayList<String>();

                choices.add(String.valueOf(rs.getInt("r_id"))); 
                choices.add(rs.getString("name")); 
                choices.add(rs.getString("description"));

                r_obj.Referral_Reasons.add(choices);
        	}
        }
        int i = 0;
        System.out.println("------------------------------------------------Report--------------------------------");
        System.out.println("Discharge Status:\t" + r_obj.discharge_status);

        if(r_obj.discharge_status.equals("Referred")){
        	System.out.println("Referral Status:\n\tFacility Id is: " + r_obj.f_id + "\n\tReferrer Id is: " + r_obj.e_id + "\n\tReasons are:[Reason Code, Service Code, Description]" );
	        for(i=0; i<r_obj.Q_Ref_Reasons.size(); i++) {
	             System.out.println(r_obj.Referral_Reasons.get(i));
	        }

        }
        
        System.out.println("Treatment given:\t" + r_obj.trmt_description);
        System.out.println("Negative Experience Code\t:" + r_obj.n_code);
        System.out.println("Negative Experience description\t:" + r_obj.n_description);
        System.out.println("--------------------------------------------------------------------------------------");

        db.terminate();
    }

    public static void main(String[] args) throws Exception
    {

    }
}