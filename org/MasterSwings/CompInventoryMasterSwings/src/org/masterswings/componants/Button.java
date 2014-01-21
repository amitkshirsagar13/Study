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
 * <p>Java class for Button complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Button">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buttonToolTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="buttonName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="buttonAction" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="buttonImage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="buttonXPos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="buttonYPos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="buttonDisabled" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="buttonListeners" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Button", propOrder = {
    "buttonToolTip"
})
public class Button {

    protected String buttonToolTip;
    @XmlAttribute(required = true)
    protected String buttonName;
    @XmlAttribute(required = true)
    protected String buttonAction;
    @XmlAttribute
    protected String buttonImage;
    @XmlAttribute
    protected String buttonXPos;
    @XmlAttribute
    protected String buttonYPos;
    @XmlAttribute
    protected String buttonDisabled;
    @XmlAttribute
    protected String buttonListeners;

    /**
     * Gets the value of the buttonToolTip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonToolTip() {
        return buttonToolTip;
    }

    /**
     * Sets the value of the buttonToolTip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonToolTip(String value) {
        this.buttonToolTip = value;
    }

    /**
     * Gets the value of the buttonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * Sets the value of the buttonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonName(String value) {
        this.buttonName = value;
    }

    /**
     * Gets the value of the buttonAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonAction() {
        return buttonAction;
    }

    /**
     * Sets the value of the buttonAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonAction(String value) {
        this.buttonAction = value;
    }

    /**
     * Gets the value of the buttonImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonImage() {
        return buttonImage;
    }

    /**
     * Sets the value of the buttonImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonImage(String value) {
        this.buttonImage = value;
    }

    /**
     * Gets the value of the buttonXPos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonXPos() {
        return buttonXPos;
    }

    /**
     * Sets the value of the buttonXPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonXPos(String value) {
        this.buttonXPos = value;
    }

    /**
     * Gets the value of the buttonYPos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonYPos() {
        return buttonYPos;
    }

    /**
     * Sets the value of the buttonYPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonYPos(String value) {
        this.buttonYPos = value;
    }

    /**
     * Gets the value of the buttonDisabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonDisabled() {
        return buttonDisabled;
    }

    /**
     * Sets the value of the buttonDisabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonDisabled(String value) {
        this.buttonDisabled = value;
    }

    /**
     * Gets the value of the buttonListeners property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getButtonListeners() {
        return buttonListeners;
    }

    /**
     * Sets the value of the buttonListeners property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setButtonListeners(String value) {
        this.buttonListeners = value;
    }

}
