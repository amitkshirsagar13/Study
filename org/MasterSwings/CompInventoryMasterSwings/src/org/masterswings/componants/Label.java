//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.01.21 at 09:41:49 PM IST 
//


package org.masterswings.componants;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Label complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Label">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="labelText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="labelName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="labelAction" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="labelToolTip" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="labelXPos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="labelYPos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="labelListeners" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Label", propOrder = {
    "labelText"
})
public class Label {

    protected String labelText;
    @XmlAttribute(required = true)
    protected String labelName;
    @XmlAttribute(required = true)
    protected String labelAction;
    @XmlAttribute
    protected String labelToolTip;
    @XmlAttribute
    protected String labelXPos;
    @XmlAttribute
    protected String labelYPos;
    @XmlAttribute
    protected String labelListeners;

    /**
     * Gets the value of the labelText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelText() {
        return labelText;
    }

    /**
     * Sets the value of the labelText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelText(String value) {
        this.labelText = value;
    }

    /**
     * Gets the value of the labelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * Sets the value of the labelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelName(String value) {
        this.labelName = value;
    }

    /**
     * Gets the value of the labelAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelAction() {
        return labelAction;
    }

    /**
     * Sets the value of the labelAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelAction(String value) {
        this.labelAction = value;
    }

    /**
     * Gets the value of the labelToolTip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelToolTip() {
        return labelToolTip;
    }

    /**
     * Sets the value of the labelToolTip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelToolTip(String value) {
        this.labelToolTip = value;
    }

    /**
     * Gets the value of the labelXPos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelXPos() {
        return labelXPos;
    }

    /**
     * Sets the value of the labelXPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelXPos(String value) {
        this.labelXPos = value;
    }

    /**
     * Gets the value of the labelYPos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelYPos() {
        return labelYPos;
    }

    /**
     * Sets the value of the labelYPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelYPos(String value) {
        this.labelYPos = value;
    }

    /**
     * Gets the value of the labelListeners property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelListeners() {
        return labelListeners;
    }

    /**
     * Sets the value of the labelListeners property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelListeners(String value) {
        this.labelListeners = value;
    }

}
