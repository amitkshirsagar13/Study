package com.base.service;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import com.base.service.bean.SubjectList;
import com.base.service.bean.User;
import com.base.service.bean.UserRequest;
import com.base.service.person.bean.Person;
import com.base.service.person.bean.PersonList;
import com.base.service.person.bean.PersonStore;

@WebService(name = "FirstTest", serviceName = "FirstTest")
public class FirstTest {
	@Resource
	WebServiceContext ctx;

	@WebMethod(operationName = "getMessage", action = "getMessage")
	@WebResult(name = "message")
	public String getMessage(@WebParam(name = "name") String name) {
		return "Hi there... " + name;
	}

	@WebMethod(operationName = "getMessageList", action = "getMessageList")
	@WebResult(name = "messageList")
	public List<String> getMessageList(
			@WebParam(name = "name") List<String> nameList) {
		nameList.add("Poonam");
		return nameList;
	}

	@WebMethod(operationName = "getUser", action = "getUser")
	@WebResult(name = "User")
	public User getUser(@WebParam(name = "name") String name) {
		User user = new User();
		user.setName(name);
		user.setAge(29);
		List<String> subjects = new ArrayList<String>();
		subjects.add("Maths");
		subjects.add("Science");
		SubjectList subjectList = new SubjectList();
		subjectList.setSubjects(subjects);
		user.setSubjectList(subjectList);
		return user;
	}

	@WebMethod(operationName = "getUserHashMap", action = "getUserHashMap")
	@WebResult(name = "User")
	public User getUserHashMap(
			@WebParam(name = "UserRequest") UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getUserName());
		user.setAge(userRequest.getUserAge());
		List<String> subjects = new ArrayList<String>();
		subjects.add("Maths");
		subjects.add("Science");
		SubjectList subjectList = new SubjectList();
		subjectList.setSubjects(subjects);
		user.setSubjectList(subjectList);
		return user;
	}

	@WebMethod(operationName = "getUserRequest", action = "getUserRequest")
	@WebResult(name = "User")
	public User getUserRequest(@WebParam(name = "UserRequest") User userRequest) {
		List<String> subjects = new ArrayList<String>();
		subjects.add("Maths");
		subjects.add("Science");
		SubjectList subjectList = new SubjectList();
		subjectList.setSubjects(subjects);
		userRequest.setSubjectList(subjectList);
		return userRequest;
	}

	@WebMethod(operationName = "getPerson", action = "getPerson")
	@WebResult(name = "PersonStore")
	public PersonStore getPersonStore(
			@WebParam(name = "PersonStore") PersonStore personStore) {

		PersonList personList = new PersonList();
		Person person1 = new Person();
		person1.setId("1");
		person1.setName("Amit");
		person1.setValue("Power");
		person1.setAge("30");
		person1.setComment("Male");
		person1.setStatus("Married");
		personList.getPerson().add(person1);

		Person person2 = new Person();
		person2.setId("2");
		person2.setName("Poonam");
		person2.setValue("Protection");
		person2.setAge("30");
		person2.setComment("Female");
		person2.setStatus("Married");
		personList.getPerson().add(person2);

		Person person3 = new Person();
		person3.setId("3");
		person3.setName("Amogh");
		person3.setValue("Cute");
		person3.setAge("30");
		person3.setComment("Male");
		person3.setStatus("Child");
		personList.getPerson().add(person3);

		personStore.setPersonList(personList);

		personStore.setPersonCount(personList.getPerson().size());
		return personStore;
	}
}
