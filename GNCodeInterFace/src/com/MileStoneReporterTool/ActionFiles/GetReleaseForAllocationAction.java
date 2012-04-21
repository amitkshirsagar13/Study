package com.MileStoneReporterTool.ActionFiles;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraRelDAO;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraUserDAO;

public class GetReleaseForAllocationAction extends Action{
	/**
	 * Creates a new instance of CustomQueryAction
	 */
	public GetReleaseForAllocationAction() {
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

		List releaseList = new ArrayList(1000);
		SQLOraRelDAO releaseListDAO = new SQLOraRelDAO();
		releaseList = releaseListDAO.listReleaseMethod();
		session.setAttribute("releaseList", releaseList);
		session.setAttribute("userAllocationCount", null);
		return mapping.findForward("success");
	}
}
