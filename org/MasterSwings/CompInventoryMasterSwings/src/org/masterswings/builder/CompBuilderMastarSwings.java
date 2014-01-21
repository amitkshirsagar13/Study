/**
 * <p>
 * <b>Overview:</b>
 * <p>
 *
 *
 * <pre>
 * Creation date: Jan 19, 2014
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

package org.masterswings.builder;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.masterswings.componants.Application;

public class CompBuilderMastarSwings {

	Logger _log = Logger.getLogger(CompBuilderMastarSwings.class.getName());

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

	static Application _application = null;

	public static void parseDom2Componants() {
		try {

			File file = new File(System.getProperty("COMPONANTSXML"));
			JAXBContext jaxbContext = JAXBContext
					.newInstance(Application.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			_application = (Application) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}
