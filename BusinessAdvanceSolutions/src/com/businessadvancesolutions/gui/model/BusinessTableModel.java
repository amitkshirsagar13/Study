package com.businessadvancesolutions.gui.model;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class BusinessTableModel extends AbstractTableModel implements
		TableModelListener {

	public BusinessTableModel(Vector recordRecordVector) {
		super();
		if (recordRecordVector == null) {
			recordRecordVector = new Vector();
		}
		this.recordRecordVector = recordRecordVector;
	}

	Vector<Vector<String>> recordRecordVector = null;

	public void setUserList(Vector recordRecordVector) {
		this.recordRecordVector = recordRecordVector;
		fireTableDataChanged();
	}

	public Vector getRecordVector() {
		return recordRecordVector;
	}

	public void addInvoiceDetail(Vector<String> invoiceDetail) {
		if (invoiceDetail.get(2) != null && !invoiceDetail.get(2).equals("")) {
			if (invoiceDetail.get(3) != null
					&& !invoiceDetail.get(3).equals("")) {
				String invoiceDetailPrice = ""
						+ Integer.parseInt(invoiceDetail.get(2))
						* Integer.parseInt(invoiceDetail.get(3));
				invoiceDetail.set(4, invoiceDetailPrice);
			}
		}
		recordRecordVector.add(invoiceDetail);
		fireTableDataChanged();
	}

	public void deleteInvoiceDetail(int selectedRecord) {
		recordRecordVector.remove(selectedRecord);
		fireTableDataChanged();
	}

	// Names of the columns
	public String[] m_colNames = null;
	// Types of the columns.
	public Class[] m_colTypes = null;

	public void setColumnNames(String[] colNames) {
		m_colNames = colNames;
	}

	public void setColumnTypes(Class[] colTypes) {
		m_colTypes = colTypes;
	}

	@Override
	public int getRowCount() {
		return recordRecordVector.size();
	}

	@Override
	public int getColumnCount() {
		return m_colNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return m_colNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Vector<String> value = recordRecordVector.get(row);
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
		Vector<String> aRow = recordRecordVector.elementAt(row);
		aRow.remove(col);
		aRow.insertElementAt((String) newVal, col);
		if (col == 2 || col == 3) {
			String unitQuantity = aRow.get(2);
			String unitPrice = aRow.get(3);
			// SystemLogger.logDebug("Updating quatity price...out" +
			// unitQuantity
			// + "*" + unitPrice);
			if (unitQuantity != null && !unitQuantity.equals("")
					&& unitPrice != null && !unitPrice.equals("")) {
				// SystemLogger.logDebug("Updating quatity price...in"
				// + Integer.parseInt(unitQuantity) + "*"
				// + Integer.parseInt(unitPrice));
				String invoiceDetailPrice = "" + Integer.parseInt(unitQuantity)
						* Integer.parseInt(unitPrice);
				aRow.remove(4);
				aRow.insertElementAt(invoiceDetailPrice, 4);
				fireTableCellUpdated(row, 4);
			}
		}
		fireTableCellUpdated(row, col);
	}

	@Override
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
		if (tme.getType() == TableModelEvent.UPDATE) {
		}
	}
}
