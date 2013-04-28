
package com.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserHashMap", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserHashMap", namespace = "http://service.base.com/")
public class GetUserHashMap {

    @XmlElement(name = "UserRequest", namespace = "")
    private com.base.service.bean.UserRequest userRequest;

    /**
     * 
     * @return
     *     returns UserRequest
     */
    public com.base.service.bean.UserRequest getUserRequest() {
        return this.userRequest;
    }

    /**
     * 
     * @param userRequest
     *     the value for the userRequest property
     */
    public void setUserRequest(com.base.service.bean.UserRequest userRequest) {
        this.userRequest = userRequest;
    }

}
