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
public class UserData implements Serializable {
    
    /** Creates a new instance of UserData */
    public UserData() {
    }
    
    private String userID;
	private String userName;
	private String role;
	private String frmDate;
	private String toDate;
	private String poc;
	private String allocationId;
	private String releaseId;
	private String releaseName;
	private String applicationName;

	/*Emp Id*/

	public String getAllocationId() {
		return allocationId;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}
	public String getPoc() {
		return poc;
	}
	public void setPoc(String poc) {
		this.poc = poc;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}
	public String getReleaseName() {
		return releaseName;
	}
	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	/*Emp Name*/

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
                this.userName = userName;
	}

	//RoleId

        public String getRole() {
                return role;
        }
        public void setRole(String role) {
                this.role = role;
	}


	//FrmDate

	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String string) {
			frmDate = string;
	}

	//toDate

	public String getToDate() {
		return toDate;
	}
	public void setToDate(String string) {
			toDate = string;
	}
    
    
}
