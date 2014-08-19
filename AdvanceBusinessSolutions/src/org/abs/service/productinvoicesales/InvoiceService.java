package org.abs.service.productinvoicesales;

import java.util.List;

import org.abs.bean.Invoice;
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
public class InvoiceService extends BaseService {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(InvoiceService.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "InvoiceService: ";
	}

	public Invoice getInvoice(Invoice invoice) {
		return invoice;
	}

	public List<Invoice> getInvoiceList(Invoice invoice) {
		List<Invoice> invoiceList = null;
		return invoiceList;
	}

	public void addInvoice(Invoice invoice) {

	}

	public void saveOrMergeInvoice(Invoice invoice) {

	}

	public void saveOrMergeInvoice(List<Invoice> invoiceList) {

	}
}
