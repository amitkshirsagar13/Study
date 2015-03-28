package org.abs.report.writer;

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
public class RecordCreator {

	public int recordCounter = 0;
	private ReportRecord record = null;

	public ReportRecord createRecord(String[] recordType, String[] recordArray) {

		record = new ReportRecord();
		for (int i = 0; i < recordArray.length; i++) {
			record.add(i, new RecordElement(recordType[i], recordArray[i]));
		}
		recordCounter++;
		return record;
	}

}
