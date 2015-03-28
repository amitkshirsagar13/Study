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

import com.MileStoneReporterTool.ConnectorForms.AllocateUsersForm;
import com.MileStoneReporterTool.DataBeans.UserData;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraUserDAO;

import java.lang.reflect.Array;
import java.util.*;

public class AllocateUsersAction extends Action {

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
		AllocateUsersForm addForm = (AllocateUsersForm) form;
		
		String releaseID = addForm.getReleaseID();
		String[] releaseUserID = addForm.getRelUserId();
		String releaseUserType = null;
		
		int releaseUserCount = releaseUserID.length;
		userDAO.cleanAllocateUserForRelease(releaseID);
		for (int i=0;i<releaseUserCount;i++) {
			System.out.println(i+" : "+releaseUserID[i]+" - "+addForm.getDynamicProps("relUserType"+i));
			
			if (releaseUserID[i].equals("POC")||releaseUserID[i].equals("blank")) {

			}else if ((addForm.getDynamicProps("relUserType"+i)+"").equals("0")){
				userDAO.allocatePOCForRelease(releaseID, releaseUserID[i]);
			}else {
				userDAO.allocateUserForRelease(releaseID, releaseUserID[i]);
			}
		}
		List relUserList = userDAO.listReleaseUsers(releaseID);
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
