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
<%
	List liveDataList = (List)session.getAttribute("liveDataList");
	String dataValue[] = new String[liveDataList.size()];
	for(int i=0;i<liveDataList.size();i++){
		dataValue[i] = ((LiveDataObject)liveDataList.get(i)).getDataValue();
		dataValue[i] = dataValue[i].replace("\\","");
	}
	int i=0;
%>


<%@page import="java.util.List"%>
<%@page import="com.MileStoneReporterTool.DataBeans.LiveDataObject"%>
<c:set var="i" value="${0}" />
<c:if test="${fn:length(liveDataList) > 0}">
	<c:forEach var="liveDataList" items="${liveDataList}">
		<a href="#" onclick="updateLiveDataBox('${textBoxName}','${liveDataList.dataValue}')" >
		<%= dataValue[i]%></a><br>
		<%
			i = i + 1;
		%>
		<c:set var="i" value="${i+1}"/>	
	</c:forEach>
</c:if>
<c:if test="${i == 0}">
	Look's Like you have entered single '. Please make it two '.
</c:if>