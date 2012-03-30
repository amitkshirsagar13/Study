package com.JTutor.data;

public class User {

	private String userId = null;
	private String userName = null;
	private String role = null;

	public User(String userId, String userName, String role) {
		setUserId(userId);
		setUserName(userName);
		setRole(role);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
