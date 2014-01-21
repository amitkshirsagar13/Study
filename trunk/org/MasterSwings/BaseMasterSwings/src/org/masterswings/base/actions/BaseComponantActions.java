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

package org.masterswings.base.actions;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

public class BaseComponantActions extends JPanel implements
		MouseMotionListener, MouseListener, ActionListener,
		BaseMasterSwingsContants {

	Logger _log = Logger.getLogger(BaseComponantActions.class.getName());

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

	public BaseComponantActions(LayoutManager layoutManager) {
		super(layoutManager);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionName = e.getActionCommand();

		Thread execThread = new Thread();
		Runnable execRunner = new Runnable() {

			@Override
			public void run() {
				switch (actionName) {
				case OK:
					executeOk();
					break;
				case RESET:
					executeReset();
					break;
				case SUBMIT:
					executeSubmit();
					break;
				case CANCEL:
					executeCancel();
					break;
				case ADD:
					executeAdd();
					break;
				case REMOVE:
					executeRemove();
					break;
				case DUPLICATE:
					executeDuplicate();
					break;
				default:
					break;
				}
			}
		};

	}

	public void waitSomeTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String executingCommand = "Executing Command: ";

	public void executeOk() {
		debug(executingCommand + OK);
	}

	public void executeCancel() {
		debug(executingCommand + CANCEL);
	}

	public void executeReset() {
		debug(executingCommand + RESET);
	}

	public void executeSubmit() {
		debug(executingCommand + SUBMIT);
	}

	public void executeAdd() {
		debug(executingCommand + ADD);
	}

	public void executeRemove() {
		debug(executingCommand + REMOVE);
	}

	public void executeDuplicate() {
		debug(executingCommand + DUPLICATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			debug("Mouse Clicked: " + e.getSource().getClass().getName());
			if (e.getSource().getClass().getName()
					.equals("javax.swing.JTextField")) {
				((JTextField) e.getSource()).setText("");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
