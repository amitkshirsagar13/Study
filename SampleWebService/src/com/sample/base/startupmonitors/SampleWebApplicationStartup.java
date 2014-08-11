package com.sample.base.startupmonitors;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class SampleWebApplicationStartup implements ServletContextListener {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger
			.getLogger(SampleWebApplicationStartup.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log4j.debug("Stopping Application...");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log4j.debug("Starting Application...");
		Context initCtx;
		try {
			initCtx = new InitialContext();

			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			Session session = (Session) envCtx.lookup("mail/MailSession");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("test.resource@tomcat.com"));
			InternetAddress to[] = new InternetAddress[1];
			to[0] = new InternetAddress("amit.kshirsagar.13@gmail.com");
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject("Test Mail from Tomcat Resource");
			message.setContent("This is a test mail sent from Tomcat Resource",
					"text/plain");
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log4j.debug("Started Application...");
	}

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "SampleWebApplicationStartup: ";
	}

}