package com.tcp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tcp.db.JDBCUtil;

/**
 * public class TcpMissionUtil {
 * 
 * }
 */

public class TcpMissionUtil {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(TcpMissionUtil.class);

	private static TcpMissionUtil tcpMIssionUtil = null;

	private TcpMissionUtil() {
	}

	public static void initilize() {
		if (tcpMIssionUtil == null) {
			tcpMIssionUtil = new TcpMissionUtil();
		}
	}

	public static TcpMissionUtil getTcpMissionUtil() {
		if (tcpMIssionUtil == null) {
			tcpMIssionUtil = new TcpMissionUtil();
		}
		return tcpMIssionUtil;
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
		input = JDBCUtil.class.getClassLoader().getResourceAsStream(
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
}