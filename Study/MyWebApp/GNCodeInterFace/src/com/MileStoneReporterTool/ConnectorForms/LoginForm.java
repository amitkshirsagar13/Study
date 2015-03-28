/*
 * CustomQueryForm.java
 *
 * Created on November 21, 2007, 5:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.ConnectorForms;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class LoginForm  extends ActionForm{
    
    /**
     * Creates a new instance of CustomQueryForm
     */
    public LoginForm() {
    }
    	private String userID = "";
        private String passWord = "";

	/**
	* Method to reset the attributes to default values
	* @param ActionMapping
	* @param HttpServletRequest
	*/

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		}


	/**
	 * @return UserID[]
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserID() {
		return userID;
	}
        
	/**
	 * @return UserID[]
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPassWord() {
		return passWord;
	}


	private void addErrorIfBlank(
		ActionErrors errors,
		String fieldValue,
		String fieldName,
		HttpServletRequest request) {

		if (fieldValue == null || fieldValue.trim().equals("")) {
			MessageResources defaultBundle =
				(MessageResources) request.getAttribute(Globals.MESSAGES_KEY);


			String label =
				defaultBundle.getMessage("userRegistration." + fieldName);

			errors.add(fieldName, new ActionError("errors.blank", label));

		}

	}

	/**
	* Method to validate the attributes
	* @param ActionMapping
	* @param HttpServletRequest
	*/
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();



		return errors;
	}
}

