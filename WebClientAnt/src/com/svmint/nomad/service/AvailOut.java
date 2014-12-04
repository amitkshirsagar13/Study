/**
 * Generated from schema type t=AvailOut@http://service.nomad.svmint.com
 */
package com.svmint.nomad.service;

public class AvailOut implements java.io.Serializable {

  private com.svmint.nomad.service.AvailList[] availList;

  public com.svmint.nomad.service.AvailList[] getAvailList() {
    return this.availList;
  }

  public void setAvailList(com.svmint.nomad.service.AvailList[] availList) {
    this.availList = availList;
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

  private java.lang.String searchLat;

  public java.lang.String getSearchLat() {
    return this.searchLat;
  }

  public void setSearchLat(java.lang.String searchLat) {
    this.searchLat = searchLat;
  }

  private java.lang.String searchLong;

  public java.lang.String getSearchLong() {
    return this.searchLong;
  }

  public void setSearchLong(java.lang.String searchLong) {
    this.searchLong = searchLong;
  }

  private int svcLocAdrSID;

  public int getSvcLocAdrSID() {
    return this.svcLocAdrSID;
  }

  public void setSvcLocAdrSID(int svcLocAdrSID) {
    this.svcLocAdrSID = svcLocAdrSID;
  }

  private int svcLocPtyId;

  public int getSvcLocPtyId() {
    return this.svcLocPtyId;
  }

  public void setSvcLocPtyId(int svcLocPtyId) {
    this.svcLocPtyId = svcLocPtyId;
  }

}
