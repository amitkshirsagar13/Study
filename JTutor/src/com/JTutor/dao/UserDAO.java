package com.JTutor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.JTutor.data.User;
import com.JTutor.dbConnection.DBConnection;

public class UserDAO {

	private static Logger _log = null;

	public UserDAO() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(UserDAO.class);
		}
	}

	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();

		Connection dbConnection = DBConnection.getDBConnection();
		String query = "select * from users";
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				userList.add(new User(rs.getInt(1) + "", rs.getString(2), rs
						.getInt(3) + ""));
			} // end while
		} catch (SQLException e) {
			_log.error("Error getting userlist", e);
		}
		return userList;
	}
}
