package com.MileStoneReporterTool.ActionFiles;

import org.apache.struts.action.Action;
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

public class UserAction extends Action {
	/**
	 * Override the execute method of action super class
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("success");
	}
}
