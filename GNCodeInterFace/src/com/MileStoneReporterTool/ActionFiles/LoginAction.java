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

import com.MileStoneReporterTool.ConnectorForms.LoginForm;
import com.MileStoneReporterTool.SQLConnector.sqldao.GetDBConnection;
import com.MileStoneReporterTool.ReportGenerator.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.List;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraUserDAO;
import com.MileStoneReporterTool.SQLConnector.sqldao.SQLOraApplicationDAO;
import com.MileStoneReporterTool.DataBeans.Message;
import com.MileStoneReporterTool.DataBeans.UserData;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class LoginAction extends Action{
    
    /**
     * Creates a new instance of CustomQueryAction
     */
    public LoginAction() {
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
                        
                        LoginForm loginForm = (LoginForm) form;
                        userID = loginForm.getUserID();
                        passWord = loginForm.getPassWord();

                        SQLOraUserDAO userDAO = new SQLOraUserDAO();
                        UserData userData = userDAO.loginMethod(userID,passWord);
                        
                        session.setAttribute("userData", userData);
                        if(userData.getUserID()=="-1"){
                        	session.setAttribute("message", Message.message);
                        	Message.message=null;
                        	System.out.println(Message.message);
                            return mapping.findForward("failure");
                        }else{
                            /*
                             * Set the Session veriables for Application and release
                             */
                        	/*
	                        	SQLOraApplicationDAO applicationDAO = new SQLOraApplicationDAO();
	                            List applicationList=new ArrayList(100);
	                            applicationList=applicationDAO.listApplicationMethod();
	                            session.setAttribute("applicationList", applicationList);
	                            List userList=new ArrayList(1000);
	                            SQLOraUserDAO userListDAO = new SQLOraUserDAO();
	                            userList = userListDAO.listUsersMethod();
	                            session.setAttribute("userList", userList);
                            */
                            return mapping.findForward("success");
                        }

	}
}