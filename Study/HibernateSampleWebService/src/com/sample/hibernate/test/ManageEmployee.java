package com.sample.hibernate.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.sample.hibernate.bean.Address;
import com.sample.hibernate.bean.Employee;
import com.sample.hibernate.dao.AbstractTransactionFactory;
import com.sample.hibernate.dao.EmployeeFactory;
import com.sample.hibernate.store.HibernateSampleConstants;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 1, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */

public class ManageEmployee implements HibernateSampleConstants {

	static Logger logger = Logger.getLogger(ManageEmployee.class.getName());

	public static void main(String[] args) {

		EmployeeFactory employeeFactory = (EmployeeFactory) AbstractTransactionFactory
				.getTransactionFactory(EMPLOYEEFACTORY);

		Employee employee = new Employee();
		employee.setFirstName("Amit");
		employee.setLastName("Kshirsagar");
		employee.setSalary(70000);

		logger.info("List Employee at Initiation:");
		List<Employee> employeeList = employeeFactory.listAllEmployee();
		for (Iterator<Employee> iterator = employeeList.iterator(); iterator
				.hasNext();) {
			employee = iterator.next();
			logger.info(employee.toString());

		}

		employeeFactory.addEmployee(employee);
		logger.info("List Employee After First Addition:");
		employeeList = employeeFactory.listAllEmployee();
		for (Iterator<Employee> iterator = employeeList.iterator(); iterator
				.hasNext();) {
			employee = iterator.next();
			logger.info(employee.toString());

		}

		Address address = new Address();
		address.setFirstLine("2320 Equestrian Dr, 2A");
		address.setCity("Miamisburg");
		address.setZip("45342");

		List<Address> addresses = new ArrayList<Address>();
		addresses.add(address);

		employee = employeeFactory.getEmployee(1);
		employee.setAddresses(addresses);
		employeeFactory.updateEmployee(employee);
		logger.info("List Employee After Address Update:");
		employeeList = employeeFactory.listAllEmployee();
		for (Iterator<Employee> iterator = employeeList.iterator(); iterator
				.hasNext();) {
			employee = iterator.next();
			logger.info(employee.toString());

		}

		employee = new Employee();
		employee.setFirstName("Poonam");
		employee.setLastName("Kshirsagar");
		employee.setSalary(35000);

		addresses = new ArrayList<Address>();

		address = new Address();
		address.setFirstLine("101 Saheels Elegance");
		address.setCity("Pune");
		address.setZip("411033");
		addresses.add(address);
		employee.setAddresses(addresses);

		employeeFactory.addEmployee(employee);
		logger.info("List Employee After Second Addition:");
		employeeList = employeeFactory.listAllEmployee();
		for (Iterator<Employee> iterator = employeeList.iterator(); iterator
				.hasNext();) {
			employee = iterator.next();
			logger.info(employee.toString());

		}

		employee = employeeFactory.getEmployee(1);
		employee.setSalary(75000);
		employeeFactory.updateEmployee(employee);
		employee.getAddresses().add(address);

		logger.info("List Employee After Second Update:");
		employeeList = employeeFactory.listAllEmployee();
		for (Iterator<Employee> iterator = employeeList.iterator(); iterator
				.hasNext();) {
			employee = iterator.next();
			logger.info(employee.toString());

		}
	}
}