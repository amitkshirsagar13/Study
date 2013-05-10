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
public class BusinessDress {
	private int dressId = -1;
	private String dressName = null;
	private String dressColor = null;
	private int sellPrice = -1;
	private int supplierId = -1;
	private int supplierPrice = -1;
	private String discount = null;
	private String lotNumberDate = null;
	private String dressBarCode = null;
	private String damageId = null;

	public int getDressId() {
		return dressId;
	}

	public String getDressName() {
		return dressName;
	}

	public String getDressColor() {
		return dressColor;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public int getSupplierPrice() {
		return supplierPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public String getLotNumberDate() {
		return lotNumberDate;
	}

	public String getDressBarCode() {
		return dressBarCode;
	}

	public String getDamageId() {
		return damageId;
	}

	public void setDressId(int dressId) {
		this.dressId = dressId;
	}

	public void setDressName(String dressName) {
		this.dressName = dressName;
	}

	public void setDressColor(String dressColor) {
		this.dressColor = dressColor;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public void setSupplierPrice(int supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setLotNumberDate(String lotNumberDate) {
		this.lotNumberDate = lotNumberDate;
	}

	public void setDressBarCode(String dressBarCode) {
		this.dressBarCode = dressBarCode;
	}

	public void setDamageId(String damageId) {
		this.damageId = damageId;
	}

}
