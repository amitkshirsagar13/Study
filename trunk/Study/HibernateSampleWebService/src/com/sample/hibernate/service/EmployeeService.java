package com.sample.hibernate.service;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.log4j.Logger;

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

@WebService(name = "EmployeeService", serviceName = "EmployeeService")
public class EmployeeService implements HibernateSampleConstants {
	static Logger logger = Logger.getLogger(EmployeeService.class.getName());
	@Resource
	WebServiceContext ctx;

	@WebMethod(operationName = "addEmployee", action = "addEmployee")
	@WebResult(name = "Employee")
	public Employee addEmployee(@WebParam(name = "Employee") Employee employee) {
		System.out.println("hi Here..." + employee.toString());
		EmployeeFactory employeeFactory = (EmployeeFactory) AbstractTransactionFactory
				.getTransactionFactory(EMPLOYEEFACTORY);
		employeeFactory.addEmployee(employee);
		return employee;
	}

	@WebMethod(operationName = "listEmployee", action = "listEmployee")
	@WebResult(name = "Employee")
	public List<Employee> ListEmployee() {

		EmployeeFactory employeeFactory = (EmployeeFactory) AbstractTransactionFactory
				.getTransactionFactory(EMPLOYEEFACTORY);
		List<Employee> employeeList = employeeFactory.listAllEmployee();
		return employeeList;
	}

}
