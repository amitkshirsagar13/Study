package com.MileStoneReporterTool.DataBeans;

public class GNCodeLegacyJVData {
	private int id = -1;
	private String state_name = null;
	private String court_name = null;
	private String county_name = null;
	private String county1_name = null;
	private String parish_name = null;
	private String publication_name = null;
	private String normalized_name = null;
	
	
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String stateName) {
		state_name = stateName;
	}
	public String getCounty_name() {
		return county_name;
	}
	public String getCourt_name() {
		return court_name;
	}
	public void setCourt_name(String courtName) {
		court_name = courtName;
	}
	public String getCounty1_name() {
		return county1_name;
	}
	public void setCounty1_name(String county1Name) {
		county1_name = county1Name;
	}
	public String getParish_name() {
		return parish_name;
	}
	public void setParish_name(String parishName) {
		parish_name = parishName;
	}
	public String getPublication_name() {
		return publication_name;
	}
	public void setPublication_name(String publicationName) {
		publication_name = publicationName;
	}
	public void setCounty_name(String countyName) {
		county_name = countyName;
	}
	public String getNormalized_name() {
		return normalized_name;
	}
	public void setNormalized_name(String normalizedName) {
		normalized_name = normalizedName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
