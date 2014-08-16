package org.abs.base.dbs;

import java.util.Date;

import org.abs.bean.Address;
import org.abs.bean.Customer;
import org.abs.bean.Discount;
import org.abs.bean.Invoice;
import org.abs.bean.Product;
import org.abs.bean.SaftyIndex;
import org.abs.bean.Sale;
import org.abs.bean.Supplier;
import org.abs.bean.SystemRole;
import org.abs.bean.SystemUser;
import org.abs.bean.map.SaftyIndexMap;
import org.apache.log4j.Logger;
import org.hibernate.Query;
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

public class DatabaseConnectionTest {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger
			.getLogger(DatabaseConnectionTest.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "DatabaseConnectionTest: ";
	}

	public static void main(String[] args) {
		DatabaseConnectionTest dbConnectTest = new DatabaseConnectionTest();
		log4j.debug("Start DatabaseConnectionTest...");
		// loads configuration and mappings
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

		// builds a session factory from the service registry
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);

		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		String stringQuery = null;
		Query query = null;

		stringQuery = "DELETE FROM Sale";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM Invoice";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM Product";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM Supplier";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM Customer";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM Address";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM Discount";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM SaftyIndex";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM SystemUser";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		stringQuery = "DELETE FROM SystemRole";
		query = session.createQuery(stringQuery);
		query.executeUpdate();

		SystemRole systemAdminRole = new SystemRole("ADMIN");
		SystemRole systemUserRole = new SystemRole("USER");

		SystemUser systemAdminUser = new SystemUser();
		systemAdminUser.setName("Amit Kshirsagar");
		systemAdminUser.setEmailId("amit.kshirsagar.13@gmail.com");
		systemAdminUser.setStatus("ACTIVE");
		systemAdminUser.setSystemRole(systemAdminRole);
		systemAdminUser.setPassword("abs123");
		systemAdminUser.setCrtDate(new Date());

		SystemUser systemUserUser = new SystemUser();
		systemUserUser.setName("Poonam Kshirsagar");
		systemUserUser.setEmailId("poonam.kshirsagar.13@gmail.com");
		systemUserUser.setStatus("ACTIVE");
		systemUserUser.setSystemRole(systemUserRole);
		systemUserUser.setPassword("abs123");
		systemUserUser.setCrtDate(new Date());

		session.save(systemAdminUser);
		session.save(systemUserUser);

		SaftyIndex saftyIndex1 = new SaftyIndex("VerySafe");
		SaftyIndex saftyIndex2 = new SaftyIndex("Safe");
		SaftyIndex saftyIndex3 = new SaftyIndex("Nutral");
		SaftyIndex saftyIndex4 = new SaftyIndex("Risk");
		SaftyIndex saftyIndex5 = new SaftyIndex("VeryRisk");

		SaftyIndexMap saftyIndexMap = new SaftyIndexMap();

		saftyIndexMap.addSaftyIndex(saftyIndex1);
		saftyIndexMap.addSaftyIndex(saftyIndex2);
		saftyIndexMap.addSaftyIndex(saftyIndex3);
		saftyIndexMap.addSaftyIndex(saftyIndex4);
		saftyIndexMap.addSaftyIndex(saftyIndex5);

		saftyIndexMap.save(session);

		Address address = new Address();
		address.setFirstLine("Sonali Collections");
		address.setSecondLine("Main Road");
		address.setLandMark("Nehru Chuak");
		address.setCity("Osmanabad");
		address.setZip(413501);
		address.setCrtDate(new Date());

		Customer customer = new Customer();
		customer.setFirstName("Sonali");
		customer.setLastName("Kathare");
		customer.setAddress(address);
		customer.setEmailId("veena.kathare.13@gmail.com");
		customer.setContactNumber("9123485739");
		customer.setBirthDate(new Date(1985, 3, 10));
		customer.setOccupation("Business");
		customer.setSaftyIndex(saftyIndex1);
		customer.setCrtDate(new Date());

		Supplier supplier = new Supplier();
		supplier.setSupplierName("Sonali Collections");
		supplier.setCustomer(customer);
		supplier.setSupplierComment("Owner");
		supplier.setAddress(address);
		supplier.setCrtDate(new Date());

		Product product = supplier.getNewProduct();
		product.setProductName("Salwar Dress");
		product.setProductType("SALWAR");
		product.setLotNumber(1);
		product.setLotSize(1000);
		product.setMargin(35);
		product.setInventory(1000);
		product.setBasePrice(850);
		product.setCrtDate(new Date());

		Product product1 = supplier.getNewProduct();
		product1.setProductName("Lehnga Dress");
		product1.setProductType("LEHNGA");
		product1.setLotNumber(2);
		product1.setLotSize(500);
		product1.setMargin(35);
		product1.setInventory(500);
		product1.setBasePrice(850);
		product1.setCrtDate(new Date());

		session.save(supplier);

		Discount discount = new Discount();
		discount.setDescription("NormalSale");
		discount.setDiscount(1);
		discount.setSeverity(0);

		Invoice invoice = new Invoice();

		invoice.setCustomer(customer);
		invoice.setCrtDate(new Date());
		invoice.setSystemUser(systemAdminUser);

		Sale sale = invoice.getNewSale();
		sale.setInvoice(invoice);
		sale.setProduct(product);
		sale.setQuantity(3);
		sale.setDiscount(discount);

		Sale sale1 = invoice.getNewSale();
		sale1.setInvoice(invoice);
		sale1.setProduct(product1);
		sale1.setQuantity(5);
		sale1.setDiscount(discount);

		session.save(invoice);
		session.getTransaction().commit();
		session.close();

	}
}