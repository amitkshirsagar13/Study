package org.abs.service.system;

import java.util.Iterator;
import java.util.List;

import org.abs.bean.SystemUser;
import org.abs.service.BaseService;
import org.apache.log4j.Logger;

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
		List<SystemUser> results = getResultsForCriteria(systemUser);
		if (results.size() == 1) {
			systemUser = results.get(0);
		} else {
			systemUser = null;
		}
		return systemUser;
	}

	public List<SystemUser> getSystemUserList(SystemUser systemUser) {
		List<SystemUser> results = getResultsForCriteria(systemUser);
		return results;
	}

	public void saveOrMergeSystemUser(SystemUser systemUser) {
		openSession();
		session.merge(systemUser);
		closeSession();
	}

	public void saveOrMergeSystemUsers(List<SystemUser> systemUserList) {
		openSession();
		for (Iterator iterator = systemUserList.iterator(); iterator.hasNext();) {
			SystemUser systemUser = (SystemUser) iterator.next();
			session.merge(systemUser);
		}
		closeSession();
	}

	public void deleteSystemUser(SystemUser systemUser) {
		openSession();
		session.delete(systemUser);
		closeSession();
	}
}
