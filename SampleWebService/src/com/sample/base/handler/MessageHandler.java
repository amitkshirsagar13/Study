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
package com.sample.base.handler;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 8, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
public class MessageHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext messageContext) {
		log(messageContext);
		return true;

	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.EMPTY_SET;
	}

	@Override
	public boolean handleFault(SOAPMessageContext messageContext) {
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	private void log(SOAPMessageContext messageContext) {
	}

}
