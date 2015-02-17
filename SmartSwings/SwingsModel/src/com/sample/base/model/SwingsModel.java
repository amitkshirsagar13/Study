package com.sample.base.model;

/**
 * ProjectName: SwingsModel
 * @author amit_kshirsagar
 * @date Jan 29, 2014
 */

import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import com.sample.base.model.store.RecordsBase;

public class SwingsModel extends DefaultTableModel {
	static Logger log = Logger.getLogger(SwingsModel.class.getName());

	public SwingsModel(Vector<RecordsBase> recordsList, Vector columns) {

		super();
		Vector<Vector> filteredRecordsList = new Vector<Vector>();
		for (Iterator<RecordsBase> iterator = recordsList.iterator(); iterator
				.hasNext();) {
			RecordsBase recordBase = iterator.next();
			Vector vector = recordBase.getRecordVector();

			vector.add(new Boolean(true));

			vector.add(5);
			filteredRecordsList.add(vector);
		}

		this.setDataVector(filteredRecordsList, columns);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}
}