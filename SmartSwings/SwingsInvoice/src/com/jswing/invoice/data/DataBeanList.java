package com.jswing.invoice.data;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.ArrayList;
import java.util.logging.Logger;

public class DataBeanList {
	static Logger log = Logger.getLogger(DataBeanList.class.getName());
	ArrayList<DataBean> dataBeanList = null;

	public ArrayList<DataBean> getDataBeanList() {
		dataBeanList = new ArrayList<DataBean>();

		dataBeanList.add(produce("Manisha", "India"));
		dataBeanList.add(produce("Dennis Ritchie", "USA"));
		dataBeanList.add(produce("V.Anand", "India"));
		dataBeanList.add(produce("Shrinath", "California"));

		return dataBeanList;
	}

	public void addDataBean(String name, String country) {
		dataBeanList.add(produce(name, country));
	}

	/**
	 * This method returns a DataBean object, with name and country set in it.
	 */
	private DataBean produce(String name, String country) {
		DataBean dataBean = new DataBean();
		dataBean.setName(name);
		dataBean.setCountry(country);
		return dataBean;
	}

}
