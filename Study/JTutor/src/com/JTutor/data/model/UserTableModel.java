package com.JTutor.data.model;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;

import com.JTutor.data.User;

public class UserTableModel extends AbstractTableModel {
	private static Logger _log = null;

	public UserTableModel(Vector userList) {
		super();
		if (_log == null) {
			_log = Logger.getLogger(UserTableModel.class);
		}
		if (userList == null) {
			userList = new Vector<User>();
		}

		this.userList = userList;
	}

	Vector<User> userList = null;
	// Columns Number.
	public static final int userid = 0;
	public static final int username = 1;
	public static final int role = 2;

	// Names of the columns
	public String[] m_colNames = { "ID", "NAME", "ROLE" };
	// Types of the columns.
	public Class[] m_colTypes = { String.class, String.class, String.class };

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return userList.size();
	}

	@Override
	public int getColumnCount() {
		return m_colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		User userData = userList.elementAt(rowIndex);
		Object cellValue = null;
		switch (columnIndex) {
		case userid:
			cellValue = userData.getUserId();
			break;
		case username:
			cellValue = userData.getUserName();
			break;
		case role:
			cellValue = userData.getRole();
			break;
		}

		return cellValue;
	}

	public void setUserList(Vector userList) {
		this.userList = userList;
		fireTableDataChanged();
	}

	public Vector<User> getUserList() {
		return userList;
	}

	public void addUser(User user) {
		userList.add(user);
		fireTableDataChanged();
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		User userData = userList.elementAt(row);

		switch (col) {
		case userid:
			userData.setUserId((String) value);
			break;
		case username:
			userData.setUserName((String) value);
			break;
		case role:
			userData.setRole((String) value);
			break;
		}
	}

	public String getRole(int row) {
		User userData = userList.elementAt(row);
		return userData.getRole();
	}

	@Override
	public String getColumnName(int col) {
		return m_colNames[col];
	}

	@Override
	public Class getColumnClass(int col) {
		return m_colTypes[col];
	}

	@Override
	public boolean isCellEditable(int row, int cols)

	{
		/*
		 * first col is not editable :)
		 */
		if (cols == 0) {
			return false;
		}
		return true;
	}

}
