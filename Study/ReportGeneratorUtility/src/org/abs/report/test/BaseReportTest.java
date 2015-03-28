package org.abs.report.test;

import org.abs.report.works.BaseReportGenerator;
import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Sep 3, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class BaseReportTest {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(BaseReportTest.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "BaseReportTest: ";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BaseReportTest baseReport = new BaseReportTest();
		baseReport.test(args);

	}

	/**
	 * 
	 */
	private void test(String[] args) {
		BaseReportGenerator baseReporter = new BaseReportGenerator();
		baseReporter.processOptions(args);
		baseReporter.prepareQuery();
		baseReporter.runQuery();
		baseReporter.createReport();
	}
}
