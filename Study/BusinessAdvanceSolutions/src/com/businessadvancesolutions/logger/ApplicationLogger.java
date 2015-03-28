package com.businessadvancesolutions.logger;

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

public abstract class ApplicationLogger {

	public String logLocation = null;
	public String logFileName = null;

	public abstract void setLogLocation(String logFileLocation);

	public abstract void setLogFileName(String logName);

	public abstract void createLogWriter() throws Exception;

}
