package com.mp3editor.util;

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
