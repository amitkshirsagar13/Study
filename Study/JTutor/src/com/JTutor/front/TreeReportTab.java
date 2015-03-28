package com.JTutor.front;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.log4j.Logger;

import com.JTutor.dao.UserDAO;
import com.JTutor.data.User;
import com.JTutor.data.UserData;
import com.JTutor.data.UserDataHash;
import com.JTutor.data.model.MyTableModel;
import com.JTutor.data.model.MyTreeModel;
import com.JTutor.data.model.UserMutableNode;
import com.JTutor.data.model.UserNodeIterator;
import com.JTutor.store.JTutorConst;

public class TreeReportTab extends JFrame implements JTutorConst,
		FocusListener, TreeSelectionListener, ActionListener {
	private static Logger _log = null;
	private JTutorMainFrame _parent = null;

	private JPanel _treeReportTab = null;

	public TreeReportTab(JTutorMainFrame parentFrame) {
		super("TreeReportTab");
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
	public JPanel getTreeReportTab() {
		if (_treeReportTab == null)
			_treeReportTab = (JPanel) this.getContentPane();
		return _treeReportTab;
	}

	UserDataHash _userDataHash = null;
	DefaultTreeModel _treeRoot = null;
	MyTreeModel _myTree = null;
	JScrollPane _listScroll = null;

	MyTableModel _tabModel = null;
	Vector _columnNames = null;
	Vector _rowData = null;
	JPanel _tableViewPanel = null;
	JTable _reportTable = null;
	JScrollPane _tableScroll = null;
	JPanel _centerPanel = null;

	public void buildTreeReportForm() {
		try {
			_treeReportTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();
			infoLabel
					.setText("This tab will used for report display with Tree Model...");
			infoPanel.add(infoLabel);

			_treeReportTab.add(infoPanel, BorderLayout.NORTH);

			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.fill = GridBagConstraints.HORIZONTAL;

			_centerPanel = new JPanel();
			_centerPanel.setBounds(0, 0, 700, 500);
			_centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			_treeReportTab.add(_centerPanel, BorderLayout.CENTER);

			/*
			 * JTable
			 */

			_listScroll = new JScrollPane();
			_listScroll.setPreferredSize(new Dimension(100, 350));

			_centerPanel.add(_listScroll, BorderLayout.WEST);

			List<User> userList = UserDAO.getUserList();
			_userDataHash = new UserDataHash();
			for (int index = 0; index < userList.size(); index++) {
				_userDataHash.addUserData(userList.get(index).getUserId());
			}

			UserMutableNode rootNode = getUserDataRootNode();
			_treeRoot = new DefaultTreeModel(rootNode);

			_myTree = new MyTreeModel(_treeRoot);
			_myTree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			_myTree.addTreeSelectionListener(this);

			_myTree.setBorder(BorderFactory.createLoweredBevelBorder());
			_myTree.setRootVisible(true);
			// _myTree.setBounds(0, 0, 500, 500);
			// treeViewPanel.add(_myTree);

			_myTree.setEditable(false);
			_listScroll.getViewport().add(_myTree);
			// _myTree.setBackground(Color.CYAN);

			/*
			 * Table Code
			 */

			_tableViewPanel = new JPanel();
			_tableViewPanel.setName("TableFrame");
			_tableViewPanel.setLayout(null);
			_tableViewPanel.setToolTipText("TableFrame");
			_tableViewPanel.setBorder(BorderFactory.createLoweredBevelBorder());

			_columnNames = new Vector();
			// _columnNames[0] = "Property";
			// _columnNames[1] = "Value";
			_rowData = new Vector();
			// _rowData[0][0] = "property";
			// _rowData[0][1] = "value";
			_tabModel = new MyTableModel();
			_tabModel.setDataVector(_rowData, _columnNames);
			_reportTable = new JTable(_tabModel);

			// _reportTable.getColumnModel().getColumn(0)
			// .setCellRenderer(new ReportTableRenderer());

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

			_tableScroll = new JScrollPane(_reportTable);

			_tableScroll.setPreferredSize(new Dimension(400, 350));
			_centerPanel.add(_tableScroll, BorderLayout.EAST);

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

			_treeReportTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("Message Here", e);

		}
	}

	public UserMutableNode getUserDataRootNode() {
		UserNodeIterator requestTypeIterator = new UserNodeIterator();
		UserMutableNode rootNode = requestTypeIterator
				.getRootNode(_userDataHash);
		return rootNode;
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
		// _userTableModel.setUserList(userList);
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
		_parent.statusBarMsg("Recreate Tree for user...");
		List<User> userList = UserDAO.getUserList();
		_userDataHash = new UserDataHash();
		for (int index = 0; index < userList.size(); index++) {
			_userDataHash.addUserData(userList.get(index).getUserId());
		}

		UserMutableNode rootNode = getUserDataRootNode();
		_treeRoot = new DefaultTreeModel(rootNode);
		_myTree.setModel(_treeRoot);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		UserMutableNode node = (UserMutableNode) _myTree
				.getLastSelectedPathComponent();

		if (node == null)
			// Nothing is selected.
			return;

		UserData userData = node.getUserData();
		if (userData != null) {
			_columnNames = new Vector(Arrays.asList(userData
					.getColumnIdentifiers()));

			String[][] recordData = userData.getRowData();

			_rowData = new Vector();
			for (int index = 0; index < recordData.length; index++) {
				Vector record = new Vector(Arrays.asList(recordData[index]));
				_rowData.add(record);
			}

			_tabModel.setDataVector(_rowData, _columnNames);

		} else {
			_columnNames = new Vector();
			_rowData = new Vector();
			_tabModel.setDataVector(_rowData, _columnNames);
		}
	}
}
