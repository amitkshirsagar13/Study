package com.businessadvancesolutions.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

		progressBar.setSize(iImgWidth, 20);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.BLACK);

		progressBarMsg.setSize(iImgWidth, 15);
		progressBarMsg.setForeground(Color.BLACK);
		progressBarMsg.setBackground(Color.WHITE);
		progressBarMsg.setOpaque(true);

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
		// southPanel.setBackground(Color.BLACK);
		this.getContentPane().add(imageLabel, BorderLayout.CENTER);
		// this.getContentPane().add(southPanel, BorderLayout.SOUTH);
		// southPanel.add(progressBar);
		this.getContentPane().add(progressBarMsg, BorderLayout.SOUTH);
		this.getContentPane().add(progressBar, BorderLayout.SOUTH);
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
				progressBar.setValue(theProgress);
				setMessage(theMessage);
			}
		});
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
			progressBarMsg.setText(message);
		}
	}
}