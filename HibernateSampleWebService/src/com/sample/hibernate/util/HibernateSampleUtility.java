package com.sample.hibernate.util;

import java.io.ByteArrayOutputStream;

import javax.xml.soap.SOAPMessage;

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
public class HibernateSampleUtility {
	public static String getMsgAsString(SOAPMessage message) {
		String msg = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			message.writeTo(baos);
			msg = baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
