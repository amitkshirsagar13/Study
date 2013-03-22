package com.businessadvancesolutions.gui;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.businessadvancesolutions.businessmodel.BusinessUser;
import com.businessadvancesolutions.businessmodel.SystemUser;
import com.businessadvancesolutions.dbapi.dao.BusinessUserDAO;
import com.businessadvancesolutions.helper.SystemLogger;

public class LoginTab extends JFrame implements FocusListener, MouseListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	public LoginTab(JMasterFrame parentFrame) {
		super("LoginTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(LoginTab.class);
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

	JTextField userNameText = new JTextField(20);
	JPasswordField userPasswordText = new JPasswordField(20);

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
					.setText("Login into the system using UserName and Password...");
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

			JLabel userIDLabel = new JLabel("UserName");
			assignJLabelDetails(userIDLabel, "User Name: ", firstColumnStart,
					50, 100, 25);

			JLabel userNameLabel = new JLabel("UserPassword");
			assignJLabelDetails(userNameLabel, "User Password: ",
					firstColumnStart, 100, labelWidth, labelHight);

			centerPanel.add(userIDLabel);
			// centerPanel.add(getEmptyLabel());
			centerPanel.add(userNameLabel);
			// centerPanel.add(getEmptyLabel());

			assignJTextFieldDetails(userNameText, "userName",
					firstTextFieldColumnStart, 50, labelWidth, labelHight);
			assignJTextFieldDetails(userPasswordText, "userPassword",
					firstTextFieldColumnStart, 100, labelWidth, labelHight);

			userNameText.setText("amit");
			userPasswordText.setText("poomit");
			centerPanel.add(userNameText);
			centerPanel.add(userPasswordText);

			_mainTab.add(centerPanel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			// buttonPanel.setMinimumSize(new Dimension(800, 100));
			buttonPanel.setBounds(0, 0, 800, 100);

			buttonPanel.setToolTipText("Panel");

			submitForm = new JButton("Login");
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

			// buttonPanel.add(toggleFunction);

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

		try {

			BusinessUser businessUser = BusinessUserDAO.loginUser(
					userNameText.getText(), userPasswordText.getText());
			if (businessUser != null) {
				SystemUser systemUser = new SystemUser();
				systemUser.setSystemUserID(businessUser.getUserId());
				systemUser.setSystemUserName(businessUser.getUserName());
				systemUser.setSystemUserRole(businessUser.getUserRole());
				systemUser
						.setSystemUserPassword(businessUser.getUserPassword());
				_parent.setSystemUser(systemUser);
				_parent.populateWorkTabs();
				_parent.statusBarMsg("System User Set as ID:"
						+ _parent.getSystemUser().getSystemUserID() + "| "
						+ " Name:"
						+ _parent.getSystemUser().getSystemUserName() + "| "
						+ " Role:"
						+ _parent.getSystemUser().getSystemUserRole() + "| "
						+ " Password: | ");
			} else {
				JOptionPane.showMessageDialog(_parent,
						"UserName/Password entered is not correct..",
						"Login Failed", JOptionPane.ERROR_MESSAGE);
				SystemLogger
						.logMessage("UserName/Password entered is not correct.."
								+ userNameText.getText()
								+ "/"
								+ userPasswordText.getText());
			}

		} catch (Exception e) {
			_parent.statusBarMsg("Exception: " + e.getMessage());
		}

	}

	protected void toggleFunction() {
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		userNameText.setText("");
		userPasswordText.setText("");
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
