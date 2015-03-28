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
public class RecordElement {
	String _columnName = null;
	String _columnData = null;

	public void setColumnName(String columnName) {
		this._columnName = columnName;
	}

	public void setColumnData(String columnData) {
		this._columnData = columnData;
	}

	public String getColumnName() {
		return this._columnName;
	}

	public String getColumnData() {
		return this._columnData;
	}

	public RecordElement(String columnName, String columnData) {
		this.setColumnName(columnName);
		this.setColumnData(columnData);
	}
}
