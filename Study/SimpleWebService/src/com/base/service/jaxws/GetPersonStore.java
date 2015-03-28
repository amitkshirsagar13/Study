
package com.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getPersonStore", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPersonStore", namespace = "http://service.base.com/")
public class GetPersonStore {

    @XmlElement(name = "PersonStore", namespace = "")
    private com.base.service.person.bean.PersonStore personStore;

    /**
     * 
     * @return
     *     returns PersonStore
     */
    public com.base.service.person.bean.PersonStore getPersonStore() {
        return this.personStore;
    }

    /**
     * 
     * @param personStore
     *     the value for the personStore property
     */
    public void setPersonStore(com.base.service.person.bean.PersonStore personStore) {
        this.personStore = personStore;
    }

}
