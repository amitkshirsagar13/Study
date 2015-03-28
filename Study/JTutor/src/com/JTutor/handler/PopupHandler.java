package com.JTutor.handler;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.JTutor.data.model.MyTreeModel;

public class PopupHandler implements ActionListener, PopupMenuListener {

	MyTreeModel tree;
	JPopupMenu popup;
	JMenuItem item;
	boolean overRoot = false;
	Point loc;

	public PopupHandler(MyTreeModel tree) {
		this.tree = tree;
		popup = new JPopupMenu();
		popup.setInvoker(tree);
		JMenu menuInsert = new JMenu("PrintReport");

		popup.add(menuInsert);

		item = new JMenuItem("PrintReportNode");
		menuInsert.add(item);
		item.setActionCommand("REPORTNODE");
		item.addActionListener(this);

		item = new JMenuItem("PrintReportChildren");
		// menuInsert.add(item);
		item.setActionCommand("REPORTCHILDREN");
		item.addActionListener(this);

		tree.addMouseListener(ma);
		popup.addPopupMenuListener(this);
	}

	private JMenuItem getMenuItem(String s) {
		JMenuItem menuItem = new JMenuItem(s);
		menuItem.setActionCommand(s.toUpperCase());
		menuItem.addActionListener(this);
		return menuItem;
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		TreePath path = tree.getPathForLocation(loc.x, loc.y);
		// System.out.println("path = " + path);
		// System.out.printf("loc = [%d, %d]%n", loc.x, loc.y);
		if (ac.equals("REPORTNODE"))
			reportNode(path);
		if (ac.equals("REPORTCHILDREN"))
			reportChild(path);
	}

	private void reportNode(TreePath path) {
		// SMINodeItem rootNode = (SMINodeItem) path.getLastPathComponent();
		// ReportHandler.downloadReport(rootNode);
	}

	private void reportChild(TreePath path) {
		// SMINodeItem rootNode = (SMINodeItem) path.getLastPathComponent();
		// // ReportHandler.downloadReportForChild(rootNode);
	}

	private final MouseListener ma = new MouseAdapter() {
		private void checkForPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				loc = e.getPoint();
				TreePath path = tree.getPathForLocation(loc.x, loc.y);
				// System.out.printf("path = %s%n", path);
				if (path == null) {
					e.consume();
					return;
				}
				TreeNode root = (TreeNode) tree.getModel().getRoot();
				;
				overRoot = path.getLastPathComponent() == root;
				popup.show(tree, loc.x, loc.y);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			checkForPopup(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			checkForPopup(e);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			checkForPopup(e);
		}
	};

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		item.setVisible(!overRoot);
	}

	public void popupMenuCanceled(PopupMenuEvent e) {
	}

	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

}
