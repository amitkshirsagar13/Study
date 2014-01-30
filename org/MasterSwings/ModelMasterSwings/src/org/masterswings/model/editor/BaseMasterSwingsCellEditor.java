/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 30, 2014
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

package org.masterswings.model.editor;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;

public class BaseMasterSwingsCellEditor extends DefaultCellEditor {

	Logger _log = Logger.getLogger(BaseMasterSwingsCellEditor.class.getName());

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
	 * @param arg0
	 */
	public BaseMasterSwingsCellEditor(String[] items) {
		super(new JComboBox(items));
	}
}
