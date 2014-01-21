/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 17, 2014
 * @author Amit Kshirsagar
 * @email amit.kshirsagar.13@gmail.com
 * @version 1.0
 * @since
 *
 * <p><b>Modification History:</b><p>
 *
 *
 * </pre>
 */

package org.masterswings.application.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.masterswings.application.ApplicationMainFrame;

public class Startup {

	Logger _log = Logger.getLogger(Startup.class.getName());

	private void logMessage(String message, Throwable exception) {
		if (exception != null) {
			_log.error(message, exception);
		} else {
			_log.info(message);
		}
	}

	private void debug(String message) {
		_log.debug(message);
	}

	public static ApplicationMainFrame _mainFrame = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			_mainFrame = new ApplicationMainFrame("MyMasterSwingsApplication");
			_mainFrame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void logMessage(String statusMessage, int progressStatus) {
		_mainFrame.setStatusBarMessage(statusMessage);
		_mainFrame.setProgressStatus(progressStatus);
	}
}
