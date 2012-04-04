/*
 * CustomQueryAction.java
 *
 * Created on November 21, 2007, 5:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.ActionFiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.MileStoneReporterTool.DataBeans.LiveDataStorage;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraGNCodeDAO;

/**
 * 
 * @author AmitC_Kshirsagar
 */
public class UpdateSessionAction extends Action {

	/**
	 * Creates a new instance of CustomQueryAction
	 */
	public UpdateSessionAction() {
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
		/*
		 * Set the Session veriables for Application and release
		 */

		SQLOraGNCodeDAO applicationDAO = new SQLOraGNCodeDAO();
		if (LiveDataStorage.State == null) {
			applicationDAO.updateLiveDataStorage();
		}
		session.setAttribute("message", "");
		/*
		 * SQLOraApplicationDAO applicationDAO = new SQLOraApplicationDAO();
		 * List applicationList=new ArrayList(100);
		 * applicationList=applicationDAO.listApplicationMethod();
		 * session.setAttribute("applicationList", applicationList);
		 * SQLOraRelDAO releaseDAO = new SQLOraRelDAO(); List releaseListMap =
		 * new ArrayList(100);
		 * releaseListMap=releaseDAO.listReleaseMapMethod(applicationList);
		 * session.setAttribute("releaseListMap", releaseListMap);
		 * System.out.println(releaseListMap.size()); List userList=new
		 * ArrayList(1000); SQLOraUserDAO userListDAO = new SQLOraUserDAO();
		 * userList = userListDAO.listUsersMethod();
		 * session.setAttribute("userList", userList);
		 * 
		 * session.setAttribute("message", ""); SaveUpdateGNCodeForm addForm =
		 * new SaveUpdateGNCodeForm(); session.setAttribute("SaveUpdateForm",
		 * addForm);
		 */
		return mapping.findForward("success");
	}
}