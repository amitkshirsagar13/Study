/**
 * 
 */
package com.base.service;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 10, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.base.service.zoo.bean.Animal;
import com.base.service.zoo.bean.AnimalStore;
import com.base.service.zoo.bean.AnimalType;
import com.base.service.zoo.bean.Zoo;

@WebService(name = "MyZooService", serviceName = "MyZooService")
// @HandlerChain(file = "Zoo_handler.xml")
public class MyZooService {
	@Resource
	WebServiceContext ctx;

	@WebMethod(operationName = "getZooAnimals", action = "getZooAnimals")
	@WebResult(name = "ZooAnimalStore")
	public Zoo getZooAnimals(@WebParam(name = "ZooAnimalStore") Zoo zoo)
			throws Exception {
		if (zoo == null) {
			throw new Exception("Zoo sent Null...", null);
		}

		MessageContext jaxwsContext = ctx.getMessageContext();

		Set<String> keySet = jaxwsContext.keySet();

		for (String key : keySet) {
			System.out.println(key + ":" + jaxwsContext.get(key));
		}

		Map<String, DataHandler> attachments = (Map<String, DataHandler>) jaxwsContext
				.get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
		System.out.println("No of attachments [" + attachments.size() + "]");
		keySet = attachments.keySet();
		DataHandler dataHandler = null;
		for (String key : keySet) {
			dataHandler = attachments.get(key);

			InputStreamReader isr = new InputStreamReader(
					dataHandler.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
				if (strLine.equalsIgnoreCase("")) {
					break;
				}
			}
		}

		System.out.println("Got Out...");

		Map<String, DataHandler> attachmentsOut = new HashMap<String, DataHandler>();

		DataHandler outDataHandler = new DataHandler(
				"Better this go through...", "text/plain");
		attachmentsOut.put("text/plain", outDataHandler);

		outDataHandler = new DataHandler("Atleast this go through...",
				"text/plain");
		attachmentsOut.put("text/plain", outDataHandler);

		if (jaxwsContext
				.containsKey(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS)) {
			System.out.println("Previous OUTPUT");
			jaxwsContext.remove(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
		}
		jaxwsContext.put(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS,
				attachmentsOut);
		jaxwsContext.put(MessageContext.MESSAGE_OUTBOUND_PROPERTY, true);

		if (jaxwsContext.containsKey(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
			System.out.println("Previous OUTPUT"
					+ jaxwsContext
							.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));

		}

		jaxwsContext.put(MessageContext.HTTP_RESPONSE_CODE, new Integer(200));

		System.out.println("Message Built...Got Out...");
		Animal animal1 = new Animal();
		animal1.setId("1");
		animal1.setAge("5");
		animal1.setStatus("Live");
		animal1.setAnimalName("Waghya");
		// Attachment attach = new Attachment();
		// attach.setDoubledNumber(outDataHandler);
		// animal1.setPicture(attach);

		Animal animal2 = new Animal();
		animal2.setId("3");
		animal2.setAge("10");
		animal2.setStatus("Live");
		animal2.setAnimalName("Shera");

		Animal animal3 = new Animal();
		animal3.setId("2");
		animal3.setAge("3");
		animal3.setStatus("Dead");
		animal3.setAnimalName("Pungya");

		AnimalType animalType1 = new AnimalType();

		animalType1.setName("Tiger");

		animalType1.getAnimal().add(animal1);
		animalType1.getAnimal().add(animal2);
		animalType1.getAnimal().add(animal3);

		Animal animal4 = new Animal();
		animal4.setId("4");
		animal4.setAge("7");
		animal4.setStatus("Live");
		animal4.setAnimalName("Bunny");

		AnimalType animalType2 = new AnimalType();

		animalType2.setName("Rabbit");
		animalType2.getAnimal().add(animal4);

		AnimalStore animalStore = new AnimalStore();
		animalStore.getAnimalType().add(animalType1);
		animalStore.getAnimalType().add(animalType2);

		zoo.setAnimalStore(animalStore);

		zoo.setClientName("New York Zoo");
		int size = zoo.getAnimalStore().getAnimalType().get(0).getAnimal()
				.size()
				+ zoo.getAnimalStore().getAnimalType().get(1).getAnimal()
						.size();
		zoo.setAnimalCount(size);

		return zoo;
	}
}
