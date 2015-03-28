<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>
<br>
<%
	String message = (String) session.getAttribute("message");
%>
<p><font color="#222222" size="3"><b><bean:message
	key="errorPage.main" /></b>
	</font></p>	
	<p><font color="#222222" size="2"><%=message%></font></p>		
