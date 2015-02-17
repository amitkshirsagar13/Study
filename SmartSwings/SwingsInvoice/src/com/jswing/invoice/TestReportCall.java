package com.jswing.invoice;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.jswing.invoice.data.DataBean;
import com.jswing.invoice.data.DataBeanList;
import com.jswing.invoice.report.Report;

public class TestReportCall {
	static Logger log = Logger.getLogger(TestReportCall.class.getName());

	public static void main(String[] args) {
		try {
			DataBeanList DataBeanList = new DataBeanList();
			ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataList);

			// statement = connection.createStatement();
			HashMap parameterMap = new HashMap();
			parameterMap.put("rtitle", "Report Title Here");// sending the
															// report title as a
															// parameter.
			Report rpt = new Report(parameterMap, beanColDataSource);
			rpt.setReportName("contacts"); // productlist is the name of my
											// jasper file.
			rpt.callReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}