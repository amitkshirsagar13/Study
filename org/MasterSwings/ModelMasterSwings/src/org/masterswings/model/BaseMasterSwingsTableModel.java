/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 29, 2014
 * @author Amit Kshirsagar
 * @email amit.kshirsagar.13@gmail.com
 * @version 1.0
 * @since
 *
 * <p><b>Modification History:</b><p>
 *
 *
 * </pre>
 */

package org.masterswings.model;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.masterswings.model.store.BaseMasterSwingsTableRecord;

public class BaseMasterSwingsTableModel extends DefaultTableModel {

	Logger _log = Logger.getLogger(BaseMasterSwingsTableModel.class.getName());

	private void logMessage(String message, Throwable exception) {
		if (exception != null) {
			_log.error(message, exception);
		} else {
			_log.info(message);
		}
	}

	private void debug(String message) {
		_log.debug(message);
	}

	/**
	 * 
	 */
	public BaseMasterSwingsTableModel(
			Vector<BaseMasterSwingsTableRecord> recordsVector,
			Vector<String> columnVector) {
		super();
		Vector<Vector> vectoredRecords = new Vector<Vector>();
		for (BaseMasterSwingsTableRecord record : recordsVector) {
			vectoredRecords.add(record.getRecordVector());
			debug(record.getRecordVector().toString());
		}
		super.setDataVector(vectoredRecords, columnVector);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}
}
