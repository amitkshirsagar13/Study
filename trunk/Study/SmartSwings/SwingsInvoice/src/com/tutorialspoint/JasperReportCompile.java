package com.tutorialspoint;

/**
 * ProjectName: SwingsInvoice
 * @author amit_kshirsagar
 * @date Jan 16, 2014
 */

import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

public class JasperReportCompile {
	static Logger log = Logger.getLogger(JasperReportCompile.class.getName());

	public static void main(String[] args) {
		String sourceFileName = "./test/jasper_report_template.jrxml";

		System.out.println("Compiling Report Design ...");
		try {
			/**
			 * Compile the report to a file name same as the JRXML file name
			 */
			JasperCompileManager.compileReportToFile(sourceFileName);
		} catch (JRException e) {
			e.printStackTrace();
		}
		System.out.println("Done compiling!!! ...");
	}
}