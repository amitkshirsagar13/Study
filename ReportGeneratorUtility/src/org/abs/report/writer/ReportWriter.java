package org.abs.report.writer;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Sep 3, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class ReportWriter {

	private static Logger log4j = Logger.getLogger(ReportWriter.class);

	private WritableWorkbook _reportWorkBook = null;

	private int _reportSheetCounter = 0;

	public ReportWriter(File reportFile) {
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("en", "EN"));
		try {
			_reportWorkBook = Workbook.createWorkbook(reportFile, ws);
		} catch (IOException e) {
			log4j.error(e.getMessage(), e);
		}
	}

	public WritableSheet getWorkSheet(String sheetName) {
		WritableSheet reportWorkSheet = _reportWorkBook.createSheet(sheetName,
				_reportSheetCounter);
		_reportSheetCounter++;
		return reportWorkSheet;
	}

	public WritableWorkbook getReportWorkbook() {
		return _reportWorkBook;
	}
}
