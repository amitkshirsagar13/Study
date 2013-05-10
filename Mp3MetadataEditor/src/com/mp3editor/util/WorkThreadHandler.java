package com.mp3editor.util;

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
