package com.jswing.base;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Feb 3, 2014
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

import org.apache.log4j.Logger;

import com.jswing.invoice.data.DataBean;
import com.jswing.invoice.data.DataBeanList;
import com.jswing.invoice.report.Report;

public class SwingsBase extends JFrame implements ActionListener {
	static Logger log = Logger.getLogger(SwingsBase.class.getName());
	JPanel centerPanel = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SwingsBase baseFrame = new SwingsBase();
		baseFrame.runFrame();
	}

	public void runFrame() {

		setLocation(100, 100);
		setSize(900, 800);
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JButton reprint = new JButton("Reprint");
		reprint.setActionCommand("Reprint");
		reprint.addActionListener(this);
		topPanel.add(reprint);
		topPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		// topPanel.setBackground(Color.BLACK);
		add(topPanel, BorderLayout.NORTH);

		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		centerPanel.setBackground(new Color(150, 150, 150));

		add(centerPanel, BorderLayout.CENTER);

		DataBeanList DataBeanList = new DataBeanList();
		dataList = DataBeanList.getDataBeanList();
		showReport();

		JPanel bottom = new JPanel();
		JButton ok = new JButton("Hide");
		ok.setActionCommand("Hide");
		ok.addActionListener(this);
		bottom.add(ok);
		bottom.setBorder(BorderFactory.createLoweredBevelBorder());
		// topPanel.setBackground(Color.BLACK);
		add(bottom, BorderLayout.SOUTH);

		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		jrViewer.setPreferredSize(new Dimension(centerPanel.getWidth() - 100,
				centerPanel.getHeight() - 20));
	}

	JRViewer jrViewer = null;
	ArrayList<DataBean> dataList = null;
	Report rpt = null;

	public void showReport() {
		try {
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataList);

			// statement = connection.createStatement();
			HashMap parameterMap = new HashMap();
			parameterMap.put("rtitle", "Report Title Here");// sending the
															// report title as a
			if (rpt == null) { // parameter.
				rpt = new Report(parameterMap, beanColDataSource);
			}
			rpt.setCon(beanColDataSource);
			rpt.setReportName("contacts"); // productlist is the name of my
											// jasper file.
			jrViewer = rpt.callReport();

			// jrViewer.setAutoscrolls(false);
			// jrViewer.setPreferredSize(null);
			centerPanel.add(jrViewer);
			// centerPanel.remove(jrViewer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evn) {
		if (((JButton) evn.getSource()).getActionCommand().equalsIgnoreCase(
				"Hide")) {

			centerPanel.remove(jrViewer);
			// this.revalidate();
			centerPanel.repaint();
			((JButton) evn.getSource()).setActionCommand("Show");
			((JButton) evn.getSource()).setText("Show");
		} else if (((JButton) evn.getSource()).getActionCommand()
				.equalsIgnoreCase("Reprint")) {
			centerPanel.remove(jrViewer);

			DataBean dataBean = new DataBean();
			dataBean.setName("Amit");
			dataBean.setCountry("USA");
			dataList.add(dataBean);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataList);
			HashMap parameterMap = new HashMap();
			parameterMap.put("rtitle", "Report Title Here");// sending the
			rpt.setCon(beanColDataSource);
			rpt.setReportName("contacts"); // productlist is the name of my
											// jasper file.
			jrViewer = rpt.callReport();

			centerPanel.add(jrViewer);
			jrViewer.setPreferredSize(new Dimension(
					centerPanel.getWidth() - 100, centerPanel.getHeight() - 20));
			centerPanel.revalidate();
		} else {
			centerPanel.add(jrViewer);
			// this.revalidate();
			centerPanel.repaint();
			((JButton) evn.getSource()).setActionCommand("Hide");
			((JButton) evn.getSource()).setText("Hide");
		}

	}
}
