/**
 * Generated from schema type t=Zoo@http://service.base.com/
 */
package com.base.service;

public class Zoo implements java.io.Serializable {

  private java.lang.String clientName;

  public java.lang.String getClientName() {
    return this.clientName;
  }

  public void setClientName(java.lang.String clientName) {
    this.clientName = clientName;
  }

  private int animalCount;

  public int getAnimalCount() {
    return this.animalCount;
  }

  public void setAnimalCount(int animalCount) {
    this.animalCount = animalCount;
  }

  private com.base.service.AnimalType[] animalStore;

  public com.base.service.AnimalType[] getAnimalStore() {
    return this.animalStore;
  }

  public void setAnimalStore(com.base.service.AnimalType[] animalStore) {
    this.animalStore = animalStore;
  }

}
