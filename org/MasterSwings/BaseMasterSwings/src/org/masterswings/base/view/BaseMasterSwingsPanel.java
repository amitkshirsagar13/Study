/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 17, 2014
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

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.masterswings.base.actions.BaseComponantActions;
import org.masterswings.base.actions.BaseMasterSwingsContants;
import org.masterswings.builder.ButtonBuilder;
import org.masterswings.builder.LabelBuilder;
import org.masterswings.builder.TextBoxBuilder;
import org.masterswings.componants.Button;
import org.masterswings.componants.Label;
import org.masterswings.componants.TextBox;

public class BaseMasterSwingsPanel extends BaseComponantActions implements
		BaseMasterSwingsContants, Runnable {

	Logger _log = Logger.getLogger(BaseMasterSwingsPanel.class.getName());

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

	private BaseMasterSwingsFrame _mainFrame = null;

	/**
	 * @param applicationName
	 */
	public BaseMasterSwingsPanel(LayoutManager layoutManager,
			BaseMasterSwingsFrame mainFrame) {
		super(layoutManager);
		_mainFrame = mainFrame;
	}

	public JPanel getTab() {
		return this;
	}

	private JPanel _infoPanel = null;
	private JPanel _centerPanel = null;
	private JPanel _buttonPanel = null;

	public void buildForm() {
		loadInfoPanel();
		loadCenterPanel();
		loadButtonPanel();
	}

	public void loadInfoPanel() {
		_infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 25));

		List<Label> labelList = LabelBuilder
				.getLabelsForPanel("BASEMASTERSWINGSPANEL");
		LabelBuilder.addLabelsToPanel(_infoPanel, labelList, this);

		this.add(_infoPanel, BorderLayout.NORTH);
	}

	public void loadCenterPanel() {
		_centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 25));
		_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

		List<TextBox> textBoxList = TextBoxBuilder
				.getTextBoxsForPanel("BASEMASTERSWINGSPANEL");

		TextBoxBuilder.addTextBoxsToPanel(_centerPanel, textBoxList, this);

		this.add(_centerPanel, BorderLayout.CENTER);
	}

	public void loadButtonPanel() {

		_buttonPanel = new JPanel(new FlowLayout());
		List<Button> buttonList = ButtonBuilder
				.getButtonsForPanel("BASEMASTERSWINGSPANEL");

		ButtonBuilder.addButtonsToPanel(_buttonPanel, buttonList, this);

		this.add(_buttonPanel, BorderLayout.SOUTH);
	}

	private String executingCommand = "Executing Command: ";

	@Override
	public void executeOk() {
		debug(executingCommand + OK);
		setStatusBarMessage(executingCommand + OK);
		waitSomeTime();
		setProgressStatus(50);
	}

	@Override
	public void executeCancel() {
		debug(executingCommand + CANCEL);
		setStatusBarMessage(executingCommand + CANCEL);
		setProgressStatus(25);
	}

	@Override
	public void executeReset() {
		debug(executingCommand + RESET);
		setStatusBarMessage(executingCommand + RESET);
		setProgressStatus(0);
	}

	@Override
	public void executeSubmit() {
		debug(executingCommand + SUBMIT);
		setStatusBarMessage(executingCommand + SUBMIT);
		setProgressStatus(100);
	}

	@Override
	public void executeAdd() {
		debug(executingCommand + ADD);
	}

	@Override
	public void executeRemove() {
		debug(executingCommand + REMOVE);
	}

	@Override
	public void executeDuplicate() {
		debug(executingCommand + DUPLICATE);
	}

	public void setStatusBarMessage(String statusMessage) {
		_mainFrame.setStatusBarMessage(statusMessage);
		debug(statusMessage);

	}

	public void setProgressStatus(int progressStatus) {
		_mainFrame.setProgressStatus(progressStatus);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		debug("Running Some Thing...");
		waitSomeTime();
	}
}
