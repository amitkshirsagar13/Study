package com.mp3editor.gui.startup;

import org.apache.log4j.Logger;

public class StartUp {

	private static org.apache.log4j.Logger _log = null;

	public StartUp() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(StartUp.class);

		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartUp startUpScreen = new StartUp();
		startUpScreen.testMessages();

		JMasterFrame mainFrame = new JMasterFrame();
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
