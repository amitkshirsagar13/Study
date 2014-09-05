package org.abs.report.writer;

import java.util.ArrayList;

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
public class ReportRecord extends ArrayList {

	/**
	 * Default constructor.
	 */
	public ReportRecord() {
		super();
	}

	/**
	 * Return the To structure associated with the input index value.
	 * 
	 * @return the To structure associated with the input index value.
	 */
	public RecordElement getRecordElement(int index) {
		return (RecordElement) super.get(index);
	}

}
