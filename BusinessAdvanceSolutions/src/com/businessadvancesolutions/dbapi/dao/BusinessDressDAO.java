package com.businessadvancesolutions.dbapi.dao;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.businessadvancesolutions.businessmodel.BusinessDress;
import com.businessadvancesolutions.helper.SystemLogger;

public class BusinessDressDAO extends BusinessAdvanceDAO {

	private static Connection _conn = null;
	private static BusinessDressDAO businessDressDao = null;

	private BusinessDressDAO() {
		BusinessAdvanceDAO.initilize();
	}

	public static BusinessDressDAO getbusinessDressDao() {
		if (businessDressDao == null) {
			businessDressDao = new BusinessDressDAO();
		}
		return businessDressDao;
	}

	public static BusinessDress getBusinessDress(int dressId) {
		BusinessDress dressForm = new BusinessDress();
		dressForm.setDressId(dressId);
		BusinessDress businessDress = getBusinessDress(dressForm);
		return businessDress;
	}

	public static BusinessDress getBusinessDress(BusinessDress dressForm) {
		BusinessDress businessDress = null;

		Query query = null;
		if (dressForm.getDressId() > 0) {
			query = createQuery("from BusinessCustomer where dressid=:dressid ");
			query.setParameter("dressid", dressForm.getDressId());
		} else if (!dressForm.getDressName().equalsIgnoreCase("")
				&& !dressForm.getDressColor().equalsIgnoreCase("")) {
			query = createQuery("from BusinessCustomer where dressname like :dressname and dresscolor like :dresscolor");
			query.setParameter("dressname", "%" + dressForm.getDressName()
					+ "%");
			query.setParameter("dresscolor", "%" + dressForm.getDressName()
					+ "%");
		}

		List<BusinessDress> list = query.list();
		Iterator<BusinessDress> iter = list.iterator();
		while (iter.hasNext()) {

			businessDress = iter.next();
			SystemLogger.logMessage("Fetched BusinessDress- "
					+ businessDress.getDressId() + ", "
					+ businessDress.getDressName() + ", "
					+ businessDress.getDressColor() + ", "
					+ businessDress.getSellPrice());

		}
		commitTransaction();
		return businessDress;
	}

	public static BusinessDress addBusinessDress(BusinessDress dressForm) {
		BusinessDress businessDress = getBusinessDress(dressForm);
		if (businessDress == null) {
			businessDress = dressForm;
			saveObject(businessDress);
		}
		businessDress = getBusinessDress(businessDress);

		return businessDress;
	}
}
