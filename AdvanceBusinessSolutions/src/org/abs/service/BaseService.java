package org.abs.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.abs.bean.BaseEntity;
import org.abs.util.dbutil.DatabaseConnectionProvider;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
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

	public Criteria getCriteriaParameterized(Criteria query,
			Map<String, Object> parameterMap) {
		Set<String> keys = parameterMap.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();

			log4j.debug("Query Parameter: "
					+ parameterMap.get(key).getClass().getName() + "|" + key
					+ "|" + parameterMap.get(key));

			if (parameterMap.get(key).getClass().getName()
					.equalsIgnoreCase("java.lang.Integer")) {
				query.add(Restrictions.eq(key,
						Integer.parseInt(parameterMap.get(key).toString())));
				continue;
			}
			if (parameterMap.get(key).toString().startsWith("%")) {
				query.add(Restrictions.like(key, parameterMap.get(key)
						.toString().replaceAll("%", ""), MatchMode.END));
			} else if (parameterMap.get(key).toString().endsWith("%")) {
				query.add(Restrictions.like(key, parameterMap.get(key)
						.toString(), MatchMode.START));
			} else if (parameterMap.get(key).toString().contains("%")) {
				query.add(Restrictions.like(key, parameterMap.get(key)
						.toString(), MatchMode.ANYWHERE));
			} else {
				query.add(Restrictions
						.eq(key, parameterMap.get(key).toString()));
			}
		}
		return query;
	}

	public void lazyInitilize(List results) {
		for (Object resultRecord : results) {
			log4j.debug(resultRecord);
		}
	}

	public List getResultsForCriteria(BaseEntity baseEntity) {
		openSession();
		Criteria query = session.createCriteria(baseEntity.getClass());
		log4j.debug("Query: " + baseEntity.getClass().getName());
		query = getCriteriaParameterized(query, baseEntity.getFieldValueMap());
		List results = query.list();
		lazyInitilize(results);
		return results;
	}
}
