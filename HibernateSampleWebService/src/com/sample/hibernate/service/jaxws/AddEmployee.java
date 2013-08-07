package com.sample.hibernate.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addEmployee", namespace = "http://service.hibernate.sample.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEmployee", namespace = "http://service.hibernate.sample.com/")
public class AddEmployee {

	@XmlElement(name = "Employee", namespace = "")
	private com.sample.hibernate.bean.Employee employee;

	/**
	 * 
	 * @return Employee
	 */
	public com.sample.hibernate.bean.Employee getEmployee() {
		return this.employee;
	}

	/**
	 * 
	 * @param employee
	 *            the value for the employee property
	 */
	public void setEmployee(com.sample.hibernate.bean.Employee employee) {
		this.employee = employee;
	}

}
