package org.abs.report.format;

import java.util.Properties;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.DateFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

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
 * Creation date: Sep 4, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class ReportCellFormat extends WritableCellFormat implements
		ReportGeneratorConstants {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(ReportCellFormat.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "ReportCellFormat: ";
	}

	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReportCellFormat() {
		super();
	}

	public ReportCellFormat(DateFormat dateFormat) {
		super(dateFormat);
	}

	/**
	 * 
	 */
	public void defaultCellFormat() {
		try {
			this.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 11);
			if (name.equals(COLUMN)) {
				cellFont.setColour(Colour.BLACK);
				cellFont.setBoldStyle(WritableFont.BOLD);
				this.setBorder(Border.ALL, BorderLineStyle.THICK);
				this.setBackground(Colour.GRAY_25);
				this.setAlignment(Alignment.CENTRE);
			} else if (name.equals(NUMBER)) {
				cellFont.setColour(Colour.BLUE);
			} else if (name.equals(DATE)) {
				cellFont.setColour(Colour.GREEN);
			} else {
				cellFont.setColour(Colour.BLACK);
			}
			this.setShrinkToFit(false);
			this.setFont(cellFont);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	public void configCellFormat() {
		try {
			this.setBorder(Border.ALL, BorderLineStyle.THIN);
			this.setAlignment(Alignment.LEFT);
			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 16);
			Properties reportFormat = ReportConfigUtil
					.getProperties("reportFormat");
			if (name.equals(COLUMN)) {
				cellFont.setColour(Colour.BLACK);
				this.setBorder(Border.ALL, BorderLineStyle.THICK);
				this.setBackground(Colour.GRAY_25);
				this.setAlignment(Alignment.CENTRE);
			} else if (name.equals(NUMBER)) {
				this.setAlignment(Alignment.RIGHT);
				cellFont.setColour(Colour.BLUE);
			} else if (name.equals(DATE)) {
				cellFont.setColour(Colour.GREEN);
			} else {
				cellFont.setColour(Colour.BLACK);
			}
			this.setShrinkToFit(true);
			this.setFont(cellFont);
		} catch (WriteException e) {
			log4j.error(e.getMessage(), e);
		}
	}
}
