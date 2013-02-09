package com.businessadvancesolutions.gui.model;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class BusinessInvoiceTableModel extends AbstractTableModel implements
		TableModelListener {

	public BusinessInvoiceTableModel(Vector recordRecordVector) {
		super();
		if (recordRecordVector == null) {
			recordRecordVector = new Vector();
		}
		this.recordRecordVector = recordRecordVector;
	}

	Vector<Vector<Object>> recordRecordVector = null;

	public void setUserList(Vector recordRecordVector) {
		this.recordRecordVector = recordRecordVector;
		fireTableDataChanged();
	}

	public Vector getRecordVector() {
		return recordRecordVector;
	}

	public void addInvoiceDetail(Vector invoiceDetail) {
		recordRecordVector.add(invoiceDetail);
		fireTableDataChanged();
	}

	public void deleteInvoiceDetail(int selectedRecord) {
		recordRecordVector.remove(selectedRecord);
		fireTableDataChanged();
	}

	// Names of the columns
	public String[] m_colNames = { "ItemSrNo", "DressName", "Quantity",
			"ItemPrice", "InvoiceDetailPrice", "DressBarCode" };
	// Types of the columns.
	public Class[] m_colTypes = { String.class, String.class, Integer.class,
			Float.class, Float.class, String.class };

	@Override
	public int getRowCount() {
		return recordRecordVector.size();
	}

	@Override
	public int getColumnCount() {
		return m_colNames.length;
	}

	public String getColumnName(int col) {
		return m_colNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Vector<Object> value = recordRecordVector.get(row);
		return value.get(col);
	}

	//
	// public void setValueAt(Object value, int rowIndex, int columnIndex) {
	// String sValue = (String) value;
	// recordRecordVector.get(rowIndex).setValueAtColumn(sValue, columnIndex);
	// fireTableDataChanged();
	// }

	@Override
	public void setValueAt(Object newVal, int row, int col) {
		Vector<Object> aRow = recordRecordVector.elementAt(row);
		aRow.remove(col);
		aRow.insertElementAt(newVal, col);
		fireTableCellUpdated(row, col);
	}

	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 0:
		case 1:
			return false;
		case 2:
		case 3:
			return true;
		case 4:
			return false;
		case 5:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void tableChanged(TableModelEvent tme) {
		System.out.println("Change event:" + tme.getType());
		if (tme.getType() == TableModelEvent.UPDATE) {
		}
	}
}
