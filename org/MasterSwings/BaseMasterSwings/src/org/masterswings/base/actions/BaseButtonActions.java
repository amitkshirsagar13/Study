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

public abstract class BaseButtonActions extends JFrame implements ActionListener {

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

	public BaseButtonActions(String applicationName){
		super(applicationName);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionName = e.getActionCommand();
		System.out.println(actionName);

	}

	public void executeOk() {

	}

	public void executeCancel() {

	}

	public void executeReset() {

	}

	public void executeSubmit() {

	}

	public void executeAdd() {

	}

	public void executeRemove() {

	}

	public void executeDuplicate() {

	}
}
