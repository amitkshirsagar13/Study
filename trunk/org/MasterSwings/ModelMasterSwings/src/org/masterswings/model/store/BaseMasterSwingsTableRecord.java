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

package org.masterswings.model.store;

import java.util.Vector;

import org.apache.log4j.Logger;

public class BaseMasterSwingsTableRecord {

	Logger _log = Logger.getLogger(BaseMasterSwingsTableRecord.class.getName());

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

	protected Vector<String> _baseRecordVector = new Vector<String>();

	public Vector<String> getRecordVector() {
		return _baseRecordVector;
	}
}
