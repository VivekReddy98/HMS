package utilities;

import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class SQLExec extends ConnectDB{

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

    public void execTransaction(ReportDS ds) throws SQLException {
      con.setAutoCommit(false);
      try {
        db.execCommand(ds.Q_trmt);
        db.execCommand(ds.Q_discharge);
        db.execCommand(ds.Q_negex);
        db.execCommand(ds.Q_Ref_to);
        int i;
        for(i=0; i<ds.Q_Ref_Reasons.size(); i++) {
            db.execCommand(ds.Q_Ref_Reasons.get(i));
        }
        con.commit();
      } catch (Exception e)
      {
        try {
          con.rollback();
          System.out.println("FUcked Up!!!!!!!!!!!!!!");
        } catch (Exception e)
        {
          System.out.println("FUcked Up Again!!!!!!!!!!!!!!" + e);
          
        }
      con.setAutoCommit(true);
      }
      }
}


