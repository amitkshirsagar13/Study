/*
 * CustomQueryAction.java
 *
 * Created on November 21, 2007, 5:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

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

import com.MileStoneReporterTool.DataBeans.GNCodeMappingInfoData;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraGNCodeDAO;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class GetGNCodeInfoAction extends Action{
    
    /**
     * Creates a new instance of CustomQueryAction
     */
    public GetGNCodeInfoAction() {
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

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		/*
		 * Set the Session veriables for Application and release
		 */
		String qName = request.getParameter("qName");
		String qSearchTerm = request.getParameter("qSearchTerm");
		String qforTable = request.getParameter("forTable");
		
		GNCodeMappingInfoData liveData = null;
		SQLOraGNCodeDAO userListDAO = new SQLOraGNCodeDAO();
		System.out.println(qSearchTerm);
		if (qSearchTerm.equals("GNCODEINFO")){
			liveData = userListDAO.getGNCodeInfoFromNormalizedNameMethod(qName,qforTable);
		}else if (qSearchTerm.equals("GNCODEID")){
			liveData = userListDAO.getGNCodeInfoFromGNCodeIDMethod(qName);
		}else if (qSearchTerm.equals("GNCODEVALUE")){
			liveData = userListDAO.getGNCodeInfoFromGNCodeValueMethod(qName);
		}

		if (liveData == null){
			liveData = new GNCodeMappingInfoData();
			liveData.setJsonObject();
		}
		session.setAttribute("gnCodeData", liveData);
		return mapping.findForward("success");
	}
}