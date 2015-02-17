package com.jswing.invoice.report;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

public class Report extends JFrame {
	static Logger log = Logger.getLogger(Report.class.getName());

	HashMap hm = null;

	/**
	 * @return the con
	 */
	public JRBeanCollectionDataSource getCon() {
		return con;
	}

	/**
	 * @param con
	 *            the con to set
	 */
	public void setCon(JRBeanCollectionDataSource con) {
		this.con = con;
	}

	JRBeanCollectionDataSource con = null;
	String reportName;

	public Report() {
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public Report(HashMap map) {
		this.hm = map;
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public Report(HashMap map, JRBeanCollectionDataSource con) {
		this.hm = map;
		this.con = con;
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Report Viewer");

	}

	public void setReportName(String rptName) {
		this.reportName = rptName;
	}

	public JRViewer callReport() {
		JasperPrint jasperPrint = generateReport();
		JRViewer viewer = new JRViewer(jasperPrint);

		// Container c = getContentPane();
		// c.add(viewer);
		// this.setVisible(true);
		return viewer;
	}

	public JasperPrint getJasperPrint() {
		return generateReport();
	}

	JRViewer viewer = null;

	public void callConnectionLessReport() {
		JasperPrint jasperPrint = generateEmptyDataSourceReport();
		viewer = new JRViewer(jasperPrint);
		// Container c = getContentPane();
		// c.add(viewer);
		this.setVisible(true);
	}

	public void closeReport() {
		// jasperViewer.setVisible(false);
	}

	/** this method will call the report from data source */
	public JasperPrint generateReport() {
		try {
			JasperPrint jasperPrint = null;
			if (hm == null) {
				hm = new HashMap();
			}
			try {
				/**
				 * You can also test this line if you want to display report
				 * from any absolute path other than the project root path
				 */
				// jasperPrint =
				// JasperFillManager.fillReport("F:/testreport/"+reportName+".jasper",hm,
				// con);
				System.out.println(reportName);
				jasperPrint = JasperFillManager.fillReport(
						"./jasper_report_template.jasper", hm, con);
			} catch (JRException e) {
				e.printStackTrace();
			}
			return jasperPrint;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	/** call this method when your report has an empty data source */
	public JasperPrint generateEmptyDataSourceReport() {
		try {
			JasperPrint jasperPrint = null;
			if (hm == null) {
				hm = new HashMap();
			}
			try {
				System.out.println(reportName);
				jasperPrint = JasperFillManager.fillReport(reportName
						+ ".jasper", hm, new JREmptyDataSource());
			} catch (JRException e) {
				e.printStackTrace();
			}
			return jasperPrint;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
}
