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
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SplashScreen extends JWindow {
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel imageLabel = new JLabel();
	JPanel southPanel = new JPanel();
	private static JProgressBar progressBar = new JProgressBar();
	private static JLabel progressBarMsg = new JLabel();
	ImageIcon imageIcon;
	private final Color iBorderColor = Color.BLACK;
	private final int iImgWidth;
	private int iBorderSize;
	private final int iImgHeight;

	public SplashScreen(Frame parent, ImageIcon imageIcon) {
		super(parent);
		this.imageIcon = imageIcon;

		if (iBorderColor != null) {
			this.getContentPane().setBackground(iBorderColor);
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		iImgWidth = imageIcon.getIconWidth();
		iImgHeight = imageIcon.getIconHeight();
		int width = iImgWidth + (iBorderSize * 2);
		int height = iImgHeight + (iBorderSize * 2);

		int x = (screenSize.width - width) / 2;
		int y = (screenSize.height - height) / 2;

		this.setBounds(x, y, width, height);

		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// parent.addWindowListener(new WindowAdapter() {
		// @Override
		// public void windowActivated(WindowEvent e) {
		// closeIt();
		// }
		// });

		// This will show the splash screen until the user presses a key
		// Notice that it is attached to the splash screen itself
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				closeIt();
			}
		});

		// This will show the splash screen until the user clicks the mouse on
		// the window
		// Notice that it is attached to the splash screen itself
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeIt();
			}
		});
	}

	public void closeIt() {
		setVisible(false);
		dispose();
	}

	// note - this class created with JBuilder
	void jbInit() throws Exception {

		imageLabel.setIcon(imageIcon);

		this.getContentPane().setLayout(borderLayout1);
		this.getContentPane().add(imageLabel, BorderLayout.CENTER);

		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.BLACK);

		progressBarMsg.setSize(iImgWidth, 10);
		progressBarMsg.setForeground(Color.BLACK);

		progressBar.setBounds(0, iImgHeight - 45, iImgWidth, 15);

		progressBarMsg.setFont(new Font("SansSerif", Font.PLAIN, 11));
		progressBarMsg.setOpaque(false);

		progressBar.add(progressBarMsg);

		imageLabel.setLayout(null);
		imageLabel.add(progressBar);

		this.pack();
	}

	public void setProgressMax(int maxProgress) {
		progressBar.setMaximum(maxProgress);
	}

	public static void setProgress(int progress) {
		final int theProgress = progress;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBar.setValue(theProgress);
			}
		});
	}

	public static void setProgress(String message, int progress) {
		final int theProgress = progress;
		final String theMessage = message;
		setProgress(progress);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				changeProgressBarColor();
				progressBar.setValue(theProgress);
				setMessage(theMessage);
			}
		});
	}

	private static void changeProgressBarColor() {
		if (progressBar.getForeground() == Color.GREEN) {
			UIManager.put("ProgressBar.foreground", Color.YELLOW);
		} else if (progressBar.getForeground() == Color.YELLOW) {
			UIManager.put("ProgressBar.foreground", Color.ORANGE);
		} else if (progressBar.getForeground() == Color.ORANGE) {
			UIManager.put("ProgressBar.foreground", Color.RED);
		} else if (progressBar.getForeground() == Color.RED) {
			UIManager.put("ProgressBar.foreground", Color.GREEN);
		}
	}

	public void setScreenVisible(boolean b) {
		final boolean boo = b;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				setVisible(boo);
			}
		});
	}

	private static void setMessage(String message) {
		if (message == null) {
			message = "";
			progressBarMsg.setText("");
		} else {
			progressBarMsg.setText("   " + message);
		}
	}
}