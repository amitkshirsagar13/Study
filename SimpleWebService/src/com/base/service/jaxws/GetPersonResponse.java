
package com.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getPersonResponse", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPersonResponse", namespace = "http://service.base.com/")
public class GetPersonResponse {

    @XmlElement(name = "Person", namespace = "")
    private com.base.service.person.bean.Person person;

    /**
     * 
     * @return
     *     returns Person
     */
    public com.base.service.person.bean.Person getPerson() {
        return this.person;
    }

    /**
     * 
     * @param person
     *     the value for the person property
     */
    public void setPerson(com.base.service.person.bean.Person person) {
        this.person = person;
    }

}
