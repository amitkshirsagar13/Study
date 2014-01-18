/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 17, 2014
 * @author Amit Kshirsagar
 * @email amit.kshirsagar.13@gmail.com
 * @version 1.0
 * @since
 *
 * <p><b>Modification History:</b><p>
 *
 *
 * </pre>
 */

package org.masterswings.base.view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.masterswings.base.actions.BaseButtonActions;
import org.masterswings.base.actions.BaseMasterSwingsContants;
import org.masterswings.splash.SplashScreen;
import org.masterswings.status.StatusPanelMasterSwings;

public abstract class BaseMasterSwingsFrame extends BaseButtonActions implements
		MouseMotionListener, MouseListener, BaseMasterSwingsContants {

	Logger _log = Logger.getLogger(BaseMasterSwingsFrame.class.getName());

	private void logMessage(String message, Throwable exception) {
		if (exception != null) {
			_log.error(message, exception);
		} else {
			_log.info(message);
		}
	}

	private void debug(String message) {
		_log.debug(message);
	}

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// Not important
		}
	}
	protected SplashScreen _splashScreen = null;

	public BaseMasterSwingsFrame(String applicationName) {
		super(applicationName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected static Properties _properties = null;

	/**
	 * @throws IOException
	 * 
	 */
	public void loadApplicationProperties() throws IOException {
		if (System.getProperty(APPLICATION_PROPERTIES) == null) {
			IOException exception = new IOException(APPLICATION_PROPERTIES
					+ " SystemProperty missing");
			logMessage(APPLICATION_PROPERTIES
					+ " needs to be passed to application during startup.",
					exception);
			throw exception;
		}
		debug("Loading the Properties file: "
				+ System.getProperty(APPLICATION_PROPERTIES));
		InputStream in = BaseMasterSwingsFrame.class
				.getClassLoader()
				.getResourceAsStream(System.getProperty(APPLICATION_PROPERTIES));
		_properties = new Properties();
		_properties.load(in);
		in.close();

		String iImgName = _properties.getProperty("APPSPLASHSCREEN");

		_splashScreen = new SplashScreen(this, new ImageIcon(iImgName));
		_splashScreen.setVisible(true);
		_splashScreen.setAlwaysOnTop(true);
		debug("Loaded the Properties file: "
				+ System.getProperty(APPLICATION_PROPERTIES));

		this.getContentPane().setLayout(new BorderLayout());

	}

	/**
	 * Default statusPanelLoader
	 * 
	 * @param statusPanel
	 */

	public void loadStatusPanel(StatusPanelMasterSwings statusPanel) {
		getContentPane().add(statusPanel, BorderLayout.SOUTH);
		setStatusPanel(statusPanel);
	}

	/**
	 * Start Loading the Componants on mainFrame...
	 */

	public void initialize() {
		loadStatusPanel(StatusPanelMasterSwings
				.getStatusPanelMasterSwings(new BorderLayout()));

		for (int index = 0; index < 11; index++) {
			_splashScreen.setProgress("Initilizing GUI..." + index, index * 10);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		_splashScreen.closeIt();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			debug("Mouse Clicked");
		} else {
			logMessage("Mouse Clicked " + e.getClickCount(), null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void changeLookdFeel(String lookAndFeel) {
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			// Not important
		}
	}

	StatusPanelMasterSwings _statusPanel = null;

	public void setStatusPanel(StatusPanelMasterSwings statusPanel) {
		this._statusPanel = statusPanel;
	}

	public void setStatusBarMessage(String statusMessage) {
		_statusPanel.setStatusBarMessage(statusMessage);
	}
}
