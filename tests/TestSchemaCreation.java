import utilities.*;
import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class TestSchemaCreation {
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		CreateTables CT = new CreateTables();
		while (2>1){
			System.out.println("What to do?? \n" + 
								"0. Just Get the Fuck out of this program \n" +
				                "1. Create Fresh Tables \n" + 
				                "2. Create AutoIncrement Triggers \n");
			String pref = System.console().readLine(); 

			if (pref.equals("1"))
			{
				CT.FreshTables();
			}
			else if (pref.equals("2"))
			{
				CT.FreshIncrementTriggers();
			}
			else if (pref.equals("0"))
			{
				break;
			}
			else{
				continue;
			}
		    
		}
		
	}

}