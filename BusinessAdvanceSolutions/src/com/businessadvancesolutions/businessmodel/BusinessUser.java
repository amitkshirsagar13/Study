package com.businessadvancesolutions.businessmodel;

public class BusinessUser {
	private int userId = -1;
	private String userName = null;
	private int userRole = 2;
	private String userPassword = null;

	public BusinessUser() {

	}

	public BusinessUser(int userId, String userName, int userRole,
			String userPassword) {
		setUserId(userId);
		setUserName(userName);
		setUserPassword(userPassword);
		setUserRole(userRole);
	}

	@Override
	public String toString() {
		return "\nuserID- " + userId + "\nuserName- " + userName
				+ "\nuserRole- " + userRole + "\nuserPassword- " + userPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
