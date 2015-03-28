/*
 * AddUserDetailsForm.java
 *
 * Created on November 23, 2007, 9:33 PM
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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorForm;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class AddUserDetailsForm  extends ActionForm{
    
    /**
     * Creates a new instance of CustomQueryForm
     */
    public AddUserDetailsForm() {
    }
    	private String userID = "";
        private String passWord = "";
        private String role = "";

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
	 * @return role[]
	 */
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
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
		 * This method is called when we extends the ActionForms
		 * Method to validate the attributes
		 * @param ActionMapping
		 * @param HttpServletRequest
		 */
			public ActionErrors validate(
				ActionMapping mapping,
				HttpServletRequest request) {
				
				ActionErrors errors =super.validate(mapping, request);
				
				if (errors==null) errors = new ActionErrors();
				
				addErrorIfBlank(errors, userID, "userID", request);
				addErrorIfBlank(errors, role, "role", request);
				
				return errors;
			}
}