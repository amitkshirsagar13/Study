package com.sample.service;

/**
 * Generated class, do not edit.
 *
 * This service interface was generated by weblogic
 * webservice stub gen on Thu Dec 04 14:12:27 CST 2014  
 */

public interface MyZooService_Service extends javax.xml.rpc.Service {

  weblogic.wsee.context.WebServiceContext context();

  weblogic.wsee.context.WebServiceContext joinContext()
       throws weblogic.wsee.context.ContextNotFoundException;
 
     

     //***********************************
     // Port Name: MyZooServicePort  
     // Port Type: MyZooService_PortType   
     //***********************************
     
  /**
   * returns MyZooServicePort port in this service 
   */
  com.sample.service.MyZooService_PortType getMyZooServicePort() throws javax.xml.rpc.ServiceException;

  /**
   * @deprecated Use getMyZooServicePort(byte[] username, byte[] password);
   */
  com.sample.service.MyZooService_PortType getMyZooServicePort(String username, String password) throws javax.xml.rpc.ServiceException;

  com.sample.service.MyZooService_PortType getMyZooServicePort(byte[] username, byte[] password) throws javax.xml.rpc.ServiceException;
  
    
  }