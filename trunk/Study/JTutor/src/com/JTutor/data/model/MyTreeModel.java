package com.JTutor.data.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.JTutor.data.UserData;
import com.JTutor.data.tableRenderer.TreeTableCellRenderer;
import com.JTutor.handler.PopupHandler;

public class MyTreeModel extends JTree implements ActionListener {

	JPopupMenu popup = new JPopupMenu();

	public MyTreeModel(DefaultTreeModel treeRoot) {
		super(treeRoot);
		popup = new JPopupMenu();
		popup.setInvoker(this);
		PopupHandler handler = new PopupHandler(this);
		this.putClientProperty("JTree.icons", makeIcons());
		this.setCellRenderer(new TreeTableCellRenderer());
	}

	private Hashtable makeIcons() {
		Hashtable icons = new Hashtable();
		icons.put("floppyDrive", MetalIconFactory.getTreeFloppyDriveIcon());
		icons.put("hardDrive", MetalIconFactory.getTreeHardDriveIcon());
		icons.put("computer", MetalIconFactory.getTreeComputerIcon());
		return icons;
	}

	@Override
	public String getToolTipText(MouseEvent evt) {
		if (getRowForLocation(evt.getX(), evt.getY()) == -1)
			return null;
		TreePath curPath = getPathForLocation(evt.getX(), evt.getY());
		UserMutableNode file = (UserMutableNode) curPath.getLastPathComponent();
		return file.getIconName() + "";
	}

	// @Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		UserMutableNode dmtn, node;

		TreePath path = this.getSelectionPath();
		dmtn = (UserMutableNode) path.getLastPathComponent();
		if (ae.getActionCommand().equals("insert")) {
			node = new UserMutableNode(new UserData());
			dmtn.add(node);
			((DefaultTreeModel) this.getModel()).nodeStructureChanged(dmtn);
		}
		if (ae.getActionCommand().equals("remove")) {
			node = (UserMutableNode) dmtn.getParent();
			int nodeIndex = node.getIndex(dmtn);
			dmtn.removeAllChildren();
			node.remove(nodeIndex);
			((DefaultTreeModel) this.getModel()).nodeStructureChanged(dmtn);
		}
	}

}
