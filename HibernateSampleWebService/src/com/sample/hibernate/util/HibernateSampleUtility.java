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
