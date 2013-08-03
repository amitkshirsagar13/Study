package com.sample.hibernate.service.jaxws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listEmployeeResponse", namespace = "http://service.hibernate.sample.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listEmployeeResponse", namespace = "http://service.hibernate.sample.com/")
public class ListEmployeeResponse {

	@XmlElement(name = "Employee", namespace = "")
	private List<com.sample.hibernate.bean.Employee> employee;

	/**
	 * 
	 * @return returns List<Employee>
	 */
	public List<com.sample.hibernate.bean.Employee> getEmployee() {
		return this.employee;
	}

	/**
	 * 
	 * @param employee
	 *            the value for the employee property
	 */
	public void setEmployee(List<com.sample.hibernate.bean.Employee> employee) {
		this.employee = employee;
	}

}
