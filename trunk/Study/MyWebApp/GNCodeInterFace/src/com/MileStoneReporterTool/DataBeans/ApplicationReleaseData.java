/*
 * UserData.java
 *
 * Created on November 23, 2007, 12:18 AM
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
public class ApplicationReleaseData  implements Serializable {
    
    /** Creates a new instance of UserData */
    public ApplicationReleaseData(){
    }
    
    private String applicationId;
	private String applicationName;
	private String releaseId;
	private String releaseName;
	private String applicationReleaseId;
	private String applicationReleasePOC;


	/*Emp Id*/

	public String getApplicationID() {
		return applicationId;
	}
	public void setApplicationID(String applicationId) {
		this.applicationId = applicationId;
	}


	/*applicationName*/

	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
                this.applicationName = applicationName;
	}

	//releaseId

	public String getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(String releaseId) {
	this.releaseId = releaseId;
	}

	//RoleId

        public String getReleaseName() {
                return releaseName;
        }
        public void setReleaseName(String releaseName) {
                this.releaseName = releaseName;
	}


	//FrmDate

	public String getApplicationReleaseId() {
		return applicationReleaseId;
	}
	public void setApplicationReleaseId(String applicationReleaseId) {
                this.applicationReleaseId = applicationReleaseId;
	}

	//toDate

	public String getApplicationReleasePOC() {
		return applicationReleasePOC;
	}
	public void setApplicationReleasePOC(String applicationReleasePOC) {
		this.applicationReleasePOC = applicationReleasePOC;
	}
    
    
}
