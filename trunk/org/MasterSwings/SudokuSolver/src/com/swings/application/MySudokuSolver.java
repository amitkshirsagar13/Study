package com.swings.application;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.swings.base.UtilityBaseFrame;

public class MySudokuSolver {
	static Logger log = Logger.getLogger(MySudokuSolver.class.getName());

	public static void main(String[] args) {
		MySudokuSolver myUtilityApplication = new MySudokuSolver();
		myUtilityApplication.initiateFrame();
	}

	public void initiateFrame() {
		UtilityBaseFrame myApplicationFrame = new UtilityBaseFrame();
		myApplicationFrame.setTitle("MySudokuSolver");
		myApplicationFrame.buildFrame();
		myApplicationFrame.show(true);

		SudokuUtilityPanel loginBasePanel = new SudokuUtilityPanel();
		loginBasePanel.buildPanel("SudokuPanel");
		loginBasePanel.setMainFrame(myApplicationFrame);
		myApplicationFrame.addTabbedPanel(loginBasePanel);

		myApplicationFrame.setSelected("SudokuPanel");

		myApplicationFrame.setExtendedState(myApplicationFrame
				.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
}
