package com.businessadvancesolutions.dbapi.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	protected static void saveObject(Object object) {
		if (_session.getTransaction() != null
				&& _session.getTransaction().isActive()) {
			Transaction tx = _session.getTransaction();
			_session.save(object);
			tx.commit();
		} else {
			Transaction tx = _session.beginTransaction();
			_session.save(object);
			tx.commit();
		}
		if (!_session.isOpen()) {
			SessionFactory sessionFactory = new AnnotationConfiguration()
					.configure().buildSessionFactory();
			_session = sessionFactory.getCurrentSession();
		}
	}

	protected static void updateObject(Object object) {
		if (_session.getTransaction() != null
				&& _session.getTransaction().isActive()) {
			Transaction tx = _session.getTransaction();
			_session.update(object);
			tx.commit();
		} else {
			Transaction tx = _session.beginTransaction();
			_session.update(object);
			tx.commit();
		}
		if (!_session.isOpen()) {
			SessionFactory sessionFactory = new AnnotationConfiguration()
					.configure().buildSessionFactory();
			_session = sessionFactory.getCurrentSession();
		}
	}

	protected static void mergeObject(Object object) {
		if (_session.getTransaction() != null
				&& _session.getTransaction().isActive()) {
			Transaction tx = _session.getTransaction();
			_session.merge(object);
			tx.commit();
		} else {
			Transaction tx = _session.beginTransaction();
			_session.merge(object);
			tx.commit();
		}
		if (!_session.isOpen()) {
			SessionFactory sessionFactory = new AnnotationConfiguration()
					.configure().buildSessionFactory();
			_session = sessionFactory.getCurrentSession();
		}
	}

	protected static Query createQuery(String queryString) {
		Query query = null;
		if (_session.getTransaction() != null
				&& _session.getTransaction().isActive()) {

			query = _session.createQuery(queryString);
		} else {
			_session.beginTransaction();
			query = _session.createQuery(queryString);
		}
		return query;
	}

	protected static void commitTransaction() {
		// _session.getTransaction().commit();
	}
}
