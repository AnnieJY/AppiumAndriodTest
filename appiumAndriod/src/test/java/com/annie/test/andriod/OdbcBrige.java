package com.annie.test.andriod;

import java.sql.Connection;
import java.sql.DriverManager;
public class OdbcBrige {

	public static void main(String[] argv) throws Exception {
	    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    String myDB = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=c:/data.xls;"
	        + "DriverID=22;READONLY=false";
	    Connection con = DriverManager.getConnection(myDB, "", "");
	  }
	
}


 