
package com.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserRequest", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserRequest", namespace = "http://service.base.com/")
public class GetUserRequest {

    @XmlElement(name = "UserRequest", namespace = "")
    private com.base.service.bean.User userRequest;

    /**
     * 
     * @return
     *     returns User
     */
    public com.base.service.bean.User getUserRequest() {
        return this.userRequest;
    }

    /**
     * 
     * @param userRequest
     *     the value for the userRequest property
     */
    public void setUserRequest(com.base.service.bean.User userRequest) {
        this.userRequest = userRequest;
    }

}
