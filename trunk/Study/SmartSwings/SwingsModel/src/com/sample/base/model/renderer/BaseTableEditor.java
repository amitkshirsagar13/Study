package com.sample.base.model.renderer;

/**
 * ProjectName: SwingsModel
 * @author amit_kshirsagar
 * @date Jan 30, 2014
 */

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import org.apache.log4j.Logger;

public class BaseTableEditor extends DefaultCellEditor {

	static Logger log = Logger.getLogger(BaseTableEditor.class.getName());

	/**
	 * @param arg0
	 */
	public BaseTableEditor(String[] items) {
		super(new JComboBox(items));
	}

}
