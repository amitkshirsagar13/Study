package org.abs.report.writer;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.DateFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

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
public class ReportCellFormatter {

	private static Logger log4j = Logger.getLogger(ReportCellFormatter.class);

	private static WritableCellFormat _commonDateCellFormat = null;

	/**
	 * This function will return the cell formator which can be<br>
	 * used for formatting the cells which we need to have date as content.
	 * 
	 * @param dateFormat
	 * @return _commonCellFormat
	 */
	public static WritableCellFormat getCommonDateCellFormat(
			DateFormat dateFormat) {
		_commonDateCellFormat = new WritableCellFormat(dateFormat);

		try {
			_commonDateCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			_commonDateCellFormat.setShrinkToFit(false);
			_commonDateCellFormat.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}

		return _commonDateCellFormat;
	}

	private static WritableCellFormat _commonCellFormat = null;

	/**
	 * This method returns the common cell formator for the report.
	 * 
	 * @return
	 */
	public static WritableCellFormat getCommonCellFormat() {
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);

		_commonCellFormat = new WritableCellFormat(wf);

		try {
			_commonCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			_commonCellFormat.setShrinkToFit(false);
			_commonCellFormat.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _commonCellFormat;
	}

	/**
	 * This method returns the common cell formator for the report.
	 * 
	 * @return
	 */
	public static WritableCellFormat getCommonCellFormat(WritableFont commonFont) {
		_commonCellFormat = new WritableCellFormat(commonFont);
		try {
			_commonCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			_commonCellFormat.setShrinkToFit(false);
			_commonCellFormat.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _commonCellFormat;
	}

	private static WritableCellFormat _headerCellFormat = null;

	/**
	 * This method returns the header cell formator for the report.
	 * 
	 * @return
	 */

	public static WritableCellFormat getHeaderCellFormat() {
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 11,
				WritableFont.BOLD);

		try {
			wf.setColour(Colour.DARK_BLUE2);

			_headerCellFormat = new WritableCellFormat(wf);

			_headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THICK);
			_headerCellFormat.setShrinkToFit(false);
			_headerCellFormat.setAlignment(Alignment.CENTRE);
			_headerCellFormat.setBackground(Colour.GRAY_25);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _headerCellFormat;
	}

	/**
	 * This method returns the header cell formator for the report.
	 * 
	 * @return
	 */

	public static WritableCellFormat getHeaderCellFormat(WritableFont commonFont) {

		try {
			_headerCellFormat = new WritableCellFormat(commonFont);

			_headerCellFormat.setBorder(Border.ALL, BorderLineStyle.THICK);
			_headerCellFormat.setShrinkToFit(false);
			_headerCellFormat.setAlignment(Alignment.CENTRE);
			_headerCellFormat.setBackground(Colour.GRAY_25);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _headerCellFormat;
	}

	private static WritableCellFormat _borderCells = null;

	/**
	 * This method returns the left border cell formator for the report.<br>
	 * We can use this cell for serial numbering also.
	 * 
	 * @return
	 */

	public static WritableCellFormat getLeftBorderCellFormat() {

		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);

		_borderCells = new WritableCellFormat(wf);

		try {
			_borderCells.setBorder(Border.ALL, BorderLineStyle.THIN);
			_borderCells.setBorder(Border.RIGHT, BorderLineStyle.THICK);
			_borderCells.setShrinkToFit(false);
			_borderCells.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _borderCells;
	}

	/**
	 * This method returns the right border cell formator for the report.<br>
	 * We can use this cell for serial numbering also.
	 * 
	 * @return
	 */

	public static WritableCellFormat getRightBorderCellFormat() {

		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);

		_borderCells = new WritableCellFormat(wf);

		try {
			_borderCells.setBorder(Border.ALL, BorderLineStyle.THIN);
			_borderCells.setBorder(Border.LEFT, BorderLineStyle.THICK);
			_borderCells.setShrinkToFit(false);
			_borderCells.setAlignment(Alignment.RIGHT);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _borderCells;
	}

	/**
	 * This method returns the bottom border cell formator for the report.<br>
	 * We can use this cell for serial numbering also.
	 * 
	 * @return
	 */

	public static WritableCellFormat getBottomBorderCellFormat() {

		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD);

		_borderCells = new WritableCellFormat(wf);

		try {
			_borderCells.setBorder(Border.ALL, BorderLineStyle.THIN);
			_borderCells.setBorder(Border.BOTTOM, BorderLineStyle.THICK);
			_borderCells.setShrinkToFit(false);
			_borderCells.setAlignment(Alignment.LEFT);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
		return _borderCells;
	}

}
