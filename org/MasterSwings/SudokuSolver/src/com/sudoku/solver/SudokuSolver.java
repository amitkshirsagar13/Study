package com.sudoku.solver;

/**
 * ProjectName: SudokuSolver
 * @author amit_kshirsagar
 * @date Feb 28, 2014
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class SudokuSolver extends SudokuPuzzle {
	static Logger log = Logger.getLogger(SudokuSolver.class.getName());

	HashMap<String, List<PuzzleCell>> sectionMap = new HashMap<String, List<PuzzleCell>>();

	public void populateSectionMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!sectionMap.containsKey(puzzleCell[i][j].getSection())) {
					sectionMap.put(puzzleCell[i][j].getSection(),
							new ArrayList<PuzzleCell>());
					sectionMap.get(puzzleCell[i][j].getSection()).add(
							puzzleCell[i][j]);
				} else {
					sectionMap.get(puzzleCell[i][j].getSection()).add(
							puzzleCell[i][j]);
				}
			}
		}
	}

	public void processSection() {
		Set<String> keySet = sectionMap.keySet();
		for (String key : keySet) {
			List<PuzzleCell> sectionList = sectionMap.get(key);
			for (PuzzleCell puzzleCell : sectionList) {
				if (puzzleCell.isSolved()) {
					for (PuzzleCell puzzleCellToSolve : sectionList) {
						if (!puzzleCellToSolve.isSolved()) {
							puzzleCellToSolve.removePossibleValue(puzzleCell
									.getCellValue());
						}
					}
				}
			}
		}
	}

	HashMap<String, List<PuzzleCell>> rowMap = new HashMap<String, List<PuzzleCell>>();

	public void populateRowMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!rowMap.containsKey(puzzleCell[i][j].getRow() + "")) {
					rowMap.put(puzzleCell[i][j].getRow() + "",
							new ArrayList<PuzzleCell>());
					rowMap.get(puzzleCell[i][j].getRow() + "").add(
							puzzleCell[i][j]);
				} else {
					rowMap.get(puzzleCell[i][j].getRow() + "").add(
							puzzleCell[i][j]);
				}
			}
		}
	}

	public void processRow() {
		Set<String> keySet = rowMap.keySet();
		for (String key : keySet) {
			List<PuzzleCell> rowList = rowMap.get(key);
			for (PuzzleCell puzzleCell : rowList) {
				if (puzzleCell.isSolved()) {
					for (PuzzleCell puzzleCellToSolve : rowList) {
						if (!puzzleCellToSolve.isSolved()) {
							puzzleCellToSolve.removePossibleValue(puzzleCell
									.getCellValue());
						}
					}
				}
			}
		}
	}

	HashMap<String, List<PuzzleCell>> columnMap = new HashMap<String, List<PuzzleCell>>();

	public void populateColumnMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!columnMap.containsKey(puzzleCell[i][j].getColumn() + "")) {
					columnMap.put(puzzleCell[i][j].getColumn() + "",
							new ArrayList<PuzzleCell>());
					columnMap.get(puzzleCell[i][j].getColumn() + "").add(
							puzzleCell[i][j]);
				} else {
					columnMap.get(puzzleCell[i][j].getColumn() + "").add(
							puzzleCell[i][j]);
				}
			}
		}
	}

	public void processColumn() {
		Set<String> keySet = columnMap.keySet();
		for (String key : keySet) {
			List<PuzzleCell> columnList = columnMap.get(key);
			for (PuzzleCell puzzleCell : columnList) {
				if (puzzleCell.isSolved()) {
					for (PuzzleCell puzzleCellToSolve : columnList) {
						if (!puzzleCellToSolve.isSolved()) {
							puzzleCellToSolve.removePossibleValue(puzzleCell
									.getCellValue());
						}
					}
				}
			}
		}
	}

	public void populatePuzzleMap() {
		populateSectionMap();
		populateRowMap();
		populateColumnMap();
	}

	public void processPuzzle() {
		while (SolveCounter.isInstanceContinue()) {
			SolveCounter.setInstanceStop();
			processSection();
			processRow();
			processColumn();
			System.out
					.println("##############################################");
			printPuzzle();
		}
	}

	public void printPuzzle() {
		for (int i = 0; i < 9; i++) {
			System.out.print("\t");
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + puzzleCell[i][j] + " |");
				if (j == 2 || j == 5) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i == 2 || i == 5) {
				System.out.println("\t======================================");
			} else {
				System.out.println("\t--------------------------------------");
			}

		}
	}
}
