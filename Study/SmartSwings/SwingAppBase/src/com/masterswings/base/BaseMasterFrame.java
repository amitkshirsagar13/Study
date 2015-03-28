package com.masterswings.base;

/**
 * ProjectName: SwingsAdvance
 * @author amit_kshirsagar
 * @date Jan 17, 2014
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.apache.log4j.Logger;

public abstract class BaseMasterFrame extends JFrame implements ActionListener,
		MouseListener, FocusListener {
	static Logger log = Logger.getLogger(BaseMasterFrame.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		JComponent button = (JComponent) arg0.getSource();
		button.setBackground(Color.BLUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		JComponent button = (JComponent) arg0.getSource();
		button.setBackground(new Color(214, 217, 223));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final String command = e.getActionCommand();
		Runnable actionRunner = new Runnable() {

			@Override
			public void run() {
				if (command.equalsIgnoreCase("OK")) {
					executeOK();
				} else if (command.equalsIgnoreCase("SUBMIT")) {
					executeSubmit();
				} else if (command.equalsIgnoreCase("CANCEL")) {
					executeCancel();
				} else if (command.equalsIgnoreCase("RESET")) {
					executeReset();
				}
			}
		};

		Thread actionThread = new Thread(actionRunner, e.getSource().toString());
		actionThread.start();
	}

	/**
	 * 
	 */
	public abstract void executeOK();

	/**
	 * 
	 */
	public abstract void executeSubmit();

	/**
	 * 
	 */
	public abstract void executeCancel();

	/**
	 * 
	 */
	public abstract void executeReset();
}
