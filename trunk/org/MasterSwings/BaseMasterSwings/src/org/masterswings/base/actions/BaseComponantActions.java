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

import javax.swing.JPanel;

import org.apache.log4j.Logger;

public abstract class BaseComponantActions extends JPanel implements ActionListener{

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
	
	
	public BaseComponantActions(LayoutManager layoutManager){
		super(layoutManager);
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

