package com.sample.base.model.store;

/**
 * ProjectName: SwingsModel
 * @author amit_kshirsagar
 * @date Jan 29, 2014
 */

import java.util.Vector;

import org.apache.log4j.Logger;

public class RecordsBase {
	static Logger log = Logger.getLogger(RecordsBase.class.getName());

	public Vector<Object> record = new Vector<Object>();

	public Vector getRecordVector() {
		return record;
	}
}
