package com.swings.builder;

/**
 * ProjectName: MyUtilityBase
 * @author amit_kshirsagar
 * @date Feb 27, 2014
 */

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.swings.componants.Application;

public class JComponantBuilder {
	static Logger log = Logger.getLogger(JComponantBuilder.class.getName());

	public static Application application = null;

	public static void parseDom2Componants() {
		try {

			File file = new File("./conf/SudokuComponants.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(Application.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			application = (Application) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
