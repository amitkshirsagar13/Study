package org.masterswings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.masterswings.componants.Button;

public class ButtonBuilder extends CompBuilderMastarSwings {
	Logger _log = Logger.getLogger(ButtonBuilder.class.getName());

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

	public static List<Button> getButtonsForPanel(String panel) {
		if (_application == null) {
			parseDom2Componants();
		}
		List<Button> buttonList = null;
		for (int i = 0; i < _application.getFrame().getPanel().size(); i++) {
			if (_application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)) {
				buttonList = _application.getFrame().getPanel().get(i)
						.getButton();
			}

		}
		return buttonList;
	}

	public static ImageIcon getScaledIcon(final Image image, final double scale) {
		ImageIcon scaledIcon = new ImageIcon(image) {
			@Override
			public int getIconWidth() {
				return (int) (image.getWidth(null) * scale);
			}

			@Override
			public int getIconHeight() {
				return (int) (image.getHeight(null) * scale);
			}

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				g.drawImage(image, x, y, getIconWidth(), getIconHeight(), c);
			}
		};
		return scaledIcon;
	}
}
