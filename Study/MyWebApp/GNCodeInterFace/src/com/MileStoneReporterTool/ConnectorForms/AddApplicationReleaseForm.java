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
import org.apache.struts.util.MessageResources;

/**
 *
 * @author AmitC_Kshirsagar
 */
public class AddApplicationReleaseForm  extends ActionForm{
    
    /**
     * Creates a new instance of CustomQueryForm
     */
    public AddApplicationReleaseForm() {
    }
    	private String applicationId = "";
        private String pointOfContact = "";
        private String releaseDate = "";
        
	/**
	* Method to reset the attributes to default values
	* @param ActionMapping
	* @param HttpServletRequest
	*/

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		}


	/**
	 * @return applicationName[]
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationId() {
		return applicationId;
	}
        
	/**
	 * @return owner[]
	 */
	public void setPointOfContact(String ponitOfContact) {
		this.pointOfContact = ponitOfContact;
	}
	public String getPointOfContact() {
		return pointOfContact;
	}

	/**
	 * @return owner[]
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReleaseDate() {
		return releaseDate;
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
				defaultBundle.getMessage("addRelease." + fieldName);

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
		ActionErrors errors =super.validate(mapping, request);
		
		if (errors==null) errors = new ActionErrors();
		addErrorIfBlank(errors, applicationId, "applicationId", request);
		addErrorIfBlank(errors, releaseDate, "releaseDate", request);
		addErrorIfBlank(errors, pointOfContact, "pointOfContact", request);


		return errors;
	}
}