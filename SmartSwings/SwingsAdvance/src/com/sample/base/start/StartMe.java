package com.sample.base.start;

import java.awt.BorderLayout;

import com.masterswings.view.StatusPanel;
import com.sample.base.display.ApplicationMasterFrame;

public class StartMe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationMasterFrame masterFrame = ApplicationMasterFrame
				.getFrame("SwingsMaster");

		StatusPanel statusPanel = StatusPanel.loadPanel(masterFrame,
				BorderLayout.SOUTH);
		masterFrame.setStatusPanel(statusPanel);
		masterFrame.setVisible(true);

	}
}
