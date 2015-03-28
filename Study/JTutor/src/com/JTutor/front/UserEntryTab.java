package com.JTutor.front;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.JTutor.store.JTutorConst;

public class UserEntryTab extends JFrame implements JTutorConst, FocusListener {
	private static Logger _log = null;
	private JTutorMainFrame _parent = null;

	private JPanel _userEntryTab = null;

	public UserEntryTab(JTutorMainFrame parentFrame) {
		super("UserEntryTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(UserEntryTab.class);
		}
	}

	/**
	 * Gets the content pane
	 * 
	 * @returns JPanel the content pane
	 */
	public JPanel getUserEntryTab() {
		if (_userEntryTab == null)
			_userEntryTab = (JPanel) this.getContentPane();
		return _userEntryTab;
	}

	JTextField userNameText = new JTextField(20);
	JTextField tutionMinutesText = new JTextField(20);
	JTextField earningText = new JTextField(20);

	public void buildUserEntryForm() {
		try {
			_userEntryTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel
					.setText("Add the user details for Tution Minutes and Earnings in this tab.");
			infoPanel.add(infoLabel);

			_userEntryTab.add(infoPanel, BorderLayout.NORTH);

			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;

			JPanel centerPanel = new JPanel();
			centerPanel.setBounds(0, 0, 700, 500);
			centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			JPanel entryLabels = new JPanel();
			entryLabels.setLayout(new GridBagLayout());
			// entryLabels.setBorder(BorderFactory.createLoweredBevelBorder());
			JLabel userNameLabel = new JLabel("User Name");
			userNameLabel.setText("            User Name: ");
			// entryLabels.add(userNameLabel);
			JLabel tutionMinutesLabel = new JLabel("Tution Minutes");
			tutionMinutesLabel.setText("            Tution minutes: ");
			// entryLabels.add(tutionMinutesLabel);
			JLabel earningLabel = new JLabel("Earning");
			earningLabel.setText("            Earning: ");
			// entryLabels.add(earningLabel);

			entryLabels.add(userNameLabel, c);
			entryLabels.add(getEmptyLabel(), c);
			entryLabels.add(tutionMinutesLabel, c);
			entryLabels.add(getEmptyLabel(), c);
			entryLabels.add(earningLabel, c);
			entryLabels.add(getEmptyLabel(), c);

			centerPanel.add(entryLabels, BorderLayout.WEST);

			JPanel entryText = new JPanel();
			entryText.setLayout(new GridBagLayout());
			// entryText.setBorder(BorderFactory.createLoweredBevelBorder());
			userNameText.setText("NAME");
			userNameText.setActionCommand("Name");
			userNameText.addFocusListener(this);
			tutionMinutesText.setText("MINUTES");
			tutionMinutesText.addFocusListener(this);
			earningText.setText("EARNINGS");
			earningText.addFocusListener(this);

			entryText.add(userNameText, c);
			entryText.add(getEmptyLabel(), c);
			entryText.add(tutionMinutesText, c);
			entryText.add(getEmptyLabel(), c);
			entryText.add(earningText, c);
			entryText.add(getEmptyLabel(), c);

			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1.0;
			c.weighty = 1.0;

			centerPanel.add(entryText, BorderLayout.EAST);

			_userEntryTab.add(centerPanel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			// buttonPanel.setMinimumSize(new Dimension(800, 100));
			buttonPanel.setBounds(0, 0, 800, 100);

			buttonPanel.setToolTipText("Panel");

			JButton submitForm = new JButton("SubmitForm");
			submitForm.setBounds(50, 5, 100, 50);

			submitForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					submitForm();
				}
			});

			buttonPanel.add(submitForm);

			JButton resetForm = new JButton("resetForm");
			resetForm.setBounds(50, 5, 100, 50);

			resetForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					resetForm();
				}
			});

			buttonPanel.add(resetForm);

			_userEntryTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("buildUserEntryForm", e);

		}

	}

	private JLabel getEmptyLabel() {
		JLabel emptyJLabel = new JLabel("Empty");
		emptyJLabel.setText(" ");
		return emptyJLabel;
	}

	@Override
	public void focusGained(FocusEvent evt) {
		if (evt.getSource() == userNameText) {
			if (userNameText.getText().equals("NAME"))
				userNameText.setText("");
		} else if (evt.getSource() == tutionMinutesText) {
			if (tutionMinutesText.getText().equals("MINUTES"))
				tutionMinutesText.setText("");
		} else if (evt.getSource() == earningText) {
			if (earningText.getText().equals("EARNINGS"))
				earningText.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent evt) {
		if (evt.getSource() == userNameText) {
			if (userNameText.getText().equals(""))
				userNameText.setText("NAME");
		} else if (evt.getSource() == tutionMinutesText) {
			if (tutionMinutesText.getText().equals(""))
				tutionMinutesText.setText("MINUTES");
		} else if (evt.getSource() == earningText) {
			if (earningText.getText().equals(""))
				earningText.setText("EARNINGS");
		}
	}

	// -------------------------------------------------------------------
	protected void submitForm() {
		_parent.statusBarMsg("Name:" + userNameText.getText()
				+ "/TutorMinutes:" + tutionMinutesText.getText() + "/Earnings:"
				+ earningText.getText());
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		userNameText.setText("NAME");
		tutionMinutesText.setText("MINUTES");
		earningText.setText("EARNINGS");
		_parent.statusBarMsg("Name:" + userNameText.getText()
				+ "/TutorMinutes:" + tutionMinutesText.getText() + "/Earnings:"
				+ earningText.getText());
	}
	// -------------------------------------------------------------------

}
