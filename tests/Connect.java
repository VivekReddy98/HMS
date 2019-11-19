package tests;

import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
// https://www.geeksforgeeks.org/establishing-jdbc-connection-in-java/ (Resources to understand JDBC Connectivity)
public class Connect{
 
protected static final String jdbcURL = "jdbc:mysql://localhost:3306/HMS?useSSL=false";
//jdbc:oracle:thin:@
protected static Connection conn = null;
    protected static Statement stmt = null;
    public String user = "root";
    public String pwd = "vivek1234";

    public Connect()
    {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        }
    catch (Exception exp) {
        //System.out.println("Sorry!!! Unable to load Oracle Driver");
        //exp.printStackTrace();
    }
    }

   public void connect(){
    try {
        conn = DriverManager.getConnection(jdbcURL, user, pwd);
        //System.out.println("Connection Successful!!!!!!!!!!!!!!!!!!");
    }
    catch (Exception e) {
        System.out.println(e);
    }
    // try{
    //     String query = "alter session set NLS_COMP=ANSI";
    //     execCommand(query);
    //     query = "alter session set NLS_SORT=BINARY_CI";
    //     execCommand(query);
    // }
    // catch (Exception e){
    //     System.out.println("Error setting up the DB");
    // }
    try{
        String query = "USE HMS";
        execCommand(query);
    }
    catch (Exception e){
        System.out.println("Error setting up the DB");
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
