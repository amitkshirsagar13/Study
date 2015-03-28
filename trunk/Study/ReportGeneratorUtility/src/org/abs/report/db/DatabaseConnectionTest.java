package org.abs.report.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.abs.report.util.ReportConfigUtil;
import org.apache.log4j.Logger;

/**
 * public class DatabaseConnectionTest {
 * 
 * }
 */

public class DatabaseConnectionTest {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger
			.getLogger(DatabaseConnectionTest.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "DatabaseConnectionTest: ";
	}

	public static void main(String[] args) {
		DatabaseConnectionTest testConnection = new DatabaseConnectionTest();
		ReportConfigUtil.initilize();

		testConnection.testTcpConnection();
		// testConnection.testMissionConnection();
		// testConnection.testDb2Connection();
	}

	public void testDb2Connection() {
		Connection conn;
		try {
			conn = DBUtilities.getConnection(ReportConfigUtil
					.getProperties("db2"));
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(getSql());
			while (resultSet.next()) {
				log4j.debug(resultSet.getString(1) + "|"
						+ resultSet.getString(2) + "|" + resultSet.getString(3));
			}
		} catch (Exception e) {
			log4j.error(e.getMessage(), e);
		}
	}

	public void testTcpConnection() {

		Connection conn;
		Statement statement;
		try {
			conn = DBUtilities.getConnection(ReportConfigUtil
					.getProperties("tcp"));
			statement = conn.createStatement();
			String sql = "select id, name from dummy";
			setSql(sql);
			ResultSet resultSet = statement.executeQuery(getSql());
			while (resultSet.next()) {
				log4j.debug(resultSet.getInt(1) + "|" + resultSet.getString(2));
			}

		} catch (Exception e) {
			log4j.error(e.getMessage());
		}
	}

	public void testMissionConnection() {

		Connection conn;
		try {
			conn = DBUtilities.getConnection(ReportConfigUtil
					.getProperties("mission"));
			Statement statement = conn.createStatement();
			String sql = "select cusptyid, cusnum, cusnam from  pycus where cusptyid in (8021293,8018630,7946204);";
			setSql(sql);
			ResultSet resultSet = statement.executeQuery(getSql());
			while (resultSet.next()) {
				log4j.debug(resultSet.getString(1) + "|"
						+ resultSet.getString(2) + "|" + resultSet.getString(3));
			}

		} catch (Exception e) {
			log4j.error(e.getMessage());
		}
	}

	String sql = "select name, email, relation from authors";

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}