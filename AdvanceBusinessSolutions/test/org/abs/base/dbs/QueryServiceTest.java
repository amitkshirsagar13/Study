package org.abs.base.dbs;

import java.util.List;

import org.abs.bean.Address;
import org.abs.bean.Product;
import org.abs.bean.SystemUser;
import org.abs.service.BaseService;
import org.abs.service.system.SystemUserService;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 13, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */

public class QueryServiceTest extends BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(QueryServiceTest.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "DatabaseConnectionTest: ";
	}

	public static void main(String[] args) {
		QueryServiceTest queryServiceTest = new QueryServiceTest();
		queryServiceTest.testQueries();
	}

	public void testQueries() {
		SystemUserService systemUserService = new SystemUserService();

		SystemUser systemUser = new SystemUser();
		systemUser = new SystemUser();
		systemUser.setEmailId("poonam.kshirsagar.13@gmail.com");
		List<SystemUser> systemUserList = systemUserService
				.getResultsForCriteria(systemUser);
		for (SystemUser systemUser1 : systemUserList) {
			log4j.debug(systemUser1);
		}
		log4j.debug("======================================================");
		Address address = new Address();
		address.setZip(413501);
		List<Address> addressList = systemUserService
				.getResultsForCriteria(address);
		for (Address address1 : addressList) {
			log4j.debug(address1);
		}
		log4j.debug("======================================================");
		address = new Address();
		address.setCity("Pune");
		addressList = systemUserService.getResultsForCriteria(address);
		for (Address address1 : addressList) {
			log4j.debug(address1);
		}
		log4j.debug("======================================================");
		Product product = new Product();
		product.setProductName("%Dress");
		List<Product> productList = systemUserService
				.getResultsForCriteria(product);
		for (Product product1 : productList) {
			log4j.debug(product1);
		}

		log4j.debug("======================================================");
		Session session = systemUserService.getSession();
		systemUserList = session.createCriteria(SystemUser.class, "30").list();
		for (SystemUser systemUser1 : systemUserList) {
			log4j.debug(systemUser1);
		}
		log4j.debug("======================================================");

		address = new Address();
		address.setCity("Pune");
		address.setZip(411033);
		Example addressExample = Example.create(address);
		Criteria criteria = session.createCriteria(Address.class).add(
				addressExample);
		addressList = criteria.list();
		for (Address address1 : addressList) {
			log4j.debug(address1);
		}
		log4j.debug("======================================================");

	}
}