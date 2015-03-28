package com.sample.base.model.renderer;

/**
 * ProjectName: SwingsModel
 * @author amit_kshirsagar
 * @date Jan 30, 2014
 */

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.apache.log4j.Logger;

public class CheckBoxBaseTableRenderer extends JCheckBox implements
		TableCellRenderer {
	static Logger log = Logger.getLogger(CheckBoxBaseTableRenderer.class
			.getName());

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

		if (isSelected) {
			this.setBackground(Color.BLUE);
		} else {
			if (row % 2 == 0) {
				this.setBackground(Color.WHITE);
			} else {
				this.setBackground(new Color(242, 242, 242));
			}
		}

		// Set tool tip if desired
		if (obj != null) {
			setToolTipText(obj.toString());
		}

		return this;

	}

}
