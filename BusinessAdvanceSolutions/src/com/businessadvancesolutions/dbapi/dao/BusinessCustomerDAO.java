package com.businessadvancesolutions.dbapi.dao;

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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.businessadvancesolutions.businessmodel.BusinessCustomer;
import com.businessadvancesolutions.helper.SystemLogger;

public class BusinessCustomerDAO extends BusinessAdvanceDAO {

	private static Connection _conn = null;
	private static BusinessCustomerDAO businessCustomerDao = null;

	private BusinessCustomerDAO() {
		BusinessAdvanceDAO.initilize();
	}

	public static BusinessCustomerDAO getbusinessCustomerDao() {
		if (businessCustomerDao == null) {
			businessCustomerDao = new BusinessCustomerDAO();
			// businessCustomerDao.setConnection();
		}
		return businessCustomerDao;
	}

	public static List<BusinessCustomer> getCustomerDetailList(
			String customerID, String firstName, String lastName) {

		List<BusinessCustomer> customerDetailList = new ArrayList<BusinessCustomer>();
		BusinessCustomer customerDetail = new BusinessCustomer();

		StringBuffer customerDetailQuery = new StringBuffer(
				"select * from customerDetails");
		String whereClouse = " where ";
		customerDetailQuery.append(whereClouse);
		boolean addOperator = false;
		if (customerID != null && !customerID.equals("")) {
			customerDetailQuery.append(" customerID like '%"
					+ customerDetailQuery + "%' ");
			addOperator = true;
		}
		if (firstName != null && !firstName.equals("")) {
			if (addOperator) {
				customerDetailQuery.append(" and ");
			}
			customerDetailQuery.append(" customerfirstname like '%" + firstName
					+ "%' ");
			addOperator = true;
		}
		if (lastName != null && !lastName.equals("")) {
			if (addOperator) {
				customerDetailQuery.append(" and ");
			}
			customerDetailQuery.append(" customerlastname like '%" + lastName
					+ "%' ");
		}
		SystemLogger.logDebug(customerDetailQuery.toString());
		try {
			ResultSet resultset = _conn.createStatement().executeQuery(
					customerDetailQuery.toString());
			while (resultset.next()) {
				customerDetail = new BusinessCustomer();

				customerDetail.setCustomerId(resultset.getInt("customerID"));
				customerDetail.setCustomerFirstName(resultset
						.getString("customerFirstName"));
				customerDetail.setCustomerLastName(resultset
						.getString("CustomerLastName"));
				// customerDetail.setBirthDate(resultset.getString("BirthDate"));
				customerDetail.setAddressFirstLine(resultset
						.getString("AddressFirstLine"));
				customerDetail.setAddressSecondLine(resultset
						.getString("AddressSecondLine"));
				customerDetail.setLandMark(resultset.getString("LandMark"));
				customerDetail.setCity(resultset.getString("City"));
				customerDetail.setState(resultset.getString("State"));
				customerDetail.setZip(resultset.getInt("Zip"));
				customerDetail.setContactNumber(resultset
						.getLong("ContactNumber"));
				customerDetail.setOccupation(resultset.getString("Occupation"));
				customerDetail.setCustomerIndex(resultset
						.getString("CustomerIndex"));
				customerDetail.setCustomerBarCode(resultset
						.getString("CustomerBarCode"));
				customerDetailList.add(customerDetail);
				SystemLogger.logDebug("CustomerDetails: "
						+ customerDetail.toString());
			}
		} catch (SQLException e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return customerDetailList;
	}

	public static BusinessCustomer getCustomerDetail(String customerID,
			String firstName, String lastName) {

		BusinessCustomer customerDetail = new BusinessCustomer();

		StringBuffer customerDetailQuery = new StringBuffer(
				"select * from customerDetails");
		String whereClouse = " where ";
		customerDetailQuery.append(whereClouse);
		boolean addOperator = false;
		if (customerID != null && !customerID.equals("")) {
			customerDetailQuery.append(" customerID like '%" + customerID
					+ "%' ");
			addOperator = true;
		} else {
			if (firstName != null && !firstName.equals("")) {
				if (addOperator) {
					customerDetailQuery.append(" and ");
				}
				customerDetailQuery.append(" customerfirstname like '%"
						+ firstName + "%' ");
				addOperator = true;
			}
			if (lastName != null && !lastName.equals("")) {
				if (addOperator) {
					customerDetailQuery.append(" and ");
				}
				customerDetailQuery.append(" customerlastname like '%"
						+ lastName + "%' ");
			}
		}
		SystemLogger.logDebug(customerDetailQuery.toString());
		try {
			ResultSet resultset = _conn.createStatement().executeQuery(
					customerDetailQuery.toString());
			while (resultset.next()) {
				customerDetail = new BusinessCustomer();

				customerDetail.setCustomerId(resultset.getInt("customerId"));
				customerDetail.setCustomerFirstName(resultset
						.getString("customerFirstName"));
				customerDetail.setCustomerLastName(resultset
						.getString("CustomerLastName"));
				// customerDetail.setBirthDate(resultset.getString("BirthDate"));
				customerDetail.setAddressFirstLine(resultset
						.getString("AddressFirstLine"));
				customerDetail.setAddressSecondLine(resultset
						.getString("AddressSecondLine"));
				customerDetail.setLandMark(resultset.getString("LandMark"));
				customerDetail.setCity(resultset.getString("City"));
				customerDetail.setState(resultset.getString("State"));
				customerDetail.setZip(resultset.getInt("Zip"));
				customerDetail.setContactNumber(resultset
						.getLong("ContactNumber"));
				customerDetail.setOccupation(resultset.getString("Occupation"));
				customerDetail.setCustomerIndex(resultset
						.getString("CustomerIndex"));
				customerDetail.setCustomerBarCode(resultset
						.getString("CustomerBarCode"));
				SystemLogger.logDebug("CustomerDetails: "
						+ customerDetail.toString());
			}
		} catch (SQLException e) {
			SystemLogger.logError(e.getMessage(), e);
		}

		return customerDetail;
	}

	public static boolean updateCustomerDetail(BusinessCustomer customerForm) {
		boolean updateStatus = false;

		BusinessCustomer businessCustomer = getBusinessCustomer(customerForm);
		if (businessCustomer != null) {
			customerForm.setCustomerId(businessCustomer.getCustomerId());
			mergeObject(customerForm);
			updateStatus = true;
		}
		businessCustomer = getBusinessCustomer(businessCustomer);
		return updateStatus;
	}

	public static BusinessCustomer addBusinessCustomer(
			BusinessCustomer customerForm) {
		boolean insertStatus = false;

		BusinessCustomer businessCustomer = getBusinessCustomer(customerForm);
		if (businessCustomer == null) {
			businessCustomer = customerForm;
			saveObject(businessCustomer);
			// _userBusinessSession.getTransaction().commit();
		}
		businessCustomer = getBusinessCustomer(businessCustomer);
		return businessCustomer;
	}

	public static BusinessCustomer getBusinessCustomer(
			BusinessCustomer customerForm) {
		BusinessCustomer businessCustomer = null;
		Query query = null;
		if (customerForm.getCustomerId() > 0) {
			query = createQuery("from BusinessCustomer where customerid=:customerid ");
			query.setParameter("customerid", customerForm.getCustomerId());
		} else if (!customerForm.getCustomerFirstName().equalsIgnoreCase("")
				&& !customerForm.getCustomerLastName().equalsIgnoreCase("")) {
			query = createQuery("from BusinessCustomer where customerfirstname like :customerfirstname and customerlastname like :customerlastname ");
			query.setParameter("customerfirstname",
					"%" + customerForm.getCustomerFirstName() + "%");
			query.setParameter("customerlastname",
					"%" + customerForm.getCustomerLastName() + "%");
		} else if (!customerForm.getCustomerFirstName().equalsIgnoreCase("")) {
			query = createQuery("from BusinessCustomer where customerfirstname like :customerfirstname ");
			query.setParameter("customerfirstname",
					"%" + customerForm.getCustomerFirstName() + "%");
		} else if (!customerForm.getCustomerLastName().equalsIgnoreCase("")) {
			query = createQuery("from BusinessCustomer where customerlastname like :customerlastname ");
			query.setParameter("customerlastname",
					"%" + customerForm.getCustomerLastName() + "%");
		} else if (customerForm.getContactNumber() > 0) {
			query = createQuery("from BusinessCustomer where customerlastname like :contactnumber ");
			query.setParameter("contactnumber",
					"%" + customerForm.getCustomerLastName() + "%");
		} else {
			return businessCustomer;
		}

		List<BusinessCustomer> list = query.list();
		Iterator<BusinessCustomer> iter = list.iterator();
		while (iter.hasNext()) {

			businessCustomer = iter.next();
			SystemLogger.logMessage("Fetched BusinessCustomer- "
					+ businessCustomer.getCustomerId() + ", "
					+ businessCustomer.getCustomerFirstName() + ", "
					+ businessCustomer.getCustomerLastName() + ", "
					+ businessCustomer.getCustomerIndex());

		}
		commitTransaction();
		return businessCustomer;
	}

	public static void insertCustomerDetail(BusinessCustomer customerForm) {
		StringBuffer insertCustomerDetail = new StringBuffer(
				"INSERT INTO `businessadvancedatabase`.`customerDetails` "
						+ "(`customerFirstName`, `CustomerLastName`, "
						+ "`BirthDate`, `AddressFirstLine`, "
						+ "`AddressSecondLine`, `LandMark`, `City`, "
						+ "`State`, `Zip`, `ContactNumber`, `Occupation`, "
						+ "`CustomerIndex`, `CustomerBarCode`) VALUES " + "('"
						+ customerForm.getCustomerFirstName()
						+ "', '"
						+ customerForm.getCustomerLastName()
						+ "', '"
						+ customerForm.getBirthDate()
						+ "', "
						+ "'"
						+ customerForm.getAddressFirstLine()
						+ "', '"
						+ customerForm.getAddressSecondLine()
						+ "', "
						+ "'"
						+ customerForm.getLandMark()
						+ "', '"
						+ customerForm.getCity()
						+ "', "
						+ "'"
						+ customerForm.getState()
						+ "', '"
						+ customerForm.getZip()
						+ "', "
						+ "'"
						+ customerForm.getContactNumber()
						+ "', '"
						+ customerForm.getOccupation()
						+ "', '5', '"
						+ customerForm.getCustomerBarCode() + "')");
		SystemLogger.logDebug(insertCustomerDetail.toString());
		try {

			_conn.createStatement().execute(insertCustomerDetail.toString());

		} catch (SQLException e) {
			SystemLogger.logError(e.getMessage(), e);
		}

	}

}
