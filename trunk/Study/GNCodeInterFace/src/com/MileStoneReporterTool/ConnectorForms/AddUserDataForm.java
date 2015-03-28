package com.MileStoneReporterTool.ConnectorForms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.ValidatorForm;

import org.apache.struts.Globals;

public class AddUserDataForm extends ValidatorForm {
	private String applId;
	private String relId;
	public HttpSession session;

	/**
	 * Reset method
	 * 
	 * @param ActionMapping,HttpServletRequest
	 * @return
	 */

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		applId = "";
		relId = "";

	}

	/**
	 * getApplication() method returns the Application Id selected by user from
	 * 'application' dropdown
	 * 
	 * @return String appl
	 */

	public String getApplication() {
		return applId;
	}

	public void setApplication(String applId) {
		this.applId = applId;
	}

	/**
	 * getApplication() method returns the Application Id selected by user from
	 * 'application' dropdown
	 * 
	 * @return String appl
	 */

	public String getRelease() {
		return relId;
	}

	public void setRelease(String relId) {

		this.relId = relId;
	}

	private void addErrorIfBlank(ActionErrors errors, String fieldValue,
			String fieldName, HttpServletRequest request) {

		System.out.println("In addErrorsIfBlank " + fieldName + " "
				+ fieldValue);

		if (fieldValue == null || fieldValue.trim().equals("0")
				|| fieldValue.trim().equals("")) {
			MessageResources defaultBundle = (MessageResources) request
					.getAttribute(Globals.MESSAGES_KEY);

			String label = defaultBundle.getMessage("login." + fieldName);
			errors.add(fieldName, new ActionError("errors.blank", label));
		}

	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		addErrorIfBlank(errors, applId, "application", request);
		addErrorIfBlank(errors, relId, "release", request);
		return errors;
	}
}
