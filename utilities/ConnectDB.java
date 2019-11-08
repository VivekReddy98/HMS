package utilities;

import java.sql.*;

// https://www.geeksforgeeks.org/establishing-jdbc-connection-in-java/ (Resources to understand JDBC Connectivity)
public class ConnectDB {
 
	protected static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	protected static Connection conn = null;
    protected static Statement stmt = null;
    public String user = "svshingt";
    public String pwd = "200312579";

    public ConnectDB()
    {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception exp) {
			System.out.println("Sorry!!! Unable to load Oracle Driver");
			exp.printStackTrace();
		}
    }

    public void connect() {
		try {			
			conn = DriverManager.getConnection(jdbcURL, user, pwd);
		}
		catch (Exception e) {
			System.out.println(e);
		}
    }
    // Ask any OODD Student for the difference between a Query and a Command.
    // ResultSet in the Java abstraction of records recieved from the Database.
    public ResultSet execQuery(String cmd) throws SQLException {
     if (conn == null)
     {
         System.out.println("Please open a connection to execute a Query");
     }
     stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(cmd);
     return rs;
     }

    public void execCommand(String cmd) throws SQLException {
     if (conn == null)
     {
         throw new SQLException("Please open a connection to execute a Query");
     }
     stmt = conn.createStatement();
     stmt.executeUpdate(cmd);
     stmt.close();
    }

    public void terminate() {

        if (stmt != null)
        {
             try {
                stmt.close();
            }
            catch (Exception e) {
                System.out.println("DB: Unable to close the statement");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception e) {
                System.out.println("DB: Unable to close the connection");
            }
        }
    }
}


