package com.JTutor.front;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.JTutor.dao.UserDAO;
import com.JTutor.data.User;
import com.JTutor.store.JTutorConst;

public class AddUserTab extends JFrame implements JTutorConst, FocusListener {
	private static Logger _log = null;
	private JTutorMainFrame _parent = null;

	private JPanel _addUserTab = null;

	public AddUserTab(JTutorMainFrame parentFrame) {
		super("AddUserTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(AddUserTab.class);
		}
	}

	/**
	 * Gets the content pane
	 * 
	 * @returns JPanel the content pane
	 */
	public JPanel getAddUserTab() {
		if (_addUserTab == null)
			_addUserTab = (JPanel) this.getContentPane();
		return _addUserTab;
	}

	JTextField userIDText = new JTextField(20);
	JTextField userNameText = new JTextField(20);
	JComboBox userRoleList = new JComboBox();

	public void buildUserEntryForm() {
		try {
			_addUserTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel
					.setText("Add the user details for Tution Minutes and Earnings in this tab.");
			infoPanel.add(infoLabel);

			_addUserTab.add(infoPanel, BorderLayout.NORTH);

			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;

			JPanel centerPanel = new JPanel();
			centerPanel.setBounds(0, 0, 700, 500);
			centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			JPanel addLabels = new JPanel();
			addLabels.setLayout(new GridBagLayout());
			// addLabels.setBorder(BorderFactory.createLoweredBevelBorder());

			JLabel userIDLabel = new JLabel("UserID");
			userIDLabel.setText("            User ID: ");
			// addLabels.add(userIDLabel);
			JLabel userNameLabel = new JLabel("User Name");
			userNameLabel.setText("            User Name: ");
			// addLabels.add(userNameLabel);
			JLabel userRoleLabel = new JLabel("UserRole");
			userRoleLabel.setText("            User Role: ");
			// addLabels.add(userRoleLabel);

			addLabels.add(userIDLabel, c);
			addLabels.add(getEmptyLabel(), c);
			addLabels.add(userNameLabel, c);
			addLabels.add(getEmptyLabel(), c);
			addLabels.add(userRoleLabel, c);
			addLabels.add(getEmptyLabel(), c);

			centerPanel.add(addLabels, BorderLayout.WEST);

			JPanel entryText = new JPanel();
			entryText.setLayout(new GridBagLayout());
			// entryText.setBorder(BorderFactory.createLoweredBevelBorder());
			userIDText.setText("ID");
			userIDText.addFocusListener(this);
			userNameText.setText("NAME");
			userNameText.setActionCommand("Name");
			userNameText.addFocusListener(this);
			populateList();
			userRoleList.addFocusListener(this);

			entryText.add(userIDText, c);
			entryText.add(getEmptyLabel(), c);
			entryText.add(userNameText, c);
			entryText.add(getEmptyLabel(), c);
			entryText.add(userRoleList, c);
			entryText.add(getEmptyLabel(), c);

			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1.0;
			c.weighty = 1.0;

			centerPanel.add(entryText, BorderLayout.EAST);

			_addUserTab.add(centerPanel, BorderLayout.CENTER);

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

			_addUserTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("buildAddUserForm", e);

		}

	}

	private JLabel getEmptyLabel() {
		JLabel emptyJLabel = new JLabel("Empty");
		emptyJLabel.setText(" ");
		return emptyJLabel;
	}

	@Override
	public void focusGained(FocusEvent evt) {
		if (evt.getSource() == userIDText) {
			if (userIDText.getText().equals("ID"))
				userIDText.setText("");
		} else if (evt.getSource() == userNameText) {
			if (userNameText.getText().equals("NAME"))
				userNameText.setText("");
		} else if (evt.getSource() == userRoleList) {
			// if (userRoleList.getText().equals("ROLE"))
			// userRoleList.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent evt) {
		if (evt.getSource() == userIDText) {
			if (userIDText.getText().equals(""))
				userIDText.setText("ID");
		} else if (evt.getSource() == userNameText) {
			if (userNameText.getText().equals(""))
				userNameText.setText("NAME");
		} else if (evt.getSource() == userRoleList) {
			if (userRoleList.getSelectedIndex() == 0)
				userRoleList.setSelectedIndex(0);
		}
	}

	// -------------------------------------------------------------------
	protected void submitForm() {
		_parent.statusBarMsg(" ID:" + userIDText.getText() + "/Name:"
				+ userNameText.getText() + "/ROLE:"
				+ userRoleList.getSelectedItem());
		User user = UserDAO.addUser(userNameText.getText(),
				userRoleList.getSelectedItem() + "");
		userNameText.setText(user.getUserName());
		userIDText.setText(user.getUserId());
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		userIDText.setText("ID");
		userNameText.setText("NAME");
		userRoleList.setSelectedIndex(0);
		_parent.statusBarMsg("ID:" + userIDText.getText()

		+ "/Name:" + userNameText.getText() + "/Role:"
				+ userRoleList.getSelectedItem());
	}

	// -------------------------------------------------------------------
	private void populateList() {
		List<String> roleList = UserDAO.getRoleList();
		for (Iterator iterator = roleList.iterator(); iterator.hasNext();) {
			String roleValue = (String) iterator.next();
			userRoleList.addItem(roleValue);
		}
		userRoleList.setSelectedIndex(0);
		_parent.statusBarMsg("Populated Role List...");
		_log.info("Populated Role List...");
	}
}
