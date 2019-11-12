package utilities;
import java.sql.*;
import java.text.SimpleDateFormat;
public class PatientSymptom{
	public PatientSymptom() {

        StaticFunctions.Initialise();
    }

    public String bodyPartMenu(String symCode){
    	String query = "Select b.name from Body_Parts b, Symptoms s where b.code =  s.b_code and s.code = '" + symCode + "'";
    	
    	//System.out.println(query);

    	return "B1";
    }

    public int severityMenu(String symCode){
    	return 10;
    }

    public void MainView(String symCode){
    	int choice;
    	int flag1, flag2, flag3, flag4, flag5;
    	flag1 = flag2 = flag3 = flag4 = flag5 = 0;
    	String bodyPart = "";
    	float duration = 0;
    	Boolean isFirst = true;
    	int severityValue = 10;
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
	                bodyPart = bodyPartMenu(symCode);
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
	        			System.out.println("Please finish entering all the metadata");
	        		}
	        		else{
	        			//return bodyPart, String.valueOf(duration), String.valueOf(isFirst), String.valueOf(severityValue), incident
	        			System.out.println("" + bodyPart + "" + duration + "" + isFirst + "" + severityValue + "" + incident);
	        			break;
	        		}
	        		break;
	        };
	    }while(true);
    }

    public static void main(String[] args) throws Exception
    {
        PatientSymptom ps = new PatientSymptom();
        ps.MainView("sym01");
    }

}