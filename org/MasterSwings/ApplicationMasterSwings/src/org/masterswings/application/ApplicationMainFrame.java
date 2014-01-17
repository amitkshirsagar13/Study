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

package org.masterswings.application;

import java.awt.Dimension;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.masterswings.base.util.BaseMasterSwingsUtil;
import org.masterswings.base.view.BaseMasterSwingsFrame;

public class ApplicationMainFrame extends BaseMasterSwingsFrame {

	Logger _log = Logger.getLogger(ApplicationMainFrame.class.getName());

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

	/**
	 * @param applicationName
	 * @throws IOException
	 */
	public ApplicationMainFrame(String applicationName) throws IOException {
		super(applicationName);
		loadApplicationProperties();
		this.setSize(new Dimension(BaseMasterSwingsUtil
				.getIntegerFromString(_properties.getProperty(APPWIDTH)),
				BaseMasterSwingsUtil.getIntegerFromString(_properties
						.getProperty(APPHEIGHT))));
		this.setLocation(BaseMasterSwingsUtil.getIntegerFromString(_properties
				.getProperty(XAPP)), BaseMasterSwingsUtil
				.getIntegerFromString(_properties.getProperty(YAPP)));
		initialize();
	}

	/**
	 * Start Loading the Componants on mainFrame...
	 */

	@Override
	public void initialize() {

		for (int index = 0; index < 11; index++) {
			_splashScreen.setProgress("Initilizing GUI..." + index, index * 10);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		_splashScreen.closeIt();
	}

}