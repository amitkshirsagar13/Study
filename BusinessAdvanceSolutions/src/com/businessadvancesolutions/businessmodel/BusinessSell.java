package com.businessadvancesolutions.businessmodel;

import java.util.Vector;

import com.businessadvancesolutions.dbapi.dao.BusinessDressDAO;

public class BusinessSell {

	private int invoiceId = -1;
	private int sellId = -1;
	private String dressBarCode = null;
	private String itemSrNo = null;
	private int quantity = 0;
	private String sellPrice = null;
	private int totalPrice = 0;

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getDressBarCode() {
		return dressBarCode;
	}

	public void setDressBarCode(String dressBarCode) {
		this.dressBarCode = dressBarCode;
	}

	public String getItemSrNo() {
		return itemSrNo;
	}

	public void setItemSrNo(String itemSrNo) {
		this.itemSrNo = itemSrNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Vector<String> getVector() {
		Vector<String> vector = new Vector<String>();
		vector.add(itemSrNo);
		vector.add(BusinessDressDAO.getBusinessDress(dressBarCode)
				.getDressName());
		vector.add(quantity + "");
		vector.add(BusinessDressDAO.getBusinessDress(dressBarCode)
				.getSellPrice() + "");
		totalPrice = quantity
				* BusinessDressDAO.getBusinessDress(dressBarCode)
						.getSellPrice();
		vector.add(totalPrice + "");
		return vector;
	}

	@Override
	public String toString() {

		return "InvoiceId: " + invoiceId + " : " + "SellId: " + sellId + " : "
				+ "Quantity: " + quantity + " : SellPrice" + sellPrice
				+ " : TotalPrice" + totalPrice;
	}

}
