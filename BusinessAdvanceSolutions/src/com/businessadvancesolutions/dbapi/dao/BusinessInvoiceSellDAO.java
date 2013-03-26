package com.businessadvancesolutions.dbapi.dao;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.businessadvancesolutions.businessmodel.BusinessInvoice;
import com.businessadvancesolutions.businessmodel.BusinessSell;

public class BusinessInvoiceSellDAO extends BusinessAdvanceDAO {

	private static Connection _conn = null;
	private static BusinessInvoiceSellDAO businessInvoiceSellDAO = null;

	private BusinessInvoiceSellDAO() {
		BusinessAdvanceDAO.initilize();
	}

	public static BusinessInvoiceSellDAO getbusinessDressDao() {
		if (businessInvoiceSellDAO == null) {
			businessInvoiceSellDAO = new BusinessInvoiceSellDAO();
		}
		return businessInvoiceSellDAO;
	}

	public static BusinessInvoice addBusinessInvoice(
			BusinessInvoice businessInvoiceForm) {

		BusinessInvoice businessInvoice = getBusinessInvoice(businessInvoiceForm);
		if (businessInvoice == null) {
			businessInvoice = businessInvoiceForm;
			saveObject(businessInvoice);
		} else {
			businessInvoice.setInvoiceTotal(businessInvoiceForm
					.getInvoiceTotal());
			updateObject(businessInvoice);
		}
		businessInvoice = getBusinessInvoice(businessInvoice);

		return businessInvoice;
	}

	public static BusinessSell addBusinessSell(BusinessSell businessSellForm) {
		BusinessSell businessSell = getBusinessSell(businessSellForm);
		if (businessSell == null) {
			businessSell = businessSellForm;
			saveObject(businessSell);
		} else {
			businessSell.setQuantity(businessSellForm.getQuantity());
			businessSell.setSellPrice(businessSellForm.getSellPrice());
			businessSell.setTotalPrice(businessSellForm.getTotalPrice());
			updateObject(businessSell);
		}
		businessSell = getBusinessSell(businessSell);

		return businessSell;
	}

	public static BusinessSell updateBusinessSell(BusinessSell businessSellForm) {
		BusinessSell businessSell = getBusinessSell(businessSellForm);
		if (businessSell != null) {
			businessSell.setQuantity(businessSellForm.getQuantity());
			businessSell.setSellPrice(businessSellForm.getSellPrice());
			businessSell.setTotalPrice(businessSellForm.getTotalPrice());
			updateObject(businessSell);
		}
		businessSell = getBusinessSell(businessSell);

		return businessSell;
	}

	public static BusinessSell getBusinessSell(BusinessSell businessSellForm) {
		BusinessSell businessSell = null;
		Query query = null;
		if (businessSellForm.getSellId() > 0) {
			query = createQuery("from BusinessSell where sellid=:sellid ");
			query.setParameter("sellid", businessSellForm.getSellId());
		} else if (businessSellForm.getDressBarCode() != null
				&& !businessSellForm.getDressBarCode().equalsIgnoreCase("")) {
			query = createQuery("from BusinessSell where dressBarCode=:dressBarCode ");
			query.setParameter("dressBarCode",
					businessSellForm.getDressBarCode());
		}
		if (query == null) {
			return null;
		}
		List<BusinessSell> list = query.list();
		Iterator<BusinessSell> iter = list.iterator();
		while (iter.hasNext()) {
			businessSell = iter.next();
		}

		return businessSell;
	}

	public static List<BusinessSell> getBusinessSellList(
			BusinessInvoice businessInvoiceForm) {
		List<BusinessSell> businessSellList = null;
		Query query = null;
		if (businessInvoiceForm.getInvoiceId() > 0) {
			query = createQuery("from BusinessSell where invoiceid=:invoiceid ");
			query.setParameter("invoiceid", businessInvoiceForm.getInvoiceId());
		}
		businessSellList = query.list();
		return businessSellList;
	}

	public static BusinessInvoice getBusinessInvoice(
			BusinessInvoice businessInvoiceForm) {
		BusinessInvoice businessInvoice = null;

		Query query = null;
		if (businessInvoiceForm.getInvoiceId() > 0) {
			query = createQuery("from BusinessInvoice where invoiceid=:invoiceid ");
			query.setParameter("invoiceid", businessInvoiceForm.getInvoiceId());
		} else if (businessInvoiceForm.getInvoiceBarCode() != null
				&& !businessInvoiceForm.getInvoiceBarCode()
						.equalsIgnoreCase("")) {
			query = createQuery("from BusinessInvoice where invoicebarcode=:invoicebarcode ");
			query.setParameter("invoicebarcode",
					businessInvoiceForm.getInvoiceBarCode());
		}
		if (query == null) {
			return null;
		}
		List<BusinessInvoice> list = null;
		try {
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// List<BusinessInvoice> list = query.list();
		Iterator<BusinessInvoice> iter = list.iterator();
		while (iter.hasNext()) {
			businessInvoice = iter.next();
		}

		return businessInvoice;
	}
}
