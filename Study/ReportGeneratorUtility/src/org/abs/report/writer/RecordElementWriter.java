package org.abs.report.writer;

import jxl.write.DateFormat;
import jxl.write.WritableCellFormat;

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
public class RecordElementWriter {

	WritableCellFormat _borderBottomCellFormat = null;
	WritableCellFormat _borderRightCellFormat = null;
	WritableCellFormat _borderLeftCellFormat = null;
	WritableCellFormat _commonDateCellFormat = null;
	WritableCellFormat _headerCellFormat = null;
	WritableCellFormat _commonCellFormat = null;

	public RecordElementWriter() {
		_borderBottomCellFormat = ReportCellFormatter
				.getBottomBorderCellFormat();
		_commonCellFormat = ReportCellFormatter.getCommonCellFormat();
		_headerCellFormat = ReportCellFormatter.getHeaderCellFormat();
		_borderLeftCellFormat = ReportCellFormatter.getLeftBorderCellFormat();
		_borderRightCellFormat = ReportCellFormatter.getRightBorderCellFormat();
		_commonDateCellFormat = ReportCellFormatter
				.getCommonDateCellFormat(new DateFormat("DD-MMM-yyyy"));
	}
}
