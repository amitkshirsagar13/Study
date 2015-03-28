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
public class AddUserDetailsAction extends Action{
    
    /**
     * Creates a new instance of CustomQueryAction
     */
    public AddUserDetailsAction() {
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
                        
                        String userID = "";
                        String passWord = "";
                        String role = "";

                        int insertedUser = 2;
                        AddUserDetailsForm addUserDetailsForm = (AddUserDetailsForm) form;
                        //System.out.println("ADD DATA ACTION 1" + addUserDetailsForm);
                        try{
                        userID = addUserDetailsForm.getUserID();
                        passWord = "Infosys";
                        role = addUserDetailsForm.getRole();
                        //System.out.println("ADD DATA ACTION 2");
                        SQLOraUserDAO userDAO = new SQLOraUserDAO();
                        insertedUser = userDAO.addUserDetailsMethod(userID,passWord,role);
                        session.setAttribute("userID",userID);
                        session.setAttribute("role",role);
                        //System.out.println("END ADD DATA ACTION");
                        }catch(Exception E){
                        System.out.println("END ADD DATA ACTION Exception "+ E);
                        }
                        if(insertedUser==0||insertedUser==2){
                            session.setAttribute("insertedUser","0");
                            return mapping.findForward("failure");
                        }else{
                            session.setAttribute("insertedUser","1");
                            return mapping.findForward("success");
                        }
	}
}
