<%@ page language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${userData.role=='1'}">
        <tiles:insert definition="adminLayout.mainLayout" flush="true" /> 
    </c:if>
    <c:if test="${userData.role=='2'}">
	<tiles:insert definition="generalLayout.mainLayout" flush="true" /> 
    </c:if>