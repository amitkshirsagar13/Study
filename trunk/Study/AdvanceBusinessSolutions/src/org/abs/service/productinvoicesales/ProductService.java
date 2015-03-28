package org.abs.service.productinvoicesales;

import java.util.List;

import org.abs.bean.Product;
import org.abs.service.BaseService;
import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 18, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class ProductService extends BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(ProductService.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "ProductService: ";
	}

	public Product getProduct(Product product) {
		return product;
	}

	public List<Product> getProductList(Product product) {
		List<Product> productList = null;
		return productList;
	}

	public void addProduct(Product product) {

	}

	public void saveOrMergeProduct(Product product) {

	}

	public void saveOrMergeProduct(List<Product> productList) {

	}
}
