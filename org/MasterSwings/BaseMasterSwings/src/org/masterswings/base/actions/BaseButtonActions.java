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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public abstract class BaseButtonActions extends JFrame implements
		ActionListener, BaseMasterSwingsContants {

	Logger _log = Logger.getLogger(BaseButtonActions.class.getName());

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

	public BaseButtonActions(String applicationName) {
		super(applicationName);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionName = e.getActionCommand();

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

		Thread execThread = new Thread(execRunner);
		execThread.start();

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
}
