package com.MileStoneReporterTool.ConnectorForms;

import java.util.HashMap;

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

public class EditUsersForm extends ActionForm {

	private HashMap dynamicProps = new HashMap();

	public Object getDynamicProps(String key) {
		return dynamicProps.get(key);
	}

	public void setDynamicProps(String key, Object value) {
		dynamicProps.put(key, value);
	}
	/**
	* Method to reset the attributes to default values
	* @param ActionMapping
	* @param HttpServletRequest
	*/

	public void reset(ActionMapping mapping, HttpServletRequest request) {

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
