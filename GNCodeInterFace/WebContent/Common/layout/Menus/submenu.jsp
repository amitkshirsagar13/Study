<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import="java.util.Iterator" %>
<tiles:importAttribute />
<%-- Check if selected exist. --%>
<logic:notPresent name="selected" >
    <% pageContext.setAttribute( "selected", "" ); %>
</logic:notPresent>

<%-- Prepare the links list to be iterated --%>
<bean:define id="links" name="links" type="java.util.List" scope="page" />
<% Iterator i = links.iterator(); %>

<%-- iterate on items list --%>
<%-- Normally, we should write something like this :
   <logic:iterate id="item" name="items" type="java.lang.String" >
   But, Struts doesn't declare the TEI class for iterate, and
   some web container deal badly with the declared variable.
   So, we use what follow instead.
    --%>
<table border="0" cellspacing="0" cellpadding="0" width="200" id="navigation" class="menuTable">
    <tr>
        <td width="200">&nbsp;</td>
    </tr>
    <tr>
        <td width="200" class="menuCell" onmouseover="this.className='menuCellSelected'" onMouseOut="this.className='menuCell'"><a href="/GNCodeInterFace/LoginSuccess.do" class="navText">Home</a></td>
    </tr>
    <logic:iterate id="iterateItem" name="items" >
    <tr>
        
        <bean:define id="item" name="iterateItem" type="java.lang.String" scope="page" />


        <td width="200" class="menuCell" onmouseover="this.className='menuCellSelected'" onMouseOut="this.className='menuCell'">
        
            <%-- check if selected --%>
            <logic:notEqual name="selected" value="<%=item%>">
                <% // Compute link value
                    String link = (String)i.next();
                    if(link.startsWith("/") )
                        link = request.getContextPath() + link;
                %>
                <font size="-1"><a href="<%=link%>" class="navText" ><%=item%></a></font>
            </logic:notEqual>
            <logic:equal name="selected" value="<%=item%>">
                <font size="-1" color="fuchsia"><%=item%></font>
            </logic:equal>
        </td>
    </tr>
    </logic:iterate>
</table>
