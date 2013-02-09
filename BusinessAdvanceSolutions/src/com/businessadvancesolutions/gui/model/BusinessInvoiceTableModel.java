package com.businessadvancesolutions.gui.model;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.businessadvancesolutions.businessmodel.InvoiceDetail;

public class BusinessInvoiceTableModel extends AbstractTableModel {

	public BusinessInvoiceTableModel(Vector recordRecordVector) {
		super();
		if (recordRecordVector == null) {
			recordRecordVector = new Vector<InvoiceDetail>();
		}
		this.recordRecordVector = recordRecordVector;
	}

	Vector<InvoiceDetail> recordRecordVector = null;

	public void setUserList(Vector<InvoiceDetail> recordRecordVector) {
		this.recordRecordVector = recordRecordVector;
		fireTableDataChanged();
	}

	public Vector<InvoiceDetail> getRecordVector() {
		return recordRecordVector;
	}

	public void addInvoiceDetail(InvoiceDetail invoiceDetail) {
		recordRecordVector.add(invoiceDetail);
		fireTableDataChanged();
	}

	public void deleteInvoiceDetail(int selectedRecord) {
		recordRecordVector.remove(selectedRecord);
		fireTableDataChanged();
	}

	public Float getInvoiceDetailPrice(int row) {
		InvoiceDetail invoiceDetail = recordRecordVector.elementAt(row);
		return invoiceDetail.getInvoiceDetailPrice();
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
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
}
