/*
 * AddUserDetailsAction.java
 *
 * Created on November 23, 2007, 9:39 PM
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

import com.MileStoneReporterTool.ConnectorForms.AddUserDetailsForm;
import com.MileStoneReporterTool.ConnectorForms.EditUsersForm;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.ReportGenerator.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraUserDAO;
import com.MileStoneReporterTool.DataBeans.UserData;

/**
 * 
 * @author AmitC_Kshirsagar
 */
public class EditUsersAction extends Action {

	/**
	 * Creates a new instance of CustomQueryAction
	 */
	public EditUsersAction() {
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

		String userID = "";
		String passWord = "";
		String role = "";

		int insertedUser = 2;
		EditUsersForm editUserDetailsForm = (EditUsersForm) form;

		SQLOraUserDAO userDAO = new SQLOraUserDAO();

		List userListOld = userDAO.listUsersMethod();
		
		List userListUpdated = new ArrayList<UserData>();
		
		

		
		
		ListIterator iter = userListOld.listIterator();
		int i = 0;
		UserData user = new UserData();
		int userUpdateCount = 0;
		while (iter.hasNext()) {

			user = (UserData) userListOld.get(i);
			String userId = user.getUserID();
			String userName = (String) editUserDetailsForm
					.getDynamicProps("userName" + i);
			String roleId = (String) editUserDetailsForm.getDynamicProps("role"
					+ i);
			UserData updatedUserData = new UserData();
			updatedUserData.setUserID((String)editUserDetailsForm
					.getDynamicProps("userID" + i));
			updatedUserData.setUserName((String)editUserDetailsForm
					.getDynamicProps("userName" + i));
			updatedUserData.setRole((String)editUserDetailsForm
					.getDynamicProps("role" + i));
			userListUpdated.add(updatedUserData);
			
			if (user.getRole().equals(roleId) && user.getUserName().equals(userName)) {

			} else {
				System.out.println(user.getRole() +" - "+ roleId +"+"+ user.getUserName() +" - "+ userName);
				userUpdateCount = userUpdateCount
				+ userDAO.updateUsers(userId, userName, roleId);
			}
			i++;
			iter.next();
		}

		List userList = userDAO.listUsersMethod();

		session.setAttribute("userList", userList);
		session.setAttribute("userUpdateCount", userUpdateCount);
		return mapping.findForward("success");

	}
}
