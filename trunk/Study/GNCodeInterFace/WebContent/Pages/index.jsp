<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<script language="JavaScript" type="text/javascript"
	SRC="./Common/layout/JavaScript/date.js"></SCRIPT>
<script language="JavaScript" type="text/javascript"
	SRC="./Common/layout/JavaScript/choosedate.js"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
	function showProgressBar(formName) {
		var oldValue = document.getElementById("userId").value;
		if (oldValue == "Please Enter UserID") {
			alert("Please Enter UserID");
		} else {
			var table = document.getElementById('tableone');
			table.deleteRow(2);
			var newtablerow = table.insertRow(2);
			var newtablecell0 = newtablerow.insertCell(0);
			var newtablecell1 = newtablerow.insertCell(1);
			var innerstring = '<IMG SRC="Images/Progress_Indicator.gif" WIDTH="100" HEIGHT="10" BORDER="0" ALT="">';
			newtablecell0.innerHTML = "&nbsp;";
			newtablecell1.innerHTML = innerstring;
			for ( var i = 0; i < 100000; i++) {
				for ( var i = 0; i < 100000; i++) {

				}
			}
			document.getElementById(formName).submit();
		}
	}
	function clearField(field) {
		var oldValue = document.getElementById(field).value;
		if (oldValue == "Please Enter UserID") {
			document.getElementById(field).value = "";
		} else if (field == "passWord") {
			document.getElementById(field).value = "";
		}

	}
	function populateField(field) {
		var oldValue = document.getElementById(field).value;
		if (oldValue == "") {
			if (field == "passWord") {
				document.getElementById(field).value = "Password";
			} else {
				document.getElementById(field).value = "Please Enter UserID";
			}
		}
	}
    	var store = new Array();
	   	var value = new Array();
	    <c:set var="i" value="${0}"/>        
	    <c:if test="${fn:length(releaseListMap) > 0}">
	        <c:forEach var="releaseItem" items="${releaseListMap}">
	            store[${i}] = new Array(
	            <c:forEach var="release" items="${releaseItem}">
	                '${release.releaseName}',
	            </c:forEach>
	            '');            
	            <c:set var="i" value="${i+1}"/>
	        </c:forEach>
	    </c:if>
	    <c:set var="i" value="${0}"/>
	    <c:if test="${fn:length(releaseListMap) > 0}">
	        <c:forEach var="releaseItem" items="${releaseListMap}">
	            value[${i}] = new Array(
	            <c:forEach var="release" items="${releaseItem}">
	                '${release.releaseName}@${release.releaseId}#${release.releaseName}',
	            </c:forEach>
	            '');
	            <c:set var="i" value="${i+1}"/>
	        </c:forEach>
	    </c:if>
    function populate(){
	    var box = document.forms[0].application;
	    var number = box.selectedIndex;
	    if (!number) return;
	    var list = store[number-1];
	    var val = value[number-1];
	    var box2 = document.forms[0].release;
	    box2.disabled=false;
	    box2.options.length = 0;
	    for(i=0;i<list.length;i++){
	    	box2.options[i] = new Option(list[i],val[i]);
    	}
    }
</SCRIPT>
<logic:notPresent name="org.apache.struts.action.MESSAGE"
	scope="application">
	<font color="red"> ERROR: Application resources not loaded --
	check servlet container logs for error messages. </font>
</logic:notPresent>
<br>

<c:if test="${userData.role==null}">
	<p><font color="#222222" size="4"> Please enter your login
	details.</font></p>
	<html:form action="Login.do" styleId="loginForm">
		<TABLE id="tableone">
			<TR id="tablerow1">
				<TD>
				<h3>UserID</h3>
				</TD>
				<TD><html:text property="userID" value="Please Enter UserID"
					styleId="userId" onfocus="clearField('userId')"
					onblur="populateField('userId')" /></TD>
			</TR>
			<TR id="tablerow2">
				<TD>
				<h3>Password</h3>
				</TD>
				<TD><html:password property="passWord" value="Password"
					styleId="passWord" onfocus="clearField('passWord')"
					onblur="populateField('passWord')" /></TD>
			</TR>
			<TR id="tablerow3">
				<TD>
				<h3>&nbsp;</h3>
				</TD>
				<TD><html:button property="Submit" value="Submit"
					onclick="showProgressBar('loginForm')">Submit</html:button></TD>
				<TD>
				<h3>&nbsp;</h3>
				</TD>
			</TR>
		</TABLE>
	</html:form>
</c:if>
<c:if test="${userData.role!=null}">
<c:if test="${userData.role=='1'}">
	<p><font color="#222222" size="4"> <bean:message
		key="userLogin.adminLogged" /></font></p>
</c:if>
<c:if test="${userData.role=='2'}">
	<p><font color="#222222" size="4"> <bean:message
		key="userLogin.userLogged" /></font></p>
</c:if>
<table border="0" cellspacing="0" cellpadding="0" width="100%"
	height="400" valign="top">
	<tr height="20">
		<td colspan=2 height="30">&nbsp;</td>
	</tr>
	<tr height="20">
		<td class="pageName" colspan="2">Welcome to Demo Application</td>
	</tr>
	<tr height="5">
		<td colspan=2>
		<hr>
		</td>
	</tr>
	<tr height="20">
		<td><bean:message key="login.application" /> <font
			color="#FF0033"> *</font></td>
		<td><html:form action="/userData">

			<select name="application" onChange="populate()">
				<option value=0 selected>-Select-</option>
				<c:if test="${fn:length(applicationList) > 0}">
					<c:forEach var="applicationList" items="${applicationList}">
						<option
							value="${applicationList.applicationName}@${applicationList.applicationId}">${applicationList.applicationName}</option>
					</c:forEach>
				</c:if>
			</select></td>
	</tr>
	<tr height="20">
		<td><bean:message key="login.release" /><font color="#FF0033">
		*</font></td>
		<td><select name="release" disabled=true>
			<option></option>

		</select></td>
	</tr>
	<tr height="20">
		<td colspan=2 align="center" height="100"><html:submit
			property="action">
			<bean:message key="login.button" />
		</html:submit> </html:form></td>
	</tr>
	<tr>
		<td></td>
	</tr>
</table>
</c:if>