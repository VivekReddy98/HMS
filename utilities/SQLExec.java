package utilities;

import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class SQLExec extends ConnectDB{

	public void connect(String user, String pwd) {
		try {			
			super.conn = DriverManager.getConnection(super.jdbcURL, user, pwd);
		}
		catch (Exception e) {
			System.out.println(e);
		}
    }
    // Only pass command queries using this method, this wont return anything if you run a query.
    public void execCommandScript(FileReader path) throws SQLException {

    	ScriptRunner sr = new ScriptRunner(super.conn);

    	//Creating a reader object
      	Reader reader = new BufferedReader(path);

      	// Run the Script
      	sr.runScript(reader);
    }
}


// //Initialize the script runner
// ScriptRunner sr = new ScriptRunner(con);
// //Creating a reader object
// Reader reader = new BufferedReader(new FileReader("E:\\sampleScript.sql"));
// //Running the script
// sr.runScript(reader);