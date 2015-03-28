package org.abs.report.writer;

import java.util.Date;

import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.abs.report.util.ReportConfigUtil;
import org.abs.report.util.ReportGeneratorConstants;
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
public class ReportRecordWriter implements ReportGeneratorConstants {

	private static Logger log4j = Logger.getLogger(ReportRecordWriter.class);
	public int rowIndexNew = 0;

	public void writeRecord(WritableSheet sheet,
			RecordElementWriter recordElementWriter, ReportRecord record,
			int rowIndex) throws RowsExceededException, WriteException {
		int recordSize = record.size();
		if (rowIndex > 0) {
			for (int recordElementCounter = 0; recordElementCounter < recordSize; recordElementCounter++) {
				if (((RecordElement) record.get(recordElementCounter))
						.getColumnName().equals(STRING)) {
					sheet.addCell(new Label(recordElementCounter, rowIndexNew,
							((RecordElement) record.get(recordElementCounter))
									.getColumnData(), ReportConfigUtil
									.getWritableCellFormat(STRING)));
				} else if (((RecordElement) record.get(recordElementCounter))
						.getColumnName().equals(NUMBER)) {
					sheet.addCell(new Number(
							recordElementCounter,
							rowIndexNew,
							Double.parseDouble(((RecordElement) record
									.get(recordElementCounter)).getColumnData()),
							ReportConfigUtil.getWritableCellFormat(NUMBER)));
				} else if (((RecordElement) record.get(recordElementCounter))
						.getColumnName().equals(DATE)) {
					if (((RecordElement) record.get(recordElementCounter))
							.getColumnData() != null) {
						sheet.addCell(new DateTime(recordElementCounter,
								rowIndexNew, new Date(Long
										.parseLong(((RecordElement) record
												.get(recordElementCounter))
												.getColumnData())),
								ReportConfigUtil.getWritableCellFormat(DATE),
								DateTime.GMT));
					} else {
						sheet.addCell(new Label(recordElementCounter,
								rowIndexNew, null, ReportConfigUtil
										.getWritableCellFormat(DATE)));
					}
				}
			}
		} else {

			for (int recordElementCounter = 0; recordElementCounter < recordSize; recordElementCounter++) {
				if (((RecordElement) record.get(recordElementCounter))
						.getColumnName().equals(COLUMN)) {
					Label columnCell = new Label(recordElementCounter,
							rowIndex,
							((RecordElement) record.get(recordElementCounter))
									.getColumnData(),
							ReportConfigUtil.getWritableCellFormat(COLUMN));
					if (recordElementCounter == 0) {
						WritableCellFeatures wcf = new WritableCellFeatures();
						wcf.setComment("Hello!");
						columnCell.setCellFeatures(wcf);
					}
					sheet.addCell(columnCell);
				}
			}

		}
		rowIndexNew++;
	}
}
