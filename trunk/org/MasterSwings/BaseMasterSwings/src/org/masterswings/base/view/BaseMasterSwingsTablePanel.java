/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Feb 5, 2014
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

package org.masterswings.base.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.masterswings.builder.TextBoxBuilder;
import org.masterswings.componants.TextBox;
import org.masterswings.model.BaseMasterSwingsScrollTable;
import org.masterswings.model.BaseMasterSwingsTableModel;
import org.masterswings.model.store.BaseMasterSwingsTableRecord;
import org.masterswings.model.store.SamplePersonMasterSwings;

public class BaseMasterSwingsTablePanel extends BaseMasterSwingsPanel {

	/**
	 * @param layoutManager
	 * @param mainFrame
	 */
	public BaseMasterSwingsTablePanel(LayoutManager layoutManager,
			BaseMasterSwingsFrame mainFrame) {
		super(layoutManager, mainFrame);
		// TODO Auto-generated constructor stub
	}

	Logger _log = Logger.getLogger(BaseMasterSwingsTablePanel.class.getName());

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

	@Override
	public void loadCenterPanel() {
		_centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

		List<TextBox> textBoxList = TextBoxBuilder
				.getTextBoxsForPanel("BASEMASTERSWINGSPANEL");

		addTextBoxsToPanel(_centerPanel, textBoxList);

		Vector<String> columnVector = new Vector<String>();

		columnVector.add("ID");
		columnVector.add("Name");
		columnVector.add("Place");
		columnVector.add("Role");
		columnVector.add("Check");

		Vector<BaseMasterSwingsTableRecord> recordsVector = new Vector<BaseMasterSwingsTableRecord>();
		recordsVector.add(new SamplePersonMasterSwings("1", "Amit", "Pune",
				"Admin", true));
		recordsVector.add(new SamplePersonMasterSwings("2", "Amogh", "Pune",
				"User", true));
		recordsVector.add(new SamplePersonMasterSwings("3", "Poonam", "Pune",
				"User", false));

		BaseMasterSwingsTableModel tableModel = new BaseMasterSwingsTableModel(
				recordsVector, columnVector);

		BaseMasterSwingsScrollTable scrollTable = new BaseMasterSwingsScrollTable(
				tableModel, _centerPanel);
		scrollTable.setTableModel(tableModel);
		this.add(_centerPanel, BorderLayout.CENTER);
	}

}
