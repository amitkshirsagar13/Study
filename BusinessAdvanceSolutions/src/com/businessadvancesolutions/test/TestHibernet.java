package com.businessadvancesolutions.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.businessadvancesolutions.businessmodel.BusinessUser;

public class TestHibernet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();

		createPerson(session);

		queryPerson(session);

	}

	private static void queryPerson(Session session) {
		Query query = session.createQuery("from businessUser");
		List<BusinessUser> list = query.list();
		java.util.Iterator<BusinessUser> iter = list.iterator();
		while (iter.hasNext()) {

			BusinessUser user = iter.next();
			System.out.println("businessUser: " + user.getUserId() + ", "
					+ user.getUserName() + ", " + user.getUserRole() + ", "
					+ user.getUserPassword());

		}

		session.getTransaction().commit();

	}

	public static void createPerson(Session session) {
		BusinessUser person = new BusinessUser();

		person.setUserName("poonam");
		person.setUserPassword("amogh");
		person.setUserRole(1);

		session.save(person);
	}
}
