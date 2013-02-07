package com.businessadvancesolutions.businessmodel;

public class UserDetail {
	private int userID = -1;
	private String userName = null;
	private int userRole = 2;
	private String userPassword = null;

	public UserDetail() {

	}

	public UserDetail(int userID, String userName, int userRole,
			String userPassword) {
		setUserID(userID);
		setUserName(userName);
		setUserPassword(userPassword);
		setUserRole(userRole);
	}

	@Override
	public String toString() {
		return "\nuserID- " + userID + "\nuserName- " + userName
				+ "\nuserRole- " + userRole + "\nuserPassword- " + userPassword;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
