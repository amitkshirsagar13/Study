package com.mp3editor.object;

/**
 * This software is created by @author Amit Kshirsagar <amit.kshirsagar.13@gmail.com>
 * It is not allowed to copy and distribute without prior approval from Auther.
 */

import java.util.HashMap;

public class Mp3FileHash extends HashMap<String, Mp3FileObject> {

	public boolean addMp3File(Mp3FileObject mp3File) {
		boolean mp3FileAdded = false;
		if (this.containsKey(getKeyString(mp3File))) {
			this.remove(getKeyString(mp3File));
			this.put(getKeyString(mp3File), mp3File);
		} else {
			this.put(getKeyString(mp3File), mp3File);
			mp3FileAdded = true;
		}
		return mp3FileAdded;
	}

	private String getKeyString(Mp3FileObject mp3File) {
		return mp3File.getFileName().replaceAll(" ", "");
	}

	private String getKeyString(String mp3File) {
		return mp3File.replaceAll(" ", "");
	}

	public Mp3FileObject getMp3File(String fileName) {
		Mp3FileObject mp3File = null;
		if (this.containsKey(getKeyString(fileName))) {
			mp3File = this.get(getKeyString(fileName));
		}
		return mp3File;
	}
}
