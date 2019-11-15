package utilities;

import java.sql.*;
import java.text.MessageFormat;
import java.util.*;
import java.io.*;
import utilities.*;

public class ReportDS {
	public String discharge_status;
	public String trmt_description;
	public int f_id = 0;
	public String e_id;
	public ArrayList<ArrayList< String>> Referral_Reasons;
	public int n_code;
	public String n_description;
	public int vid = 1;

	public ArrayList<String> Q_Ref_Reasons;
	public String Q_Ref_to;
	public String Q_trmt;
	public String Q_discharge;
	public String Q_negex;

}