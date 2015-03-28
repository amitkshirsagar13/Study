package com.sample.base.service;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.sample.base.Animal;
import com.sample.base.AnimalStore;
import com.sample.base.AnimalType;
import com.sample.base.Attachment;
import com.sample.base.Zoo;

@WebService(name = "MyZooService", serviceName = "MyZooService")
@HandlerChain(file = "Zoo_handler.xml")
public class MyZooService {
	private static Logger log4j = Logger.getLogger(MyZooService.class);

	@Resource
	WebServiceContext ctx;

	@WebMethod(operationName = "getZooAnimals", action = "getZooAnimals")
	@WebResult(name = "ZooAnimalStore")
	public Zoo getZooAnimals(@WebParam(name = "ZooAnimalStore") Zoo zoo)
			throws Exception {
		if (zoo == null) {
			throw new Exception("Zoo sent Null...", null);
		}

		try {

			MessageContext jaxwsContext = ctx.getMessageContext();

			Set<String> keySet = jaxwsContext.keySet();

			Map<String, DataHandler> attachments = (Map<String, DataHandler>) jaxwsContext
					.get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
			log4j.debug("No of attachments [" + attachments.size() + "]");
			keySet = attachments.keySet();
			DataHandler dataHandler = null;
			for (String key : keySet) {
				dataHandler = attachments.get(key);
				log4j.debug("Attachment:" + key);
				InputStreamReader isr = new InputStreamReader(
						dataHandler.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String strLine;
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
					log4j.debug(strLine);
					if (strLine.equalsIgnoreCase("")) {
						log4j.debug(strLine);
					}
				}
			}

			log4j.debug("Got Out...");

			Map<String, DataHandler> attachmentsOut = new HashMap<String, DataHandler>();

			DataHandler outDataHandler = new DataHandler(
					"Better this go through...", "text/plain");
			attachmentsOut.put("output_message1.txt", outDataHandler);

			/**
			 * Image Attachment
			 */

			Image img = ImageIO
					.read(new File(
							"D://MyWork//MyProjects//SampleWebService//resource//image//trisha12.png"));
			outDataHandler = new DataHandler(img, "image/png");
			// outDataHandler = new DataHandler("Atleast this go through...",
			// "image/png");

			attachmentsOut.put("ganga-aurum-park.png", outDataHandler);

			if (jaxwsContext
					.containsKey(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS)) {
				log4j.debug("Previous OUTPUT");
				jaxwsContext
						.remove(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
			}
			jaxwsContext.put(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS,
					attachmentsOut);
			// jaxwsContext.put(MessageContext.MESSAGE_OUTBOUND_PROPERTY, true);

			if (jaxwsContext
					.containsKey(MessageContext.MESSAGE_OUTBOUND_PROPERTY)) {
				log4j.debug("Previous OUTPUT"
						+ jaxwsContext
								.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));

			}

			log4j.debug("Message Built...Got Out...");
			Animal animal1 = new Animal();
			animal1.setId("1");
			animal1.setAge("5");
			animal1.setStatus("Live");
			animal1.setAnimalName("Waghya");
			Attachment attach = new Attachment();
			attach.setDoubledNumber(outDataHandler);
			animal1.setPicture(attach);

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
			double random = Math.random();
			if (random > 0) {
				throw new Exception("Random Martial:" + random);
			}

			zoo.setClientName("New York Zoo");
			int size = zoo.getAnimalStore().getAnimalType().get(0).getAnimal()
					.size()
					+ zoo.getAnimalStore().getAnimalType().get(1).getAnimal()
							.size();
			zoo.setAnimalCount(size);
		} catch (Exception e) {
			log4j.error("Cought Exception:" + e.getMessage());
		}
		return zoo;
	}

	public static void printToSystem(String message) {
		log4j.debug(message);
	}
}
