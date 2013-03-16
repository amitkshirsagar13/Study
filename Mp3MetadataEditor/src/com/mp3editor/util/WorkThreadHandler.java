package com.mp3editor.util;

/**
 * This software is created by @author Amit Kshirsagar <amit.kshirsagar.13@gmail.com>
 * It is not allowed to copy and distribute without prior approval from Auther.
 */

import com.mp3editor.gui.tabs.Mp3Tab;

public class WorkThreadHandler implements Runnable {
	Mp3Tab templateTab = null;

	public WorkThreadHandler() {
	}

	public void setTemplateTab(Mp3Tab templateTab) {
		this.templateTab = templateTab;
	}

	@Override
	public void run() {
		templateTab.searchImagesOnline();
	}

}
