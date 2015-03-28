package com.jswing.invoice.data;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.logging.Logger;

public class DataBean {
	static Logger log = Logger.getLogger(DataBean.class.getName());

	private String name;
	private String country;
	private String id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getId() {
		return country;
	}

	public void setId(String id) {
		this.id = id;
	}
}
