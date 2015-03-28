package com.JTutor.front;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A Splash Screen class that I grabbed off of the Web somewhere. (See, they
 * don't call it the <I>Information Superhighway</I> for nothing.) Fortunately
 * for me, some guy named Ralph answered a question in the
 * <code>comp.lang.java.help</code>Newsgroup back in July '98. I changed it a
 * bit, but definitely: "Thanks a lot, Ralph"!
 * <P>
 * Objects of this class are created for <I>System Start-Up</I> and for the
 * <I>Help-About</I> menu choice. Any key press or mouse click (on the window)
 * while the Splash Screen is being displayed will cause the the window to be
 * closed.
 * <P>
 * Indeed, this is what is required for <I>Help-About</I> to dismiss the window.
 * At <I>System Start-Up</I> a key press is not required; the window will
 * automatically close when the main application window becomes visible.
 * 
 * Here is an example on how to use the SplashScreen with a "bogus frame":
 * 
 * SplashScreen ss = new SplashScreen( new JFrame(),
 * "d:/vss/test/splash/image001.gif", 10, Color.red);
 * 
 **/
public class SplashHelper extends Window {
	private final Image iImage;
	private final String iImgName;

	private int iImgWidth, iImgHeight;
	private final int iBorderSize;

	private final Color iBorderColor;

	/**
	 * Constructor displays the Splash Screen until the main application window
	 * becomes visible or until the user presses a key or clicks the mouse,
	 * whichever comes first.
	 * <P>
	 * In the case of <I>System Start-Up</I> it should be instantiated as soon
	 * as possible in the main window creation logic, thus allowing you to see
	 * the Splash Screen.
	 * 
	 * @param aParent
	 *            The main frame that the Splash Screen will wait for to become
	 *            visible. A "bogus frame" can be passed when using this class
	 *            for <I>Help-About</I>
	 * @param anImgName
	 *            The path and filename that contains the image
	 * @param aBorderSize
	 *            The number of pixels surrounding the image. A value of 0 means
	 *            that no border will be drawn.
	 * @param aBorderColor
	 *            The color of the border surrounding the image. A value of
	 *            <code>null</code> means the default frame color will be used
	 */
	public SplashHelper(Frame aParent, String anImgName, int aBorderSize,
			Color aBorderColor) {
		super(aParent);

		iImgName = anImgName;
		iBorderSize = aBorderSize;
		iBorderColor = aBorderColor;

		iImage = loadSplashImage();
		showSplashScreen();

		requestFocus(); // Need this to allow any key press to close the window

		// This will show the splash screen until the main window becomes
		// visible
		// Notice that it is attached to the main application window
		aParent.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				closeIt();
			}
		});

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

	// ---------------------------------------------------------------------------------
	// Get rid of the Splash Screen
	public void closeIt() {
		setVisible(false);
		dispose();
	}

	// ---------------------------------------------------------------------------------
	// Grab the image and figure out how big it is
	private Image loadSplashImage() {

		MediaTracker tracker = new MediaTracker(this);
		Image image = Toolkit.getDefaultToolkit().getImage(iImgName);

		tracker.addImage(image, 1);

		try {
			tracker.waitForID(1);
		} // Wait until the image is done loading
		catch (InterruptedException e) {
		} // Hey, NOTHING ever goes wrong!

		iImgWidth = image.getWidth(null);
		iImgHeight = image.getHeight(null);

		return (image);
	}

	// ---------------------------------------------------------------------------------
	// Show the Splash Screen image in the center of the screen
	private void showSplashScreen() {
		if (iBorderColor != null)
			setBackground(iBorderColor);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int width = iImgWidth + (iBorderSize * 2);
		int height = iImgHeight + (iBorderSize * 2);

		int x = (screenSize.width - width) / 2;
		int y = (screenSize.height - height) / 2;

		setBounds(x, y, width, height);
		show();
	}

	/**
	 * Draws the image on the window
	 * 
	 * @param aGraphics
	 *            A graphics device context automatically passed to this routine
	 */
	@Override
	public void paint(Graphics aGraphics) {
		aGraphics.drawImage(iImage, iBorderSize, iBorderSize, iImgWidth,
				iImgHeight, null);
	}

}
