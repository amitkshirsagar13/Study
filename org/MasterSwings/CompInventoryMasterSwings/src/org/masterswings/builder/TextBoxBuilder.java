package org.masterswings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import java.util.List;

import org.apache.log4j.Logger;
import org.masterswings.componants.TextBox;

public class TextBoxBuilder extends CompBuilderMastarSwings {
	Logger _log = Logger.getLogger(TextBoxBuilder.class.getName());

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

	public static List<TextBox> getTextBoxsForPanel(String panel) {
		if (_application == null) {
			parseDom2Componants();
		}
		List<TextBox> labelList = null;
		for (int i = 0; i < _application.getFrame().getPanel().size(); i++) {
			if (_application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)
					&& _application.getFrame().getPanel().get(i)
							.getSubPanelName().equalsIgnoreCase("CENTERPANEL")) {
				labelList = _application.getFrame().getPanel().get(i)
						.getTextBox();
			}

		}
		return labelList;
	}

}
