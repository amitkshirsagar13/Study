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

import com.MileStoneReporterTool.ConnectorForms.AddApplicationReleaseForm;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraRelDAO;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.ReportGenerator.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraUserDAO;
import com.MileStoneReporterTool.DataBeans.UserData;
import com.MileStoneReporterTool.ReportGenerator.DateFormator;
/**
 *
 * @author AmitC_Kshirsagar
 */
public class AddApplicationReleaseAction extends Action{
    
    /**
     * Creates a new instance of CustomQueryAction
     */
    public AddApplicationReleaseAction() {
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
			HttpSession session=request.getSession();
                        
                        String applicationId = "";
                        String pOC = "";
                        String releaseDate = "";

                        int insertedApplicationRelease = 2;
                        AddApplicationReleaseForm addApplicationReleaseForm = (AddApplicationReleaseForm) form;
                        System.out.println("addApplicationReleaseForm"+addApplicationReleaseForm.getApplicationId()+" "+addApplicationReleaseForm.getReleaseDate()+" "+addApplicationReleaseForm.getPointOfContact());
                        applicationId = addApplicationReleaseForm.getApplicationId();
                        pOC = addApplicationReleaseForm.getPointOfContact();
                        releaseDate = addApplicationReleaseForm.getReleaseDate();
                        DateFormator dateFormator = new DateFormator();
                        
                        String formattedDate = dateFormator.getDateFormatted(releaseDate);                       
                        
                        try{
                        SQLOraRelDAO releaseDAO = new SQLOraRelDAO();
                        insertedApplicationRelease = releaseDAO.addApplicationReleaseMethod(applicationId,pOC,formattedDate);
                        }catch(Exception E){
                        System.out.println("Add Application Exception "+ E);
                        insertedApplicationRelease = 0;
                        }
                        if(insertedApplicationRelease==0){
                            session.setAttribute("insertedApplicationRelease","0");
                            System.out.print("insertedApplicationRelease"+insertedApplicationRelease);
                            return mapping.findForward("failure");
                        }else{
                            session.setAttribute("insertedApplicationRelease","1");
                            System.out.print("insertedApplicationRelease"+insertedApplicationRelease);
                            return mapping.findForward("success");
                        }
	}
}
