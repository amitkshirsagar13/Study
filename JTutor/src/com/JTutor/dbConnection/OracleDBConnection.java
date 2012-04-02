package com.JTutor.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.JTutor.dao.UserDAO;

public class OracleDBConnection {

	private static Logger _log = null;

	private OracleDBConnection() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(OracleDBConnection.class);
		}
	}

	private static OracleDBConnection oracleDBConnection = null;
	private static Connection dbConnection = null;

	public static Connection getDBConnection() {
		if (dbConnection != null) {
			return dbConnection;
		}
		if (oracleDBConnection == null) {
			oracleDBConnection = new OracleDBConnection();
		}
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbUrl = "jdbc:oracle:thin:@ldap://cporaldap:389/CPCDBII,cn=OracleContext,dc=lexisnexis,dc=com";
			String username = "kshirsac";
			String password = "end8world";
			dbConnection = DriverManager.getConnection(dbUrl, username,
					password);

		} catch (ClassNotFoundException e) {
			_log.error("Error while connection", e);
		} catch (SQLException e) {
			_log.error("Error while connection", e);
		}
		initDAO();
		return dbConnection;
	}

	public static void initDAO() {
		UserDAO.initiateUserDAO();
	}

	public static void main(String[] args) {
		String dbtime;
		String dbUrl = "jdbc:oracle:thin:@ldap://cporaldap:389/CPCDBII,cn=OracleContext,dc=lexisnexis,dc=com";
		String query = "Select * FROM users";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String username = "kshirsac";
			String password = "end8world";
			Connection con = DriverManager.getConnection(dbUrl, username,
					password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				dbtime = rs.getString(2);
				System.out.println(dbtime);
			} // end while

			con.close();
		} // end try

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
