package com.businessadvancesolutions.businessmodel;

import java.sql.Date;

public class BusinessCustomer {
	public int customerId = -1;

	public String customerFirstName = null;
	public String customerLastName = null;

	public Date birthDate = null;

	public String addressFirstLine = null;
	public String addressSecondLine = null;
	public String landMark = null;
	public String city = null;
	public String state = null;
	public int zip = -1;

	public long contactNumber = -1;
	public String occupation = null;

	public String customerIndex = null;
	public String customerComment = null;

	public String customerBarCode = null;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddressFirstLine() {
		return addressFirstLine;
	}

	public void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}

	public String getAddressSecondLine() {
		return addressSecondLine;
	}

	public void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCustomerIndex() {
		return customerIndex;
	}

	public void setCustomerIndex(String customerIndex) {
		this.customerIndex = customerIndex;
	}

	public String getCustomerComment() {
		return customerComment;
	}

	public void setCustomerComment(String customerComment) {
		this.customerComment = customerComment;
	}

	public String getCustomerBarCode() {
		return customerBarCode;
	}

	public void setCustomerBarCode(String customerBarCode) {
		this.customerBarCode = customerBarCode;
	}

	@Override
	public String toString() {
		return "\nCustomerID- " + customerId + "\nCustomerFirstName- "
				+ customerFirstName + "\nCustomerLastName- " + customerLastName
				+ "\nBirthDate- " + birthDate
				+ "\nAddress-\n\tAddressFirstLine- " + addressFirstLine
				+ "\n\tAddressSecondLine- " + addressSecondLine
				+ "\n\tLandMark- " + landMark + "\n\tCity- " + city
				+ "\n\tState- " + state + "\n\tZip- " + zip
				+ "\nContactNumber- " + contactNumber + "\nOccupation- "
				+ occupation + "\nCustomerIndex- " + customerIndex
				+ "\nCustomerBarCode- " + customerBarCode;
	}
}
