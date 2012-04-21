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

import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraGNCodeDAO;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class GetLiveDataAction extends Action{
    
    /**
     * Creates a new instance of CustomQueryAction
     */
    public GetLiveDataAction() {
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
		String qTable = request.getParameter("qTable");
		String qName = request.getParameter("qName");
		String qNameValue = request.getParameter("qNameValue");
		System.out.println(qName+":"+qTable+":"+qNameValue);
		List liveData=new ArrayList(1000);
		SQLOraGNCodeDAO dataListDAO = new SQLOraGNCodeDAO();
		if (qTable.equals("GNCODE_CASELAW_LOOKUP")){
			liveData = dataListDAO.listCourtNamesMethod(qName,qNameValue);
			//System.out.println(liveData.size());
	        session.setAttribute("textBoxName", qName);
			session.setAttribute("liveDataList", liveData);
			return mapping.findForward("courtDataList");
		}else if (qTable.equals("GNCODE_JV_LOOKUP")){
			String qName1 = request.getParameter("qName1");
			String qNameValue1 = request.getParameter("qNameValue1");
			String qName2 = request.getParameter("qName2");
			String qNameValue2 = request.getParameter("qNameValue2");
			String qName3 = request.getParameter("qName3");
			String qNameValue3 = request.getParameter("qNameValue3");			
			liveData = dataListDAO.listJVMethod(qName1, qNameValue1,
					qName2, qNameValue2,
					qName3, qNameValue3);
			//System.out.println(liveData.size());
			session.setAttribute("textBoxName", qName);
			session.setAttribute("liveDataList", liveData);
			return mapping.findForward("jvDataList");
		}else if (qTable.equals("GNCODE_LEGACY_JV_LOOKUP")){
			String qName1 = request.getParameter("qName1");
			String qNameValue1 = request.getParameter("qNameValue1");
			String qName2 = request.getParameter("qName2");
			String qNameValue2 = request.getParameter("qNameValue2");
			String qName3 = request.getParameter("qName3");
			String qNameValue3 = request.getParameter("qNameValue3");
			String qName4 = request.getParameter("qName4");
			String qNameValue4 = request.getParameter("qNameValue4");
			String qName5 = request.getParameter("qName5");
			String qNameValue5 = request.getParameter("qNameValue5");
			String qName6 = request.getParameter("qName6");
			String qNameValue6 = request.getParameter("qNameValue6");	
			liveData = dataListDAO.listLegacyJVMethod(qName1, qNameValue1,
					qName2, qNameValue2,qName3, qNameValue3,
					qName4, qNameValue4,qName5, qNameValue5,qName6, qNameValue6);
			//System.out.println(liveData.size());
			session.setAttribute("textBoxName", qName);
			session.setAttribute("liveDataList", liveData);
			return mapping.findForward("legacyDataList");
		}else if (qTable.equals("gncode_mapping_info")){
			liveData = dataListDAO.listGNCodeMappingInfoDataMethod(qName, qNameValue);
			//System.out.println(liveData.size());
			session.setAttribute("textBoxName", qName);
			session.setAttribute("liveDataList", liveData);
			return mapping.findForward("liveDataList");
		}else{
			return mapping.findForward("liveDataList");
		}
        
	}
}