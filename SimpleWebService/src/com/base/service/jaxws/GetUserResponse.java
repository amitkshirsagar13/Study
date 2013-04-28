
package com.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserResponse", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserResponse", namespace = "http://service.base.com/")
public class GetUserResponse {

    @XmlElement(name = "User", namespace = "")
    private com.base.service.bean.User user;

    /**
     * 
     * @return
     *     returns User
     */
    public com.base.service.bean.User getUser() {
        return this.user;
    }

    /**
     * 
     * @param user
     *     the value for the user property
     */
    public void setUser(com.base.service.bean.User user) {
        this.user = user;
    }

}
