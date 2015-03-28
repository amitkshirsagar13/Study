package com.tutorialspoint;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperReportFill {
	static Logger log = Logger.getLogger(JasperReportFill.class.getName());

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String sourceFileName = "./test/jasper_report_template.jasper";
		DataBeanList DataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataList);

		Map parameters = new HashMap();
		try {
			JasperFillManager.fillReportToFile(sourceFileName, parameters,
					beanColDataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
