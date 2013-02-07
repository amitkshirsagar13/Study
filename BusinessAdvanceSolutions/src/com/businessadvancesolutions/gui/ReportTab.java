package com.businessadvancesolutions.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;

public class ReportTab extends JFrame implements FocusListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	public ReportTab(JMasterFrame parentFrame) {
		super("ReportTab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(ReportTab.class);
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

	// UserTableModel _userTableModel = null;
	JTable _mainTable = null;
	JScrollPane _listScroll = null;
	JPanel _centerPanel = null;

	public void buildForm() {
		try {
			_mainTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel.setText("This tab will used for report generation.");
			infoPanel.add(infoLabel);

			_mainTab.add(infoPanel, BorderLayout.NORTH);

			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;

			_centerPanel = new JPanel();
			_centerPanel.setBounds(0, 0, 700, 500);
			_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			_mainTab.add(_centerPanel, BorderLayout.CENTER);

			/*
			 * JTable
			 */
			// _userTableModel = new UserTableModel(null);
			// _mainTable = new JTable(_userTableModel);

			// _mainTable.getColumnModel().getColumn(0)
			// .setCellRenderer(new ReportTableRenderer());
			// _mainTable.getColumnModel().getColumn(1)
			// .setCellRenderer(new ReportTableRenderer());
			// _mainTable.getColumnModel().getColumn(2)
			// .setCellRenderer(new ReportTableRenderer());

			// _mainTable.setCellSelectionEnabled(true);

			// _mainTable.addMouseMotionListener(new MouseMotionAdapter() {
			// @Override
			// public void mouseMoved(MouseEvent e) {
			// Point p = e.getPoint();
			// int row = _mainTable.rowAtPoint(p);
			// int column = _mainTable.columnAtPoint(p);
			// _mainTable.setToolTipText(String.valueOf(_mainTable
			// .getValueAt(row, column)));
			// }// end MouseMoved
			// }); // end MouseMotionAdapter

			_listScroll = new JScrollPane(_mainTable);

			_listScroll.setBounds(5, 5, 150, 200);
			_centerPanel.add(_listScroll);

			/*
			 * Report Buttons
			 */

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

			JButton changeForm = new JButton("changeForm");
			changeForm.setBounds(50, 5, 100, 50);

			changeForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					changeForm();
				}
			});

			buttonPanel.add(changeForm);

			_mainTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("Message Here", e);

		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	// -------------------------------------------------------------------
	protected void submitForm() {
		_parent.statusBarMsg("Name:Get User Report");
		// Vector<User> userList = new Vector(UserDAO.getUserList());
		// _userTableModel.setUserList(userList);
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		_parent.statusBarMsg("Name:Add User");
		// _userTableModel.addUser(new User("", "", ""));
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void changeForm() {
		_parent.statusBarMsg("Check Change");
		// List<User> userList = _userTableModel.getUserList();
		// int userCount = userList.size();
		// for (int index = 0; index < userCount; index++) {
		// _log.info("ID: " + userList.get(index).getUserId() + "\tName: "
		// + userList.get(index).getUserName() + "\tRole: "
		// + userList.get(index).getRole());
		// }

	}
	// -------------------------------------------------------------------

}
