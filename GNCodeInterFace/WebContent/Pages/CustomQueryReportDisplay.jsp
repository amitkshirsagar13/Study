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

    <h1>JSP Page</h1>
    <br/>
            <%
            String message = (String)session.getAttribute("message");
            %>
        <p>
            <b>SQL Result</b>
            <table cellpadding="5" border="1">
                <%=message%>                
            </table>
            <br/>
            <% 
            String msg = 
                    (String)session.getAttribute("message");
            if(msg != null)
                {
             out.println("<form action=\"/GNCodeInterFace/Pages/ExportToExel.jsp\" method=\"post\">"+
                "<input type=\"submit\" value=\"Export to Excel\"></form>") ;
                }else{
                	
                }
            %>

    
    </body>
</html>
