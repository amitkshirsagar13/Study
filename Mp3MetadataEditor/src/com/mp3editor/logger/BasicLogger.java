package com.mp3editor.logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;

public class BasicLogger extends ApplicationLogger {

	// private Logger _logger = null;

	@Override
	public void setLogLocation(String logFileLocation) {
		logLocation = logFileLocation;

	}

	@Override
	public void setLogFileName(String logName) {
		logFileName = logName;
	}

	@Override
	public void createLogWriter() throws Exception {
		if (logLocation != null && logFileName != null) {
			_logWriter = new PrintWriter(new File(logLocation + "/"
					+ logFileName));
		} else {
			Throwable exception = new Throwable(
					"Log Writer can not be created with this method, try passing PrintStream Object or System.out.");
			// _logger.error(
			// "BasicLogger: LogFileName or LogFileLocation is null.",
			// exception);
			throw new Exception(
					"BasicLogger: LogFileName or LogFileLocation is null.",
					exception);
		}
		_logLevel = LogLevel.MESSAGE;

	}

	/**
	 * You can pass your own printStream object here, like System.out for
	 * Console. This is more to make sure, in case application is used as API or
	 * plugin on third party Application, it has more control for the Logging
	 * from Implementer.
	 * 
	 * @param yourPrintStream
	 */

	public void createLogWriter(PrintStream yourPrintStream) {
		if (yourPrintStream == null) {
			yourPrintStream = System.out;
		}
		_logWriter = new PrintWriter(yourPrintStream);
	}

	private BasicLogger(String logConfiguration, String logLocation,
			String logFileName) {
		setLogFileName(logFileName);
		setLogLocation(logLocation);
		// _logger = Logger.getLogger(BasicLogger.class);
	}

	private static BasicLogger basicLogger = null;
	private PrintWriter _logWriter = null;

	public static BasicLogger createBasicLogger(String logConfiguration,
			String logLocation, String logFileName) {
		if (basicLogger == null) {
			try {
				basicLogger = new BasicLogger(logConfiguration, logLocation,
						logFileName);
				basicLogger.createLogWriter(new PrintStream(new File(
						logLocation + "/" + logFileName)));
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return basicLogger;
	}

	/**
	 * Set Log Level
	 */

	private LogLevel _logLevel = null;

	public void setLevel(LogLevel logLevel) {
		_logLevel = logLevel;
	}

	int index = 4;

	public void logError(String error) {
		switch (_logLevel) {
		case MESSAGE:
		case ERROR:
			error = error.replaceAll(":", "-");
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			_logWriter.println(formattedMessage(getStackLogged(" :Error  : "
					+ error)));
			_logWriter.flush();
		}
	}

	public void logWarning(String warning) {
		switch (_logLevel) {
		case MESSAGE:
		case WARNING:
		case ERROR:
			warning = warning.replaceAll(":", "-");
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			_logWriter.println(formattedMessage(getStackLogged(" :Warning: "
					+ warning)));
			_logWriter.flush();
		}
	}

	public void logInfo(String info) {
		switch (_logLevel) {
		case INFO:
		case MESSAGE:
		case WARNING:
		case ERROR:
			info = info.replaceAll(":", "-");
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			_logWriter.println(formattedMessage(getStackLogged(" :Info   : "
					+ info)));
			_logWriter.flush();
		}
	}

	public void logTrace(String trace) {
		switch (_logLevel) {
		case TRACE:
		case DEBUG:
		case INFO:
		case MESSAGE:
		case WARNING:
		case ERROR:
			trace = trace.replaceAll(":", "-");
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			_logWriter.println(formattedMessage(getStackLogged(" :Trace  : "
					+ trace)));
			_logWriter.flush();
		}
	}

	public void logDebug(String debug) {
		switch (_logLevel) {
		case DEBUG:
		case INFO:
		case MESSAGE:
		case WARNING:
		case ERROR:
			debug = debug.replaceAll(":", "-");
			StackTraceElement[] stackTrace = Thread.currentThread()
					.getStackTrace();
			_logWriter.println(formattedMessage(getStackLogged(" :Debug  : "
					+ debug)));
			_logWriter.flush();
		}
	}

	public void logMessage(String message) {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		_logWriter.println(formattedMessage(getStackLogged(" :Message: "
				+ message)));
		_logWriter.flush();
	}

	public void logMessage(String message, Throwable t) {
		_logWriter.println(formattedMessage(getStackLogged(" :Message: "
				+ message)));
		_logWriter.println(prepareStackTraceForPrint(t));
		// t.printStackTrace(_logWriter);
		_logWriter.flush();

	}

	private String formattedMessage(String message) {
		StringBuffer returnMessage = new StringBuffer();
		String split[] = message.split(":");

		returnMessage.append(padStringMessage(split[0], 16, true));
		returnMessage.append(padStringMessage(split[1], 16, false));
		returnMessage.append(padStringMessage(split[2], 4, false));
		returnMessage.append(split[4] + ":");
		returnMessage.append(split[5]);
		return returnMessage.toString();
	}

	private String padStringMessage(String stringToPad, int padLength,
			boolean padLeft) {
		String paddedString = null;
		while (stringToPad.length() < padLength) {
			if (padLeft) {
				stringToPad = " " + stringToPad;
			} else {
				stringToPad = stringToPad + " ";
			}
		}
		return stringToPad + ":";
	}

	private String getStackLogged(String message) {
		String returnMessageLog = null;
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		returnMessageLog = Thread.currentThread().getStackTrace()[index]
				.getFileName()
				+ ":"
				+ Thread.currentThread().getStackTrace()[index].getMethodName()
				+ ":"
				+ Thread.currentThread().getStackTrace()[index].getLineNumber()
				+ ": " + message;
		return returnMessageLog;
	}

	private String prepareStackTraceForPrint(Throwable t) {
		StringBuffer stackMessage = new StringBuffer();
		StackTraceElement[] stackTrace = t.getStackTrace();
		stackMessage.append("\t" + t + "\n");
		for (int i = 0; i < stackTrace.length; i++) {
			stackMessage.append("\t\t" + stackTrace[i].toString() + "\n");
		}

		return stackMessage.toString();
	}
}
