package com.sample.base.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.activation.DataHandler;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

public class SampleWebServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			// Send SOAP Message to SOAP Server
			String url = "http://localhost:8080/SampleWebService/myZooService";

			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(),
					url);

			// Process the SOAP Response
			printSOAPRequestResponse(soapResponse);
			// citeBridgeStore = processSOAPResponse(soapResponse);

			soapConnection.close();
		} catch (Exception e) {

		}

	}

	/**
	 * @param soapResponse
	 * @throws SOAPException
	 * @throws TransformerException
	 * @throws IOException
	 */
	private static void printSOAPRequestResponse(SOAPMessage soapResponse)
			throws SOAPException, TransformerException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nSOAP Message = ");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
		printMessageToSystem("\n");

		Iterator iterator = soapResponse.getAttachments();

		while (iterator.hasNext()) {
			AttachmentPart attachment = (AttachmentPart) iterator.next();
			String id = attachment.getContentId();
			String type = attachment.getContentType();
			System.out.print("Attachment " + id + " has content type " + type
					+ " \n");

			Object content = attachment.getContent();
			printMessageToSystem("Attachment contains:\n" + content);

		}

		Element doubled = (Element) soapResponse.getSOAPBody()
				.getElementsByTagName("doubledNumber").item(0);
		try {
			if (doubled != null) {
				DataHandler dataHandler = new DataHandler(
						doubled.getTextContent(), "application/octet-stream");
				InputStreamReader isr = new InputStreamReader(
						dataHandler.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String strLine;
				// Read File Line By Line
				while ((strLine = br.readLine()) != null) {
					// Print the content on the console
					printMessageToSystem(strLine);
					if (strLine.equalsIgnoreCase("")) {
						break;
					}
				}
			}
		} catch (Exception e) {
			// printMessageToSystem(e.getMessage());
		}
	}

	private static String serviceURL = "http://localhost:8080/SampleWebService/myZooService";
	private static String serviceURI = "http://service.base.sample.com/";

	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("ser", serviceURI);

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement operationElem = soapBody.addChildElement("getZooAnimals",
				"ser");

		SOAPElement citeBridgeStoreElem = soapBody
				.addChildElement("ZooAnimalStore");
		operationElem.appendChild(citeBridgeStoreElem);

		AttachmentPart attachment = soapMessage.createAttachmentPart();
		String stringContent = "Update address for Sunny Skies "
				+ "Inc., to 10 Upbeat Street, Pleasant Grove, CA 95439";

		attachment.setContent(stringContent, "text/plain");
		attachment.setContentId("update_address");

		soapMessage.addAttachmentPart(attachment);

		AttachmentPart attachment1 = soapMessage.createAttachmentPart();
		String stringContent1 = "This one is extra..";

		attachment1.setContent(stringContent1, "text/plain");
		attachment1.setContentId("extra");

		soapMessage.addAttachmentPart(attachment1);

		printSOAPRequestResponse(soapMessage);
		return soapMessage;
	}

	public static void printMessageToSystem(String message) {
		System.out.println(message);
	}
}
