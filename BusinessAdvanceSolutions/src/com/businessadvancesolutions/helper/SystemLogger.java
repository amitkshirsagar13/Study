package com.businessadvancesolutions.helper;

import java.io.PrintStream;

import com.businessadvancesolutions.logger.BasicLogger;
import com.businessadvancesolutions.logger.LogLevel;

public class SystemLogger {

	private SystemLogger() {

	}

	private static SystemLogger _systemLogger = null;

	public static SystemLogger createSystemLogger() {
		if (_systemLogger == null) {
			_systemLogger = new SystemLogger();
			_systemLogger.initiateLogger();
		}
		return _systemLogger;
	}

	private static BasicLogger _basicLogger = null;

	private static void initiateLogger() {
		String logConfiguration = System.getProperty("logConfiguration");
		String logLocation = System.getProperty("logLocation");
		String logFileName = System.getProperty("logFileName");
		_basicLogger = BasicLogger.createBasicLogger(logConfiguration,
				logLocation, logFileName);
		_basicLogger.setLevel(LogLevel.DEBUG);
	}

	public static void setPrintStream(PrintStream printStream) {
		if (_basicLogger == null) {
			initiateLogger();
		}
		_basicLogger.createLogWriter(printStream);
	}

	public BasicLogger getLogger() {
		return _basicLogger;
	}

	public static void logError(String message, Throwable t) {
		_basicLogger.logMessage(message, t);
	}

	public static void logMessage(String message) {
		_basicLogger.logMessage(message);
	}

	public static void logDebug(String debug) {
		_basicLogger.logDebug(debug);
	}
}
