package org.abs.report.util;

import java.util.HashMap;

import jxl.write.DateFormat;

import org.abs.report.format.ReportCellFormat;
import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Sep 4, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class ReportCellFormatterHash extends HashMap<String, ReportCellFormat>
		implements ReportGeneratorConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger
			.getLogger(ReportCellFormatterHash.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "ReportCellFormatterHash: " + this;
	}

	public void addReportCellFormat(ReportCellFormat reportCellFormat) {
		if (!this.containsKey(reportCellFormat.getName())) {
			this.put(reportCellFormat.getName(), reportCellFormat);
		}
	}

	public ReportCellFormat getReportCellFormat(String name) {
		ReportCellFormat reportCellFormat = null;
		if (!this.containsKey(name)) {
			if (name.equalsIgnoreCase(DATE)) {
				reportCellFormat = new ReportCellFormat(new DateFormat(
						ReportConfigUtil.getReportFormatProperty(DATE)));
			} else {
				reportCellFormat = new ReportCellFormat();
			}
			reportCellFormat.setName(name);
			reportCellFormat.defaultCellFormat();
			this.addReportCellFormat(reportCellFormat);
		}
		reportCellFormat = this.get(name);

		return reportCellFormat;
	}

}
