package utilities;

import java.sql.*;
import java.io.*;
import utilities.*;

public class CreateTables {

    public SQLExec sqlexec;

	public CreateTables()
	{
		sqlexec = new SQLExec();
		sqlexec.connect();
	}

	public void ClearDB()
	{	
		sqlexec.connect();
		String query = "begin " + 
					  "for i in (select * from tabs) loop " + 
					  "execute immediate ('drop table ' || i.table_name || ' cascade constraints'); " + 
					  "end loop; " + 
					  "end;" ;
		System.out.println(query);
		try {
			sqlexec.execCommand(query);
		}
		catch (Exception e){
			System.out.println(e);
		}
		sqlexec.terminate();
	}

	public void ClearTriggers() throws SQLException, FileNotFoundException
	{
		sqlexec.connect();
		String userWindows = System.getenv("HMSPATH");
		FileReader pathTrigers = new FileReader(userWindows + "sql/removeTriggers.sql");
		sqlexec.execCommandScript(pathTrigers);
		sqlexec.terminate();
	}

	public void CreateTables(FileReader path) throws SQLException
	{
		sqlexec.connect();
		sqlexec.execCommandScript(path);
		sqlexec.terminate();
	}

	public void CreateTriggers(FileReader path) throws SQLException
	{
		sqlexec.connect();
		sqlexec.execCommandScript(path, ">");
		sqlexec.terminate();
	}

	public void FreshTables() throws FileNotFoundException, SQLException
	{
		String userWindows = System.getenv("HMSPATH");
		FileReader pathSchema = new FileReader(userWindows + "sql/createSchema.sql");
		CreateTables CT = new CreateTables();
		this.ClearDB();
		this.CreateTables(pathSchema);
		FileReader pathData = new FileReader(userWindows + "sql/populateData.sql");
		this.CreateTables(pathData);
	}

	public void FreshIncrementTriggers() throws FileNotFoundException, SQLException
	{
		String userWindows = System.getenv("HMSPATH");
		this.ClearTriggers();
		FileReader pathTriggers = new FileReader(userWindows + "sql/triggerAutoIncrement.sql");
		this.CreateTriggers(pathTriggers);
		FileReader pathData = new FileReader(userWindows + "sql/staffDataPopulated.sql");
		this.CreateTables(pathData);
		FileReader pathmiscTriggers = new FileReader(userWindows + "sql/miscTriggers.sql");
		this.CreateTables(pathmiscTriggers);
	}
}