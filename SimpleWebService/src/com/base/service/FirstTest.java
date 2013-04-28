package com.base.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "FirstTest", serviceName = "FirstTest")
public class FirstTest {

	@WebMethod(operationName = "getMessage", action = "getMessage")
	@WebResult(name = "message")
	public String getMessage(@WebParam(name = "name") String name) {
		return "Hi there... " + name;
	}

}
