/*==================================================================*
 * Copyright (c) 2009 LEXISNEXIS                                    *
 * 9443 Springboro Pike, P.O. Box 933, Miamisburg, Ohio, 45401-0933 *
 * All rights reserved.                                             *
 * This software is the proprietary information of LexisNexis       *
 * ("Confidential Information"). You shall                          *
 * disclose such Confidential Information and shall use it only in  *
 * accordance with the terms of the license agreement you entered   *
 * with LEXISNEXIS.                                                 *
 ===================================================================*/
package com.sample.hibernate.saaj;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 5, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.io.StringWriter;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jboss.logging.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sample.hibernate.util.HibernateSampleUtility;

public class EmployeeServiceSaajClient {

	private String serviceURL = null;
	private String namespaceURI = "http://service.hibernate.sample.com/";
	protected static Logger logger = Logger
			.getLogger(EmployeeServiceSaajClient.class.getName());

	/**
	 * Starting point for the SAAJ - SOAP Client Testing
	 */
	public String getEmployeeList() {
		String soapResponseString = null;
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			// Send SOAP Message to SOAP Server
			if (serviceURL == null) {
				serviceURL = "http://localhost:8080/HibernetSample/employeeService";
			}
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(),
					serviceURL);

			// Process the SOAP Response
			soapResponseString = printSOAPResponse(soapResponse);
			// citeBridgeStore = processSOAPResponse(soapResponse);

			logger.info("Response SOAP Message: " + soapResponseString);
			soapConnection.close();
		} catch (Exception e) {
			logger.error("Error occurred while sending SOAP Request to Server",
					e);
			e.printStackTrace();
		}
		return soapResponseString;
	}

	public Node getEmployeeListNode() {
		Node employeeListNode = null;
		try {
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			// Send SOAP Message to SOAP Server
			if (serviceURL == null) {
				serviceURL = "http://localhost:8080/HibernetSample/employeeService";
			}
			SOAPMessage soapMessage = createSOAPRequest();

			String soapMsg = HibernateSampleUtility.getMsgAsString(soapMessage);
			logger.info("Request SOAP Message: " + soapMsg);

			soapMessage = soapConnection.call(soapMessage, serviceURL);

			soapMsg = HibernateSampleUtility.getMsgAsString(soapMessage);
			logger.info("Response SOAP Message: " + soapMsg);

			employeeListNode = soapMessage.getSOAPBody()
					.getElementsByTagName("ns2:listEmployeeResponse").item(0);

			soapConnection.close();
		} catch (Exception e) {
			logger.error("Error occurred while sending SOAP Request to Server",
					e);
			e.printStackTrace();
		}
		return employeeListNode;
	}

	private SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("ser", namespaceURI);

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement listEmployeeElem = soapBody.addChildElement("listEmployee",
				"ser");

		// JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
		//
		// Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		//
		// // output pretty printed
		// jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//
		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		//
		// DocumentBuilder db = dbf.newDocumentBuilder();
		// Document doc = db.newDocument();
		// Employee employee = null;
		// jaxbMarshaller.marshal(employee, doc);
		//
		// TransformerFactory tranFactory = TransformerFactory.newInstance();
		// Transformer transformer = tranFactory.newTransformer();
		//
		// StringWriter sw = new StringWriter();
		//
		// Source src = new DOMSource(doc);
		//
		// transformer.transform(src, new StreamResult(sw));
		//
		// String soapRequest = sw.toString();

		return soapMessage;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private String printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		// Source sourceContent = soapResponse.getSOAPPart().getContent();
		// System.out.print("\nResponse SOAP Message = ");
		// StreamResult result = new StreamResult(System.out);
		// transformer.transform(sourceContent, result);
		// System.out.println("\n");

		StringWriter writer = new StringWriter();

		String xml = null;

		NodeList faultList = soapResponse.getSOAPBody().getElementsByTagName(
				"faultstring");
		if (faultList.getLength() > 0) {
			transformer.transform(new DOMSource(faultList.item(0)),
					new StreamResult(writer));

			xml = writer.toString();
		} else {
			NodeList employeeList = soapResponse.getSOAPBody()
					.getElementsByTagName("ns2:listEmployeeResponse");

			transformer.transform(new DOMSource(employeeList.item(0)),
					new StreamResult(writer));

			xml = writer.toString();
		}
		return xml;
	}

	public String getServiceURL() {
		return this.serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

}
