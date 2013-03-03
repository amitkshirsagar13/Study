package com.businessadvancesolutions.dbapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.businessadvancesolutions.businessmodel.BusinessUser;
import com.businessadvancesolutions.helper.SystemLogger;

public class BusinessUserDAO extends BusinessAdvanceDAO {

	private static Connection _conn = null;
	private static BusinessUserDAO businessUserDao = null;
	private static Session _userBusinessSession = null;

	private BusinessUserDAO() {
		BusinessAdvanceDAO.initilize();
		_userBusinessSession = super.getSession();
	}

	public static BusinessUserDAO getBusinessUserDao() {
		if (businessUserDao == null) {
			businessUserDao = new BusinessUserDAO();
			businessUserDao.setConnection();
		}
		return businessUserDao;
	}

	private void setConnection() {
		_conn = GetDBConnection.getDatabaseConnection();
	}

	private void resetConnection() {
		_conn = GetDBConnection.getDatabaseConnection();
	}

	public static List<BusinessUser> getbusinessUserList(String userID,
			String userName, String userRole) throws Exception {

		if (_conn == null) {
			throw new Exception("Initiate businessUsersDAO, before user...");
		}

		List<BusinessUser> businessUserList = new ArrayList<BusinessUser>();
		BusinessUser businessUser = null;

		StringBuffer businessUserQuery = new StringBuffer(
				"select * from businessuser");

		String whereClouse = " where ";

		businessUserQuery.append(whereClouse);

		boolean addOperator = false;
		if (userID != null && !userID.equals("")) {
			businessUserQuery.append(" userID like '%" + userID + "%' ");
			addOperator = true;
		}

		if (userName != null && !userName.equals("")) {
			if (addOperator) {
				businessUserQuery.append(" and ");
			}
			businessUserQuery.append(" username like '%" + userName + "%' ");
			addOperator = true;
		}
		if (userRole != null && !userRole.equals("")) {
			if (addOperator) {
				businessUserQuery.append(" and ");
			}
			businessUserQuery.append(" userrole like '%" + userRole + "%' ");
		}
		SystemLogger.logDebug(businessUserQuery.toString());
		try {
			ResultSet resultset = _conn.createStatement().executeQuery(
					businessUserQuery.toString());
			while (resultset.next()) {
				businessUser = new BusinessUser();

				businessUser.setUserId(resultset.getInt("userID"));
				businessUser.setUserName(resultset.getString("userName"));
				businessUser.setUserRole(resultset.getInt("userRole"));
				businessUser.setUserPassword(resultset
						.getString("userPassword"));
				businessUserList.add(businessUser);
				SystemLogger.logDebug("businessUsers: "
						+ businessUser.toString());
			}
		} catch (SQLException e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return businessUserList;
	}

	public static boolean addbusinessUser(String userID, String userName,
			String userRole) {
		boolean insertStatus = false;

		String insertUser = "insert into businessuser (username, userrole, userpassword) values ('"
				+ userName + "'," + userRole + ",'" + userName + "')";

		SystemLogger.logDebug(insertUser);
		try {
			insertStatus = _conn.createStatement().execute(insertUser);
		} catch (Exception e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return insertStatus;
	}

	public static boolean updatebusinessUser(String userID, String userName,
			String userRole) {
		boolean updateStatus = false;
		String updateUser = "update businessuser set username='" + userName
				+ "', userpassword='" + userName + "', userrole=" + userRole
				+ " where userid=" + userID;

		SystemLogger.logDebug(updateUser);
		try {
			updateStatus = _conn.createStatement().execute(updateUser);
		} catch (Exception e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return updateStatus;
	}

	public static BusinessUser loginUserOld(String userName, String userPassword) {
		BusinessUser businessUser = null;
		StringBuffer businessUserQuery = new StringBuffer(
				"select * from businessuser");

		String whereClouse = " where ";

		businessUserQuery.append(whereClouse);

		businessUserQuery.append("username='" + userName
				+ "' and userpassword='" + userPassword + "'");

		SystemLogger.logDebug(businessUserQuery.toString());
		try {
			ResultSet resultset = _conn.createStatement().executeQuery(
					businessUserQuery.toString());
			while (resultset.next()) {
				businessUser = new BusinessUser();
				businessUser.setUserId(resultset.getInt("userID"));
				businessUser.setUserName(resultset.getString("userName"));
				businessUser.setUserRole(resultset.getInt("userRole"));
				businessUser.setUserPassword(resultset
						.getString("userPassword"));
				SystemLogger.logDebug("businessUsers: "
						+ businessUser.toString());
			}
		} catch (Exception e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return businessUser;
	}

	public static BusinessUser loginUser(String userName, String userPassword) {
		BusinessUser businessUser = null;
		Query query = _userBusinessSession
				.createQuery("from BusinessUser where username= :username and password= :userpassword");

		List<BusinessUser> list = query.list();
		java.util.Iterator<BusinessUser> iter = list.iterator();
		while (iter.hasNext()) {

			businessUser = iter.next();
			SystemLogger.logMessage("Fetched BusinessUser- "
					+ businessUser.getUserId() + ", "
					+ businessUser.getUserName() + ", "
					+ businessUser.getUserRole() + ", "
					+ businessUser.getUserPassword());

		}

		return businessUser;
	}

	public static void testHibernate() {
		Query query = _userBusinessSession.createQuery("from BusinessUser");

		List<BusinessUser> list = query.list();
		java.util.Iterator<BusinessUser> iter = list.iterator();
		while (iter.hasNext()) {

			BusinessUser user = iter.next();
			SystemLogger.logMessage("Test BusinessUserList- "
					+ user.getUserId() + ", " + user.getUserName() + ", "
					+ user.getUserRole() + ", " + user.getUserPassword());

		}

	}
}
