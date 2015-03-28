package com.mp3editor.gui.startup;

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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.mp3editor.gui.model.FileSystemModel;
import com.mp3editor.gui.tabs.LoginTab;
import com.mp3editor.gui.tabs.Mp3Tab;
import com.mp3editor.logger.SystemLogger;
import com.mp3editor.util.Mp3FileFilter;

public class JMasterFrame extends JFrame implements MouseListener,
		TreeSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		try {
			// mp3FileObject.set(UIManager.getSystemLookAndFeelClassName());
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {

				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
				// else if ("Nimbus".equals(info.getName())) {
				// UIManager.setLookAndFeel(info.getClassName());
				// break;
				// } else if ("Windows Classic".equals(info.getName())) {
				// UIManager.setLookAndFeel(info.getClassName());
				// break;
				// } else if ("Metal".equals(info.getName())) {
				// UIManager.setLookAndFeel(info.getClassName());
				// break;
				// } else if ("CDE/Motif".equals(info.getName())) {
				// UIManager.setLookAndFeel(info.getClassName());
				// break;
				// } else {
				//
				// }
			}
		} catch (Exception e) {
			// Not important
		}
	}

	private static Properties _properties = null;

	public String getProperty(String key) {
		return _properties.getProperty(key);
	}

	public JMasterFrame() {
		super();
	}

	public void startJTutor() {
		this.initialize();
		this.createComponents();
		slowDownASecond();
		_splashScreen.dispose();

	}

	SplashScreen _splashScreen = null;

	private void initialize() {
		try {
			String iImgName = System.getProperty("JMASTER_SPLASH_SCREEN");

			_splashScreen = new SplashScreen(this, new ImageIcon(iImgName));
			_splashScreen.setVisible(true);
			_splashScreen.setAlwaysOnTop(true);
			readProperties();
			SystemLogger.createSystemLogger();
			SystemLogger.setPrintStream(System.out);

		} catch (Exception e) {
			// logError("Exception:" + e.getMessage());
		}

		// readProperties();
	}

	private void readProperties() {
		_properties = new Properties();
		try {
			_properties.load(new FileInputStream(new File(System
					.getProperty("JMASTER_CONFIGURATION_FILE"))));
		} catch (FileNotFoundException e) {
			// logError("Error reading File.." + e.getMessage());
		} catch (IOException e) {
			// logError("Error reading File..." + e.getMessage());
		}
		// logMessage("Read Some Properties...");
	}

	// private void logMessage(String message) {
	// _log.info(message);
	// }
	//
	// private void logError(String message) {
	// _log.info(message);
	// }

	/**
	 * Main Panel for Frame...
	 */

	private JPanel _maintPanel = null;
	private final JTabbedPane _tabPanel = new JTabbedPane();

	private final JPanel _statusPanel = new JPanel();
	private static final JLabel _statusBar = new JLabel();

	private final JPanel _directoryPanel = new JPanel();
	private final JLabel _fileCountLabel = new JLabel();
	private final JLabel _directoryLabel = new JLabel();
	private final JLabel _userIDLabel = new JLabel();
	private final JLabel _mp3SelectedLabel = new JLabel();
	private FileSystemModel fileSystemModel = null;
	private JTree fileTree = null;
	private JSplitPane splitPane = null;

	private void createComponents() {
		String app_icon = System.getProperty("APP_ICON");
		setIconImage(Toolkit.getDefaultToolkit().createImage(app_icon));
		setTitle("Mp3 Metadata Editor");
		this.setSize(new Dimension(1000, 750));

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_splashScreen.setProgress("Initilizing GUI...", 10);
		/*
		 * Create main ContentPane for holding the Objects in Frame.
		 */
		// logMessage("Components:_maintPanel");
		_maintPanel = (JPanel) this.getContentPane();

		_maintPanel.setLayout(new BorderLayout());
		// _maintPanel.setMinimumSize(new Dimension(750, 650));
		// _maintPanel.setPreferredSize(new Dimension(1080, 900));
		_maintPanel.add(_statusPanel, BorderLayout.SOUTH);

		// Center the window and display it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		setVisible(true);
		/*
		 * Populate Status bar and info panel at bottom.
		 */
		_splashScreen.setProgress("Initilizing StatusBar...", 20);
		populateStatusBarInfoPanel();

		/*
		 * Populate JMenuBar
		 */

		_splashScreen.setProgress("Initilizing MenuBars...", 40);
		populateJMenuBar();

		/*
		 * Populate Status bar and info panel at bottom.
		 */
		_splashScreen.setProgress("Initilizing ToolBars...", 60);
		populateToolBar();

		/*
		 * Add Tabbed Panel/TreePanel to hold secondary Objects.
		 */

		populateFileSystemTree();

		fileTree = new JTree(fileSystemModel);

		fileTree.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (e.getButton() == e.BUTTON1) {
					// System.out.println("Clicked BUTTON1");
				} else if (e.getButton() == e.BUTTON2) {
					// System.out.println("Clicked BUTTON2");
				} else if (e.getButton() == e.BUTTON3) {

					handleRightMouseButtonClick(e);
					// System.out.println("Show Popup Option here");
					final File chosenFile = (File) fileTree
							.getLastSelectedPathComponent();
					final JPopupMenu popup = new JPopupMenu();
					JMenuItem menuItem1 = new JMenuItem(chosenFile.getName());
					popup.add(menuItem1);

					menuItem1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(
									fileTree.getParent(),
									"File Path: "
											+ chosenFile.getAbsolutePath(),
									"Selected File Path",
									JOptionPane.PLAIN_MESSAGE);
						}
					});

					popup.show(fileTree, e.getX(), e.getY());

				}

			}

			private void handleRightMouseButtonClick(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					// System.out.println("Right Mouse button clicked.");
					Point p = e.getPoint();
					if (moreThanOneTreeNodesSelected()) {
						if (!fileTree.getSelectionModel().isPathSelected(
								fileTree.getPathForLocation(p.x, p.y))) {
							selectPointedPathOnTree(p);
						}
					} else {
						selectPointedPathOnTree(p);
					}
				}
			}

			private void selectPointedPathOnTree(Point p) {
				fileTree.getSelectionModel().setSelectionPath(
						fileTree.getPathForLocation(p.x, p.y));
			}

			private boolean moreThanOneTreeNodesSelected() {
				if (fileTree.getSelectionPaths() == null) {
					return false;
				}
				if (fileTree.getSelectionPaths().length > 1) {
					return true;
				}
				return false;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		fileTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent event) {
				File chosenFile = (File) fileTree
						.getLastSelectedPathComponent();
				mp3Tab.setForm(chosenFile);
			}
		});

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,
				new JScrollPane(fileTree), new JScrollPane(_tabPanel));

		_maintPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

		// _maintPanel.add(_tabPanel, BorderLayout.CENTER);

		_maintPanel.add(splitPane, BorderLayout.CENTER);

		/*
		 * check if systemUser is not set, it means we need login
		 */
		// if (getSystemUser() == null) {
		// _splashScreen.setProgress("Initilizing Login Panel...", 80);
		// triggerLoginTab();
		// } else {
		populateWorkTabs();
		// }
		_splashScreen.setProgress("Initilized Application Completed...", 100);
		slowDownASecond();
		_splashScreen.closeIt();
		statusBarMsg("Added Tabbed Panel...");

	}

	public String systemUser = null;

	private String getSystemUser() {
		return systemUser;
	}

	private void populateFileSystemTree() {
		File directory = null;
		if (_properties.getProperty("LAST_ACCESS_DIRECTORY") != null
				&& !_properties.getProperty("LAST_ACCESS_DIRECTORY").equals("")) {

			if (new File(_properties.getProperty("LAST_ACCESS_DIRECTORY"))
					.exists()) {
				directory = new File(
						_properties.getProperty("LAST_ACCESS_DIRECTORY"));

			} else {
				directory = new File("c:/");
			}
		} else {
			directory = new File("c:/");
		}

		fileSystemModel = new FileSystemModel(directory);

	}

	/**
	 * Populate Status Bar and Info Panel
	 */

	private void populateStatusBarInfoPanel() {
		/**
		 * Status Bar and Info Panels in Bottom
		 */

		_fileCountLabel.setName("File Count");
		_directoryLabel.setName("Directory path");
		_userIDLabel.setName("User Name");
		_mp3SelectedLabel.setName("mp3Selected");
		_statusBar.setName("Status Bar");

		_statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		_fileCountLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_directoryLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_userIDLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_mp3SelectedLabel.setBorder(BorderFactory.createLoweredBevelBorder());

		_directoryPanel.setLayout(new BoxLayout(_directoryPanel,
				BoxLayout.X_AXIS));

		_directoryPanel.add(_directoryLabel, null);
		_directoryPanel.add(_fileCountLabel, null);
		_directoryPanel.add(_userIDLabel, null);
		_directoryPanel.add(_mp3SelectedLabel, null);

		_statusPanel.setLayout(new BorderLayout());
		_statusPanel.add(_statusBar, BorderLayout.CENTER);
		_statusPanel.add(_directoryPanel, BorderLayout.EAST);

		_fileCountLabel.setText(" ");
		_directoryLabel.setText(" ");
		_userIDLabel.setText("        ");
		_mp3SelectedLabel.setText(" N ");

		_statusBar.setOpaque(true);
		_fileCountLabel.setOpaque(true);
		_directoryLabel.setOpaque(true);
		_userIDLabel.setOpaque(true);
		_mp3SelectedLabel.setOpaque(true);

		_fileCountLabel.setToolTipText("File Count in Directory");
		_directoryLabel.setToolTipText("Directory Path Selected");
		_userIDLabel.setToolTipText("UserID");
		_mp3SelectedLabel.setToolTipText("Selectd Mp3");

		_fileCountLabel.addMouseListener(this);
		_directoryLabel.addMouseListener(this);
		_userIDLabel.addMouseListener(this);
		_mp3SelectedLabel.addMouseListener(this);
		_statusBar.addMouseListener(this);

		_statusBar.setToolTipText("Status Bar Message...");

		statusBarMsg("Status bar working...");
		/**
		 * Status Bar and info panel Populated.
		 */
	}

	/**
	 * Populate ToolBar for the MainFrame
	 */

	private void populateToolBar() {
		/*
		 * Add Toolbar at top to hold some buttons.
		 */
		statusBarMsg("Loading ToolBar....");
		slowDownASecond();
		JToolBar toolbar = new JToolBar();
		toolbar.setMargin(new Insets(25, 25, 25, 25));
		toolbar.setBorder(null);
		toolbar.setBorderPainted(false);
		ImageIcon icon = new ImageIcon(_properties.getProperty("IMAGES_DIR")
				+ "/folderIcon.jpg");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(25, 25,
				java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		JButton directoryButton = new JButton(icon);

		// JButton userActionButton = new JButton("Select Directory");
		directoryButton.setToolTipText("Select Folder");
		directoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonDirectoryAction(e);
			}
		});
		toolbar.add(directoryButton, null);

		_maintPanel.add(toolbar, BorderLayout.NORTH);

		icon = new ImageIcon(_properties.getProperty("IMAGES_DIR")
				+ "/musicFile.jpg");
		img = icon.getImage();
		newimg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newimg);

		JButton mp3select = new JButton(icon);
		// JButton mp3select = new JButton("Help");
		mp3select.setToolTipText("Select Music File");
		mp3select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonMusicSelection(e);
			}
		});
		toolbar.add(mp3select, null);
	}

	/**
	 * Populate JMenus
	 */

	private void populateJMenuBar() {
		// Drop-down menu

		statusBarMsg("Loading MenuBar....");
		slowDownASecond();
		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenuFile = new JMenu();
		jMenuFile.setText("File");
		jMenuFile.setMnemonic('F');

		JMenuItem menuFileSendLog = new JMenuItem();
		menuFileSendLog.setText("Send Log File");
		menuFileSendLog.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendLogFile(e);
			}
		});

		jMenuFile.add(menuFileSendLog);

		JMenuItem menuExit = new JMenuItem();
		menuExit.setText("Exit");
		menuExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pullThePlug();
			}
		});

		jMenuFile.add(menuExit);

		/*
		 * Finally add jMenuFile to jMenuBar
		 */
		jMenuBar.add(jMenuFile);

		setJMenuBar(jMenuBar);
	}

	/**
	 * Sets the status bar message
	 * 
	 * @param msg
	 *            String holding the message
	 */

	public static void statusBarMsg(String msg) {
		try {
			if (msg == null || msg.equals("")) {
				msg = "    ";
			}
			// Optimized drawing of the status bar. Don't use
			// update(), which erases the entire background of the control.
			// Just trace over the old text with the background color,
			// effectively erasing it.

			String oldText;
			oldText = _statusBar.getText();
			// If it was previously blank, no need to get tricky.
			if (!oldText.trim().equals("")) {
				Color originalForeground, background;

				// Get current fore- and background colors.
				// E.g. fore = black text, back = white component
				background = _statusBar.getBackground();
				originalForeground = _statusBar.getForeground();

				// Change foreground = background. Text written will
				// be invisible, which we want.
				// E.g. text color = white
				_statusBar.setForeground(background);
				// Rewrite what IS there right now, but with the background
				// color (e.g. white). Think white-out.
				_statusBar.setText(oldText);
				// Push it out to screen.
				_statusBar.paint(_statusBar.getGraphics());
				// Put color back to something visible (the original color, e.g.
				// black)
				_statusBar.setForeground(originalForeground);
			}
			// Write what we want.
			_statusBar.setText(" " + msg);
			// push it out
			_statusBar.paint(_statusBar.getGraphics());

		} catch (Exception e) {
		}
	}

	public void triggerLoginTab() {
		statusBarMsg("Loading Login Tab....");
		slowDownASecond();
		LoginTab loginTab = new LoginTab(this);
		loginTab.buildForm();
		_tabPanel.add(loginTab.getTab(), "Login");
	}

	private Mp3Tab mp3Tab = null;

	public void populateWorkTabs() {
		statusBarMsg("Loading Working Tab....");
		slowDownASecond();
		// _tabPanel.removeTabAt(0);

		mp3Tab = new Mp3Tab(this);
		mp3Tab.buildForm();
		_tabPanel.add(mp3Tab.getTab(), "Mp3MetadataEditor");

	}

	public Mp3Tab getMp3Tab() {
		return mp3Tab;
	}

	// -------------------------------------------------------------------
	/**
	 * Help | About action performed
	 */
	public void buttonMusicSelection(ActionEvent e) {

		statusBarMsg("Clicked Help...");
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		if (_properties.getProperty("LAST_ACCESS_DIRECTORY") != null
				&& !_properties.getProperty("LAST_ACCESS_DIRECTORY").equals("")) {

			if (new File(_properties.getProperty("LAST_ACCESS_DIRECTORY"))
					.exists()) {
				chooser.setCurrentDirectory(new File(_properties
						.getProperty("LAST_ACCESS_DIRECTORY")));

				_directoryLabel.setText(" "
						+ _properties.getProperty("LAST_ACCESS_DIRECTORY")
						+ " ");
				File chosenFile = new File(
						_properties.getProperty("LAST_ACCESS_DIRECTORY"));
				File[] fileList = chosenFile.listFiles(new Mp3FileFilter());
				_fileCountLabel.setText(" " + fileList.length + " ");

			} else {
				chooser.setCurrentDirectory(new File("c:/"));
			}
		} else {
			chooser.setCurrentDirectory(new File("c:/"));
		}

		int choice = chooser.showOpenDialog(this);

		if (choice != JFileChooser.APPROVE_OPTION)
			return;

		File chosenFile = chooser.getSelectedFile();

		statusBarMsg("Selected Mp3: " + chosenFile.getName());

		_mp3SelectedLabel.setText(" " + chosenFile.getName() + " ");

		mp3Tab.setForm(chosenFile);
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	/**
	 * Help | About action performed
	 */
	public void buttonDirectoryAction(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (_properties.getProperty("LAST_ACCESS_DIRECTORY") != null
				&& !_properties.getProperty("LAST_ACCESS_DIRECTORY").equals("")) {

			if (new File(_properties.getProperty("LAST_ACCESS_DIRECTORY"))
					.exists()) {
				chooser.setCurrentDirectory(new File(_properties
						.getProperty("LAST_ACCESS_DIRECTORY")));
			} else {
				chooser.setCurrentDirectory(new File("c:/"));
			}
		} else {
			chooser.setCurrentDirectory(new File("c:/"));
		}

		int choice = chooser.showOpenDialog(this);

		if (choice != JFileChooser.APPROVE_OPTION)
			return;

		File chosenFile = chooser.getSelectedFile();

		statusBarMsg("Selected Folder: " + chosenFile.getName());

		_directoryLabel.setText(" " + chosenFile.getAbsolutePath() + " ");

		File[] fileList = chosenFile.listFiles(new Mp3FileFilter());
		_fileCountLabel.setText(" " + fileList.length + " ");

		fileSystemModel = new FileSystemModel(chosenFile);
		fileTree.setModel(fileSystemModel);
		fileTree.repaint();

		_properties.setProperty("LAST_ACCESS_DIRECTORY",
				chosenFile.getAbsolutePath());
		try {
			_properties.save(
					new FileOutputStream(new File(System
							.getProperty("JMASTER_CONFIGURATION_FILE"))), "");
		} catch (FileNotFoundException e1) {
			SystemLogger.logError("Properties File Save Failed.. ", e1);
		}

	}

	public void triggerTreeRefresh() {
		File chosenFile = new File(
				_properties.getProperty("LAST_ACCESS_DIRECTORY"));

		fileSystemModel = new FileSystemModel(chosenFile);
		fileTree.setModel(fileSystemModel);
		fileTree.repaint();
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	/**
	 * File | Send Log File action performed
	 */
	public void sendLogFile(ActionEvent e) {
		statusBarMsg("Send Log Files......");
	}

	// -------------------------------------------------------------------

	private void slowDownASecond() {

		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void pullThePlug() {
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		setVisible(false);
		dispose();
		System.exit(0);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			JOptionPane.showMessageDialog(this,
					((JLabel) event.getSource()).getName() + " : "
							+ ((JLabel) event.getSource()).getText(),
					((JLabel) event.getSource()).getName(),
					JOptionPane.INFORMATION_MESSAGE);
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

	@Override
	public void valueChanged(TreeSelectionEvent e) {

	}
}
