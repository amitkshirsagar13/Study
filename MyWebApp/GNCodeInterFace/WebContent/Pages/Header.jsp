<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<script language="JavaScript" type="text/javascript" SRC="./Common/layout/JavaScript/choosedate.js"></SCRIPT>
<script language="JavaScript" type="text/javascript" SRC="./Common/layout/JavaScript/date.js"></SCRIPT>
<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#CODFFF" align="center">
	<tr>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
		<td width="10%" bgcolor='white'>&nbsp;</td>
	</tr>
    <tr bgcolor="white">
        <td colspan="3" rowspan="2" bgcolor="white" align="left"><img src="Images/art.gif" alt="Header image" width="260" height="100" border="0" /></td>
        <td height="60" colspan="4" align="center" valign="bottom" nowrap="nowrap" bgcolor='white' id="logo">Demo Web Application </td>
        <td colspan="3" rowspan="2" bgcolor="white" align="left">&nbsp;</td>
    </tr>
    <tr bgcolor="#3366CC">
        <td height="39" colspan="4" align="center" valign="top" bgcolor='white' id="tagline">Project</td>
    </tr>
    <tr>
        <td colspan="10" bgcolor="#003366"><img src="Images/mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
    </tr>
    
    <c:if test="${userData.role==null}">
    <tr bgcolor="#000000">
      	<td colspan="3" id="dateformat" height="25"><font color="#FFFFFF">&nbsp;&nbsp;
			<script language="JavaScript" type="text/javascript">
            var TODAYDATE = TODAY();
            document.write(TODAYDATE);	</script>
			</font></td>
		<td colspan="4" align="left" ><!--marquee behavior="alternate" direction="left" WIDTH="75%"-->You are not logged in...<!--/marquee--></td>
		<td align=right colspan="3"><font color="#FFFFFF">  No User is logged In.&nbsp;&nbsp;</font></td>
    </tr>
    </c:if>
    
    <c:if test="${userData.role=='1'}">
    <tr bgcolor="#FF6633">
      	<td id="dateformat" colspan="3" height="25">&nbsp;&nbsp;
			<script language="JavaScript" type="text/javascript">
            var TODAYDATE = TODAY();
            document.write(TODAYDATE);	</script>
		</td>
		<td colspan="4" align="left" ><!--marquee behavior="alternate" direction="left" WIDTH="75%"-->You are admin...<!--/marquee--></td>
		<td align=right colspan="3"> ${userData.userName}&nbsp;&nbsp;</td>
    </tr>
    </c:if>    
    
    <c:if test="${userData.role=='2'}">
    <tr bgcolor="#CCFF99">
      	<td colspan="3" id="dateformat" height="25">&nbsp;&nbsp;
			<script language="JavaScript" type="text/javascript">
            var TODAYDATE = TODAY();
            document.write(TODAYDATE);	</script>
		</td>
		<td colspan="4" align="left" ><!--marquee behavior="alternate" direction="left" WIDTH="75%"-->You are user...<!--/marquee--></td>
		<td align=right colspan="3">  ${userData.userName}&nbsp;&nbsp;</td>
    </tr>
    </c:if>    
    <tr>
        <td colspan="10" bgcolor="#003366"><img src="Images/mm_spacer.gif" alt="" width="1" height="1" border="0" /></td>
    </tr>
</table>
