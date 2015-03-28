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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    	<br>
        <p><font color="#666666" size="4"><b>Enter a SQL query and press Execute button</b></font></p>
        <form action="/GNCodeInterFace/SqlGatewayServlet.do">
        <b>SQL statement:</b><br>
        <textarea name="sqlStatement" cols=60 rows=8></textarea><br>
        <br>
        <input type="submit" value="Execute">
        </form>
    </body>
</html>