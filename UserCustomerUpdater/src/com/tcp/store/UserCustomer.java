package com.tcp.store;

import org.apache.log4j.Logger;

/**
 * public class UserCustomer {
 * 
 * }
 */

public class UserCustomer {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(UserCustomer.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "UserCustomer: " + customerId + "|" + customerNumber + "|"
				+ customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	private Long customerId = null;
	private Long customerNumber = null;
	private String customerName = null;

	public UserCustomer(Long customerId, Long customerNumber,
			String customerName) {
		setCustomerId(customerId);
		setCustomerNumber(customerNumber);
		setCustomerName(customerName);
	}

	public void setMissionCustomer(Long customerNumber, String customerName) {
		setCustomerNumber(customerNumber);
		setCustomerName(customerName);
	}

	public String getTcpUpdate() {
		return "update usercustomers set customernumber=" + customerNumber
				+ " , customername='" + getCustomerName().replaceAll("'", "''")
				+ "' where customerid=" + customerId;
	}
}