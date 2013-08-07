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
package com.sample.hibernate.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sample.hibernate.bean.Employee;
import com.sample.hibernate.saaj.EmployeeServiceSaajClient;

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
public class EmployeeServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletException servletException = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		Element employeeListNode = null;
		try {
			String webServiceRequestUrl = "http://"
					+ request.getRequestURL().toString().split("/")[2]
					+ request.getContextPath() + "/employeeService";
			EmployeeServiceSaajClient service = new EmployeeServiceSaajClient();
			service.setServiceURL(webServiceRequestUrl);

			employeeListNode = (Element) service.getEmployeeListNode();

		} catch (Exception e) {
			// System.out.println("Exception handling user request...");
			servletException = new ServletException(e);

		}

		Employee employee = null;
		NodeList employeeNodeList = employeeListNode
				.getElementsByTagName("Employee");

		JAXBContext jaxbContext = null;
		Unmarshaller jaxbUnmarshaller = null;
		try {
			jaxbContext = JAXBContext.newInstance(Employee.class);

			jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < employeeNodeList.getLength(); i++) {
			Node employeeNode = employeeNodeList.item(i);

			try {
				employee = (Employee) jaxbUnmarshaller.unmarshal(employeeNode);
				employeeList.add(employee);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (servletException == null) {
			request.setAttribute("employeeList", employeeList);
			request.getRequestDispatcher("/employeeResponse.jsp").forward(
					request, response);
		} else {
			request.setAttribute("servletException", servletException);
			request.getRequestDispatcher("/ShowError.jsp").forward(request,
					response);
		}
	}
}
