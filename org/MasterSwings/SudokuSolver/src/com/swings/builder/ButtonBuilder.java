package com.swings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import static com.swings.builder.JComponantBuilder.application;

import java.util.List;

import org.apache.log4j.Logger;

import com.swings.componants.Button;

public class ButtonBuilder extends JComponantBuilder {
	static Logger log = Logger.getLogger(ButtonBuilder.class.getName());

	public static List<Button> getButtonsForPanel(String panel) {
		if (application == null) {
			parseDom2Componants();
		}
		List<Button> buttonList = null;
		for (int i = 0; i < application.getFrame().getPanel().size(); i++) {
			if (application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)
					&& application.getFrame().getPanel().get(i)
							.getSubPanelName().equalsIgnoreCase("ButtonPanel")) {
				buttonList = application.getFrame().getPanel().get(i)
						.getButton();
			}

		}
		return buttonList;
	}

	public static void main(String[] args) {
		ButtonBuilder.parseDom2Componants();
	}

}
