/**
 * Generated from schema type t=NotesOut@http://service.nomad.svmint.com
 */
package com.svmint.nomad.service;

public class NotesOut implements java.io.Serializable {

  private com.svmint.nomad.service.Messages[] messages;

  public com.svmint.nomad.service.Messages[] getMessages() {
    return this.messages;
  }

  public void setMessages(com.svmint.nomad.service.Messages[] messages) {
    this.messages = messages;
  }

  private com.svmint.nomad.service.Notes[] notes;

  public com.svmint.nomad.service.Notes[] getNotes() {
    return this.notes;
  }

  public void setNotes(com.svmint.nomad.service.Notes[] notes) {
    this.notes = notes;
  }

  private java.lang.String retCode;

  public java.lang.String getRetCode() {
    return this.retCode;
  }

  public void setRetCode(java.lang.String retCode) {
    this.retCode = retCode;
  }

}
