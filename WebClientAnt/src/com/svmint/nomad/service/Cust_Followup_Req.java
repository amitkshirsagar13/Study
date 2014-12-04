/**
 * Generated from schema type t|e=cust_followup_req|d=cust_followup_req@http://service.nomad.svmint.com
 */
package com.svmint.nomad.service;

public class Cust_Followup_Req implements java.io.Serializable {

  private com.svmint.nomad.service.User logon;

  public com.svmint.nomad.service.User getLogon() {
    return this.logon;
  }

  public void setLogon(com.svmint.nomad.service.User logon) {
    this.logon = logon;
  }

  private com.svmint.nomad.service.Notes[] notes;

  public com.svmint.nomad.service.Notes[] getNotes() {
    return this.notes;
  }

  public void setNotes(com.svmint.nomad.service.Notes[] notes) {
    this.notes = notes;
  }

  private com.svmint.nomad.service.Cfr in;

  public com.svmint.nomad.service.Cfr getIn() {
    return this.in;
  }

  public void setIn(com.svmint.nomad.service.Cfr in) {
    this.in = in;
  }

}
