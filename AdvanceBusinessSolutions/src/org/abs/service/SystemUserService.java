package org.abs.service;

import java.util.List;

import org.abs.bean.SystemUser;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
public class SystemUserService extends BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(SystemUserService.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SystemUserService: ";
	}

	public SystemUser loginSystemUser(SystemUser systemUser) {
		openSession();
		String sql = "FROM SystemUser where emailId=:emailId and password=:password";

		Query query = session.createQuery(sql);
		query.setParameter("emailId", systemUser.getEmailId());
		query.setParameter("password", systemUser.getPassword());
		List<SystemUser> results = query.list();
		if (results.size() == 1) {
			systemUser = results.get(0);
		} else {
			systemUser = null;
		}
		System.out.println(systemUser);
		closeSession();
		return systemUser;
	}

	public List<SystemUser> getSystemUserList(SystemUser systemUser) {
		openSession();
		Criteria query = session.createCriteria(SystemUser.class);
		query.add(Restrictions.like("emailId", systemUser.getEmailId(),
				MatchMode.END));
		// query.add(Restrictions.like("password", systemUser.getPassword(),
		// MatchMode.START));
		List<SystemUser> results = query.list();
		for (int index = 0; index < results.size(); index++) {
			System.out.println(results.get(index));
		}
		closeSession();
		return results;
	}
}
