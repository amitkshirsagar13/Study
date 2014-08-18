<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="java.util.Iterator"%>
<tiles:importAttribute />
<%-- Check if selected exist. --%>
<logic:notPresent name="selected">
	<%
		pageContext.setAttribute("selected", "");
	%>
</logic:notPresent>

<%-- Prepare the links list to be iterated --%>
<bean:define id="links" name="links" type="java.util.List" scope="page" />
<%
	Iterator i = links.iterator();
%>

<%-- iterate on items list --%>
<%-- Normally, we should write something like this :
<logic:iterate id="item" name="items" type="java.lang.String" >
But, Struts doesn't declare the TEI class for iterate, and
some web container deal badly with the declared variable.
So, we use what follow instead.
--%>
<table id="navigation" class="menuTable">
	<tr>
		<td width="200">&nbsp;</td>
	</tr>
	<tr>
		<td width="200" class="menuItem"
			onmouseover="this.className='menuItemSelected'"
			onMouseOut="this.className='menuItem'"><a href="LoginSuccess"
			class="navigationText">Home</a></td>
	</tr>
	<logic:iterate id="iterateItem" name="items">
		<tr>

			<bean:define id="item" name="iterateItem" type="java.lang.String"
				scope="page" />
			<td width="200" class="menuItem"
				onmouseover="this.className='menuItemSelected'"
				onMouseOut="this.className='menuItem'">
				<%-- check if selected --%> <logic:notEqual name="selected"
					value="<%=item%>">
					<%
						// Compute link value
								String link = (String) i.next();
								if (link.startsWith("/"))
									link = request.getContextPath() + link;
					%>
					<font size="-1"><a href="<%=link%>" class="navigationText"><%=item%></a></font>
				</logic:notEqual> <logic:equal name="selected" value="<%=item%>">
					<font size="-1" color="fuchsia"><%=item%></font>
				</logic:equal>
			</td>
		</tr>
	</logic:iterate>
</table>