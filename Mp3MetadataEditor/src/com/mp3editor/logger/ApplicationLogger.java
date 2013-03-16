package com.mp3editor.logger;

import java.io.FileNotFoundException;

public abstract class ApplicationLogger {

	public String logLocation = null;
	public String logFileName = null;

	public abstract void setLogLocation(String logFileLocation);

	public abstract void setLogFileName(String logName);

	public abstract void createLogWriter() throws Exception;

}
