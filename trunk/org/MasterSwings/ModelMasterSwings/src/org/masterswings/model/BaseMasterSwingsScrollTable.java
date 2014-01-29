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

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.masterswings.model.renderer.BaseMasterSwingsCellRenderer;

public class BaseMasterSwingsScrollTable extends JScrollPane {

	Logger _log = Logger.getLogger(BaseMasterSwingsScrollTable.class.getName());

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

	static JTable _reportTable = null;

	public BaseMasterSwingsScrollTable(BaseMasterSwingsTableModel tableModel,
			JPanel root) {
		super();
		_reportTable = new JTable(tableModel);
		this.getViewport().add(_reportTable);
		for (int i = 0; i < _reportTable.getColumnCount(); i++) {
			_reportTable.getColumnModel().getColumn(i)
					.setCellRenderer(new BaseMasterSwingsCellRenderer());
		}
		root.add(this);
	}

	public void setTableModel(DefaultTableModel tableModel) {
		_reportTable.setModel(tableModel);
	}
}
