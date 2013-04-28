package com.base.service.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	public User() {

	}

	public String name = null;
	public int age = 0;
	@XmlElement(name = "subjectList")
	public SubjectList subjectList = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public SubjectList getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(SubjectList subjectList) {
		this.subjectList = subjectList;
	}

}
