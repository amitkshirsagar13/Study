package com.mp3editor.util;

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
