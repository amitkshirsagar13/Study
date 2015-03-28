package com.businessadvancesolutions.businessmodel;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.util.Vector;

public class BusinessSell {

	private int invoiceId = -1;
	private int sellId = -1;
	private String dressBarCode = null;
	private String itemSrNo = null;
	private int quantity = 0;
	private String sellPrice = null;
	private int totalPrice = 0;
	private BusinessDress businessDress = null;

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

	public Vector<String> getVectorOld() {
		Vector<String> vector = new Vector<String>();
		vector.add(itemSrNo);
		vector.add(businessDress.getDressName());
		vector.add(quantity + "");
		vector.add(businessDress.getSellPrice() + "");
		totalPrice = quantity * businessDress.getSellPrice();
		vector.add(totalPrice + "");
		return vector;
	}

	public Vector<String> getVector() {
		Vector<String> vector = new Vector<String>();
		vector.add(itemSrNo);
		vector.add(businessDress.getDressName());
		vector.add(quantity + "");
		vector.add(businessDress.getSellPrice() + "");
		totalPrice = quantity * businessDress.getSellPrice();
		vector.add(totalPrice + "");
		return vector;
	}

	@Override
	public String toString() {

		return "InvoiceId: " + invoiceId + " : " + "SellId: " + sellId + " : "
				+ "Quantity: " + quantity + " : SellPrice" + sellPrice
				+ " : TotalPrice" + totalPrice;
	}

	public BusinessDress getBusinessDress() {
		return businessDress;
	}

	public void setBusinessDress(BusinessDress businessDress) {
		this.businessDress = businessDress;
	}

}
