package com.MileStoneReporterTool.DataBeans;

public class GNCodeMappingInfoData {
	private int gncode_id = -1;
	private String gncode_value = "";
	private String state = "";
	private String court_type = "";
	private String system = "";
	private String subject_matter = "";
	private String location = "";
	private String court_level = "";
	private String court_long_name = "";
	private String court_short_name = "";
	private String jsonObject = "";
	public int getGncode_id() {
		return gncode_id;
	}
	public void setGncode_id(int gncodeId) {
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
	
	public void setJsonObject(){
		StringBuffer xmlDoc = new StringBuffer();
		xmlDoc.append("{");
		xmlDoc.append("'gncode_id':'"+gncode_id+"',");
		xmlDoc.append("'gncode_value':'"+gncode_value+"',");
		xmlDoc.append("'state':'"+state+"',");
		xmlDoc.append("'court_type':'"+court_type+"',");
		xmlDoc.append("'system':'"+system+"',");
		xmlDoc.append("'subject_matter':'"+subject_matter+"',");
		xmlDoc.append("'location':'"+location+"',");
		xmlDoc.append("'court_level':'"+court_level+"',");
		xmlDoc.append("'court_long_name':'"+court_long_name+"',");
		xmlDoc.append("'court_short_name':'"+court_short_name+"'");
		xmlDoc.append("}");		
		jsonObject = xmlDoc.toString();		
	}
	public String getJsonObject(){
		return jsonObject;
	}
}
