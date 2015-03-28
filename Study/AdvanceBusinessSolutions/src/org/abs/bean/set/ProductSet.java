package org.abs.bean.set;

import java.util.HashSet;

import org.abs.bean.Product;
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
public class ProductSet extends HashSet<Product> {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(ProductSet.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "ProductSet: ";
	}

	public void addProduct(Product product) {
		this.add(product);
	}

	public Product getNewProduct() {
		Product product = new Product();
		this.addProduct(product);
		return product;
	}
}
