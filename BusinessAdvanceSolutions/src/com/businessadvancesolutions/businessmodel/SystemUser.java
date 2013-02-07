package com.businessadvancesolutions.businessmodel;

public class SystemUser {

	private int systemUserID = -1;
	private String systemUserName = null;
	private String systemUserPassword = null;
	private int systemUserRole = -1;

	public int getSystemUserID() {
		return systemUserID;
	}

	public void setSystemUserID(int systemUserID) {
		this.systemUserID = systemUserID;
	}

	public String getSystemUserName() {
		return systemUserName;
	}

	public void setSystemUserName(String systemUserName) {
		this.systemUserName = systemUserName;
	}

	public String getSystemUserPassword() {
		return systemUserPassword;
	}

	public void setSystemUserPassword(String systemUserPassword) {
		this.systemUserPassword = systemUserPassword;
	}

	public int getSystemUserRole() {
		return systemUserRole;
	}

	public void setSystemUserRole(int systemUserRole) {
		this.systemUserRole = systemUserRole;
	}

}
