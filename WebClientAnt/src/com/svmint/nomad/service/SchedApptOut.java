/**
 * Generated from schema type t=SchedApptOut@http://service.nomad.svmint.com
 */
package com.svmint.nomad.service;

public class SchedApptOut implements java.io.Serializable {

  private com.svmint.nomad.service.Messages[] messages;

  public com.svmint.nomad.service.Messages[] getMessages() {
    return this.messages;
  }

  public void setMessages(com.svmint.nomad.service.Messages[] messages) {
    this.messages = messages;
  }

  private java.lang.String retCode;

  public java.lang.String getRetCode() {
    return this.retCode;
  }

  public void setRetCode(java.lang.String retCode) {
    this.retCode = retCode;
  }

  private com.svmint.nomad.service.SchedApptList[] schedApptList;

  public com.svmint.nomad.service.SchedApptList[] getSchedApptList() {
    return this.schedApptList;
  }

  public void setSchedApptList(com.svmint.nomad.service.SchedApptList[] schedApptList) {
    this.schedApptList = schedApptList;
  }

}
