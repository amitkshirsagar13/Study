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

import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.apache.log4j.Logger;
import org.masterswings.base.actions.BaseComponantActions;
import org.masterswings.base.actions.BaseMasterSwingsContants;

public abstract class BaseMasterSwingsPanel extends BaseComponantActions
		implements MouseMotionListener, MouseListener, BaseMasterSwingsContants {

	Logger _log = Logger.getLogger(BaseMasterSwingsPanel.class.getName());

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

	/**
	 * @param applicationName
	 */
	public BaseMasterSwingsPanel(LayoutManager layoutManager) {
		super(layoutManager);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

}
