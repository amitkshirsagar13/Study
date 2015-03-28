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
<%@page import="com.MileStoneReporterTool.DataBeans.GNCodeCourtData"%>
<c:set var="i" value="${0}" />
<%
	List liveDataList = (List)session.getAttribute("liveDataList");
	String courtNames[] = new String[liveDataList.size()];
	for(int i=0;i<liveDataList.size();i++){
		courtNames[i] = ((GNCodeCourtData)liveDataList.get(i)).getCourt_name();
		courtNames[i] = courtNames[i].replace("\\","");
	}
	int i=0;
%>


<c:if test="${fn:length(liveDataList) > 0}">
	<c:forEach var="liveDataList" items="${liveDataList}">
		<a href="#" onclick="updateCourtDataBox('${textBoxName}','${liveDataList.court_name}','${liveDataList.normalized_court}')" >
		<c:if test="${'court_name' eq textBoxName}">
		<%= courtNames[i]%></a><br>
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