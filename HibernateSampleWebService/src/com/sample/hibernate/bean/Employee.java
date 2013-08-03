package com.sample.hibernate.bean;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Employee")
public class Employee implements HibernateSampleConstants {
	private int id;

	private String firstName;
	private String lastName;
	private int salary;
	@XmlElementWrapper(name = "Addresses")
	@XmlElement(name = "Address")
	private List<Address> addresses = null;

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string = "EmployeeID: " + id + CONNECTOR + firstName + CONNECTOR
				+ lastName + CONNECTOR + salary + NEWLN;
		if (addresses != null) {
			for (Iterator<Address> iterator = addresses.iterator(); iterator
					.hasNext();) {
				Address address = iterator.next();
				string = string.concat(address.toString() + NEWLN);
			}
		}

		return string;
	}
}