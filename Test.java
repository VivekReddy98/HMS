import utilities.ConnectDB;
import java.sql.*;

public class Test {
	public static void main(String[] args) throws SQLException {
		ConnectDB CDB = new ConnectDB();
	}
}


// CDB.connect("vkarri@orcl", "200315262");
// 		String cmd = "SELECT COF_NAME, PRICE FROM COFFEES1";
// 		ResultSet rs = CDB.execQuery(cmd);
// 		CDB.terminate();