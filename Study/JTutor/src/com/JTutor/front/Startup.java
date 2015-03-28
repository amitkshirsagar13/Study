package com.JTutor.front;

import org.apache.log4j.Logger;

public class Startup {

	private static org.apache.log4j.Logger _log = null;

	public Startup() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(Startup.class);

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Startup startUpScreen = new Startup();
		startUpScreen.testMessages();

		JTutorMainFrame mainFrame = new JTutorMainFrame();
		mainFrame.startJTutor();
	}

	private void testMessages() {
		_log.trace("Trace Message!");
		_log.debug("Debug Message!");
		_log.info("Info Message!");
		_log.warn("Warn Message!");
		_log.error("Error Message!");
		_log.fatal("Fatal Message!");

	}

}
