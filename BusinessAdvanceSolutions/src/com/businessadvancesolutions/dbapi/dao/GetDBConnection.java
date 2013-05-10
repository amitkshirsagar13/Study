/*
 * GetDBConnection.java
 *
 * Created on November 20, 2007, 11:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
package com.businessadvancesolutions.dbapi.dao;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.businessadvancesolutions.helper.SystemLogger;

public class GetDBConnection {

	private static Connection _connection = null;
	private static GetDBConnection _dbConnection = null;

	/** Creates a new instance of GetDBConnection */
	private GetDBConnection() {
	}

	public static Connection getDatabaseConnection() {
		if (_dbConnection == null) {
			_dbConnection = new GetDBConnection();
			_dbConnection.createDatabaseConnection();
		}
		return _connection;
	}

	private void createDatabaseConnection() {

		try {
			String userName = "root";
			String password = "amogh";
			String url = "jdbc:mysql://localhost:3306/BusinessAdvanceDatabase";
			Class.forName("com.mysql.jdbc.Driver");
			_connection = DriverManager.getConnection(url, userName, password);
			// connection = DriverManager.getConnection (url);
		} catch (SQLException e) {
			SystemLogger.logError(
					"GetDBConnection.getDBConnection : Error opening the db connection: "
							+ e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			SystemLogger.logError(
					"GetDBConnection.getDBConnection : Error opening the db connection: "
							+ e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		Connection conn = GetDBConnection.getDatabaseConnection();
	}
}
