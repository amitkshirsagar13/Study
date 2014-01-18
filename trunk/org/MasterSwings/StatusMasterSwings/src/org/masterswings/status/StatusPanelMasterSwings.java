package org.masterswings.status;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 18, 2014
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

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.apache.log4j.Logger;
import org.masterswings.base.actions.BaseMasterSwingsContants;
import org.masterswings.base.view.BaseMasterSwingsPanel;

public class StatusPanelMasterSwings extends BaseMasterSwingsPanel implements
		BaseMasterSwingsContants {

	Logger _log = Logger.getLogger(StatusPanelMasterSwings.class.getName());

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
	 */
	private StatusPanelMasterSwings(LayoutManager layoutManager) {
		super(layoutManager);
	}

	public static StatusPanelMasterSwings getStatusPanelMasterSwings(
			LayoutManager layoutManager) {
		if (_statusPanel == null) {
			_statusPanel = new StatusPanelMasterSwings(layoutManager);
			_statusPanel.populateStatusBarInfoPanel();
		}
		return _statusPanel;
	}

	private static StatusPanelMasterSwings _statusPanel = null;

	private static JProgressBar _progressBar = new JProgressBar();

	private static JLabel _statusBar = new JLabel();
	private JPanel _statusBarPanel = new JPanel();
	private JPanel _infoPanel = new JPanel();
	private JLabel _customerIndex = new JLabel();
	private JLabel _readWriteLabel = new JLabel();
	private JLabel _userIDLabel = new JLabel();
	private JLabel _privilegedUserLabel = new JLabel();

	private void populateStatusBarInfoPanel() {
		/**
		 * Status Bar and Info Panels in Bottom
		 */

		_customerIndex.setName("Customer Index");
		_readWriteLabel.setName("Access Level");
		_userIDLabel.setName("User Name");
		_privilegedUserLabel.setName("Privilaged");
		_statusBar.setName("Status Bar");

		// _statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		_customerIndex.setBorder(BorderFactory.createLoweredBevelBorder());
		_readWriteLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_userIDLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_privilegedUserLabel
				.setBorder(BorderFactory.createLoweredBevelBorder());

		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.X_AXIS));
		_infoPanel.add(_customerIndex, null);
		_infoPanel.add(_readWriteLabel, null);
		_infoPanel.add(_userIDLabel, null);
		_infoPanel.add(_privilegedUserLabel, null);

		// _statusBarPanel.setLayout(null);
		// _statusBarPanel.add(_progressBar);
		// _statusBarPanel.add(_progressBar);

		_statusBarPanel.setLayout(new BorderLayout());

		_statusBar.setOpaque(false);
		_statusBar.setSize(_progressBar.getWidth(),
				_progressBar.getHeight() - 2);
		_progressBar.setMaximum(100);
		_statusBarPanel.add(_statusBar, BorderLayout.WEST);

		_progressBar.setLayout(new BorderLayout());
		_statusBarPanel.setOpaque(false);
		_progressBar.add(_statusBarPanel);
		_statusPanel.setLayout(new BorderLayout());
		_statusPanel.add(_progressBar, BorderLayout.CENTER);
		_statusPanel.add(_infoPanel, BorderLayout.EAST);

		_customerIndex.setText("CustomerIndex");
		_readWriteLabel.setText(" R ");
		_userIDLabel.setText("        ");
		_privilegedUserLabel.setText(" N ");

		_customerIndex.setOpaque(true);
		_readWriteLabel.setOpaque(true);
		_userIDLabel.setOpaque(true);
		_privilegedUserLabel.setOpaque(true);

		_customerIndex.setToolTipText("Business Safty with Customer..");
		_readWriteLabel.setToolTipText("Access For Read Write");
		_userIDLabel.setToolTipText("UserID");
		_privilegedUserLabel.setToolTipText("Privileged User");

		_customerIndex.addMouseListener(this);
		_readWriteLabel.addMouseListener(this);
		_userIDLabel.addMouseListener(this);
		_privilegedUserLabel.addMouseListener(this);
		_statusBar.addMouseListener(this);

		_statusBar.setToolTipText("Status Bar Message...");
		_statusBar.setText(" Status Bar Message...");
		/**
		 * Status Bar and info panel Populated.
		 */
	}

	public void setStatusBarMessage(String statusMessage) {
		_statusBar.setText(getFormatedStatusMessage(statusMessage));
	}

	public String getFormatedStatusMessage(String statusMessage) {
		return SPACE + statusMessage + SPACE;

	}

	boolean _isInfoPanelCleaned = false;
	HashMap<String, JLabel> _infoPanelList = null;

	public void addLabelToInfoPanel(JLabel customLabel) {
		if (!_isInfoPanelCleaned) {
			_isInfoPanelCleaned = true;
			_infoPanel.removeAll();
			_infoPanel.revalidate();
			_infoPanelList = new HashMap<String, JLabel>();
		}
		customLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_infoPanel.add(customLabel);
		_infoPanelList.put(customLabel.getName(), customLabel);
	}

	public void updateInfoPanel(HashMap<String, JLabel> infoPanel) {
		for (String key : infoPanel.keySet()) {
			_infoPanelList.get(key).setText(infoPanel.get(key).getText());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.masterswings.base.view.BaseMasterSwingsPanel#mouseClicked(java.awt
	 * .event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (e.getClickCount() == 2) {
			JOptionPane.showMessageDialog(this.getParent(),
					((JLabel) e.getSource()).getText(),
					((JLabel) e.getSource()).getName(),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
