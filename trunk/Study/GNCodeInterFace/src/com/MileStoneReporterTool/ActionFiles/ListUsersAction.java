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
import javax.servlet.jsp.PageContext;

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
import com.MileStoneReporterTool.DataBeans.UserData;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class ListUsersAction extends Action{
    
    /**
     * Creates a new instance of CustomQueryAction
     */
    public ListUsersAction() {
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
                /*
                 * Set the Session veriables for Application and release
                 */

                List userList=new ArrayList(1000);
                SQLOraUserDAO userListDAO = new SQLOraUserDAO();
                userList = userListDAO.listUsersMethod();
                session.setAttribute("userList", userList);
                
                session.setAttribute("userUpdateCount", null);
                return mapping.findForward("success");
	}
}