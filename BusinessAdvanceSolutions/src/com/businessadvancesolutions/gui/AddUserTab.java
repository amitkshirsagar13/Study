package com.businessadvancesolutions.gui;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.businessadvancesolutions.businessmodel.BusinessUser;
import com.businessadvancesolutions.dbapi.dao.BusinessUserDAO;

public class AddUserTab extends JFrame implements FocusListener, MouseListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	public AddUserTab(JMasterFrame parentFrame) {
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
	public JPanel getTab() {
		if (_mainTab == null)
			_mainTab = (JPanel) this.getContentPane();
		return _mainTab;
	}

	JTextField userIDText = new JTextField(20);
	JTextField userNameText = new JTextField(20);
	JTextField userRoleText = new JTextField(20);

	JButton resetForm = null;
	JButton toggleFunction = null;
	JButton submitForm = null;

	public void buildForm() {
		try {
			_mainTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel
					.setText("Add the user details for Tution Minutes and Earnings in this tab.");
			infoPanel.add(infoLabel);

			_mainTab.add(infoPanel, BorderLayout.NORTH);

			JPanel centerPanel = new JPanel();
			// centerPanel.setBounds(0, 0, 700, 800);
			centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			centerPanel.setLayout(null);

			int labelWidth = 150;
			int labelHight = 25;

			/*
			 * First Column
			 */
			int firstColumnStart = 50;

			int firstTextFieldColumnStart = labelWidth + firstColumnStart + 50;

			JLabel userIDLabel = new JLabel("UserID");
			assignJLabelDetails(userIDLabel, "User ID: ", firstColumnStart, 50,
					labelWidth, labelHight);

			JLabel userNameLabel = new JLabel("UserName");
			assignJLabelDetails(userNameLabel, "User Name: ", firstColumnStart,
					100, labelWidth, labelHight);

			JLabel userRoleLabel = new JLabel("UserRole");
			assignJLabelDetails(userRoleLabel, "User Role: ", firstColumnStart,
					150, labelWidth, labelHight);

			centerPanel.add(userIDLabel);
			// centerPanel.add(getEmptyLabel());
			centerPanel.add(userNameLabel);
			// centerPanel.add(getEmptyLabel());
			centerPanel.add(userRoleLabel);
			// centerPanel.add(getEmptyLabel());

			assignJTextFieldDetails(userIDText, "userID",
					firstTextFieldColumnStart, 50, labelWidth, labelHight, true);
			assignJTextFieldDetails(userNameText, "userName",
					firstTextFieldColumnStart, 100, labelWidth, labelHight);
			assignJTextFieldDetails(userRoleText, "userRole",
					firstTextFieldColumnStart, 150, labelWidth, labelHight);

			centerPanel.add(userIDText);
			centerPanel.add(userNameText);
			centerPanel.add(userRoleText);

			_mainTab.add(centerPanel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			// buttonPanel.setMinimumSize(new Dimension(800, 100));
			buttonPanel.setBounds(0, 0, 800, 100);

			buttonPanel.setToolTipText("Panel");

			submitForm = new JButton("Insert/Update");
			submitForm.setBounds(50, 5, 100, 50);

			submitForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					submitForm();
				}
			});

			buttonPanel.add(submitForm);

			resetForm = new JButton("Reset");
			resetForm.setBounds(50, 5, 100, 50);

			resetForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					resetForm();
				}
			});

			buttonPanel.add(resetForm);

			toggleFunction = new JButton("ToggleQuery");
			toggleFunction.setBounds(50, 5, 100, 50);

			toggleFunction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					toggleFunction();
				}
			});

			buttonPanel.add(toggleFunction);

			_mainTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("buildForm", e);

		}

	}

	private JLabel getEmptyLabel() {
		JLabel emptyJLabel = new JLabel("Empty");
		emptyJLabel.setText(" : ");
		return emptyJLabel;
	}

	@Override
	public void focusGained(FocusEvent evt) {
		// if (evt.getSource() == userIDText) {
		// if (userIDText.getText().equals("UserID")) {
		// userIDText.setText("");
		// }
		// } else if (evt.getSource() == userNameText) {
		// if (userNameText.getText().equals("UserName")) {
		// userNameText.setText("");
		// }
		// } else if (evt.getSource() == userRoleText) {
		// if (userRoleText.getText().equals("UserRole")) {
		// userRoleText.setText("");
		// }
		// }
	}

	@Override
	public void focusLost(FocusEvent evt) {
		// if (evt.getSource() == userIDText) {
		// if (userIDText.getText().equals("")) {
		// userIDText.setText("UserID");
		// }
		// } else if (evt.getSource() == userNameText) {
		// if (userNameText.getText().equals("")) {
		// userNameText.setText("UserName");
		// }
		// } else if (evt.getSource() == userRoleText) {
		// if (userRoleText.getText().equals("")) {
		// userRoleText.setText("UserRole");
		// }
		// }
	}

	// -------------------------------------------------------------------
	protected void submitForm() {

		if (userIDText.isEnabled()) {
			try {

				List<BusinessUser> businessUserList = BusinessUserDAO
						.getBusinessUserList(userIDText.getText(),
								userNameText.getText(), null);
				if (businessUserList.size() > 0) {
					userIDText
							.setText(businessUserList.get(0).getUserId() + "");
					userNameText.setText(businessUserList.get(0).getUserName());
					userRoleText.setText(businessUserList.get(0).getUserRole()
							+ "");
				}

			} catch (Exception e) {
				_parent.statusBarMsg("Exception: " + e.getMessage());
			}
			_parent.statusBarMsg(" ID:" + userIDText.getText() + "| "
					+ " Name:" + userNameText.getText() + "| " + " Role:"
					+ userRoleText.getText() + "| " + " Password: | ");

		} else {
			if (userIDText.getText().equals("")) {
				BusinessUser businessUser = BusinessUserDAO.addBusinessUser(
						userIDText.getText(), userNameText.getText(),
						userRoleText.getText());
				userIDText.setText("" + businessUser.getUserId());
				_parent.statusBarMsg("Inserted: " + businessUser.getUserId()
						+ " for " + businessUser.getUserName());
			} else {
				boolean insertStatus = BusinessUserDAO.updateBusinessUser(
						userIDText.getText(), userNameText.getText(),
						userRoleText.getText());
				_parent.statusBarMsg("Updated: " + insertStatus);
			}

		}

	}

	protected void toggleFunction() {

		if (!userIDText.isEnabled()) {
			_parent.statusBarMsg("ID: Enabled For Query.");
			userIDText.setEnabled(true);
			toggleFunction.setText("ToggleInsert/Update");
			submitForm.setText("Query");
		} else {
			_parent.statusBarMsg("ID: Disabled For Insert.");
			userIDText.setEnabled(false);
			submitForm.setText("Insert/Update");
			toggleFunction.setText("ToggleQuery");
		}

	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		userIDText.setText("");
		userNameText.setText("");
		userRoleText.setText("");
	}

	// -------------------------------------------------------------------
	private void populateList() {
		_log.info("Populated Role List...");
	}

	private void assignJLabelDetails(JLabel newJLabel, String text,
			int startHorizontal, int startVertical, int width, int hight) {
		newJLabel.setText(" " + text);
		newJLabel.setToolTipText(text);
		newJLabel.setBounds(getBound(startHorizontal, startVertical, width,
				hight));
		newJLabel.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	private void assignJTextFieldDetails(JTextField newJTextField, String text,
			int startHorizontal, int startVertical, int width, int hight) {
		assignJTextFieldDetails(newJTextField, text, startHorizontal,
				startVertical, width, hight, false);
	}

	private void assignJTextFieldDetails(JTextField newJTextField, String text,
			int startHorizontal, int startVertical, int width, int hight,
			boolean disabled) {
		newJTextField.setName(text);
		if (disabled) {
			newJTextField.setEnabled(false);
		}
		newJTextField.setToolTipText(text);
		newJTextField.setBounds(getBound(startHorizontal, startVertical, width,
				hight));
		newJTextField.setBorder(BorderFactory.createLoweredBevelBorder());

		newJTextField.addMouseListener(this);
		newJTextField.addFocusListener(this);

	}

	private Rectangle getBound(int startHorizontal, int startVertical,
			int width, int hight) {
		return new Rectangle(startHorizontal, startVertical, width, hight);
	}

	JTextField _dummyTextField = new JTextField();

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 3) {
			if (event.getSource().getClass().equals(_dummyTextField.getClass())) {
				((JTextField) event.getSource()).setText("");
			} else {
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub

	}
}
