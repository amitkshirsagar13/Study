package com.sample.base.model.store;

/**
 * ProjectName: SwingsModel
 * @author amit_kshirsagar
 * @date Jan 29, 2014
 */

import java.util.Vector;

import org.apache.log4j.Logger;

public class PersonRecord extends RecordsBase {
	static Logger log = Logger.getLogger(PersonRecord.class.getName());

	/**
	 * 
	 */
	public PersonRecord(String id, String name, String place, String role) {
		this.id = id;
		this.name = name;
		this.place = place;
		this.role = role;
	}

	private String id = null;
	private String name = null;
	private String place = null;
	private String role = null;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place
	 *            the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sample.base.model.RecordBase#getRecordVector()
	 */
	@Override
	public Vector getRecordVector() {
		record.add(id);
		record.add(name);
		record.add(place);
		record.add(role);
		return record;
	}

}
