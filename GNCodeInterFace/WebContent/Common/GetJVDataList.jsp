<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/myCustom-tags.tld" prefix="customTags" %>
    

<%@page import="java.util.List"%>
<%@page import="com.MileStoneReporterTool.DataBeans.GNCodeJVData"%>
<c:set var="i" value="${0}" />
<%
	List liveDataList = (List)session.getAttribute("liveDataList");
	String stateNames[] = new String[liveDataList.size()];
	String cityNames[] = new String[liveDataList.size()];
	String countyNames[] = new String[liveDataList.size()];
	for(int i=0;i<liveDataList.size();i++){
		stateNames[i] = ((GNCodeJVData)liveDataList.get(i)).getState_name();
		if (stateNames[i]!=null){
			stateNames[i] = stateNames[i].replace("\\","");
		}
		cityNames[i] = ((GNCodeJVData)liveDataList.get(i)).getCity_name();
		if (cityNames[i]!=null){
			cityNames[i] = cityNames[i].replace("\\","");
		}		
		countyNames[i] = ((GNCodeJVData)liveDataList.get(i)).getCounty_name();
		if (countyNames[i]!=null){
			countyNames[i] = countyNames[i].replace("\\","");
		}		
	}
	int i=0;
%>

<c:if test="${fn:length(liveDataList) > 0}">
	<c:forEach var="liveDataList" items="${liveDataList}">		
		<c:if test="${'state_name' eq textBoxName}">
		<a href="#" onclick="updateJVDataBox('${textBoxName}','${liveDataList.state_name}','${liveDataList.normalized_name}')" >
		<%= stateNames[i]%></a><br>
		</c:if>
		<c:if test="${'city' eq textBoxName}">
		<a href="#" onclick="updateJVDataBox('${textBoxName}','${liveDataList.city_name}','${liveDataList.normalized_name}')" >
		<%= cityNames[i]%></a><br>
		</c:if>
		<c:if test="${'court' eq textBoxName}">
		<a href="#" onclick="updateJVDataBox('${textBoxName}','${liveDataList.county_name}','${liveDataList.normalized_name}')" >
		<%= countyNames[i]%></a><br>
		</c:if>
		<%
			i = i + 1;
		%>
		<c:set var="i" value="${i+1}"/>	
	</c:forEach>
</c:if>
<c:if test="${i == 0}">
	Look's Like you have entered single '. Please make it two '.
</c:if>