package utilities;

import java.sql.*;
import java.io.*;
import utilities.*;

public class CreateTables{

    public SQLExec sqlexec;

	public CreateTables()
	{
		sqlexec = new SQLExec();
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
	}

	public void CreateTables(FileReader path) throws SQLException
	{
		sqlexec.execCommandScript(path);
	}

}