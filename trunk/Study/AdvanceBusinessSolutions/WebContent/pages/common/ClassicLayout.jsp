<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<HTML>
<HEAD>
<title><tiles:getAsString name="title" ignore="true" /></title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="resources/abs.css" />
<link rel="stylesheet" type="text/css" href="/resources/abs.css" />
<script language="javascript" src="resources/JavaScript/choosedate.js"></script>
</HEAD>
<BODY class="pageLayout" onload="JavaScript:history.go(1);">
	<TABLE class="layoutTable" align=center>
		<TR>
			<TD colSpan="4"><tiles:insertAttribute name="header" /></TD>
		</TR>
		<TR valign="top">
			<TD width="19%"><tiles:insertAttribute name="menu" /></TD>
			<TD width="5%">&nbsp;</TD>
			<TD valign="top"><tiles:insertAttribute name="body" /></TD>
			<TD width="5%">&nbsp;</TD>
		</TR>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<TR>
			<td colspan="4" width="80%"><tiles:insertAttribute name="footer" />
			</td>
		</TR>
	</TABLE>
</BODY>
</HTML>