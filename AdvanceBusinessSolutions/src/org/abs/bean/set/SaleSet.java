package org.abs.bean.set;

import java.util.HashSet;
import java.util.Iterator;

import org.abs.bean.Sale;
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
public class SaleSet extends HashSet<Sale> {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(SaleSet.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SaleSet: ";
	}

	public void addSale(Sale sale) {
		sale.setItemSrNo(this.size() + 1);
		this.add(sale);
	}

	public Sale getNewSale() {
		Sale sale = new Sale();
		this.addSale(sale);
		return sale;
	}

	public Sale getSaleItemSrNo(int itemSrNo) {
		for (Iterator<Sale> iterator = this.iterator(); iterator.hasNext();) {
			Sale sale = iterator.next();
			if (itemSrNo == sale.getItemSrNo()) {
				return sale;
			}
		}
		return null;
	}

	public void removeSale(Sale sale) {
		this.remove(sale);
		int itemSrNoChg = sale.getItemSrNo();
		for (Iterator<Sale> iterator = this.iterator(); iterator.hasNext();) {
			Sale saleChg = iterator.next();
			if (itemSrNoChg > saleChg.getItemSrNo()) {
				saleChg.setItemSrNo(saleChg.getItemSrNo() - 1);
			}
		}
	}
}
