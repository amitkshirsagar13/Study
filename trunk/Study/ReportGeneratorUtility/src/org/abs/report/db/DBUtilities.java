package org.abs.report.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBUtilities {
	// Every once in a while we want to ignore certain errors generated
	// by the db so we define names for those error codes here.

	public static final int SQL_DUP_ERROR = 2627;
	private static Logger log4j = Logger.getLogger(DBUtilities.class);

	// public interfaces
	public static Connection getConnection(Properties properties)
			throws SQLException {
		log4j.debug(properties);
		return getDriver(properties.getProperty("driver")).connect(
				properties.getProperty("dbString"), properties);
	}

	/**
	 * closes result set, callable statement, and connection (whichever ones are
	 * not null)
	 *
	 * @param connection
	 *            to be closed
	 * @param callable
	 *            statment to be closed ResultSet result set to be closed
	 */
	public static void closeObjects(Connection conn, Statement cs, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Throwable t) {
		}

		try {
			if (cs != null)
				cs.close();
		} catch (Throwable t) {
		}

		try {
			if (conn != null && !conn.getAutoCommit())
				conn.rollback();
		} catch (Throwable t) {
		}

		try {
			if (conn != null)
				conn.close();
		} catch (Throwable t) {
		}
	}

	public static void closeObjects(Connection conn, Connection connToUse,
			CallableStatement cs, ResultSet rs) {
		// The caller does not really want to close a connectin it was given
		// as a parameter (ie, connToUse).
		if (conn == connToUse) {
			conn = null;
		}
		closeObjects(conn, cs, rs);
	}

	// private workings
	private static Driver getDriver(String driver) {
		if (jdbcDriverMap.get(driver) == null)
			initialize(driver);
		return jdbcDriverMap.get(driver);
	}

	private static synchronized void initialize(String driver) {
		try {
			Driver m_jdbcDriver = (Driver) Class.forName(driver).newInstance();
			jdbcDriverMap.put(driver, m_jdbcDriver);
		} catch (Exception e) {
		}
	}

	private static HashMap<String, Driver> jdbcDriverMap = new HashMap<String, Driver>();
}
