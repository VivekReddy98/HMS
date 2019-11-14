package utilities;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*; 
import java.text.MessageFormat;
import utilities.*;

/// Usage: Just initialize this Object and call MainView() Method

public class AddRule {

    public ArrayList<RuleDS> Rules;
    public RuleDS rule = new RuleDS();
    public Hashtable<Integer,String> listSymptoms = new Hashtable<Integer,String>(); 
    public Hashtable<Integer,String> listCodes = new Hashtable<Integer,String>();
    public int numSymptoms;
    public int numParts;
    public ResultSet rs;
    public String query;
    public String output;
    public SQLExec db = new SQLExec();
    public int scale;
    public String b_code;
    public String type;
    public boolean Trigger;

    public AddRule() {
        StaticFunctions.Initialise();
    }
    public String takeSymptoms() throws SQLException {
        int i;
    	String query = "SELECT code, name FROM Symptoms";
    	db.connect();
    	rs = db.execQuery(query);
        i = 1;
        while(rs.next()) {
            if (!rs.getString("name").equals("Other")){
                listSymptoms.put(i, rs.getString("name"));
                listCodes.put(i, rs.getString("code"));
                i = i + 1;
            }
        }
        numSymptoms = i-1;
        listSymptoms.put(i, "Select Priority");
        db.terminate();

    	System.out.println("\n\t\t Symptoms");
		for (int j = 1; j<=numSymptoms; j++){
			System.out.println(MessageFormat.format("{0}. {1}", j, listSymptoms.get(j)));
		}
        System.out.println(MessageFormat.format("{0}. {1}", numSymptoms+1, "Select Priority"));
		System.out.print(MessageFormat.format("Enter Choice (1 - {0})", numSymptoms));
		int choice_smp = StaticFunctions.nextInt();
		StaticFunctions.nextLine();
        if (choice_smp < 1 || choice_smp > numSymptoms+1) {
            System.out.println("Invalid Selection !!!! ");
            takeSymptoms();
        }
        if (choice_smp == numSymptoms+1)
        {
            Trigger = true;
        }
		return listCodes.get(choice_smp);
    }

    public String takeParts(String s_code) throws SQLException {
    	query = MessageFormat.format("SELECT b_code FROM Symptoms WHERE code=''{0}''", s_code);
    	System.out.println(query);
    	db.connect();
    	rs = db.execQuery(query);
        if (rs.next()) {
			b_code = rs.getString("b_code");
        }
        Hashtable<Integer,String> listParts = new Hashtable<Integer,String>(); 
    	Hashtable<Integer,String> listBCodes = new Hashtable<Integer,String>();
        if(b_code.equals("OTH000")) {
        	query = "SELECT code, name FROM Body_Parts"; }
        else {
        	query = MessageFormat.format("SELECT code, name FROM Body_Parts WHERE code = ''{0}''", b_code); 
        } 
        System.out.println(query);
        rs = db.execQuery(query);
    	int i = 1;
    	while(rs.next()) {
            if (!rs.getString("code").equals("OTH000")){
                listParts.put(i, rs.getString("name"));
                listBCodes.put(i, rs.getString("code"));
                i = i + 1;
            }
        }
        numParts = i-1;
    	System.out.println("\n\t\t Body Parts");
		for (i = 1; i<=numParts; i++){
			System.out.println(MessageFormat.format("{0}. {1})", i, listParts.get(i)));
		}
		System.out.print(MessageFormat.format("Enter your Choice (1 - {0})", numParts));
		int choice_prt = StaticFunctions.nextInt();
		StaticFunctions.nextLine();
        if (choice_prt < 1 || choice_prt > numParts) {
            System.out.println("Invalid Selection !!!! ");
            takeParts(s_code);
        } 
        db.terminate();
		return listBCodes.get(choice_prt);
    }

    public RuleDS takeSeverity(String s_code, RuleDS rule) throws SQLException {
        query = MessageFormat.format("SELECT Severity.s_id, Severity.type FROM Severity, Symptoms WHERE Severity.s_id=Symptoms.severity_type AND Symptoms.code=''{0}''", s_code);
        System.out.println(query);
        db.connect();
        int s_id;
        rs = db.execQuery(query);
        if (rs.next()) {
            s_id = rs.getInt("s_id");
            type = rs.getString("type");
        }
        String[] levels = type.trim().split(",");
        System.out.println("\n\t\t Severity Value");
        for (int i = 1; i<=levels.length; i++){
            System.out.println(MessageFormat.format("{0}. {1})", i, levels[i-1]));
        }
        System.out.print(MessageFormat.format("Enter your Choice (1 - {0})", levels.length));
        int choice_sev_value = StaticFunctions.nextInt();
        StaticFunctions.nextLine();
        rule.s_scale.add(levels[choice_sev_value-1]);
    
        try {
            int i = Integer.parseInt(levels[choice_sev_value-1]);
             System.out.println("\n\t\t Comparison Entry");
             System.out.println("1. >");
             System.out.println("2. <");
             System.out.println("3  <=");
             System.out.println("4  >=");
             boolean entry = true;
             while (entry) {
                System.out.println("Entry your Choice (1 - 4)");
                scale = StaticFunctions.nextInt();
                StaticFunctions.nextLine();
                if (scale>=1 && scale<=4)
                {
                    entry = false;
                }
                else{
                    entry = true;
                    System.out.println("Invalid Input !!! Re-enter");
                }
             }
             if (scale == 1) 
                output = ">";
             else if (scale == 2)
                output = "<";
             else if (scale == 3)
                output = "<=";
             else if (scale == 4)
                output = ">=";
        }
        catch (Exception e)
        {
            output = "=";
        } 
        rule.comp.add(output);
        return rule;
    }

    public boolean createRule() throws Exception {
		String choice_smp = takeSymptoms();
        if (!Trigger)
        {
            rule.s_code.add(choice_smp);
            String choice_bp = takeParts(choice_smp);   
            rule.b_code.add(choice_bp);
            rule = takeSeverity(choice_smp, rule);
            return true;
        }
		else {
            boolean enter = true;
            while (enter) {
                System.out.println("\n\t\t Select the Proirity");
                System.out.println("1. High ");
                System.out.println("2. Normal ");
                System.out.println("3. Quarantine");
                System.out.print("Enter Choice (1 - 3)");
                int pri = StaticFunctions.nextInt();
                if (pri == 1) {
                    rule.priority = "High";
                    enter = false;
                }
                else if (pri == 2) {
                    rule.priority = "Normal";
                    enter = false;
                }
                else if (pri == 3) {
                    rule.priority = "Low";
                    enter = false;
                }
                else{
                    System.out.println("Invalid Entry!!!!!, Re-enter");
                }
            }
            return false;
        }

    }

	public void MainView() throws Exception {
		int choice;
		boolean dont_terminate = true;
		Rules = new ArrayList<RuleDS>();
		System.out.println("\n\t\t Menu to add a New Assessment Rules ");
		// System.out.println("1. Add a New Rule");
		// System.out.println("2. Go Back");

		while (dont_terminate) {
               dont_terminate = createRule();

			// System.out.println("Enter Choice (1-2): ");
			// choice = StaticFunctions.nextInt();
   //          StaticFunctions.nextLine();
			// if (choice == 1) {
			// 	int a = createRule();
		 //    }
	  //       else if (choice == 2) {
	  //       	dont_terminate = false;
	  //       }
	  //       else{
	  //       	System.out.println("Invalid Choice, Plese Re-enter");
	  //       }
	    }
        
        for (int j=0; j<rule.s_code.size(); j++){
            System.out.println(rule.s_code.get(j)+"|"+rule.b_code.get(j)+"|"+rule.s_scale.get(j)+"|"+rule.comp.get(j));               
        }
        System.out.println(rule.priority);

        
	}
 	public static void main(String[] args) throws Exception {
        AddRule AR = new AddRule();
        AR.MainView();
    }
}

// Class Representing a tuple in Rule Class
class RuleDS {	
	public ArrayList<String> s_code = new ArrayList<String>();
	public ArrayList<String> b_code = new ArrayList<String>(); 
	public ArrayList<String> s_scale = new ArrayList<String>(); 
	public ArrayList<String> comp = new ArrayList<String>();
    public String priority;
}