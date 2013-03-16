package com.mp3editor.util;

/**
 * This software is created by @author Amit Kshirsagar <amit.kshirsagar.13@gmail.com>
 * It is not allowed to copy and distribute without prior approval from Auther.
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
