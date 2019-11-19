package tests;

import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class SQLExec extends Connect{

    // Only pass command queries using this method, this wont return anything if you run a query.
    public void execCommandScript(FileReader path) throws SQLException {

    	ScriptRunner sr = new ScriptRunner(super.conn);

    	//Creating a reader object
      	Reader reader = new BufferedReader(path);

      	// Run the Script
  		sr.runScript(reader);
    }

    public void execCommandScript(FileReader path, String delimitter) throws SQLException {

    	ScriptRunner sr = new ScriptRunner(super.conn);

    	sr.setDelimiter(delimitter);

    	//Creating a reader object
      	Reader reader = new BufferedReader(path);

      	// Run the Script
  		sr.runScript(reader);	
    }

}


