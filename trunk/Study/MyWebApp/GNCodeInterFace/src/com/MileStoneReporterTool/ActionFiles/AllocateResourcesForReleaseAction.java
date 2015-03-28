package com.MileStoneReporterTool.ActionFiles;

import java.sql.Connection;

import javax.servlet.http.*;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.MileStoneReporterTool.ConnectorForms.AllocateResourcesForReleaseForm;
import com.MileStoneReporterTool.ConnectorForms.AllocateUsersForm;
import com.MileStoneReporterTool.DataBeans.UserData;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraUserDAO;

import java.lang.reflect.Array;
import java.util.*;

public class AllocateResourcesForReleaseAction extends Action {

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
		if (isCancelled(request)) {

			return mapping.findForward("welcome");
		}
		//DataSource dataSource = getDataSource(request, "userDB");
		GetDBConnection dbConnection = new GetDBConnection();
		Connection conn = dbConnection.getDBConnection();

		/* Create UserDAO */
		// UserDAO dao = DAOFactory.createUserDAO(conn,"TEST_");
		SQLOraUserDAO userDAO = new SQLOraUserDAO();
		AllocateResourcesForReleaseForm addForm = (AllocateResourcesForReleaseForm) form;
		String releaseId = addForm.getSelectedRelease();
		List relUserList = userDAO.listReleaseUsers(releaseId);
		List userList = userDAO.listUsersMethod();

		ListIterator iter = relUserList.listIterator();
		UserData releaseUser = new UserData();

		while (iter.hasNext()) {
			releaseUser = (UserData) iter.next();
			String releaseUserId = releaseUser.getUserID();
			ListIterator iterUser = userList.listIterator();
			while (iterUser.hasNext()) {
				UserData user = (UserData)iterUser.next();
				if(user.getUserID().equals(releaseUserId)) {					
					userList.remove(user);
					break;
				}
			}
		}

		session.setAttribute("relUserList", relUserList);
		session.setAttribute("availableUserList", userList);
		conn.close();
		return mapping.findForward("success");

	}
}
