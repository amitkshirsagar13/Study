
package com.base.service.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getMessageListResponse", namespace = "http://service.base.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getMessageListResponse", namespace = "http://service.base.com/")
public class GetMessageListResponse {

    @XmlElement(name = "messageList", namespace = "")
    private List<String> messageList;

    /**
     * 
     * @return
     *     returns List<String>
     */
    public List<String> getMessageList() {
        return this.messageList;
    }

    /**
     * 
     * @param messageList
     *     the value for the messageList property
     */
    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

}
