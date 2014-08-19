package org.abs.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.abs.util.dbutil.DatabaseConnectionProvider;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 17, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(BaseService.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "BaseService: ";
	}

	protected Session session = null;

	public void closeSession() {
		if (session != null && session.isOpen()) {
			session.close();
		}
	}

	public void openSession() {
		if (session == null || !session.isOpen()) {
			session = DatabaseConnectionProvider.openSession();
		}
	}

	public Query getQueryParameterized(Query query,
			Map<String, Object> parameterMap) {
		Set<String> keys = parameterMap.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			log4j.debug("Setting Parameter: " + key + "|"
					+ parameterMap.get(key));
			query.setParameter(key, parameterMap.get(key));
		}
		return query;
	}
}
