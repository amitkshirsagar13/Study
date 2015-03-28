package com.sample.hibernate.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sample.hibernate.store.HibernateSampleConstants;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 2, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public abstract class AbstractTransactionFactory implements
		HibernateSampleConstants {
	private static SessionFactory factory;

	private Session session = null;

	private static void initiateHibernateFactory() {
		if (factory != null) {
			return;
		}
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static AbstractTransactionFactory getTransactionFactory(
			String factoryName) {
		AbstractTransactionFactory transactionFactory = null;
		if (factoryName.equalsIgnoreCase(EMPLOYEEFACTORY)) {
			initiateHibernateFactory();
			transactionFactory = EmployeeFactory.getEmployeeFactory();
		}
		return transactionFactory;
	}

	protected Session getTransactionSession() {
		if (session == null) {
			session = factory.openSession();
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		return session;
	}

	protected void commitSession(Session session) {
		if (session.getTransaction().isActive()) {
			commitTransaction(session.getTransaction());
		}
	}

	protected void commitTransaction(Transaction tx) {
		try {
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	protected void rollbackTransaction(Transaction tx) {
		try {
			tx.rollback();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

}
