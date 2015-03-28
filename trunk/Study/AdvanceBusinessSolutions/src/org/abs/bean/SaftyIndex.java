package org.abs.bean;

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
public class SaftyIndex extends BaseEntity implements AbsBaseConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	protected static Logger log4j = Logger.getLogger(SaftyIndex.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SaftyIndex: ";
	}

	public SaftyIndex() {

	}

	public SaftyIndex(String saftyIndexName) {
		this.saftyIndexName = saftyIndexName;
	}

	protected String saftyIndexName;

	public String getSaftyIndexName() {
		return saftyIndexName;
	}

	public void setSaftyIndexName(String saftyIndexName) {
		this.saftyIndexName = saftyIndexName;
	}
}
