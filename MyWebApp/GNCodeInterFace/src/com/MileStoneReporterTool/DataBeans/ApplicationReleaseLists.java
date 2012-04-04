/*
 * ApplicationReleaseLists.java
 *
 * Created on November 29, 2007, 2:53 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.MileStoneReporterTool.DataBeans;

import java.io.Serializable;
/**
 *
 * @author AmitC_Kshirsagar
 */
public class ApplicationReleaseLists implements Serializable{
    
    /** Creates a new instance of ApplicationReleaseLists */
    public ApplicationReleaseLists() {
    }
    	private String applicationId;
	private String applicationName;
        private String owner;
	private String releaseId;
        private String releaseDate;
        private String applRelId;
        private String pOC;


	/*applicationId*/

	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
                this.applicationId = applicationId;
	}


	/*applicationName*/

	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

        /*owner*/

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
        
	/*releaseId*/
	public String getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(String releaseId) {
                this.releaseId = releaseId;
	}


	/*releaseDate*/

	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
        
        /*pOC*/

	public String getPOC() {
		return pOC;
	}
	public void setPOC(String pOC) {
		this.pOC = pOC;
	}
        
	/*applRelId*/

	public String getApplRelId() {
		return applRelId;
	}
	public void setApplRelId(String applRelId) {
                this.applRelId = applRelId;
	}
        
}
