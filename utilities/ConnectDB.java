package utilities;

import java.sql.*;

// https://www.geeksforgeeks.org/establishing-jdbc-connection-in-java/ (Resources to understand JDBC Connectivity)
public class ConnectDB {
 
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	public static Connection conn = null;

    public ConnectDB(String user, String pwd)
    {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception exp) {
			System.out.println("Sorry!!! Unable to load Oracle Driver");
			exp.printStackTrace();
		}
    	this.user = user
    	this.pwd = pwd
    }

    public static Connection connect() {
		try {			
			conn = DriverManager.getConnection(jdbcURL, this.user, this.password);
			return conn;
		}
		catch (Exception e) {
			System.out.println("Sorry!!! unable to connect to the oracle server");
		}
    }
}
    // // Ask any OODD Student for the difference between a Query and a Command.
    // // ResultSet in the Java abstraction of records recieved from the Database.
    // public static ResultSet executeQuery(String cmd) {

    // 	if (conn == null)
    // 	{
    // 		throw new SQLException("Please open a connection to execute a Query");
    // 	}
    // 	stmt = conn.createStatement();
    // 	stmt.

    // }

    // public static void executeCommand(String cmd) {

    // 

