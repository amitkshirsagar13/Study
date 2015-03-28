<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${userData.role==null}">
	<tiles:insert definition="general.mainLayout" flush="true" /> 
</c:if>
<c:if test="${userData.role=='1'}">
	<tiles:insert definition="admin.mainLayout" flush="true" /> 
</c:if>
<c:if test="${userData.role=='2'}">
	<tiles:insert definition="general.mainLayout" flush="true" /> 
</c:if>