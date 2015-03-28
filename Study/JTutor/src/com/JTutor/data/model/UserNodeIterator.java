package com.JTutor.data.model;

import java.util.Iterator;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import com.JTutor.data.UserData;
import com.JTutor.data.UserDataHash;

public class UserNodeIterator extends DefaultMutableTreeNode {
	private UserMutableNode rootNode = null;
	private UserDataHash root = null;

	public UserMutableNode getRootNode(UserDataHash userDataHash) {
		root = userDataHash;
		rootNode = new UserMutableNode("UserData");
		rootNode.setIconName("computer");
		if (userDataHash != null) {
			listAllUserDataHash(rootNode, root);
		}
		return rootNode;
	}

	public void listAllUserDataHash(UserMutableNode rootNode1,
			UserDataHash root1) {
		Set requestTypeSet = root1.keySet();

		Iterator requestTypeIterator = requestTypeSet.iterator();

		while (requestTypeIterator.hasNext()) {
			UserData requestTypeData = (UserData) root1.get(requestTypeIterator
					.next());
			UserMutableNode node = new UserMutableNode(requestTypeData);
			node.setIconName("hardDrive");
			rootNode1.add(node);
		}
		rootNode1 = (UserMutableNode) UserNodeIterator.sortTree(rootNode1);
	}

	public static DefaultMutableTreeNode sortTree(DefaultMutableTreeNode root) {
		{
			for (int i = 0; i < root.getChildCount() - 1; i++) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
						.getChildAt(i);
				String nt = node.getUserObject().toString();

				for (int j = i + 1; j <= root.getChildCount() - 1; j++) {
					DefaultMutableTreeNode prevNode = (DefaultMutableTreeNode) root
							.getChildAt(j);
					String np = prevNode.getUserObject().toString();
					if (nt.compareToIgnoreCase(np) > 0) {
						root.insert(node, j);
						root.insert(prevNode, i);
					}
				}
				if (node.getChildCount() > 0) {
					node = sortTree(node);
				}
			}

			return root;
		}
	}

}
