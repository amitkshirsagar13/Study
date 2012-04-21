package com.MileStoneReporterTool.DataBeans;

public class GNCodeJVData {
	private int id = -1;
	private String state_name = null;
	private String city_name = null;
	private String county_name = null;
	private String normalized_name = null;
	
	
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String stateName) {
		state_name = stateName;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String cityName) {
		city_name = cityName;
	}
	public String getCounty_name() {
		return county_name;
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
