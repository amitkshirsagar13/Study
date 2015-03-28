package com.JTutor.front;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import com.JTutor.dao.UserDAO;
import com.JTutor.data.User;
import com.JTutor.data.model.UserTableModel;
import com.JTutor.data.tableRenderer.ReportTableRenderer;
import com.JTutor.store.JTutorConst;

public class ReportTab extends JFrame implements JTutorConst, FocusListener {
	private static Logger _log = null;
	private JTutorMainFrame _parent = null;

	private JPanel _reportTab = null;

	public ReportTab(JTutorMainFrame parentFrame) {
		super("ReportTab");
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
	public JPanel getReportTab() {
		if (_reportTab == null)
			_reportTab = (JPanel) this.getContentPane();
		return _reportTab;
	}

	UserTableModel _userTableModel = null;
	JTable _reportTable = null;
	JScrollPane _listScroll = null;
	JPanel _centerPanel = null;

	public void buildUserEntryForm() {
		try {
			_reportTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel.setText("This tab will used for report generation.");
			infoPanel.add(infoLabel);

			_reportTab.add(infoPanel, BorderLayout.NORTH);

			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;

			_centerPanel = new JPanel();
			_centerPanel.setBounds(0, 0, 700, 500);
			_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			_reportTab.add(_centerPanel, BorderLayout.CENTER);

			/*
			 * JTable
			 */
			_userTableModel = new UserTableModel(null);
			_reportTable = new JTable(_userTableModel);

			_reportTable.getColumnModel().getColumn(0)
					.setCellRenderer(new ReportTableRenderer());
			_reportTable.getColumnModel().getColumn(1)
					.setCellRenderer(new ReportTableRenderer());
			_reportTable.getColumnModel().getColumn(2)
					.setCellRenderer(new ReportTableRenderer());

			_reportTable.setCellSelectionEnabled(true);

			_reportTable.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					Point p = e.getPoint();
					int row = _reportTable.rowAtPoint(p);
					int column = _reportTable.columnAtPoint(p);
					_reportTable.setToolTipText(String.valueOf(_reportTable
							.getValueAt(row, column)));
				}// end MouseMoved
			}); // end MouseMotionAdapter

			_listScroll = new JScrollPane(_reportTable);

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

			_reportTab.add(buttonPanel, BorderLayout.SOUTH);

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
		Vector<User> userList = new Vector(UserDAO.getUserList());
		_userTableModel.setUserList(userList);
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		_parent.statusBarMsg("Name:Add User");
		_userTableModel.addUser(new User("", "", ""));
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void changeForm() {
		_parent.statusBarMsg("Check Change");
		List<User> userList = _userTableModel.getUserList();
		int userCount = userList.size();
		for (int index = 0; index < userCount; index++) {
			_log.info("ID: " + userList.get(index).getUserId() + "\tName: "
					+ userList.get(index).getUserName() + "\tRole: "
					+ userList.get(index).getRole());
		}

	}
	// -------------------------------------------------------------------

}
