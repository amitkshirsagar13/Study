package org.abs.service.productinvoicesales;

import java.util.List;

import org.abs.bean.Sale;
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
public class SalesService extends BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(SalesService.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SaleService: ";
	}

	public Sale getSale(Sale sale) {
		return sale;
	}

	public List<Sale> getSaleList(Sale sale) {
		List<Sale> saleList = null;
		return saleList;
	}

	public void addSale(Sale sale) {

	}

	public void saveOrMergeSale(Sale sale) {

	}

	public void saveOrMergeSale(List<Sale> saleList) {

	}
}
