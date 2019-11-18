package utilities;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class ReportDS {
	public String discharge_status = new String();
	public String trmt_description = new String();
	public int f_id = 0 ;
	public int vid = 0;
	public String e_id = new String();
	public ArrayList<ArrayList< String>> Referral_Reasons = new ArrayList<ArrayList< String>>();
	public int n_code = 0;
	public String n_description = new String();

	public ArrayList<String> Q_Ref_Reasons = new ArrayList<String>();
	public String Q_Ref_to = new String();
	public String Q_trmt = new String();
	public String Q_discharge = new String();
	public String Q_negex = "Nothing";

}