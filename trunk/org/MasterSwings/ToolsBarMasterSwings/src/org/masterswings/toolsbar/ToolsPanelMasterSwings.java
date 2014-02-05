/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 23, 2014
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

package org.masterswings.toolsbar;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.masterswings.base.view.BaseMasterSwingsFrame;
import org.masterswings.base.view.BaseMasterSwingsPanel;
import org.masterswings.builder.ButtonBuilder;
import org.masterswings.componants.Button;

public class ToolsPanelMasterSwings extends BaseMasterSwingsPanel {

	Logger _log = Logger.getLogger(ToolsPanelMasterSwings.class.getName());

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
	 * @param layoutManager
	 * @param mainFrame
	 */
	public ToolsPanelMasterSwings(LayoutManager layoutManager,
			BaseMasterSwingsFrame mainFrame) {
		super(new FlowLayout(FlowLayout.LEFT), mainFrame);
		this.setName("BASEMASTERSWINGSFRAME");
	}

	public void loadToolBarButtons() {
		List<Button> buttonList = ButtonBuilder.getButtonsForPanel(this
				.getName());

		try {
			addButtonsToPanel((JPanel) this, buttonList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
