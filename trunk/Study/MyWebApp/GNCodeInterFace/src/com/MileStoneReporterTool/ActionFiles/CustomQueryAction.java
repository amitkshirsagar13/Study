/*
 * CustomQueryAction.java
 *
 * Created on November 21, 2007, 5:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.ActionFiles;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import com.MileStoneReporterTool.ConnectorForms.*;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.ReportGenerator.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author AmitC_Kshirsagar
 */
public class CustomQueryAction extends Action {

	/**
	 * Creates a new instance of CustomQueryAction
	 */
	public CustomQueryAction() {
	}

	/**
	 * Method execute.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String message = "";

		GetDBConnection connectionObject = new GetDBConnection();
		Connection connection = connectionObject.getDBConnection();

		CustomQueryForm customQueryForm = (CustomQueryForm) form;
		String sqlStatement = customQueryForm.getSqlStatement();
		System.out.println("sqlStatement " + sqlStatement);

		try {

			Statement statement = connection.createStatement();
			sqlStatement = sqlStatement.trim();
			String sqlType = sqlStatement.substring(0, 6);
			if (sqlType.equalsIgnoreCase("select")) {
				ResultSet resultSet = statement.executeQuery(sqlStatement);
				// create a string that contains a HTML-formatted result set
				message = SQLUtil.getHtmlRows(resultSet);
			} else {
				String sqlTypeShow = sqlStatement.substring(0, 4);
				if (sqlTypeShow.equalsIgnoreCase("show")) {
					ResultSet resultSet = statement.executeQuery(sqlStatement);
					// create a string that contains a HTML-formatted result set
					message = SQLUtil.getHtmlRows(resultSet);
				} else {
					int i = statement.executeUpdate(sqlStatement);
					if (i == 0) // this is a DDL statement
						message = "<tr><td>"
								+ "The statement executed successfully."
								+ "</td></tr>";
					else
						// this is an INSERT, UPDATE, or DELETE statement
						message = "<tr><td>"
								+ "The statement executed successfully.<br>"
								+ i + " row(s) affected." + "</td></tr>";
				}
			}

			statement.close();
		} catch (Exception e) {
			message = "<br>Error executing the SQL statement: <br><font color='red'>"
					+ e.getMessage() + "</font>";
		}
		System.out.println("message " + message);
		session.setAttribute("message", message);
		session.setAttribute("sqlStatement", sqlStatement);
		connection.close();
		return mapping.findForward("success");

	}
}
