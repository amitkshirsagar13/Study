package org.abs.bean;

import java.util.Date;

import org.abs.util.AbsBaseConstants;
import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 14, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class Customer extends RecordDate implements AbsBaseConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	protected static Logger log4j = Logger.getLogger(Customer.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "Customer: " + firstName + CONNECTOR + lastName + CONNECTOR
				+ CONNECTOR + address + CONNECTOR + CONNECTOR + contactNumber
				+ CONNECTOR + emailId + CONNECTOR + occupation + CONNECTOR
				+ birthDate + CONNECTOR + saftyIndex + CONNECTOR + creditPoints;
	}

	protected String firstName;
	protected String lastName;
	protected Address address;
	protected String contactNumber;
	protected String emailId;
	protected String occupation;
	protected Date birthDate;
	protected SaftyIndex saftyIndex;
	protected String barCode;
	protected int creditPoints;
	protected Date crtDate;
	protected Date updDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public SaftyIndex getSaftyIndex() {
		return saftyIndex;
	}

	public void setSaftyIndex(SaftyIndex saftyIndex) {
		this.saftyIndex = saftyIndex;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getCreditPoints() {
		return creditPoints;
	}

	public void setCreditPoints(int creditPoints) {
		this.creditPoints = creditPoints;
	}

	@Override
	public Date getCrtDate() {
		return crtDate;
	}

	@Override
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	@Override
	public Date getUpdDate() {
		return updDate;
	}

	@Override
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

}
