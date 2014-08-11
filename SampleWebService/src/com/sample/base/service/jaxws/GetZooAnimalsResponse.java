
package com.sample.base.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getZooAnimalsResponse", namespace = "http://service.base.sample.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getZooAnimalsResponse", namespace = "http://service.base.sample.com/")
public class GetZooAnimalsResponse {

    @XmlElement(name = "ZooAnimalStore", namespace = "")
    private com.sample.base.Zoo zooAnimalStore;

    /**
     * 
     * @return
     *     returns Zoo
     */
    public com.sample.base.Zoo getZooAnimalStore() {
        return this.zooAnimalStore;
    }

    /**
     * 
     * @param zooAnimalStore
     *     the value for the zooAnimalStore property
     */
    public void setZooAnimalStore(com.sample.base.Zoo zooAnimalStore) {
        this.zooAnimalStore = zooAnimalStore;
    }

}
