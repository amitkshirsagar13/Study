package com.businessadvancesolutions.dbapi.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class BusinessAdvanceDAO {
	private static Session _session = null;
	private static BusinessAdvanceDAO _businessAdvanceDao = null;

	protected BusinessAdvanceDAO() {
		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		_session = sessionFactory.getCurrentSession();
		_session.beginTransaction();
	}

	public static void initilize() {
		if (_businessAdvanceDao == null) {
			_businessAdvanceDao = new BusinessAdvanceDAO();
		}
	}

	protected static Session getSession() {
		return _session;
	}

}
