package com.sample.hibernate.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "listEmployee", namespace = "http://service.hibernate.sample.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listEmployee", namespace = "http://service.hibernate.sample.com/")
public class ListEmployee {

}
