package org.abs.service;

import org.abs.service.dbutil.DatabaseConnectionProvider;
import org.apache.log4j.Logger;
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
}
