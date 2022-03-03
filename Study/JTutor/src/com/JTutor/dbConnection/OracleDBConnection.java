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
			String dbUrl = "jdbc:oracle:thin:@ldap://xxxxxx:389/xxxxx,cn=OracleContext,dc=xxxxxxxx,dc=xxx";
			String username = "xxxxx";
			String password = "xxxxx";
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
		String dbUrl = "jdbc:oracle:thin:@ldap://xxxxxxx:389/xxxxxx,cn=OracleContext,dc=ccccccc,dc=io";
		String query = "Select * FROM users";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String username = "xxxxxxx";
			String password = "xxxxxxxxx";
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
