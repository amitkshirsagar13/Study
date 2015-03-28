<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<%
    response.setContentType("application/vnd.ms-excel");
    response.addHeader("Content-Disposition", "attachment;filename=\"QueryResult.xls\"");
    String message =
      (String) session.getAttribute("message");
%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<style>
			table {
				border-collapse:collapse;
			}
			table th.mainHeader {
				background-color:#000099;
				border-color:#000000;
				color:#FFFFFF;
				font-family:Arial, Helvetica, sans-serif;
				font-weight:bold;
				font-size:13pt;
			}
			table th {
				background-color:#6687C4;
				border-color:#000000;
				color:#FFFFFF;
				font-family:Arial, Helvetica, sans-serif;
				font-weight:bold;
				font-size:12pt;
			}
			table td.color1 {
				background-color:#FFFFFF;
				border-color:#A2C2E8;
				color:#000000;
				font-family:"Times New Roman", Times, serif;
				font-weight:normal;
				font-size:12pt;
			}
			table td.color2 {
				background-color:#EAF8FF;
				border-color:#A2C2E8;
				color:#000000;
				font-family:"Times New Roman", Times, serif;
				font-weight:normal;
				font-size:12pt;
			}
		</style>
	</head>
        
    
        <body>
	<br>
        <table cellspacing="0" cellpadding="2" border="1">
        <%=message%>       
        </table>
    <%--
    This example uses JSTL, uncomment the taglib directive above.
    To test, display the page like this: index.jsp?sayHello=true&name=Murphy
    --%>
    <%--
    <c:if test="${param.sayHello}">
        <!-- Let's welcome the user ${param.name} -->
        Hello ${param.name}!
    </c:if>
    --%>
    
    </body>
</html>
