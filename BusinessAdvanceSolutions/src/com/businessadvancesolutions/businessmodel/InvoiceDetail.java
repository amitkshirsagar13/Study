package com.businessadvancesolutions.businessmodel;

import java.util.Vector;

public class InvoiceDetail {
	String invoiceDetailID = null;
	String invoiceID = null;
	String dressID = null;
	String itemSrNo = null;
	String quantity = null;
	String invoiceDetailPrice = null;
	String dressBarCodeID = "";

	public String getInvoiceDetailID() {
		return invoiceDetailID;
	}

	public void setInvoiceDetailID(String invoiceDetailID) {
		this.invoiceDetailID = invoiceDetailID;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public String getDressID() {
		return dressID;
	}

	public void setDressID(String dressID) {
		this.dressID = dressID;
	}

	public String getItemSrNo() {
		return itemSrNo;
	}

	public void setItemSrNo(String itemSrNo) {
		this.itemSrNo = itemSrNo;
	}

	public String getInvoiceDetailPrice() {
		return invoiceDetailPrice;
	}

	public void setInvoiceDetailPrice(String invoiceDetailPrice) {
		this.invoiceDetailPrice = invoiceDetailPrice;
	}

	public String getDressBarCodeID() {
		return dressBarCodeID;
	}

	public void setDressBarCodeID(String dressBarCodeID) {
		this.dressBarCodeID = dressBarCodeID;
	}

	public Vector<Object> getVector() {
		Vector<Object> vector = new Vector<Object>();
		vector.add(itemSrNo);
		vector.add(dressID);
		vector.add(quantity);
		vector.add(dressID);
		vector.add(invoiceDetailPrice);
		vector.add(dressBarCodeID);
		return vector;
	}
}
