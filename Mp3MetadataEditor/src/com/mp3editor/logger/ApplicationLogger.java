package com.mp3editor.logger;

/**
 * This software is created by @author Amit Kshirsagar <amit.kshirsagar.13@gmail.com>
 * It is not allowed to copy and distribute without prior approval from Auther.
 */


import java.io.FileNotFoundException;

public abstract class ApplicationLogger {

	public String logLocation = null;
	public String logFileName = null;

	public abstract void setLogLocation(String logFileLocation);

	public abstract void setLogFileName(String logName);

	public abstract void createLogWriter() throws Exception;

}
