package com.JTutor.data.tableRenderer;

import java.awt.Component;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.JTutor.data.model.UserMutableNode;

public class TreeTableCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5593629042737938947L;

	// public static final DefaultTreeCellRenderer DEFAULT_RENDERER = new
	// DefaultTreeCellRenderer();

	public TreeTableCellRenderer() {
		// setOpenIcon(new ImageIcon("resourceFiles/images/rootNode.png"));
		// setClosedIcon(new ImageIcon("resourceFiles/images/closed.png"));
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		Component renderer = super.getTreeCellRendererComponent(tree, value,
				sel, expanded, leaf, row, hasFocus);
		// SMINodeItem node = (SMINodeItem) value;

		// setToolTipText(node.getSize()+"");
		Icon icon = ((UserMutableNode) value).getIcon();

		if (icon == null) {
			Hashtable icons = (Hashtable) tree.getClientProperty("JTree.icons");
			String name = ((UserMutableNode) value).getIconName();
			if ((icons != null) && (name != null)) {
				icon = (Icon) icons.get(name);
				if (icon != null) {
					setIcon(icon);
				}
			}
		} else {
			setIcon(icon);
		}

		return this;

	}

	private final JTextField editor = new JTextField();

	protected JTextField getNodeRendererEditor() {
		return editor;
	}

	private void setIcon(String icon) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
