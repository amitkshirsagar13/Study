/**
 * Generated from schema type t=EmailOut@http://service.nomad.svmint.com
 */
package com.svmint.nomad.service;

public class EmailOut implements java.io.Serializable {

  private com.svmint.nomad.service.Email[] emailList;

  public com.svmint.nomad.service.Email[] getEmailList() {
    return this.emailList;
  }

  public void setEmailList(com.svmint.nomad.service.Email[] emailList) {
    this.emailList = emailList;
  }

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

}
