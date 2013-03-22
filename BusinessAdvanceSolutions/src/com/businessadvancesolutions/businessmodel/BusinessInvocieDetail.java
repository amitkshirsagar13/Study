package com.businessadvancesolutions.businessmodel;

import java.util.Vector;

import com.businessadvancesolutions.dbapi.dao.BusinessDressDAO;

public class BusinessInvocieDetail {

	String sellId = null;
	String invoiceId = null;
	int dressId = -1;
	String itemSrNo = null;
	int quantity = -1;
	String sellPrice = null;
	int totalPrice = -1;

	public Vector<String> getVector() {
		Vector<String> vector = new Vector<String>();
		vector.add(itemSrNo);
		vector.add(BusinessDressDAO.getBusinessDress(dressId).getDressName());
		vector.add(quantity + "");
		vector.add(BusinessDressDAO.getBusinessDress(dressId).getSellPrice()
				+ "");
		totalPrice = quantity
				* BusinessDressDAO.getBusinessDress(dressId).getSellPrice();
		vector.add(totalPrice + "");
		return vector;
	}

}
