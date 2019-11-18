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
      
      // System.out.println(ds.Q_trmt);
      // System.out.println(ds.Q_discharge);
      // System.out.println(ds.discharge_status);
      // System.out.println(ds.Q_negex);

      super.conn.setAutoCommit(false);
      try {
        this.execCommand(ds.Q_trmt);
        this.execCommand(ds.Q_discharge);
        if (ds.discharge_status.equals("Referred")) {

      
            this.execCommand(ds.Q_Ref_to);
            int i;
            for(i=0; i<ds.Q_Ref_Reasons.size(); i++) {
              this.execCommand(ds.Q_Ref_Reasons.get(i));
            }
        }

        if (!ds.Q_negex.equals("Nothing")) {         
          this.execCommand(ds.Q_negex);
        }
        super.conn.commit();
      } catch (Exception f)
      {
        try {
          super.conn.rollback();
          System.out.println(f);
        } 
        catch (Exception e)
        {
          System.out.println("Fucked Up Again!!!!!!!!!!!!!!" + e);
        }
      }
      super.conn.setAutoCommit(true);
      return;
      }

}


