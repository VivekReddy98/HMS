package utilities;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.io.*;
import utilities.*;
import java.util.Map.Entry;
import java.util.Set;

public class ReferralReason {


    public String query = "";
    public SQLExec db = new SQLExec();
    public int choice = 0;
    public Hashtable<Integer,String> hashReasons = new Hashtable<Integer,String>();
    public Hashtable<String,String> hashService = new Hashtable<String,String>();
    public int count_records = 0;
    public ArrayList<String> choices;
    public ResultSet rs;
    public int t = 0;

    public ReferralReason() throws Exception {
        StaticFunctions.Initialise();
    }

    public Integer getReason() throws Exception { 
        System.out.println("\n\t\tMenu to enter the code for Referral Reason");
        Set<Entry<Integer, String>> entrySet = hashReasons.entrySet();

        for(Entry<Integer, String> entry1 : entrySet) {
            System.out.println("Reason ID " + String.valueOf(entry1.getKey()) + " -> Description: " + entry1.getValue());
        }

        System.out.println("Enter Your Choice: ");

        Integer reason = StaticFunctions.nextInt();
        if (!hashReasons.containsKey(reason)){
            System.out.println(" Invalied Entry, Enter the Reason ID's specified above ");
            return getReason();
        }   
        return reason;  
    }

    public String getService() throws Exception {
        String service;
        

        Set<Entry<String, String>> entrySet = hashService.entrySet();
        if(!(t == 0)){
            System.out.println("\n\t\tMenu to enter the code for a Service");
            for(Entry<String, String> entry1 : entrySet) {
                System.out.println("Code: " + entry1.getKey() + " -> Name: " + entry1.getValue());
            }
        System.out.println("Enter Your Choice: ");
        }
        
        

        service = StaticFunctions.nextLine();
    
        if (!hashService.containsKey(service)){
            if(!(t == 0)){
                System.out.println(" Invalied Entry!!!, Enter the code exactly as shown above");
            }
            t += 1;
            
            return getService();
        }
        return service;
    }

    public void MainView(ReportDS r_obj) throws Exception{

        String service, description;
        int reason;

        query = "SELECT r_id, description FROM Reasons";
        db.connect();
        rs = db.execQuery(query);
        while(rs.next()) {
            hashReasons.put(rs.getInt("r_id"), rs.getString("description"));
        }
        
        query = "SELECT code, name FROM Services";
        rs = db.execQuery(query);
        while(rs.next()) {
            hashService.put(rs.getString("code"), rs.getString("name"));
        }
        
        db.terminate();

        while (true) {
            System.out.println("\n\t\t Menu to add a Referral Reason");
            System.out.println("1. Add a new Reason");
            System.out.println("2. Go Back");
            System.out.print("Enter Choice (1-2): ");
            choice = StaticFunctions.nextInt();
            StaticFunctions.nextLine();

            count_records = r_obj.Q_Ref_Reasons.size();

            if (count_records >= 4) {
                System.out.println("You have reached the the limit of number of reasons you could enter");
                System.out.println("Exiting !!!!!!!!!!!!!!!");
                break;
            }
            
            if (choice == 1) {
                choices = new ArrayList<String>();
                reason = getReason();
                service = getService();
                System.out.println("\n\t\t Enter a Descrition for this Reason!!!");
                description = StaticFunctions.nextLine();

                choices.add(String.valueOf(reason)); 
                choices.add(service); 
                choices.add(description);

                r_obj.Referral_Reasons.add(choices);

                query = MessageFormat.format("INSERT INTO Referral_Reason VALUES ({0}, {1}, ''{2}'', ''{3}'')", r_obj.vid, reason, service, description);
                r_obj.Q_Ref_Reasons.add(query);

            } else if (choice == 2) {
                System.out.println("Going Back !!!!!!!!");
                break;
            } else {
                System.out.println("Invalid input!!! Please re-enter");
                continue;
            }        
        }

    }

    public static void main(String[] args) throws Exception
    {
        ReferralReason spr = new ReferralReason();
        ReportDS r_ob = new ReportDS();
        r_ob.vid = 1;
        spr.MainView(r_ob);
        int i;
        for(i=0; i<r_ob.Q_Ref_Reasons.size(); i++) {
            System.out.println(r_ob.Q_Ref_Reasons.get(i));
        }
    }
}
