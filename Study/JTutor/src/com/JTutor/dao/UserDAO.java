package com.JTutor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.JTutor.data.User;
import com.JTutor.data.UserData;
import com.JTutor.dbConnection.DBConnection;
import com.JTutor.front.JTutorMainFrame;

public class UserDAO {

	private static Logger _log = null;
	private static UserDAO userDAO = null;

	private UserDAO() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(UserDAO.class);
		}
	}

	public static void initiateUserDAO() {
		if (userDAO == null) {
			userDAO = new UserDAO();
			logMessage("Initializing UserDAO...");
		}
	}

	private static void logMessage(String message) {
		JTutorMainFrame.statusBarMsg(message);
		_log.info(message);

	}

	public static List<User> getUserList() {

		Connection dbConnection = DBConnection.getDBConnection();

		List<User> userList = new ArrayList<User>();
		logMessage("Getting user list...");
		String query = "select * from users order by 1";
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				userList.add(new User(rs.getInt(1) + "", rs.getString(2), rs
						.getInt(3) + ""));
			} // end while

			logMessage("Got user list...");
		} catch (SQLException e) {
			_log.error("Error getting userlist", e);
		}
		return userList;
	}

	public static List<String> getRoleList() {
		Connection dbConnection = DBConnection.getDBConnection();
		logMessage("Getting role list...");
		String query = "select distinct role from users order by 1";
		List<String> roleList = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				roleList.add(rs.getString(1));
			} // end while

			logMessage("Got role list...");
		} catch (SQLException e) {
			_log.error("Error getting rolelist", e);
		}
		return roleList;
	}

	public static User addUser(String name, String role) {
		Connection dbConnection = DBConnection.getDBConnection();
		logMessage("Adding User" + name + "/" + role + "...");
		String query = "select max(userid) from users order by 1";
		User user = new User(null, name, role);
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int nextID = Integer.parseInt(rs.getString(1)) + 1;
				user.setUserId(nextID + "");
			} // end while

			query = "insert into users values(" + user.getUserId() + ",'"
					+ user.getUserName() + "'," + user.getRole() + ")";

			stmt.execute(query);

		} catch (SQLException e) {
			_log.error("Error Adding User" + name + "/" + role + "...", e);
		}
		logMessage("Added User " + user.getUserId() + "/" + name + "/" + role
				+ "...");
		return user;
	}

	public static UserData getUserData(String userID) {

		try {
			Connection dbConnection = DBConnection.getDBConnection();
			logMessage("Getting User Data" + userID + "...");
			String query = "select * from userdata where id = " + userID
					+ " order by 1";
			Statement stmt;
			stmt = dbConnection.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			int columnCount = rs.getMetaData().getColumnCount() + 1;
			String[] columnIdentifiers = new String[columnCount - 1];
			for (int index = 1; index < columnCount; index++) {
				columnIdentifiers[index - 1] = rs.getMetaData().getColumnName(
						index);
			}

			UserData userData = new UserData();
			rs.last();

			String[][] rowData = new String[rs.getRow()][columnCount - 1];
			rs.beforeFirst();
			int rowId = 0;
			while (rs.next()) {
				for (int index = 1; index < columnCount; index++) {
					rowData[rowId][index - 1] = rs.getString(index);
				}
				rowId = rowId + 1;
			} // end while
			userData.setColumnIdentifiers(columnIdentifiers);
			userData.setRowData(rowData);
			return userData;
		} catch (SQLException e) {
			_log.error("Error Getting User Data " + userID + "...", e);
		}
		return null;
	}

}
