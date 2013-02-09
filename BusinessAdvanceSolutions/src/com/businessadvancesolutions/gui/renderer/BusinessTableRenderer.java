package com.businessadvancesolutions.gui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.businessadvancesolutions.gui.model.BusinessInvoiceTableModel;

public class BusinessTableRenderer extends DefaultTableCellRenderer {
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int column) {

		Component cell = super.getTableCellRendererComponent(table, obj,
				isSelected, hasFocus, row, column);

		// Get the status for the current row.
		BusinessInvoiceTableModel tableModel = (BusinessInvoiceTableModel) table
				.getModel();

		if (table.isRowSelected(row)) {
			setForeground(Color.BLUE);
			setFont(new Font("Courier New", Font.BOLD, 12));
			// } else {
			// if (tableModel.getRole(row).equals("1")) {
			// setForeground(Color.RED);
			// } else {
			// setForeground(Color.GREEN);
			// }
			// setFont(new Font("Courier New", Font.PLAIN, 12));
		}

		if (table.isCellSelected(row, column)) {
			setBackground(Color.GREEN);
		} else {
			if (row % 2 == 0) {
				cell.setBackground(Color.WHITE);
			} else {
				cell.setBackground(Color.LIGHT_GRAY);
			}
		}

		// Return the JLabel which renders the cell.
		return cell;

	}
}
