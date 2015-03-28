package com.sample.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterTestServlet
 */
public class RegisterTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RegisterTestServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");

		String randomNumberHidden = request.getParameter("randomNumberHidden");
		String randomNumberInput = request.getParameter("randomNumberInput");
		StringBuffer responseString = new StringBuffer("Hello " + name + ", with passws: " + passwd);
		responseString.append("<br/>");
		responseString.append(randomNumberHidden + "::" + randomNumberInput);
		response.getOutputStream().print(responseString.toString());

	}

}
