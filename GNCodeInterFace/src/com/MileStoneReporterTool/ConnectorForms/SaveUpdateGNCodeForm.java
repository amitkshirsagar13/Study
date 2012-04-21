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
public class SaveUpdateGNCodeForm  extends ActionForm{
    
    /**
     * Creates a new instance of CustomQueryForm
     */
    public SaveUpdateGNCodeForm() {
    }
    private int reset = -1;
	public int getReset() {
		return reset;
	}


	public void setReset(int reset) {
		this.reset = reset;
	}
	
	private String state_name = "";
	private String city = "";
	private String court = "";

	private String gncode_id = "";
	private String gncode_value = "";
	private String state = "";
	private String court_type = "";
	private String system = "";
	private String subject_matter = "";
	private String location = "";
	private String court_level = "";
	private String court_long_name = "";
	private String court_short_name = "";
	
	private int id = -1;
	private String court_name = null;
	private String normalized_court = null;
    
	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String getCounty1() {
		return county1;
	}


	public void setCounty1(String county1) {
		this.county1 = county1;
	}


	public String getParish() {
		return parish;
	}


	public void setParish(String parish) {
		this.parish = parish;
	}


	public String getPublication() {
		return publication;
	}


	public void setPublication(String publication) {
		this.publication = publication;
	}
	private String county = "";
	private String county1 = "";
	private String parish = "";
	private String publication = "";
	
	public String getGncode_id() {
		if (gncode_id.equals("")){
			gncode_id = "0";
		}
		return gncode_id;
	}


	public void setGncode_id(String gncodeId) {
		gncode_id = gncodeId;
	}


	public String getGncode_value() {
		return gncode_value;
	}


	public void setGncode_value(String gncodeValue) {
		gncode_value = gncodeValue;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCourt_type() {
		return court_type;
	}


	public void setCourt_type(String courtType) {
		court_type = courtType;
	}


	public String getSystem() {
		return system;
	}


	public void setSystem(String system) {
		this.system = system;
	}


	public String getSubject_matter() {
		return subject_matter;
	}


	public void setSubject_matter(String subjectMatter) {
		subject_matter = subjectMatter;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCourt_level() {
		return court_level;
	}


	public void setCourt_level(String courtLevel) {
		court_level = courtLevel;
	}


	public String getCourt_long_name() {
		return court_long_name;
	}


	public void setCourt_long_name(String courtLongName) {
		court_long_name = courtLongName;
	}


	public String getCourt_short_name() {
		return court_short_name;
	}


	public void setCourt_short_name(String courtShortName) {
		court_short_name = courtShortName;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCourt_name() {
		return court_name;
	}


	public void setCourt_name(String courtName) {
		court_name = courtName;
	}


	public String getNormalized_court() {
		return normalized_court;
	}


	public void setNormalized_court(String normalizedCourt) {
		normalized_court = normalizedCourt;
	}

	public String getState_name() {
		return state_name;
	}


	public void setState_name(String stateName) {
		state_name = stateName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String cityName) {
		city = cityName;
	}


	public String getCourt() {
		return court;
	}


	public void setCourt(String countyName) {
		court = countyName;
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
		return errors;
	}
}