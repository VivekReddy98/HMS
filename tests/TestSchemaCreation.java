import utilities.*;
import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.*;

public class TestSchemaCreation {
	public static void main(String[] args) throws SQLException, FileNotFoundException {

		FileReader path = new FileReader("/afs/unity.ncsu.edu/users/v/vkarri/HMS/sql/createSchema.sql");

		CreateTables CT = new CreateTables();

		CT.ClearDB();

		CT.CreateTables(path);
	}
}