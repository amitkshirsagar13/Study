/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 29, 2014
 * @author Amit Kshirsagar
 * @email amit.kshirsagar.13@gmail.com
 * @version 1.0
 * @since
 *
 * <p><b>Modification History:</b><p>
 *
 *
 * </pre>
 */

package org.masterswings.model.store;

import java.util.Vector;

import org.apache.log4j.Logger;

public class SamplePersonMasterSwings extends BaseMasterSwingsTableRecord {

	Logger _log = Logger.getLogger(SamplePersonMasterSwings.class.getName());

	private void logMessage(String message, Throwable exception) {
		if (exception != null) {
			_log.error(message, exception);
		} else {
			_log.info(message);
		}
	}

	private void debug(String message) {
		_log.debug(message);
	}

	/**
	 * @param id
	 * @param name
	 * @param place
	 * @param role
	 */
	public SamplePersonMasterSwings(String id, String name, String place,
			String role, boolean checked) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.role = role;
		this.checked = checked;
	}

	private String id = null;
	private String name = null;
	private String place = null;
	private String role = null;
	private boolean checked = false;

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
	 * @see
	 * org.masterswings.model.store.BaseMasterSwingsTableRecord#getRecordVector
	 * ()
	 */
	@Override
	public Vector<Object> getRecordVector() {
		_baseRecordVector.add(id);
		_baseRecordVector.add(name);
		_baseRecordVector.add(place);
		_baseRecordVector.add(role);
		_baseRecordVector.add(checked);
		return _baseRecordVector;
	}
}
