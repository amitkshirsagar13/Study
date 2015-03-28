package com.sample.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.sample.hibernate.bean.Address;
import com.sample.hibernate.bean.Employee;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 2, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class EmployeeFactory extends AbstractTransactionFactory {
	static Logger logger = Logger.getLogger(EmployeeFactory.class.getName());

	private static EmployeeFactory employeeFactory = null;

	private EmployeeFactory() {

	}

	public static EmployeeFactory getEmployeeFactory() {
		if (employeeFactory == null) {
			employeeFactory = new EmployeeFactory();
		}
		return employeeFactory;
	}

	public synchronized int addEmployee(Employee employee) {
		Session session = getTransactionSession();

		List<Address> addressList = employee.getAddresses();
		List<Address> checkedAddressList = new ArrayList<Address>();
		for (Iterator iterator = addressList.iterator(); iterator.hasNext();) {
			Address address = (Address) iterator.next();
			Address addressOld = (Address) session
					.createCriteria(Address.class).add(Example.create(address))
					.uniqueResult();
			if (addressOld != null) {
				checkedAddressList.add(addressOld);
			} else {
				checkedAddressList.add(address);
			}
		}
		employee.setAddresses(checkedAddressList);
		int employeeID = (Integer) session.save(employee);
		logger.info("Employee added as " + employeeID);
		commitSession(session);
		return employeeID;
	}

	public synchronized List<Employee> listAllEmployee() {
		Session session = getTransactionSession();
		List<Employee> employees = session.createQuery("FROM Employee").list();
		commitSession(session);
		return employees;
	}

	public synchronized Employee getEmployee(Integer EmployeeID) {
		Session session = getTransactionSession();
		Employee employee = (Employee) session.get(Employee.class, EmployeeID);
		commitSession(session);
		return employee;
	}

	/* Method to UPDATE salary for an employee */
	public synchronized void updateEmployee(Employee employee) {
		Session session = getTransactionSession();
		session.update(employee);
		commitSession(session);
	}

	/* Method to DELETE an employee from the records */
	public synchronized void deleteEmployee(Employee employee) {
		Session session = getTransactionSession();
		session.delete(employee);
		commitSession(session);
	}

}
