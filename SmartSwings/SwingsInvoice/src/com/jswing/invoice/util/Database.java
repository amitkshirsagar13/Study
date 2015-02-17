package com.jswing.invoice.util;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Database {
	static Logger log = Logger.getLogger(Database.class.getName());

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/dbname", "root", "dbpass");
			return con;
		} catch (Exception ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}
}