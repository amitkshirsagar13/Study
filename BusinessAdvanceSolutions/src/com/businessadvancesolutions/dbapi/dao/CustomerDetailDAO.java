package com.businessadvancesolutions.dbapi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.businessadvancesolutions.businessmodel.CustomerDetail;
import com.businessadvancesolutions.helper.SystemLogger;

public class CustomerDetailDAO {

	private static Connection _conn = null;
	private static CustomerDetailDAO customerDetailDao = null;

	private CustomerDetailDAO() {
	}

	public static CustomerDetailDAO getCustomerDetailDAO() {
		if (customerDetailDao == null) {
			customerDetailDao = new CustomerDetailDAO();
			customerDetailDao.setConnection();
		}
		return customerDetailDao;
	}

	private void setConnection() {
		_conn = GetDBConnection.getDatabaseConnection();
	}

	private void resetConnection() {
		_conn = GetDBConnection.getDatabaseConnection();
	}

	public static List<CustomerDetail> getCustomerDetailList(String customerID,
			String firstName, String lastName) {

		List<CustomerDetail> customerDetailList = new ArrayList<CustomerDetail>();
		CustomerDetail customerDetail = new CustomerDetail();

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
				customerDetail = new CustomerDetail();

				customerDetail.setCustomerID(resultset.getInt("customerID"));
				customerDetail.setCustomerFirstName(resultset
						.getString("customerFirstName"));
				customerDetail.setCustomerLastName(resultset
						.getString("CustomerLastName"));
				customerDetail.setBirthDate(resultset.getString("BirthDate"));
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

	public static CustomerDetail getCustomerDetail(String customerID,
			String firstName, String lastName) {

		CustomerDetail customerDetail = new CustomerDetail();

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
				customerDetail = new CustomerDetail();

				customerDetail.setCustomerID(resultset.getInt("customerID"));
				customerDetail.setCustomerFirstName(resultset
						.getString("customerFirstName"));
				customerDetail.setCustomerLastName(resultset
						.getString("CustomerLastName"));
				customerDetail.setBirthDate(resultset.getString("BirthDate"));
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

	public static void updateCustomerDetail(CustomerDetail customerForm) {
		if (customerForm.getCustomerID() < 0) {
			SystemLogger.logMessage("CustomerUpdate not possible as id is -1.");
		}
		StringBuffer updateCustomerDetail = new StringBuffer(
				"update `businessadvancedatabase`.`customerDetails` "
						+ "set `customerFirstName`='"
						+ customerForm.getCustomerFirstName()
						+ "', `CustomerLastName`='"
						+ customerForm.getCustomerLastName()
						+ "', `BirthDate`='"
						+ customerForm.getBirthDate()
						+ "', `AddressFirstLine`='"
						+ customerForm.getAddressFirstLine()
						+ "', `AddressSecondLine`='"
						+ customerForm.getAddressSecondLine()
						+ "', `LandMark`='"
						+ customerForm.getLandMark()
						+ "', `City`='"
						+ customerForm.getCity()
						+ "', `State`='"
						+ customerForm.getState()
						+ "', `Zip`='"
						+ customerForm.getZip()
						+ "', `ContactNumber`='"
						+ customerForm.getContactNumber()
						+ "', `Occupation`='"
						+ customerForm.getOccupation()
						+ "', `CustomerBarCode`='"
						+ customerForm.getCustomerBarCode()
						+ "' where customerID='"
						+ customerForm.getCustomerID()
						+ "'");
		SystemLogger.logDebug(updateCustomerDetail.toString());
		try {

			_conn.createStatement().execute(updateCustomerDetail.toString());

		} catch (SQLException e) {
			SystemLogger.logError(e.getMessage(), e);
		}

	}

	public static void insertCustomerDetail(CustomerDetail customerForm) {
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
