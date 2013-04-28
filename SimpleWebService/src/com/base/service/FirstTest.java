package com.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.base.service.bean.SubjectList;
import com.base.service.bean.User;
import com.base.service.bean.UserRequest;

@WebService(name = "FirstTest", serviceName = "FirstTest")
public class FirstTest {

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
}
