import utilities.ConnectDB;
import java.sql.*;

public class Test {
	public static void main(String[] args) throws SQLException {
		ConnectDB CDB = new ConnectDB();
		CDB.connect("vkarri", "200315262");
		String cmd;
		// cmd = "CREATE TABLE COFFEES1 " +
		// 	   "(COF_NAME VARCHAR(32), SUP_ID INTEGER, " +
		// 	   "PRICE FLOAT, SALES INTEGER, TOTAL INTEGER)";
		// CDB.execCommand(cmd);

		// cmd = "INSERT INTO COFFEES1 " +  "VALUES ('Colombian', 101, 7.99, 0, 0)";
		// CDB.execCommand(cmd);

		// cmd = "INSERT INTO COFFEES1 " +  "VALUES ('French_Roast', 49, 8.99, 0, 0)";	   
		// CDB.execCommand(cmd);

		// cmd = "INSERT INTO COFFEES1 " +  "VALUES ('Colombian_Decaf', 101, 8.99, 0, 0)";
		// CDB.execCommand(cmd);

		cmd = "SELECT COF_NAME, PRICE FROM COFFEES1";
		ResultSet rs = CDB.execQuery(cmd);

		while (rs.next()) {
		    String s = rs.getString("COF_NAME");
		    float n = rs.getFloat("PRICE");
		    System.out.println(s + "   " + n);
		}

		rs.close();

		CDB.terminate();
	}
}


