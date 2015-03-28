package com.swings.builder;

/**
 * ProjectName: SwingsParseX2J
 * @author amit_kshirsagar
 * @date Jan 20, 2014
 */

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.swings.componants.Application;
import com.swings.componants.Button;

public class ButtonBuilder {
	static Logger log = Logger.getLogger(ButtonBuilder.class.getName());

	private static Application application = null;

	public static void parseDom2Componants() {
		try {

			File file = new File("./conf/SampleComponants.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(Application.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			application = (Application) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public static List<Button> getButtonsForPanel(String panel) {
		if (application == null) {
			parseDom2Componants();
		}
		List<Button> buttonList = null;
		for (int i = 0; i < application.getFrame().getPanel().size(); i++) {
			if (application.getFrame().getPanel().get(i).getPanelName()
					.equalsIgnoreCase(panel)) {
				buttonList = application.getFrame().getPanel().get(0)
						.getButton();
			}

		}
		return buttonList;
	}

	public static void main(String[] args) {
		ButtonBuilder.parseDom2Componants();
	}

}
