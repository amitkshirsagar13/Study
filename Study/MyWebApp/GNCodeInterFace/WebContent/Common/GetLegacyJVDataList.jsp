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
<%@page import="com.MileStoneReporterTool.DataBeans.GNCodeLegacyJVData"%><c:set var="i" value="${0}" />
<%
	List liveDataList = (List)session.getAttribute("liveDataList");
	String stateNames[] = new String[liveDataList.size()];
	String courtNames[] = new String[liveDataList.size()];
	String countyNames[] = new String[liveDataList.size()];
	String county1Names[] = new String[liveDataList.size()];
	String parishNames[] = new String[liveDataList.size()];
	String publicationNames[] = new String[liveDataList.size()];
	for(int i=0;i<liveDataList.size();i++){
		stateNames[i] = ((GNCodeLegacyJVData)liveDataList.get(i)).getState_name();
		if (stateNames[i]!=null){
			stateNames[i] = stateNames[i].replace("\\","");
		}
		courtNames[i] = ((GNCodeLegacyJVData)liveDataList.get(i)).getCourt_name();
		if (courtNames[i]!=null){
			courtNames[i] = courtNames[i].replace("\\","");
		}		
		countyNames[i] = ((GNCodeLegacyJVData)liveDataList.get(i)).getCounty_name();
		if (countyNames[i]!=null){
			countyNames[i] = countyNames[i].replace("\\","");
		}
		county1Names[i] = ((GNCodeLegacyJVData)liveDataList.get(i)).getCounty1_name();
		if (county1Names[i]!=null){
			county1Names[i] = county1Names[i].replace("\\","");
		}
		parishNames[i] = ((GNCodeLegacyJVData)liveDataList.get(i)).getParish_name();
		if (parishNames[i]!=null){
			parishNames[i] = parishNames[i].replace("\\","");
		}
		publicationNames[i] = ((GNCodeLegacyJVData)liveDataList.get(i)).getPublication_name();
		if (publicationNames[i]!=null){
			publicationNames[i] = publicationNames[i].replace("\\","");
		}
	}
	int i=0;
%>

<c:if test="${fn:length(liveDataList) > 0}">
	<c:forEach var="liveDataList" items="${liveDataList}">		
		<c:if test="${'state_name' eq textBoxName}">
		<a href="#" onclick="updateLegacyJVDataBox('${textBoxName}','${liveDataList.state_name}','${liveDataList.normalized_name}')" >
		<%= stateNames[i]%></a><br>
		</c:if>
		<c:if test="${'court' eq textBoxName}">
		<a href="#" onclick="updateLegacyJVDataBox('${textBoxName}','${liveDataList.court_name}','${liveDataList.normalized_name}')" >
		<%= courtNames[i]%></a><br>
		</c:if>
		<c:if test="${'county' eq textBoxName}">
		<a href="#" onclick="updateLegacyJVDataBox('${textBoxName}','${liveDataList.county_name}','${liveDataList.normalized_name}')" >
		<%= countyNames[i]%></a><br>
		</c:if>
		<c:if test="${'county1' eq textBoxName}">
		<a href="#" onclick="updateLegacyJVDataBox('${textBoxName}','${liveDataList.county1_name}','${liveDataList.normalized_name}')" >
		<%= county1Names[i]%></a><br>
		</c:if>
		<c:if test="${'parish' eq textBoxName}">
		<a href="#" onclick="updateLegacyJVDataBox('${textBoxName}','${liveDataList.parish_name}','${liveDataList.normalized_name}')" >
		<%= parishNames[i]%></a><br>
		</c:if>
		<c:if test="${'publication' eq textBoxName}">
		<a href="#" onclick="updateLegacyJVDataBox('${textBoxName}','${liveDataList.publication_name}','${liveDataList.normalized_name}')" >
		<%= publicationNames[i]%></a><br>
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