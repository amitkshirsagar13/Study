package org.abs.bean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 13, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class BaseEntity {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(BaseEntity.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "BaseEntity: " + id;
	}

	protected Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public boolean isNew() {
		return (this.id == null);
	}

	private Map<String, Object> fieldValueMap = new HashMap<String, Object>();

	private List<String> ignoreTypeList = null;

	private void populateIgnoreTypeList() {
		if (ignoreTypeList == null) {
			ignoreTypeList = new ArrayList<String>();
			ignoreTypeList.add("org.apache.log4j.Logger");
			ignoreTypeList.add("java.util.Map");
			ignoreTypeList.add("java.util.List");
			ignoreTypeList.add("java.util.Set");
			ignoreTypeList.add("org.abs.bean");
		}
	}

	public Map<String, Object> getFieldValueMap() {
		fieldValueMap.clear();
		populateIgnoreTypeList();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (!(ignoreTypeList.contains(fields[i].getType().getName()) || ignoreTypeList
					.contains(fields[i].getType().getPackage()))) {
				try {
					if (!(fields[i].get(this) == null || (fields[i].get(this)
							.getClass().getName()
							.equalsIgnoreCase("java.lang.Integer") && Integer
							.parseInt(fields[i].get(this).toString()) == -999))) {
						fieldValueMap.put(fields[i].getName(),
								fields[i].get(this));
					}
				} catch (IllegalArgumentException e) {
					log4j.error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					log4j.error(e.getMessage(), e);
				}
			}
		}
		return fieldValueMap;
	}
}
