package com.tcp.processor;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tcp.store.UserCustomerMap;

/**
 * public class UserCustomerProcessor {
 * 
 * }
 */

public class UserCustomerProcessor {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(UserCustomerProcessor.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "UserCustomerProcessor: ";
	}

	public static void main(String[] args) {
		UserCustomerProcessor processor = new UserCustomerProcessor();

		UserCustomerMap userCustomerMap = new UserCustomerMap();

		processor.populateFromTcp(userCustomerMap);

		processor.populateFromMission(userCustomerMap);

		processor.updateUserCustomerInTcp(userCustomerMap);

		userCustomerMap = new UserCustomerMap();

		processor.populateFromTcp(userCustomerMap);
		processor.showUserCustomers(userCustomerMap);

	}

	/**
	 * Populate the Customers from Tcp.
	 */

	public void populateFromTcp(UserCustomerMap userCustomerMap) {
		userCustomerMap.populateUserCustomerFromTcp();
		log4j.debug(userCustomerMap);
	}

	/**
	 * Populate the Customers from Mission.
	 */

	public void populateFromMission(UserCustomerMap userCustomerMap) {
		userCustomerMap.populateUserCustomerFromMission();
	}

	/**
	 * Show the Customers.
	 */

	public void showUserCustomers(UserCustomerMap userCustomerMap) {
		Set<Long> keySet = userCustomerMap.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Long customerId = (Long) iterator.next();
			log4j.debug(userCustomerMap.get(customerId));
		}
	}

	/**
	 * Update the Customers in Tcp.
	 */

	public void updateUserCustomerInTcp(UserCustomerMap userCustomerMap) {
		userCustomerMap.updateUserCustomerInTcp();
	}
}