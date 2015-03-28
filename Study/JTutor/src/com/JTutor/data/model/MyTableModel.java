package com.JTutor.data.model;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

public class MyTableModel extends DefaultTableModel {
	private static Logger _log = null;

	public MyTableModel() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(MyTableModel.class);
		}
	}

	@Override
	public void setColumnIdentifiers(Vector column) {
		super.setColumnIdentifiers(column);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return super.getRowCount();
	}

	@Override
	public int getColumnCount() {
		return super.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object cellValue = super.getValueAt(rowIndex, columnIndex);
		return cellValue;
	}

	@Override
	public void addRow(Vector rowData) {
		super.addRow(rowData);
		fireTableDataChanged();
	}

	// public Vector<User> getUserList() {
	// return userList;
	// }

	// public void addUser(User user) {
	// userList.add(user);
	// fireTableDataChanged();
	// }

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		super.setValueAt(aValue, row, column);
	}

	@Override
	public boolean isCellEditable(int row, int cols)

	{
		/*
		 * first col is not editable :)
		 */
		// if (cols == 0) {
		// return false;
		// }
		return true;
	}

}
