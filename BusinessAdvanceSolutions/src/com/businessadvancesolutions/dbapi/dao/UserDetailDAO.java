package com.businessadvancesolutions.dbapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.businessadvancesolutions.businessmodel.UserDetail;
import com.businessadvancesolutions.helper.SystemLogger;

public class UserDetailDAO {

	private static Connection _conn = null;
	private static UserDetailDAO userDetailDao = null;

	private UserDetailDAO() {
	}

	public static UserDetailDAO getUserDetailDAO() {
		if (userDetailDao == null) {
			userDetailDao = new UserDetailDAO();
			userDetailDao.setConnection();
		}
		return userDetailDao;
	}

	private void setConnection() {
		_conn = GetDBConnection.getDatabaseConnection();
	}

	private void resetConnection() {
		_conn = GetDBConnection.getDatabaseConnection();
	}

	public static List<UserDetail> getUserDetailList(String userID,
			String userName, String userRole) throws Exception {

		if (_conn == null) {
			throw new Exception("Initiate UserDetailsDAO, before user...");
		}

		List<UserDetail> userDetailList = new ArrayList<UserDetail>();
		UserDetail userDetail = null;

		StringBuffer userDetailQuery = new StringBuffer(
				"select * from businessuser");

		String whereClouse = " where ";

		userDetailQuery.append(whereClouse);

		boolean addOperator = false;
		if (userID != null && !userID.equals("")) {
			userDetailQuery.append(" userID like '%" + userID + "%' ");
			addOperator = true;
		}

		if (userName != null && !userName.equals("")) {
			if (addOperator) {
				userDetailQuery.append(" and ");
			}
			userDetailQuery.append(" username like '%" + userName + "%' ");
			addOperator = true;
		}
		if (userRole != null && !userRole.equals("")) {
			if (addOperator) {
				userDetailQuery.append(" and ");
			}
			userDetailQuery.append(" userrole like '%" + userRole + "%' ");
		}
		SystemLogger.logMessage(userDetailQuery.toString());
		try {
			ResultSet resultset = _conn.createStatement().executeQuery(
					userDetailQuery.toString());
			while (resultset.next()) {
				userDetail = new UserDetail();

				userDetail.setUserID(resultset.getInt("userID"));
				userDetail.setUserName(resultset.getString("userName"));
				userDetail.setUserRole(resultset.getInt("userRole"));
				userDetail.setUserPassword(resultset.getString("userPassword"));
				userDetailList.add(userDetail);
				SystemLogger.logDebug("UserDetails: " + userDetail.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userDetailList;
	}

	public static boolean addUserDetail(String userID, String userName,
			String userRole) {
		boolean insertStatus = false;
		String insertUser = "insert into businessuser (username, userrole, userpassword) values ('"
				+ userName + "'," + userRole + ",'" + userName + "')";

		SystemLogger.logMessage(insertUser);
		try {
			insertStatus = _conn.createStatement().execute(insertUser);
		} catch (Exception e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return insertStatus;
	}

	public static boolean updateUserDetail(String userID, String userName,
			String userRole) {
		boolean updateStatus = false;
		String updateUser = "update businessuser set username='" + userName
				+ "', userpassword='" + userName + "', userrole=" + userRole
				+ " where userid=" + userID;

		SystemLogger.logMessage(updateUser);
		try {
			updateStatus = _conn.createStatement().execute(updateUser);
		} catch (Exception e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return updateStatus;
	}

	public static UserDetail loginUser(String userName, String userPassword) {
		UserDetail userDetail = null;
		StringBuffer userDetailQuery = new StringBuffer(
				"select * from businessuser");

		String whereClouse = " where ";

		userDetailQuery.append(whereClouse);

		userDetailQuery.append("username='" + userName + "' and userpassword='"
				+ userPassword + "'");

		SystemLogger.logMessage(userDetailQuery.toString());
		try {
			ResultSet resultset = _conn.createStatement().executeQuery(
					userDetailQuery.toString());
			while (resultset.next()) {
				userDetail = new UserDetail();
				System.out.println("Is it working");
				userDetail.setUserID(resultset.getInt("userID"));
				userDetail.setUserName(resultset.getString("userName"));
				userDetail.setUserRole(resultset.getInt("userRole"));
				userDetail.setUserPassword(resultset.getString("userPassword"));
				SystemLogger.logDebug("UserDetails: " + userDetail.toString());
			}
		} catch (Exception e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return userDetail;
	}
}
