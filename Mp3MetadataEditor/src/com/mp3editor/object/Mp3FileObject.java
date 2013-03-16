package com.mp3editor.object;

import java.io.File;

public class Mp3FileObject {
	private String fileName = null;
	private String track = null;
	private String title = null;
	private String artist = null;
	private String album = null;
	private String year = null;
	private String genre = null;
	private String imageName = null;
	private String comment = null;
	private String customSearch = null;
	private String filePath = null;
	private File file = null;

	public Mp3FileObject(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public String getTrack() {
		return track;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

	public String getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	public String getImageName() {
		return imageName;
	}

	public String getComment() {
		return comment;
	}

	public String getCustomSearch() {
		return customSearch;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCustomSearch(String customSearch) {
		this.customSearch = customSearch;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getFile() {
		return file;
	}
}
