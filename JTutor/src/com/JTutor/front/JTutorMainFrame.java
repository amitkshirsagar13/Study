package com.JTutor.front;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.JTutor.store.JTutorConst;

public class JTutorMainFrame extends JFrame implements JTutorConst {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// Not important
		}
	}

	private static Logger _log = null;
	private static Properties _properties = null;

	public JTutorMainFrame() {
		super();
		if (_log == null) {
			_log = Logger.getLogger(JTutorMainFrame.class);
		}
	}

	public void startJTutor() {
		this.initialize();
		this.createComponents();
	}

	SplashHelper _splashScreen = null;

	private void initialize() {
		try {
			String iImgName = System.getProperty("JTUTOR_SPLASH_SCREEN");
			// display a message that the system is loading
			_splashScreen = new SplashHelper(this, iImgName, 1, Color.black);
		} catch (Exception e) {
			logError("Exception:" + e.getMessage());
		}

		readProperties();
	}

	private void readProperties() {
		_properties = new Properties();
		try {
			_properties.load(new FileInputStream(new File(System
					.getProperty("JTUTOR_CONFIGURATION_FILE"))));
		} catch (FileNotFoundException e) {
			logError("Error reading File.." + e.getMessage());
		} catch (IOException e) {
			logError("Error reading File..." + e.getMessage());
		}
		logMessage("Read Some Properties...");
	}

	private void logMessage(String message) {
		_log.info(message);
	}

	private void logError(String message) {
		_log.info(message);
	}

	/**
	 * Main Panel for Frame...
	 */

	private JPanel _maintPanel = null;
	private final JTabbedPane _tabPanel = new JTabbedPane();

	private final JPanel _statusPanel = new JPanel();
	private static final JLabel _statusBar = new JLabel();

	private final JPanel _infoPanel = new JPanel();
	private final JLabel _r3Label = new JLabel();
	private final JLabel _readWriteLabel = new JLabel();
	private final JLabel _userIDLabel = new JLabel();
	private final JLabel _privilegedUserLabel = new JLabel();

	private void createComponents() {
		String app_icon = System.getProperty("APP_ICON");
		setIconImage(Toolkit.getDefaultToolkit().createImage(app_icon));
		setTitle("JTutor Application");
		this.setSize(new Dimension(800, 589));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Create main ContentPane for holding the Objects in Frame.
		 */
		logMessage("Components:_maintPanel");
		_maintPanel = (JPanel) this.getContentPane();

		_maintPanel.setLayout(new BorderLayout());
		_maintPanel.setMinimumSize(new Dimension(750, 650));
		_maintPanel.setPreferredSize(new Dimension(750, 750));
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

		populateStatusBarInfoPanel();

		/*
		 * Populate JMenuBar
		 */
		populateJMenuBar();

		/*
		 * Populate Status bar and info panel at bottom.
		 */

		populateToolBar();

		// slowDownASecond();
		/*
		 * Add Tabbed Panel to hold secondary Objects.
		 */

		_maintPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		_maintPanel.add(_tabPanel, BorderLayout.CENTER);

		populateWorkTabs();

		statusBarMsg("Added Tabbed Panel...");

	}

	/**
	 * Populate Status Bar and Info Panel
	 */

	private void populateStatusBarInfoPanel() {
		/**
		 * Status Bar and Info Panels in Bottom
		 */

		_statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		_r3Label.setBorder(BorderFactory.createLoweredBevelBorder());
		_readWriteLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_userIDLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_privilegedUserLabel
				.setBorder(BorderFactory.createLoweredBevelBorder());

		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.X_AXIS));
		_infoPanel.add(_r3Label, null);
		_infoPanel.add(_readWriteLabel, null);
		_infoPanel.add(_userIDLabel, null);
		_infoPanel.add(_privilegedUserLabel, null);

		_statusPanel.setLayout(new BorderLayout());
		_statusPanel.add(_statusBar, BorderLayout.CENTER);
		_statusPanel.add(_infoPanel, BorderLayout.EAST);

		_r3Label.setText("   ");
		_readWriteLabel.setText(" RW ");
		_userIDLabel.setText(" kshirsac ");
		_userIDLabel.setToolTipText("UserID");
		_privilegedUserLabel.setText(" N ");

		_r3Label.setToolTipText("Not used yet...");
		_readWriteLabel.setToolTipText("Access For Read Write");
		_privilegedUserLabel.setToolTipText("Privileged User");
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

		JToolBar toolbar = new JToolBar();
		toolbar.setMargin(new Insets(25, 25, 25, 25));
		toolbar.setBorder(null);
		toolbar.setBorderPainted(false);

		JButton userActionButton = new JButton("UserAction");
		userActionButton.setToolTipText("UserAction");
		userActionButton.setMinimumSize(new Dimension(27, 27));
		userActionButton.setMaximumSize(new Dimension(27, 27));
		userActionButton.setPreferredSize(new Dimension(27, 27));
		userActionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonUserAction(e);
			}
		});
		toolbar.add(userActionButton, null);

		_maintPanel.add(toolbar, BorderLayout.NORTH);

		JButton helpButton = new JButton(new ImageIcon(
				_properties.getProperty("IMAGES_DIR") + "/help.gif"));
		helpButton.setToolTipText("Help");
		helpButton.setMinimumSize(new Dimension(27, 27));
		helpButton.setMaximumSize(new Dimension(27, 27));
		helpButton.setPreferredSize(new Dimension(27, 27));
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonHelp(e);
			}
		});
		toolbar.add(helpButton, null);
	}

	/**
	 * Populate JMenus
	 */

	private void populateJMenuBar() {
		// Drop-down menu
		JMenuBar jMenuBar = new JMenuBar();
		JMenu jMenuFile = new JMenu();
		jMenuFile.setText("File");
		jMenuFile.setMnemonic('F');
		JMenuItem menuFileSendLog = new JMenuItem();
		menuFileSendLog.setText("Send Log File");
		menuFileSendLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendLogFile(e);
			}
		});

		jMenuFile.add(menuFileSendLog);

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

	private void populateWorkTabs() {
		AddUserTab addUserTab = new AddUserTab(this);
		addUserTab.buildUserEntryForm();
		_tabPanel.add(addUserTab.getAddUserTab(), "Add User");

		UserEntryTab userEntryTab = new UserEntryTab(this);
		userEntryTab.buildUserEntryForm();
		_tabPanel.add(userEntryTab.getUserEntryTab(), "User Entry");

		ReportTab reportTab = new ReportTab(this);
		reportTab.buildUserEntryForm();
		_tabPanel.add(reportTab.getReportTab(), "User Report");
	}

	// -------------------------------------------------------------------
	/**
	 * Help | About action performed
	 */
	public void buttonHelp(ActionEvent e) {
		statusBarMsg("Clicked Help...");
	}

	// -------------------------------------------------------------------
	// -------------------------------------------------------------------
	/**
	 * Help | About action performed
	 */
	public void buttonUserAction(ActionEvent e) {
		statusBarMsg("Clicked UserAction...");
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
