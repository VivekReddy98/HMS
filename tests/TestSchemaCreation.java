package tests;

import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class TestSchemaCreation {
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		CreateTables CT = new CreateTables();
		while (2>1){
			System.out.println("What to do?? \n" + 
								"0. Just Get out of this program \n" +
				                "1. Reset Schema \n" + 
				                "2. Populate Data \n");
			String pref = System.console().readLine(); 

			if (pref.equals("1"))
			{
				CT.newTables();
			}
			else if (pref.equals("2"))
			{
				CT.populate();
				break;
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