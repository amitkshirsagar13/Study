
package com.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getMessageHashMapResponse", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageHashMapResponse", namespace = "http://service.base.com/")
public class GetMessageHashMapResponse {

    @XmlElement(name = "messageHashMap", namespace = "")
    private com.base.service.bean.User messageHashMap;

    /**
     * 
     * @return
     *     returns User
     */
    public com.base.service.bean.User getMessageHashMap() {
        return this.messageHashMap;
    }

    /**
     * 
     * @param messageHashMap
     *     the value for the messageHashMap property
     */
    public void setMessageHashMap(com.base.service.bean.User messageHashMap) {
        this.messageHashMap = messageHashMap;
    }

}
