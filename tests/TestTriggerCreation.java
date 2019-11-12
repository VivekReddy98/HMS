import utilities.*;
import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class TestTriggerCreation {
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		String userWindows = System.getenv("HMSPATH");
		FileReader path = new FileReader(userWindows + "sql/triggerAutoIncrement.sql");

		CreateTables CT = new CreateTables();

		CT.ClearTriggers();

		CT.CreateTriggers(path);
	}
}