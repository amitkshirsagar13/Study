package com.businessadvancesolutions.dbapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class DBTest {
	public static void main(String[] args) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// set this to a MS Access DB you have on your machine
			String filename = "D:/workspace/SonaliCollectionsProject/database/mdbTEST.mdb";
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += filename.trim() + ";DriverID=22;READONLY=true}";

			// now we can get the connection from the DriverManager
			Connection con = DriverManager.getConnection(database, "", "");
			Statement stmt = con.createStatement();
			stmt.execute("select * from CustomerDetails where CustomerFirstName like '%mi%'"); // execute
																								// query
																								// in

			ResultSet rs = stmt.getResultSet(); // get any Result that came from
												// our query
			if (rs != null)
				while (rs.next()) {
					System.out.println("Name: "
							+ rs.getString("CustomerFirstName") + " ID: "
							+ rs.getString("CustomerID"));
				}

			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}