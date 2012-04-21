package com.MileStoneReporterTool.DataBeans;

public class GNCodeCourtData {
	private int id = -1;
	private String court_name = null;
	private String normalized_court = null;
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

}
