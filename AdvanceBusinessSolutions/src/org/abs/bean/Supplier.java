package org.abs.bean;

import java.util.Date;
import java.util.Set;

import org.abs.bean.set.ProductSet;
import org.abs.util.AbsBaseConstants;
import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 14, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class Supplier extends BaseEntity implements AbsBaseConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(Supplier.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "Supplier: ";
	}

	private String supplierName;
	private Customer customer;
	private Address address;
	private String supplierComment;
	private Date crtDate;
	private Date updDate;
	private Set products;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSupplierComment() {
		return supplierComment;
	}

	public void setSupplierComment(String supplierComment) {
		this.supplierComment = supplierComment;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Set getProducts() {
		if (products == null) {
			products = new ProductSet();
		}
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Product getNewProduct() {
		Product product = ((ProductSet) this.getProducts()).getNewProduct();
		product.setSupplier(this);
		return product;
	}
}
