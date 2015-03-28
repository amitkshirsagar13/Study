package com.JTutor.data.model;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.JTutor.data.UserData;

public class UserMutableNode extends DefaultMutableTreeNode {
	protected String iconName;
	protected Icon icon;
	public UserData userData = null;

	public UserMutableNode(UserData userData) {
		super(userData.getId());
		this.userData = userData;
	}

	public UserMutableNode(String userName) {
		super(userName);
	}

	public UserData getUserData() {
		return userData;
	}

	public void setIconName(String icon) {
		iconName = icon;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}
}
