package com.JTutor.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) {
		String dbtime;
		String dbUrl = "jdbc:mysql://localhost:3306/test";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "Select * FROM users";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbUrl);
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
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
