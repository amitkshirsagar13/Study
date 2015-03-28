package org.masterswings.tools;

/**
 * ProjectName: ToolBarMasterSwings
 * @author amit_kshirsagar
 * @date Jan 23, 2014
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.masterswings.base.BasePanel;

public class ToolsMasterSwings extends BasePanel {
	static Logger log = Logger.getLogger(ToolsMasterSwings.class.getName());

	public ToolsMasterSwings() {
		this.setName("TOOLBARPANEL");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		// loadToolBarButtons();
	}

	// public void loadToolBarButtons() {
	// List<Button> buttonList = ButtonBuilder
	// .getButtonsForPanel("TOOLBARPANEL");
	// ButtonBuilder.addButtonsToPanel(this, buttonList, this);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.masterswings.base.BasePanel#actionPerformed(java.awt.event.ActionEvent
	 * )
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final String command = e.getActionCommand();
		final ActionEvent event = e;
		Runnable actionRunner = new Runnable() {

			@Override
			public void run() {
				if (command.equalsIgnoreCase("OK")) {
				} else if (command.equalsIgnoreCase("SUBMIT")) {
				} else if (command.equalsIgnoreCase("RESET")) {
				} else {
					buttonWithOutAction(event);
				}
			}
		};

		Thread actionThread = new Thread(actionRunner, e.getSource().toString());
		actionThread.start();
	}

	public void buttonWithOutAction(ActionEvent event) {
		JOptionPane
				.showMessageDialog(this.getParent(),
						((JComponent) event.getSource()).getName() + " : "
								+ "Button without action: Modify class "
								+ this.getClass().getName()
								+ " to add action logic...",
						((JComponent) event.getSource()).getName(),
						JOptionPane.INFORMATION_MESSAGE);
	}
}
