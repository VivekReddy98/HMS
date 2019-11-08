import utilities.*;
import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class Test {
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		SQLExec CDB = new SQLExec();
		CDB.connect("vkarri", "200315262");
		
		FileReader path = new FileReader("/afs/unity.ncsu.edu/users/v/vkarri/HMS/lib/samplescript.sql");
		
		CDB.execCommandScript(path);

		String cmd = "SELECT COF_NAME, PRICE FROM COFFEES1";
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


