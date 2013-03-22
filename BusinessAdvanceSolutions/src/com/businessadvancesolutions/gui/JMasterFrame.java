package com.businessadvancesolutions.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.businessadvancesolutions.businessmodel.BusinessCustomer;
import com.businessadvancesolutions.businessmodel.SystemUser;
import com.businessadvancesolutions.dbapi.dao.BusinessCustomerDAO;
import com.businessadvancesolutions.dbapi.dao.BusinessUserDAO;
import com.businessadvancesolutions.helper.SystemLogger;

public class JMasterFrame extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// Not important
		}
	}

	private static Properties _properties = null;

	public JMasterFrame() {
		super();
	}

	public void startJMasterFrame() {
		this.initialize();
		this.createComponents();
		slowDownASecond();
		_splashScreen.dispose();

	}

	SplashScreen _splashScreen = null;

	private void initialize() {
		try {
			String iImgName = System.getProperty("JMASTER_SPLASH_SCREEN");
			// display a message that the system is loading
			// _splashScreen = new SplashHelper(new JFrame(), iImgName, 1,
			// Color.black);
			// _splashScreen.setAlwaysOnTop(true);
			_splashScreen = new SplashScreen(this, new ImageIcon(iImgName));
			_splashScreen.setVisible(true);
			_splashScreen.setAlwaysOnTop(true);

			SystemLogger.createSystemLogger();
			SystemLogger.setPrintStream(System.out);

			BusinessUserDAO.getBusinessUserDao();
			BusinessCustomerDAO.getbusinessCustomerDao();

			// BusinessUserDAO.testHibernate();
		} catch (Exception e) {
			SystemLogger.logError("Exception:" + e.getMessage(), e);
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

	private final JPanel _infoPanel = new JPanel();
	private final JLabel _customerIndex = new JLabel();
	private final JLabel _readWriteLabel = new JLabel();
	private final JLabel _userIDLabel = new JLabel();
	private final JLabel _privilegedUserLabel = new JLabel();

	private void createComponents() {
		String app_icon = System.getProperty("APP_ICON");
		setIconImage(Toolkit.getDefaultToolkit().createImage(app_icon));
		setTitle("BusinessAdvanceSoftwares Application");
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
		 * Add Tabbed Panel to hold secondary Objects.
		 */

		_maintPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		_maintPanel.add(_tabPanel, BorderLayout.CENTER);

		/*
		 * check if systemUser is not set, it means we need login
		 */
		if (getSystemUser() == null) {
			_splashScreen.setProgress("Initilizing Login Panel...", 80);
			triggerLoginTab();
		} else {
			populateWorkTabs();
		}
		_splashScreen.setProgress("Initilized Application Completed...", 100);
		slowDownASecond();
		_splashScreen.closeIt();
		statusBarMsg("Added Tabbed Panel...");

	}

	/**
	 * Populate Status Bar and Info Panel
	 */

	private void populateStatusBarInfoPanel() {
		/**
		 * Status Bar and Info Panels in Bottom
		 */

		_customerIndex.setName("Customer Index");
		_readWriteLabel.setName("Access Level");
		_userIDLabel.setName("User Name");
		_privilegedUserLabel.setName("Privilaged");
		_statusBar.setName("Status Bar");

		_statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
		_customerIndex.setBorder(BorderFactory.createLoweredBevelBorder());
		_readWriteLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_userIDLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		_privilegedUserLabel
				.setBorder(BorderFactory.createLoweredBevelBorder());

		_infoPanel.setLayout(new BoxLayout(_infoPanel, BoxLayout.X_AXIS));
		_infoPanel.add(_customerIndex, null);
		_infoPanel.add(_readWriteLabel, null);
		_infoPanel.add(_userIDLabel, null);
		_infoPanel.add(_privilegedUserLabel, null);

		_statusPanel.setLayout(new BorderLayout());
		_statusPanel.add(_statusBar, BorderLayout.CENTER);
		_statusPanel.add(_infoPanel, BorderLayout.EAST);

		_customerIndex.setText("CustomerIndex");
		_readWriteLabel.setText(" R ");
		_userIDLabel.setText("        ");
		_privilegedUserLabel.setText(" N ");

		_statusBar.setOpaque(true);
		_customerIndex.setOpaque(true);
		_readWriteLabel.setOpaque(true);
		_userIDLabel.setOpaque(true);
		_privilegedUserLabel.setOpaque(true);

		_customerIndex.setToolTipText("Business Safty with Customer..");
		_readWriteLabel.setToolTipText("Access For Read Write");
		_userIDLabel.setToolTipText("UserID");
		_privilegedUserLabel.setToolTipText("Privileged User");

		_customerIndex.addMouseListener(this);
		_readWriteLabel.addMouseListener(this);
		_userIDLabel.addMouseListener(this);
		_privilegedUserLabel.addMouseListener(this);
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

		JButton userActionButton = new JButton("UserAction");
		userActionButton.setToolTipText("UserAction");
		userActionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonUserAction(e);
			}
		});
		toolbar.add(userActionButton, null);

		_maintPanel.add(toolbar, BorderLayout.NORTH);

		// JButton helpButton = new JButton(new ImageIcon(
		// _properties.getProperty("IMAGES_DIR") + "/help.gif"));
		JButton helpButton = new JButton("Help");
		helpButton.setToolTipText("Help");
		helpButton.addActionListener(new ActionListener() {
			@Override
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

	public void populateWorkTabs() {
		statusBarMsg("Loading Working Tab....");
		slowDownASecond();
		_tabPanel.removeTabAt(0);

		AddCustomerTab addcustomerTab = new AddCustomerTab(this);
		addcustomerTab.buildForm();
		_tabPanel.add(addcustomerTab.getTab(), "Add Customer");

		GenerateInvoiceTab addGenerateInvoiceTab = new GenerateInvoiceTab(this);
		addGenerateInvoiceTab.buildForm();
		_tabPanel.add(addGenerateInvoiceTab.getTab(), "Generate Invoice");

		AddUserTab addUserTab = new AddUserTab(this);
		addUserTab.buildForm();
		_tabPanel.add(addUserTab.getTab(), "Add User");

		ReportTab reportTab = new ReportTab(this);
		reportTab.buildForm();
		_tabPanel.add(reportTab.getTab(), "Sells Report");

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
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public SystemUser _systemUser = null;

	public void setSystemUser(SystemUser systemUser) {
		_systemUser = systemUser;
		_userIDLabel.setText(_systemUser.getSystemUserName());
		_userIDLabel.setToolTipText("UserName:"
				+ _systemUser.getSystemUserName());
		if (_systemUser.getSystemUserRole() == 1) {
			_userIDLabel.setBackground(Color.GREEN);
			_userIDLabel.setForeground(Color.RED);
			_readWriteLabel.setText(" RW ");
			_readWriteLabel.setToolTipText("Role:Admin");
			_privilegedUserLabel.setText(" Y ");
		} else {
			_userIDLabel.setBackground(Color.CYAN);
			_userIDLabel.setForeground(Color.GREEN);
			_readWriteLabel.setText(" R ");
			_readWriteLabel.setToolTipText("Role:User");
			_privilegedUserLabel.setText(" N ");
		}
	}

	public void setCustomerDetail(BusinessCustomer customerDetail) {
		if (Integer.parseInt(customerDetail.getCustomerIndex()) < 4) {
			_customerIndex.setText("Good Buyer");
			_customerIndex.setToolTipText("Good Buyer");
			_customerIndex.setBackground(Color.BLACK);
			_customerIndex.setForeground(Color.GREEN);
		} else if (Integer.parseInt(customerDetail.getCustomerIndex()) < 7) {
			_customerIndex.setText("Nuetral Buyer");
			_customerIndex.setToolTipText("Nuetral Buyer");
			_customerIndex.setBackground(Color.CYAN);
			_customerIndex.setForeground(Color.YELLOW);
		} else {
			_customerIndex.setText("Bad Buyer");
			_customerIndex.setToolTipText("Bad Buyer");
			_customerIndex.setBackground(Color.RED);
			_customerIndex.setForeground(Color.GREEN);
			JOptionPane.showMessageDialog(this, "Customer may be at risk..",
					"CustomerIndex", JOptionPane.ERROR_MESSAGE);
		}
	}

	public SystemUser getSystemUser() {
		return _systemUser;
	}

	public void pullThePlug() {
		// this will make sure WindowListener.windowClosing() et al. will be
		// called.
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);

		// this will hide and dispose the frame, so that the application quits
		// by
		// itself if there is nothing else around.
		setVisible(false);
		dispose();
		// if you have other similar frames around, you should dispose them,
		// too.

		// finally, call this to really exit.
		// i/o libraries such as WiiRemoteJ need this.
		// also, this is what swing does for JFrame.EXIT_ON_CLOSE
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
}
