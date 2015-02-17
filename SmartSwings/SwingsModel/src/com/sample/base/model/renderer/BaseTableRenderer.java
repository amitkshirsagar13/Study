package com.sample.base.model.renderer;

/**
 * ProjectName: SwingsModel
 * @author amit_kshirsagar
 * @date Jan 30, 2014
 */

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.log4j.Logger;

public class BaseTableRenderer extends DefaultTableCellRenderer {
	static Logger log = Logger.getLogger(BaseTableRenderer.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
	 * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
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

		if (row == 2) {

		}

		// Set tool tip if desired
		if (obj != null) {
			setToolTipText(obj.toString());
		}

		return cell;

	}

}
