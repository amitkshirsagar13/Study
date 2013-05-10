/**
 * 
 */
package com.base.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import com.base.service.person.bean.Person;
import com.base.service.person.bean.PersonList;
import com.base.service.person.bean.PersonStore;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 10, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */

@WebService(name = "FirstTest", serviceName = "FirstTest")
public class FirstTest {
	@Resource
	WebServiceContext ctx;

	@WebMethod(operationName = "getPersonStore", action = "getPersonStore")
	@WebResult(name = "PersonStore")
	public PersonStore getPersonStore(
			@WebParam(name = "PersonStore") PersonStore personStore) {
		PersonList personList = new PersonList();
		personStore.setPersonList(personList);

		Person person = new Person();
		person.setId("1");
		person.setName("Poonam");
		person.setAge("29");
		person.setComment("Wife");
		person.setValue("Woman");
		person.setStatus("Married");

		Person person1 = new Person();
		person1.setId("2");
		person1.setName("Amit");
		person1.setAge("29");
		person1.setComment("Husband");
		person1.setValue("Man");
		person1.setStatus("Married");

		Person person2 = new Person();
		person2.setId("3");
		person2.setName("Amogh");
		person2.setAge("3");
		person2.setComment("Son");
		person2.setValue("Man");
		person2.setStatus("Child");

		Person person3 = new Person();
		person3.setId("4");
		person3.setName("Pallavi");
		person3.setAge("27");
		person3.setComment("SIL");
		person3.setValue("Woman");
		person3.setStatus("Unmarried");

		personStore.getPersonList().getPerson().add(person);
		personStore.getPersonList().getPerson().add(person1);
		personStore.getPersonList().getPerson().add(person2);
		personStore.getPersonList().getPerson().add(person3);

		personStore.setPersonCount(personStore.getPersonList().getPerson()
				.size());
		personStore.setClientName("MyPC");
		return personStore;
	}

}
