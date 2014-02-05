/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Feb 2, 2014
 * @author Amit Kshirsagar
 * @email amit.kshirsagar.13@gmail.com
 * @version 1.0
 * @since
 *
 * <p><b>Modification History:</b><p>
 *
 *
 * </pre>
 */

package org.masterswings.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.sf.jasperreports.view.JRViewer;

import org.apache.log4j.Logger;
import org.masterswings.base.view.BaseMasterSwingsFrame;
import org.masterswings.base.view.BaseMasterSwingsPanel;

import report.Report;
import report.test.OrderDetails;
import report.test.OrderDetailsList;

public class ReportPanel extends BaseMasterSwingsPanel {
	/**
	 * @param layoutManager
	 * @param mainFrame
	 */
	public ReportPanel(LayoutManager layoutManager,
			BaseMasterSwingsFrame mainFrame) {
		super(layoutManager, mainFrame);
	}

	Logger _log = Logger.getLogger(ReportPanel.class.getName());

	private void logMessage(String message, Throwable exception) {
		if (exception != null) {
			_log.error(message, exception);
		} else {
			_log.info(message);
		}
	}

	private void debug(String message) {
		_log.debug(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.masterswings.base.view.BaseMasterSwingsPanel#buildForm()
	 */
	@Override
	public void buildForm() {
		super.buildForm();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.masterswings.base.view.BaseMasterSwingsPanel#loadCenterPanel()
	 */
	@Override
	public void loadCenterPanel() {
		_centerPanel = new JPanel();
		_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		_centerPanel.setBackground(Color.BLUE);
		OrderDetailsList orderDetailsSet = new OrderDetailsList();
		for (int i = 0; i < 10; i++) {
			orderDetailsSet.add(new OrderDetails("Sr" + i, "Item" + i,
					"ItemPrice" + i, "ItemCount" + i, "ItemTotalPrice" + i));
		}
		OrderDetailsList.setOrderDetailsList(orderDetailsSet);
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("invoiceTotal", "InvoiceTotalCost");
		hm.put("invoiceNumber", "InvoiceNumber");
		hm.put("customerName", "Amit Kshirsagar");
		hm.put("customerAddressLine", "Address D Mart");
		hm.put("customerContact", "7276100034");

		Report report = new Report(hm);
		report.setReportName("Invoice");
		JRViewer reportView = report.getReport();
		reportView.setAutoscrolls(true);
		reportView.setPreferredSize(new Dimension(900, 600));
		reportView.setBorder(BorderFactory.createEtchedBorder());
		reportView.setToolTipText("JasperReportView" + reportView.getWidth()
				+ "x" + reportView.getHeight());

		JScrollPane reportScrollPane = new JScrollPane(reportView);
		reportScrollPane.setBackground(Color.pink);
		_centerPanel.add(reportScrollPane);
		this.add(_centerPanel, BorderLayout.CENTER);

	}
}
