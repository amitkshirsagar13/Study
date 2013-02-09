package com.businessadvancesolutions.businessmodel;

public class InvoiceDetail {
	int invoiceDetailID = -1;
	int invoiceID = -1;
	int dressID = -1;
	int itemSrNo = 0;
	int quantity = 0;
	float invoiceDetailPrice = 0;
	String dressBarCodeID = "";

	public int getInvoiceDetailID() {
		return invoiceDetailID;
	}

	public void setInvoiceDetailID(int invoiceDetailID) {
		this.invoiceDetailID = invoiceDetailID;
	}

	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public int getDressID() {
		return dressID;
	}

	public void setDressID(int dressID) {
		this.dressID = dressID;
	}

	public int getItemSrNo() {
		return itemSrNo;
	}

	public void setItemSrNo(int itemSrNo) {
		this.itemSrNo = itemSrNo;
	}

	public float getInvoiceDetailPrice() {
		return invoiceDetailPrice;
	}

	public void setInvoiceDetailPrice(float invoiceDetailPrice) {
		this.invoiceDetailPrice = invoiceDetailPrice;
	}

	public String getDressBarCodeID() {
		return dressBarCodeID;
	}

	public void setDressBarCodeID(String dressBarCodeID) {
		this.dressBarCodeID = dressBarCodeID;
	}
}
