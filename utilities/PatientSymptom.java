package utilities;
import java.sql.*;
import java.util.*;
import java.io.*;
import utilities.*;

public class PatientSymptom{
	public PatientSymptom() {

        StaticFunctions.Initialise();
    }

    public String bodyPartMenu(String symCode) throws Exception{

    	String query = "Select * from Body_Parts b, Symptoms s where b.code =  s.b_code and s.code = '" + symCode + "'";

    	SQLExec db = new SQLExec();
    	db.connect();
        ResultSet rs = db.execQuery(query);

        int choice;
        ArrayList<String> bparts = new ArrayList<String>();
        int i = 0;

    	try {
            while(rs.next()) {
            	//-------------If body part implicit------------------------
            	if(!rs.getString("name").equals("Nothing Specific")){
            		System.out.println("Body part for this symptom is:" + rs.getString("name"));
            		StaticFunctions.nextLine();
            		return rs.getString("code");
            	}
            	//-------------If body part implicit(end)------------------------

            	//------------If user entering body part----------------------

            	else{


            		query = "Select * from Body_Parts";
            		ResultSet rs1 = db.execQuery(query);
            		try{
	            		while(rs1.next() && !rs1.getString("name").equals("Nothing Specific")){
	            			++i;
	            			System.out.println(Integer.toString(i) + ". " + rs1.getString("name"));
	            			bparts.add(rs1.getString("code"));
	            		}

	            	}
	            	catch (Exception e) {
	            		System.out.println("Unable to fetch all body parts");
	            	}


	            	do{

		            	System.out.println("Enter number corresponding to body part:");
		            	choice = StaticFunctions.nextInt();
		            	StaticFunctions.nextLine();
		            	if(choice < 1 || choice > i) {
	                		System.out.println("Invalid Choice");
	            		}
	            		else{
	            			return bparts.get(choice - 1);
	            		}

	            	}while(choice < 1 || choice > i);


            	}

            	//-------------------If user entering body part(end)------------------------------------
            }
        }


        catch (Exception e) {
            System.out.println("Unable to fetch corresponding body part record");
        }
    	return "Default";
    }


    public String severityMenu(String symCode) throws Exception{

    	String query = "Select sev.type from Severity sev, Symptoms s where sev.s_id = s.severity_type and s.code = '" + symCode + "'";

    	SQLExec db = new SQLExec();
    	db.connect();
        ResultSet rs = db.execQuery(query);
        
        String sevType = "";
        int sevValue ;
        int i = 0;

        try {
            while(rs.next()) {
                //System.out.println(""+rs.getString("type"));
                sevType = rs.getString("type");
            }
        }
        catch (Exception e) {
            System.out.println("Unable to fetch records");
        }
        //sevType = "High, Low";
        String[] levels = sevType.split(",");

        System.out.println("Enter the severity level:");

        for(String level : levels){
            ++i;
            System.out.println(Integer.toString(i) + ")\t"  + level);
        }

        sevValue = StaticFunctions.nextInt();
        

        // switch(sevType){                                        //Add more cases
        // 	case "1-10":
        // 		do{
        // 			System.out.println("Enter Severity value 1-10");
        // 			sevValue = StaticFunctions.nextInt();
        // 			StaticFunctions.nextLine();

        // 			if (sevValue<1 || sevValue > 10){
        // 				System.out.println("Invalid Choice");
        // 			}

        // 		}while(sevValue<1 || sevValue > 10);

        // 		break;

        // 	case "Low/High":
	       //  	do{
	       //  		System.out.println("Select one: \n1.Heavy\n2.Light:");
	       //  		sevValue = StaticFunctions.nextInt();
	       //  		StaticFunctions.nextLine();
	       //  	}while(sevValue<1 || sevValue > 2);

	       //  	break;

	       //  default:
	       //  	sevValue = 1;
        // };

    	return levels[--sevValue];
    }

    public void insertIntoAffected(String vid, String symCode, String bodyPartCode, String duration, String isFirst, String severityValue, String incident) throws Exception{
    	String query = "Insert into Affected_Info (v_id, s_code, b_code, duration, is_first, incident, sev_value ) values (" + vid + 
        ",'" + symCode + "', '" + bodyPartCode + "', " + duration + ", '" + isFirst + "', '" + incident + "', '" + severityValue + "')";
        //System.out.println(""+query);

        SQLExec db = new SQLExec();
        db.connect();

        try{
                db.execCommand(query);
        }
        
        catch (Exception e) {

                System.out.println("Could not insert data into the DB: "+e);
        }

    }

    public void MainView(String symCode) throws Exception{

    	int choice;
    	int flag1, flag2, flag3, flag4, flag5;
    	flag1 = flag2 = flag3 = flag4 = flag5 = 0;
    	String bodyPartCode = "";
    	float duration = 0;
    	Boolean isFirst = true;
    	String severityValue = "";
    	String incident = "";
    	do{
	        System.out.println("\n\t\tEnter metadata for selected symptom");
	        System.out.println("1. Body Part");
	        System.out.println("2. Duration");
	        System.out.println("3. Reoccurring?");
	        System.out.println("4. Severity");
	        System.out.println("5. Cause(Incident)");
	        System.out.println("6. Go back");

	        do{
	            System.out.print("Enter Choice (1-6): ");
	            choice = StaticFunctions.nextInt();
	            StaticFunctions.nextLine();
	            if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6)
	            {
	                System.out.println("Invalid Choice, Please Try again.");
	            }
	        }while(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6);


	        switch(choice) {

	            case 1:
	                bodyPartCode = bodyPartMenu(symCode);
	                flag1 = 1;
	                break;
	            case 2:
	            	System.out.println("Enter number of days since symptoms showed up.");
	            	duration = StaticFunctions.nextFloat();
	            	flag2 = 1;
	            	break;
	            case 3:
	            	System.out.println("Is it a reoccurring symptom? (Enter 1 if yes, 2 if no)");
	            	isFirst = StaticFunctions.nextInt() == 1;
	            	flag3 = 1;
	            	break;
	            case 4:
	            	severityValue = severityMenu(symCode);
	            	flag4 = 1;
	            	break;
	            case 5:
	            	System.out.println("Did the symptom show up after a particular incident?\nDescribe it or enter NA");
	            	incident = StaticFunctions.nextLine();
	            	flag5 = 1;
	            	break;
	            case 6:
	            	if(flag1*flag2*flag3*flag4*flag5 == 0){
	        			System.out.println("\n\n\t\tPlease finish entering all the metadata!!");
	        		}
	        		else{
	        			//System.out.println("" + bodyPart + "" + duration + "" + isFirst + "" + severityValue + "" + incident);
	        			insertIntoAffected("1", symCode, bodyPartCode, String.valueOf(duration), String.valueOf(isFirst), String.valueOf(severityValue), incident);
                        return;
	        		}
	        		break;
	        };
	    }while(true);
    }

    public static void main(String[] args) throws Exception
    {
        PatientSymptom ps = new PatientSymptom();
        ps.MainView("SYM001");
        //ps.insertIntoAffected("1", "SYM001", "ARM000", "3.0", "true", "5", "Incident");
        //System.out.println(""+ps.severityMenu("SYM002"));
        System.out.println("Back to prev screen");
    }

}