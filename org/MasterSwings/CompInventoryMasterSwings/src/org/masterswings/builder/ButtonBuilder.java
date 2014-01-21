package org.masterswings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.masterswings.componants.Button;

public class ButtonBuilder extends CompBuilderMastarSwings {
	Logger _log = Logger.getLogger(ButtonBuilder.class.getName());

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

	public static List<Button> getButtonsForPanel(String panel) {
		if (_application == null) {
			parseDom2Componants();
		}
		List<Button> buttonList = null;
		for (int i = 0; i < _application.getFrame().getPanel().size(); i++) {
			if (_application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)) {
				buttonList = _application.getFrame().getPanel().get(i)
						.getButton();
			}

		}
		return buttonList;
	}

	/**
	 * @param controlPanel
	 * @param buttonList
	 */
	public static void addButtonsToPanel(JPanel controlPanel,
			List<Button> buttonList, EventListener eventLister) {
		for (int i = 0; i < buttonList.size(); i++) {
			Button button = buttonList.get(i);
			JButton jButton = new JButton(button.getButtonName());
			jButton.setActionCommand(button.getButtonAction());
			jButton.addActionListener((ActionListener) eventLister);
			jButton.setToolTipText(button.getButtonToolTip().toString());
			if (button.getButtonDisabled() != null
					&& button.getButtonDisabled().equalsIgnoreCase("Disabled")) {
				jButton.setEnabled(false);
			}
			controlPanel.add(jButton);
		}

	}
}
