package org.abs.bean.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.abs.bean.SaftyIndex;
import org.apache.log4j.Logger;
import org.hibernate.Session;

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
public class SaftyIndexMap extends HashMap<String, SaftyIndex> {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(SaftyIndexMap.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SaftyIndexMap: ";
	}

	/**
	 * @param saftyIndex5
	 */
	public void addSaftyIndex(SaftyIndex saftyIndex) {
		if (!this.containsKey(saftyIndex.getSaftyIndexName())) {
			this.put(saftyIndex.getSaftyIndexName(), saftyIndex);
		}
	}

	/**
	 * @param session
	 */
	public void save(Session session) {
		Set<String> keySet = this.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String saftyIndexName = (String) iterator.next();
			session.save(this.get(saftyIndexName));
		}
	}
}
