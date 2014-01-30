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

package org.masterswings.model.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.log4j.Logger;

public class BaseMasterSwingsCellRenderer extends DefaultTableCellRenderer {

	Logger _log = Logger
			.getLogger(BaseMasterSwingsCellRenderer.class.getName());

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
	 * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, obj,
				isSelected, hasFocus, row, column);
		if (isSelected) {
			// cell.setBackground(Color.BLUE);
		} else {
			if (row % 2 == 0) {
				cell.setBackground(Color.WHITE);
			} else {
				cell.setBackground(new Color(242, 242, 242));
			}
		}
		// Set tool tip if desired
		if (obj != null) {
			setToolTipText(obj.toString());
		}
		return cell;
	}

}
