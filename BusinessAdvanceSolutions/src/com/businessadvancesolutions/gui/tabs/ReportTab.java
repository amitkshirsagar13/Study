package com.businessadvancesolutions.gui.tabs;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.awt.BorderLayout;
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

import com.businessadvancesolutions.gui.report.FirstReport;
import com.businessadvancesolutions.gui.startup.JMasterFrame;

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

			_centerPanel = new JPanel();
			_centerPanel.setBounds(0, 0, 700, 500);
			_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			_mainTab.add(_centerPanel, BorderLayout.CENTER);

			// _listScroll = new JScrollPane(_centerPanel);

			// _listScroll.setBounds(5, 5, 150, 200);
			// _centerPanel.add(_listScroll);

			FirstReport html = new FirstReport(_parent);

			html.startPlatform();

			System.out.println("Started");

			html.runReport();

			html.stopPlatform();

			System.out.println("Finished");

			_centerPanel.add(html.getReportPanel());

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
