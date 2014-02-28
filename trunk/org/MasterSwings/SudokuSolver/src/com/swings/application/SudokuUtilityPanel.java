package com.swings.application;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 5, 2014
 */

import java.util.Set;

import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.sudoku.solver.SudokuSolver;
import com.swings.base.UtilityBasePanel;

public class SudokuUtilityPanel extends UtilityBasePanel {
	static Logger log = Logger.getLogger(SudokuUtilityPanel.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.swings.base.UtilityBasePanel#populateCenterPanel()
	 */
	@Override
	protected void populateCenterPanel() {
		super.populateCenterPanel();
		Set<String> keySet = _formComponents.keySet();
		for (String key : keySet) {
			((JTextField) _formComponents.get(key)).setText("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.swings.base.UtilityBasePanelActionHandler#executeOK()
	 */
	@Override
	protected void executeOK() {
		super.executeOK();
		Set<String> keySet = _formComponents.keySet();
		SudokuSolver sudokuSolver = new SudokuSolver();
		for (String key : keySet) {
			JTextField cell = (JTextField) _formComponents.get(key);
			int i = Integer.parseInt(cell.getName().substring(1, 2));
			int j = Integer.parseInt(cell.getName().substring(3, 4));
			int cellValue = 0;
			if (cell.getText() != null && !cell.getText().equals("")) {
				cellValue = Integer.parseInt(cell.getText());
			}
			sudokuSolver.initilizePuzzleParameter(i, j, cellValue);

		}
		sudokuSolver.printPuzzle();
		sudokuSolver.populatePuzzleMap();
		sudokuSolver.processPuzzle();
		System.out.println("Processed....");

	}
}
