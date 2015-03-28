package org.abs.report.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jxl.write.WritableCellFormat;

import org.apache.log4j.Logger;

public class ReportConfigUtil {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(ReportConfigUtil.class);

	private static ReportConfigUtil reportConfigUtil = null;

	private ReportConfigUtil() {
	}

	public static void initilize() {
		if (reportConfigUtil == null) {
			reportConfigUtil = new ReportConfigUtil();
		}
	}

	public static ReportConfigUtil getReportConfigUtil() {
		if (reportConfigUtil == null) {
			reportConfigUtil = new ReportConfigUtil();
		}
		return reportConfigUtil;
	}

	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

	public static Properties getProperties(String propertiesFile) {
		if (!propertiesMap.containsKey(propertiesFile)) {
			propertiesMap.put(propertiesFile,
					getPropertiesForFile(propertiesFile));
		}
		return propertiesMap.get(propertiesFile);
	}

	private static Properties getPropertiesForFile(String propertiesFile) {
		InputStream input = null;
		input = ReportConfigUtil.class.getClassLoader().getResourceAsStream(
				propertiesFile + ".properties");
		if (input == null) {
			log4j.error("Sorry, unable to find " + propertiesFile
					+ ".properties");
			return null;
		}
		log4j.debug("Loading properties from :" + propertiesFile
				+ ".properties");
		Properties properties = new Properties();
		try {
			properties.load(input);
		} catch (IOException ioException) {
			log4j.error(ioException.getMessage());
		}

		return properties;
	}

	private static ReportCellFormatterHash reportCellFormatterHash = null;

	public static WritableCellFormat getWritableCellFormat(String name) {
		if (reportCellFormatterHash == null) {
			reportCellFormatterHash = new ReportCellFormatterHash();
		}
		return reportCellFormatterHash.getReportCellFormat(name);
	}

	public static String getReportFormatProperty(String property) {
		Properties reportProperties = getProperties("reportFormat");
		return reportProperties.getProperty(property);
	}
}