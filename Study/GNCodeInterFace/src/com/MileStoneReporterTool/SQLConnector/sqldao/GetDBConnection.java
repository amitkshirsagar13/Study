/*
 * GetDBConnection.java
 *
 * Created on November 20, 2007, 11:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.SQLConnector.sqldao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author AmitC_Kshirsagar
 */
public class GetDBConnection {

	/** Creates a new instance of GetDBConnection */
	public GetDBConnection() {
	}

	public Connection getDBConnection() {
		Connection connection = null;
		try {
			/*
			 * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //
			 * DriverManager.registerDriver (new
			 * oracle.jdbc.driver.OracleDriver());
			 * Class.forName("oracle.jdbc.driver.OracleDriver"); String dbURL =
			 * new String
			 * ("jdbc:oracle:thin:@//tpdb3551:1521/SMI.pcltest.lexisnexis.com")
			 * ; String username = "oracle"; String password = "oracleuser";
			 * connection = DriverManager.getConnection(dbURL, username,
			 * password);
			 */
			String userName = "gncode_user";
			String password = "gncode_user_t";
			String url = "jdbc:oracle:thin:@//tpdb3551:1521/SMI.pcltest.lexisnexis.com";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, userName, password);
			// connection = DriverManager.getConnection (url);
		} catch (SQLException e) {
			System.out
					.println("GetDBConnection.getDBConnection : Error opening the db connection: "
							+ e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out
					.println("GetDBConnection.getDBConnection : Error opening the db connection: "
							+ e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	public static void main(String[] args) {
		GetDBConnection getDB = new GetDBConnection();
		Connection conn = getDB.getDBConnection();
	}
}
