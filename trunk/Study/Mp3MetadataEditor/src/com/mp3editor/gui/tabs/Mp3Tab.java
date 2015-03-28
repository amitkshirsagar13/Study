package com.mp3editor.gui.tabs;

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
import jaco.mp3.player.MP3Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.mp3editor.gui.startup.JMasterFrame;
import com.mp3editor.logger.SystemLogger;
import com.mp3editor.object.Mp3FileObject;
import com.mp3editor.util.ImageHelper;
import com.mp3editor.util.SearchResultParser;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v22Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Mp3Tab extends JFrame implements FocusListener, MouseListener {
	private static Logger _log = null;
	private JMasterFrame _parent = null;

	private JPanel _mainTab = null;

	private Mp3FileObject mp3FileObject = null;
	private MP3Player mp3Player = null;

	public MP3Player getMP3PlayerForThisFile(File mp3File) {
		if (mp3Player == null) {
			mp3Player = new MP3Player(mp3File);
		} else {
			if (mp3Player.isStopped()) {
				mp3Player = new MP3Player(mp3File);
			} else {
				boolean pause = false;
				if (mp3Player.isPaused()) {
					pause = true;
				}
				mp3Player.stop();
				mp3Player = new MP3Player(mp3File);
				if (!pause) {
					mp3Player.play();
				} else {
					mp3Player.play();
					mp3Player.pause();
				}
			}
		}
		return mp3Player;
	}

	public void setForm(File chosenFile) {

		if (chosenFile == null || chosenFile.isDirectory()) {
			return;
		} else if (!(chosenFile.getName().endsWith("mp3") || chosenFile
				.getName().endsWith("MP3"))) {
			_parent.statusBarMsg("Selected File is not mp3 or not Compatible for ID3v1 or ID3v2 tags...");
			JOptionPane
					.showMessageDialog(
							this,
							"Selected File is not mp3 or not Compatible for ID3v1 or ID3v2 tags...",
							"Invalid File Format", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Mp3File mp3file = null;
		try {
			mp3file = new Mp3File(chosenFile.getAbsolutePath());
		} catch (UnsupportedTagException e1) {
			_parent.statusBarMsg("Error Reading Mp3 File..."
					+ chosenFile.getName());
			SystemLogger.logError("Error Reading Mp3 File...", e1);
			JOptionPane
					.showMessageDialog(
							this,
							"Selected File is not mp3 or not Compatible for ID3v1 or ID3v2 tags...",
							"Invalid File Format", JOptionPane.WARNING_MESSAGE);
			return;
		} catch (InvalidDataException e1) {
			_parent.statusBarMsg("Error Reading Mp3 File..."
					+ chosenFile.getName());
			SystemLogger.logError("Error Reading Mp3 File...", e1);
			JOptionPane
					.showMessageDialog(
							this,
							"Selected File is not mp3 or not Compatible for ID3v1 or ID3v2 tags...",
							"Invalid File Format", JOptionPane.WARNING_MESSAGE);
			return;
		} catch (IOException e1) {
			_parent.statusBarMsg("Error Reading Mp3 File..."
					+ chosenFile.getName());
			SystemLogger.logError("Error Reading Mp3 File...", e1);
			JOptionPane
					.showMessageDialog(
							this,
							"Selected File is not mp3 or not Compatible for ID3v1 or ID3v2 tags...",
							"Invalid File Format", JOptionPane.WARNING_MESSAGE);
			return;
		}
		this.mp3FileObject = new Mp3FileObject(chosenFile);
		mp3FileObject.setFileName(chosenFile.getName());
		mp3FileObject.setFilePath(chosenFile.getAbsolutePath());
		if (mp3file.hasId3v1Tag()) {
			ID3v1 id3v1Tag = mp3file.getId3v1Tag();
			mp3FileObject.setTrack(id3v1Tag.getTrack());
			mp3FileObject.setArtist(id3v1Tag.getArtist());
			mp3FileObject.setTitle(id3v1Tag.getTitle());
			mp3FileObject.setAlbum(id3v1Tag.getAlbum());
			mp3FileObject.setYear(id3v1Tag.getYear());
			mp3FileObject.setGenre(id3v1Tag.getGenre() + " ("
					+ id3v1Tag.getGenreDescription() + ")");
			mp3FileObject.setComment(id3v1Tag.getComment());
		}
		ImageIcon icon = new ImageIcon(_parent.getProperty("IMAGES_DIR")
				+ "/musicFile.jpg");
		if (mp3file.hasId3v2Tag()) {
			ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			byte[] imageData = id3v2Tag.getAlbumImage();
			if (imageData != null) {
				// String mimeType = id3v2Tag.getAlbumImageMimeType();
				// System.out.println("Mime type: " + mimeType);
				BufferedImage img = null;

				try {
					img = ImageIO.read(new ByteArrayInputStream(imageData));
					icon = new ImageIcon(img);
				} catch (FileNotFoundException e) {
					SystemLogger.logError("Icon Read Filed: ", e);
				} catch (IOException e) {
					SystemLogger.logError("Icon Read Filed: ", e);
				}
			}
		}
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(127, 94,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		albumArtImage.setIcon(icon);

		fileName.setText(mp3FileObject.getFileName());
		track.setText(mp3FileObject.getTrack());
		title.setText(mp3FileObject.getTitle());
		artist.setText(mp3FileObject.getArtist());
		album.setText(mp3FileObject.getAlbum());
		year.setText(mp3FileObject.getYear());
		genre.setText(mp3FileObject.getGenre());
		comment.setText(mp3FileObject.getComment());

		customSearchTerm.setText(mp3FileObject.getTitle() + " "
				+ mp3FileObject.getAlbum() + " " + mp3FileObject.getArtist());
		resetSearchResultImages();
		getMP3PlayerForThisFile(chosenFile);

		// mp3Player.addToPlayList(chosenFile);
	}

	public Mp3Tab(JMasterFrame parentFrame) {
		super("Mp3Tab");
		_parent = parentFrame;
		if (_log == null) {
			_log = Logger.getLogger(Mp3Tab.class);
		}
	}

	/**
	 * Gets the content pane
	 * 
	 * @returns JPanel the content pane
	 */
	public JPanel getTab() {
		if (_mainTab == null)
			_mainTab = (JPanel) this.getContentPane();
		return _mainTab;
	}

	JTextField fileName = new JTextField(20);
	JTextField track = new JTextField(20);
	JTextField title = new JTextField(20);
	JTextField artist = new JTextField(20);
	JTextField album = new JTextField(20);
	JTextField year = new JTextField(20);
	JTextField genre = new JTextField(20);

	JTextField imageName = new JTextField(20);
	JTextField comment = new JTextField(20);
	JTextField customSearchTerm = new JTextField(20);

	JButton albumArtImage = new JButton();
	private static JProgressBar progressBar = new JProgressBar();
	List<JButton> albumArtButtonList = null;

	JButton resetForm = null;
	JButton submitForm = null;
	JButton playMp3 = null;
	JButton stopMp3 = null;

	JPanel centerPanel = null;

	public void buildForm() {
		try {
			_mainTab = (JPanel) this.getContentPane();

			JPanel infoPanel = new JPanel();
			infoPanel.setBounds(10, 10, 600, 200);
			JLabel infoLabel = new JLabel();

			infoLabel.setText("Play Song...");

			infoPanel.add(infoLabel);

			_mainTab.add(infoPanel, BorderLayout.NORTH);

			centerPanel = new JPanel();
			// centerPanel.setBounds(0, 0, 700, 800);
			centerPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			centerPanel.setLayout(null);

			int labelWidth = 150;
			int labelHight = 25;

			/*
			 * First Column
			 */
			int firstColumnStart = 50;
			int firstTextFieldColumnStart = labelWidth + firstColumnStart + 50;

			JLabel fileNameLabel = new JLabel("fileNameLabel");
			assignJLabelDetails(fileNameLabel, "File Name: ", firstColumnStart,
					50, labelWidth, labelHight);

			JLabel trackLabel = new JLabel("trackLabel");
			assignJLabelDetails(trackLabel, "Track Number: ", firstColumnStart,
					100, labelWidth, labelHight);

			JLabel titleLabel = new JLabel("titleLabel");
			assignJLabelDetails(titleLabel, "Title: ", firstColumnStart, 150,
					labelWidth, labelHight);

			JLabel artistLabel = new JLabel("artistLabel");
			assignJLabelDetails(artistLabel, "Artist: ", firstColumnStart, 200,
					labelWidth, labelHight);

			JLabel albumLabel = new JLabel("albumLabel");
			assignJLabelDetails(albumLabel, "Album: ", firstColumnStart, 250,
					labelWidth, labelHight);

			JLabel yearLabel = new JLabel("yearLabel");
			assignJLabelDetails(yearLabel, "Year: ", firstColumnStart, 300,
					labelWidth, labelHight);

			JLabel genreLabel = new JLabel("genreLabel");
			assignJLabelDetails(genreLabel, "Genre: ", firstColumnStart, 350,
					labelWidth, labelHight);

			assignJTextFieldDetails(fileName, "fileName",
					firstTextFieldColumnStart, 50, labelWidth, labelHight, true);

			assignJTextFieldDetails(track, "track", firstTextFieldColumnStart,
					100, labelWidth, labelHight);

			assignJTextFieldDetails(title, "title", firstTextFieldColumnStart,
					150, labelWidth, labelHight);

			assignJTextFieldDetails(artist, "artist",
					firstTextFieldColumnStart, 200, labelWidth, labelHight);

			assignJTextFieldDetails(album, "album", firstTextFieldColumnStart,
					250, labelWidth, labelHight);

			assignJTextFieldDetails(year, "year", firstTextFieldColumnStart,
					300, labelWidth, labelHight);

			assignJTextFieldDetails(genre, "genre", firstTextFieldColumnStart,
					350, labelWidth, labelHight, true);

			albumArtImage.setBounds(firstTextFieldColumnStart - 75, 400, 127,
					94);
			albumArtImage
					.setToolTipText("Current Album Art/Click to Search New Online");

			ImageIcon icon = new ImageIcon(_parent.getProperty("IMAGES_DIR")
					+ "/musicFile.jpg");
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(127, 94,
					java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);

			albumArtImage.setIcon(icon);
			albumArtImage.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					// WorkThreadHandler workHandler = new WorkThreadHandler();
					// workHandler.setTemplateTab(_parent.getMp3Tab());
					// Thread workerThread = new Thread(workHandler);
					// workerThread.start();

					// searchImagesOnline();

					Runnable runSearch = new Runnable() {

						@Override
						public void run() {
							searchImagesOnline();
						}
					};

					Thread onlineSearchThread = new Thread(runSearch,
							"OnlineSearchThread");

					onlineSearchThread.start();

				}
			});

			playMp3 = new JButton("play");
			playMp3.setBounds(firstColumnStart, 425, 100, 50);
			playMp3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					playToggleMusic();
				}
			});

			stopMp3 = new JButton("stop");
			stopMp3.setBounds(firstTextFieldColumnStart + 77, 425, 100, 50);
			stopMp3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stopMusic();
				}
			});
			centerPanel.add(playMp3);
			centerPanel.add(stopMp3);

			// mp3Player = new MP3Player();

			// mp3Player.setBounds(firstColumnStart, 500, 300, 100);

			// centerPanel.add(mp3Player);
			/*
			 * Second Column
			 */
			int secondColumnStart = 500;
			int secondTextFieldColumnStart = secondColumnStart + labelWidth
					+ 50;

			JLabel imageNameLabel = new JLabel("imageNameLabel");
			assignJLabelDetails(imageNameLabel, "Image Name: ",
					secondColumnStart, 50, labelWidth, labelHight);

			JLabel commentLabel = new JLabel("commentLabel");
			assignJLabelDetails(commentLabel, "Comment: ", secondColumnStart,
					100, labelWidth, labelHight);

			JLabel customerSearchLabel = new JLabel("customerSearchLabel");
			assignJLabelDetails(customerSearchLabel, "Custom Search: ",
					secondColumnStart, 150, labelWidth, labelHight);

			assignJTextFieldDetails(imageName, "imageName",
					secondTextFieldColumnStart, 50, labelWidth, labelHight);

			assignJTextFieldDetails(comment, "comment",
					secondTextFieldColumnStart, 100, labelWidth, labelHight);

			assignJTextFieldDetails(customSearchTerm, "customSearchTerm",
					secondTextFieldColumnStart, 150, labelWidth, labelHight);

			centerPanel.add(fileNameLabel);
			centerPanel.add(trackLabel);
			centerPanel.add(titleLabel);
			centerPanel.add(artistLabel);
			centerPanel.add(albumLabel);
			centerPanel.add(yearLabel);
			centerPanel.add(genreLabel);
			centerPanel.add(imageNameLabel);
			centerPanel.add(commentLabel);
			centerPanel.add(customerSearchLabel);

			centerPanel.add(fileName);
			centerPanel.add(track);
			centerPanel.add(title);
			centerPanel.add(artist);
			centerPanel.add(album);
			centerPanel.add(year);
			centerPanel.add(genre);
			centerPanel.add(imageName);
			centerPanel.add(comment);
			centerPanel.add(customSearchTerm);

			imageSearchPanel = new JPanel();
			imageSearchPanel.setToolTipText("Online Search results...");
			imageSearchPanel.setBounds(500, 200, 400, 300);
			imageSearchPanel
					.setBorder(BorderFactory.createLoweredBevelBorder());
			imageSearchPanel.setLayout(new GridLayout(0, 3, 10, 10));
			centerPanel.add(imageSearchPanel);

			albumArtButtonList = new ArrayList<JButton>();
			int rztCountInt = 8;

			JButton imgResult = null;
			for (int i = 0; i < rztCountInt; i++) {
				imgResult = new JButton();
				imageSearchPanel.add(imgResult);
				albumArtButtonList.add(imgResult);
			}

			progressBar.setBounds(500, 510, imageSearchPanel.getWidth(), 15);
			progressBar.setVisible(false);
			progressBar.setBackground(Color.BLACK);
			progressBar.setForeground(Color.GREEN);
			centerPanel.add(progressBar);

			// centerPanel.add(landMark);
			// centerPanel.add(city);
			// centerPanel.add(state);
			// centerPanel.add(zip);
			// centerPanel.add(occupation);
			// centerPanel.add(customerIndex);
			// centerPanel.add(customerBarCode);

			centerPanel.add(albumArtImage);
			title.addFocusListener(this);
			artist.addFocusListener(this);
			album.addFocusListener(this);

			title.addMouseListener(this);
			artist.addMouseListener(this);
			album.addMouseListener(this);

			_mainTab.add(centerPanel, BorderLayout.CENTER);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setBorder(BorderFactory.createLoweredBevelBorder());
			// buttonPanel.setMinimumSize(new Dimension(800, 100));
			buttonPanel.setBounds(0, 0, 800, 100);

			buttonPanel.setToolTipText("Panel");

			submitForm = new JButton("Insert/Update");
			submitForm.setBounds(50, 5, 100, 50);

			submitForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					submitForm();
				}
			});

			buttonPanel.add(submitForm);

			resetForm = new JButton("Reset");
			resetForm.setBounds(50, 5, 100, 50);

			resetForm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					resetForm();
				}
			});

			buttonPanel.add(resetForm);

			_mainTab.add(buttonPanel, BorderLayout.SOUTH);

		} catch (Exception e) {
			_log.error("buildForm", e);

		}

	}

	private JLabel getEmptyLabel() {
		JLabel emptyJLabel = new JLabel("Empty");
		emptyJLabel.setText(" : ");
		return emptyJLabel;
	}

	@Override
	public void focusGained(FocusEvent evt) {
		// if (evt.getSource() == userIDText) {
		// if (userIDText.getText().equals("UserID")) {
		// userIDText.setText("");
		// }
		// } else if (evt.getSource() == userNameText) {
		// if (userNameText.getText().equals("UserName")) {
		// userNameText.setText("");
		// }
		// } else if (evt.getSource() == userRoleText) {
		// if (userRoleText.getText().equals("UserRole")) {
		// userRoleText.setText("");
		// }
		// }
	}

	@Override
	public void focusLost(FocusEvent evt) {
		// System.out.println(((JButton) evt.getSource()).getName());
		if (evt.getSource() == title || evt.getSource() == artist
				|| evt.getSource() == album) {
			String newSearchString = "";
			if (!title.getText().equals("")) {
				newSearchString = newSearchString + title.getText();
			}
			if (!artist.getText().equals("")) {
				newSearchString = newSearchString + " " + artist.getText();
			}
			if (!album.getText().equals("")) {
				newSearchString = newSearchString + " " + album.getText();
			}
			customSearchTerm.setText(newSearchString);
		}
	}

	protected void playToggleMusic() {
		if (playMp3.getText().equals("play")) {
			mp3Player.play();
			playMp3.setText("pause");
		} else if (playMp3.getText().equals("pause")) {
			mp3Player.pause();
			playMp3.setText("play");
		}
	}

	protected void stopMusic() {
		if (!mp3Player.isStopped()) {
			mp3Player.stop();
			playMp3.setText("play");
		}
	}

	// -------------------------------------------------------------------
	protected void submitForm() {
		if (mp3FileObject != null) {
			Mp3File mp3File = null;
			try {
				mp3File = new Mp3File(mp3FileObject.getFilePath());
			} catch (UnsupportedTagException e) {
				SystemLogger.logError("Error Reading Mp3 for Save ... ", e);
			} catch (InvalidDataException e) {
				SystemLogger.logError("Error Reading Mp3 for Save ... ", e);
			} catch (IOException e) {
				SystemLogger.logError("Error Reading Mp3 for Save ... ", e);
			}

			if (mp3FileObject.getFileName().equals(fileName.getText())) {

				mp3FileObject.setTrack(track.getText());
				mp3FileObject.setArtist(artist.getText());
				mp3FileObject.setTitle(title.getText());
				mp3FileObject.setAlbum(album.getText());
				mp3FileObject.setYear(year.getText());
				// mp3FileObject.setGenre(genre.getText());
				mp3FileObject.setComment(comment.getText());
			}
			ID3v1 id3v1Tag = mp3File.getId3v1Tag();
      
      if (id3v1Tag==null){
        id3v1Tag = new ID3v1Tag();
      }
  
      id3v1Tag.setAlbum(mp3FileObject.getAlbum());
			id3v1Tag.setArtist(mp3FileObject.getArtist());
			id3v1Tag.setComment(mp3FileObject.getComment());
			id3v1Tag.setTitle(mp3FileObject.getTitle());
			id3v1Tag.setTrack(mp3FileObject.getTrack());
			id3v1Tag.setYear(mp3FileObject.getYear());
			mp3File.setId3v1Tag(id3v1Tag);

			ID3v2 id3v2Tag = mp3File.getId3v2Tag();

			if (id3v2Tag == null) {
				id3v2Tag = new ID3v22Tag();
			}

			Image albumArtImg = ImageHelper
					.iconToImage(albumArtImage.getIcon());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				ImageIO.write(ImageHelper.getRenderedImage(albumArtImg), "png",
						baos);
			} catch (IOException e1) {
				_parent.statusBarMsg("Error Saving Album Art for ID3v2Tags ... "
						+ e1);
				SystemLogger.logError(
						"Error Saving Album Art for ID3v2Tags ... ", e1);
			} catch (Exception e) {
				_parent.statusBarMsg("Error Saving Album Art for ID3v2Tags ... "
						+ e);
				SystemLogger.logError(
						"Error Saving Album Art for ID3v2Tags ... ", e);
			}
			byte[] updatedAlbumArt = baos.toByteArray();

			id3v2Tag.setAlbumImage(updatedAlbumArt, "png");
			id3v2Tag.setAlbum(mp3FileObject.getAlbum());
			id3v2Tag.setAlbumArtist(mp3FileObject.getArtist());
			id3v2Tag.setArtist(mp3FileObject.getArtist());
			id3v2Tag.setComment(mp3FileObject.getComment());
			id3v2Tag.setTitle(mp3FileObject.getTitle());
			id3v2Tag.setTrack(mp3FileObject.getTrack());
			id3v2Tag.setYear(mp3FileObject.getYear());

			mp3File.setId3v2Tag(id3v2Tag);
			try {
				mp3File.save(mp3FileObject.getFilePath() + ".tmp");
				File tmpFile = new File(mp3FileObject.getFilePath() + ".tmp");
				if (tmpFile.exists()) {
					File file = new File(mp3FileObject.getFilePath());
					file.delete();
					tmpFile.renameTo(new File(mp3FileObject.getFilePath()));
				}

			} catch (NotSupportedException e) {
				SystemLogger.logError("Error Saving Mp3 for ID3v1Tags ... ", e);
			} catch (IOException e) {
				SystemLogger.logError("Error Saving Mp3 for ID3v1Tags ... ", e);
			}

			_parent.statusBarMsg("Saved Metadata Successfully...");

			_parent.triggerTreeRefresh();
		}
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	protected void resetForm() {
	}

	private JPanel imageSearchPanel = null;

	public void searchImagesOnline() {
		if (customSearchTerm.getText() == null
				|| customSearchTerm.getText().equals("")) {
			return;
		}
		_parent.statusBarMsg("Searching Google for Image with ..."
				+ customSearchTerm.getText());
		String rztCount = _parent.getProperty("ALBUM_ART_SEARCH_RESULT");
		if (rztCount == null || rztCount.equals("")) {
			rztCount = "" + 8;
		}
		URL myURL;

		progressBar.setMaximum(9);
		progressBar.setValue(0);
		progressBar.setVisible(true);

		String[] imageUrl = null;
		String baseSearchUrl = _parent.getProperty("SEARCH_URL");
		try {

			String searchUrl = baseSearchUrl + rztCount + "&userip="
					+ InetAddress.getLocalHost().getHostAddress() + "&q="
					+ URLEncoder.encode(customSearchTerm.getText());
			SystemLogger.logMessage(searchUrl);
			System.out.println(searchUrl);
			myURL = new URL(searchUrl);

			// myURL = new URL("http://www.google.com");

			URLConnection connection = myURL.openConnection();
			connection.addRequestProperty("Referer", "https://home.example.in");

			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			imageUrl = SearchResultParser.getImageUrls(builder.toString());

		} catch (MalformedURLException e) {
			_parent.statusBarMsg("Online Search Failed..." + e);
			SystemLogger.logError("Online Search Failed...", e);
		} catch (IOException e) {
			_parent.statusBarMsg("Online Search Failed..." + e);
			SystemLogger.logError("Online Search Failed...", e);
		} catch (Exception e) {
			_parent.statusBarMsg("Online Search Failed..." + e);
			SystemLogger.logError("Online Search Failed...", e);
		}

		int rztCountInt = Integer.parseInt(rztCount);

		ImageIcon icon = getDefaultIcon();
		Image img = null;
		Image newimg = null;
		progressBar.setValue(1);
		progressBar.revalidate();
		progressBar.repaint();
		for (int i = 0; i < rztCountInt; i++) {
			JButton imgResult = albumArtButtonList.get(i);
			imgResult.setName("imgResult" + i);

			_parent.statusBarMsg("Fetching imgResult: " + i + " url: "
					+ imageUrl[i]);

			imageUrl[i] = imageUrl[i].replaceAll("-", "%2D");
			String searchButtonToolTip = imageUrl[i];
			BufferedImage bfimg = null;
			try {
				bfimg = ImageIO.read(new URL(imageUrl[i]));
			} catch (MalformedURLException e) {
				SystemLogger.logError("Failed to fetch image from net: ", e);
				searchButtonToolTip = "<html>" + e + "<br>" + imageUrl[i]
						+ "</html>";
			} catch (IOException e) {
				SystemLogger.logError("Failed to fetch image from net: ", e);
				searchButtonToolTip = "<html>" + e + "<br>" + imageUrl[i]
						+ "</html>";
			}
			if (bfimg == null) {
				SystemLogger.logMessage("Failure for..."
						+ imageUrl[i].replaceAll(":", ";"));
				icon = getDefaultIcon();
			} else {
				icon = new ImageIcon(bfimg);
			}

			img = icon.getImage();
			newimg = img.getScaledInstance(imgResult.getWidth(),
					imgResult.getHeight(), java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);

			imgResult.setIcon(icon);
			imgResult.setToolTipText(searchButtonToolTip);

			imgResult.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					imgResultAction((JButton) arg0.getSource());
				}
			});
			final int theProgress = i + 2;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					progressBar.setValue(theProgress);
					progressBar.revalidate();
					progressBar.repaint();
				}
			});
			imageSearchPanel.repaint(10);
			// sleepAMoment();
		}
		sleepAMoment();
		progressBar.setVisible(false);
		// imageSearchPanel.repaint();
	}

	private void resetSearchResultImages() {
		ImageIcon icon = getDefaultIcon();
		Image img = null;
		Image newimg = null;
		for (int i = 0; i < 8; i++) {
			JButton imgResult = albumArtButtonList.get(i);
			imgResult.setName("imgResult" + i);

			icon = getDefaultIcon();

			img = icon.getImage();
			newimg = img.getScaledInstance(imgResult.getWidth(),
					imgResult.getHeight(), java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);

			imgResult.setIcon(icon);
			imgResult.setToolTipText("imgResult" + i);

			imgResult.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					imgResultAction((JButton) arg0.getSource());
				}
			});

			imageSearchPanel.repaint(10);
			// sleepAMoment();
		}
	}

	private ImageIcon getDefaultIcon() {
		ImageIcon icon = new ImageIcon(_parent.getProperty("IMAGES_DIR")
				+ "/folderIcon.jpg");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(127, 94,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);
		return icon;
	}

	private void imgResultAction(JButton imgResultClicked) {
		_parent.statusBarMsg("Setting Album Art from Button: "
				+ imgResultClicked.getName());
		albumArtImage.setIcon(imgResultClicked.getIcon());
	}

	// -------------------------------------------------------------------
	private void populateList() {

	}

	private void assignJLabelDetails(JLabel newJLabel, String text,
			int startHorizontal, int startVertical, int width, int hight) {
		newJLabel.setText(" " + text);
		newJLabel.setToolTipText(text);
		newJLabel.setBounds(getBound(startHorizontal, startVertical, width,
				hight));
		newJLabel.setBorder(BorderFactory.createLoweredBevelBorder());
	}

	private void assignJTextFieldDetails(JTextField newJTextField, String text,
			int startHorizontal, int startVertical, int width, int hight) {
		assignJTextFieldDetails(newJTextField, text, startHorizontal,
				startVertical, width, hight, false);
	}

	private void sleepAMoment() {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void assignJTextFieldDetails(JTextField newJTextField, String text,
			int startHorizontal, int startVertical, int width, int hight,
			boolean disabled) {
		newJTextField.setName(text);
		if (disabled) {
			newJTextField.setEnabled(false);
		}
		newJTextField.setToolTipText(text);
		newJTextField.setBounds(getBound(startHorizontal, startVertical, width,
				hight));
		newJTextField.setBorder(BorderFactory.createLoweredBevelBorder());

		newJTextField.addFocusListener(this);

	}

	private Rectangle getBound(int startHorizontal, int startVertical,
			int width, int hight) {
		return new Rectangle(startHorizontal, startVertical, width, hight);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 3) {
			if (event.getSource() == title) {
				title.setText("");
			} else if (event.getSource() == artist) {
				artist.setText("");
			} else if (event.getSource() == album) {
				album.setText("");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

}
