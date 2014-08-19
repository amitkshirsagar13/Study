package org.abs.service.system;

import java.util.Iterator;
import java.util.List;

import org.abs.bean.SystemRole;
import org.abs.service.BaseService;
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
public class SystemRoleService extends BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(SystemRoleService.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SystemRoleService: ";
	}

	public SystemRole loginSystemRole(SystemRole systemRole) {
		openSession();
		String sql = "FROM SystemRole where roleName=:roleName";

		Query query = session.createQuery(sql);
		query.setParameter("roleName", systemRole.getRoleName());
		List<SystemRole> results = query.list();
		if (results.size() == 1) {
			systemRole = results.get(0);
		} else {
			systemRole = null;
		}
		System.out.println(systemRole);
		closeSession();
		return systemRole;
	}

	public List<SystemRole> getSystemRoleList(SystemRole systemRole) {
		openSession();
		Criteria query = session.createCriteria(SystemRole.class);
		query.add(Restrictions.like("roleName", systemRole.getRoleName(),
				MatchMode.END));
		List<SystemRole> results = query.list();
		closeSession();
		return results;
	}

	public void saveOrMergeSystemRole(SystemRole systemRole) {
		openSession();
		session.merge(systemRole);
		closeSession();
	}

	public void saveOrMergeSystemRoles(List<SystemRole> systemRoleList) {
		openSession();
		for (Iterator iterator = systemRoleList.iterator(); iterator.hasNext();) {
			SystemRole systemRole = (SystemRole) iterator.next();
			session.merge(systemRole);
		}
		closeSession();
	}

	public void deleteSystemRole(SystemRole systemRole) {
		openSession();
		session.delete(systemRole);
		closeSession();
	}
}
