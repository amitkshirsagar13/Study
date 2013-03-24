package com.businessadvancesolutions.businessmodel;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BusinessInvoice {
	private int invoiceId = -1;
	private int customerId = -1;
	private int invoiceTotal = 0;
	private int totalDiscount = 0;
	private Date invoiceDate = null;
	private String invoiceBarCode = null;
	private List<BusinessSell> businessSells = null;

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(int invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public int getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(int totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceBarCode() {
		return invoiceBarCode;
	}

	public void setInvoiceBarCode(String invoiceBarCode) {
		this.invoiceBarCode = invoiceBarCode;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("InvoiceId: " + invoiceId + " : "
				+ "CustomerId: " + customerId + " : " + "invoiceTotal: "
				+ invoiceTotal + "\n");
		for (Iterator<BusinessSell> iterator = businessSells.iterator(); iterator
				.hasNext();) {
			str.append(iterator.next() + "\n");

		}
		return str.toString();
	}

	public List<BusinessSell> getBusinessSells() {
		return businessSells;
	}

	public void setBusinessSells(List<BusinessSell> businessSells) {
		this.businessSells = businessSells;
	}
}
