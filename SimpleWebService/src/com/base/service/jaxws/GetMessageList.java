
package com.base.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getMessageList", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageList", namespace = "http://service.base.com/")
public class GetMessageList {

    @XmlElement(name = "name", namespace = "")
    private List<String> name;

    /**
     * 
     * @return
     *     returns List<String>
     */
    public List<String> getName() {
        return this.name;
    }

    /**
     * 
     * @param name
     *     the value for the name property
     */
    public void setName(List<String> name) {
        this.name = name;
    }

}
