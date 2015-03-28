package org.abs.util.dbutil;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

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
public class DatabaseConnectionProvider {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger
			.getLogger(DatabaseConnectionProvider.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "DatabaseConnectionProvider: ";
	}

	private static DatabaseConnectionProvider databaseConnectionProvider = null;

	private DatabaseConnectionProvider() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

		// builds a session factory from the service registry
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static DatabaseConnectionProvider getInstance() {
		if (databaseConnectionProvider == null) {
			databaseConnectionProvider = new DatabaseConnectionProvider();
		}
		return databaseConnectionProvider;
	}

	private static Session session = null;
	private static SessionFactory sessionFactory = null;

	public static Session openSession() {
		if (databaseConnectionProvider == null) {
			getInstance();
		}
		session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}
}
