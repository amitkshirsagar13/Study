package org.masterswings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.masterswings.componants.Label;

public class LabelBuilder extends CompBuilderMastarSwings {
	Logger _log = Logger.getLogger(LabelBuilder.class.getName());

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

	public static List<Label> getLabelsForPanel(String panel) {
		if (_application == null) {
			parseDom2Componants();
		}
		List<Label> labelList = null;
		for (int i = 0; i < _application.getFrame().getPanel().size(); i++) {
			if (_application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)
					&& _application.getFrame().getPanel().get(i)
							.getSubPanelName().equalsIgnoreCase("INFOPANEL")) {
				labelList = _application.getFrame().getPanel().get(i)
						.getLabel();
			}

		}
		return labelList;
	}

	/**
	 * @param controlPanel
	 * @param buttonList
	 */
	public static void addLabelsToPanel(JPanel controlPanel,
			List<Label> labelList, EventListener eventLister) {
		for (int i = 0; i < labelList.size(); i++) {
			Label label = labelList.get(i);
			JLabel jLabel = new JLabel(label.getLabelName());
			jLabel.setText(label.getLabelText());
			jLabel.addMouseListener((MouseListener) eventLister);
			jLabel.setToolTipText(label.getLabelToolTip());

			controlPanel.add(jLabel);
		}

	}
}
