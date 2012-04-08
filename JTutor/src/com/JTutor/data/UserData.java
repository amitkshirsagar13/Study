package com.JTutor.data;

import com.JTutor.dao.UserDAO;

public class UserData {

	public String id = null;

	public String[] columnIdentifiers = null;
	public String[] rowData = null;

	public UserData() {

	}

	public UserData(String userid) {
		UserData userData = UserDAO.getUserData(userid);
		setColumnIdentifiers(userData.getColumnIdentifiers());
		setRowData(userData.getRowData());
		id = userid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getColumnIdentifiers() {
		return columnIdentifiers;
	}

	public void setColumnIdentifiers(String[] columnIdentifiers) {
		this.columnIdentifiers = columnIdentifiers;
	}

	public String[] getRowData() {
		return rowData;
	}

	public void setRowData(String[] rowData) {
		this.rowData = rowData;
	}
}
