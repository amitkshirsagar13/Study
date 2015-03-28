//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.10 at 11:48:58 AM EDT 
//


package com.base.service.zoo.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Zoo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Zoo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="animalCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="animalStore" type="{}AnimalStore"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zoo", propOrder = {
    "clientName",
    "animalCount",
    "animalStore"
})
public class Zoo {

    @XmlElement(required = true)
    protected String clientName;
    protected int animalCount;
    @XmlElement(required = true)
    protected AnimalStore animalStore;

    /**
     * Gets the value of the clientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Sets the value of the clientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientName(String value) {
        this.clientName = value;
    }

    /**
     * Gets the value of the animalCount property.
     * 
     */
    public int getAnimalCount() {
        return animalCount;
    }

    /**
     * Sets the value of the animalCount property.
     * 
     */
    public void setAnimalCount(int value) {
        this.animalCount = value;
    }

    /**
     * Gets the value of the animalStore property.
     * 
     * @return
     *     possible object is
     *     {@link AnimalStore }
     *     
     */
    public AnimalStore getAnimalStore() {
        return animalStore;
    }

    /**
     * Sets the value of the animalStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnimalStore }
     *     
     */
    public void setAnimalStore(AnimalStore value) {
        this.animalStore = value;
    }

}
