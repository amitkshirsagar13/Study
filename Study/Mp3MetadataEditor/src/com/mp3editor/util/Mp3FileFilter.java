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
import java.io.File;
import java.io.FileFilter;

public class Mp3FileFilter implements FileFilter {

	@Override
	public boolean accept(File fileName) {
		if (fileName.getName().endsWith("mp3")
				|| fileName.getName().endsWith("MP3")) {
			return true;
		}
		return false;
	}

}
