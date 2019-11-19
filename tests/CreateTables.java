package tests;

import java.sql.*;
import java.io.*;

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

	public void newTables() throws FileNotFoundException, SQLException
	{
		String userWindows = System.getenv("HMSPATH");
		FileReader pathSchema = new FileReader(userWindows + "sql/createSchema.sql");
		FileReader pathDrop = new FileReader(userWindows + "sql/removeTables.sql");
		FileReader pathData = new FileReader(userWindows + "sql/staffDataPopulated.sql");

		CreateTables CT = new CreateTables();
		
		this.CreateTables(pathDrop);
		this.CreateTables(pathSchema);
		//this.CreateTables(pathData);
	}

	public void populate() throws FileNotFoundException, SQLException
	{
		String userWindows = System.getenv("HMSPATH");
		//this.ClearTriggers();
		//FileReader pathTriggers = new FileReader(userWindows + "sql/triggerAutoIncrement.sql");
		//this.CreateTriggers(pathTriggers);
		FileReader pathmiscTriggers = new FileReader(userWindows + "sql/populateData.sql");
		this.CreateTables(pathmiscTriggers);
	}
}