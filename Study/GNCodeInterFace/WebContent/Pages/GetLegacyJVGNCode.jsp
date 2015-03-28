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

<h2>Update GNCode Mapping for the Legacy JV.</h2>

<h5>Please enter the State, City and County Name for which you need to update the GNCode value.<br>
If the State, City and County Name is present in the Database, it will be prompted to you for suggestions.<br>
This will provide you the Normalized Name from database for existing State, City and County in the database.<br>
For the new State, City and County, it will give you the newly created Normalized Name for the Given Court.<br>
<br>
Now to get the GNCode Mapping for the Exiting State, City and County, click on the "Get GNCode Information" button.<br>
Else, enter the GNCode data for the State, City and County and click on save.<br>
<font color="red"><b>It will be good to start your search from County, County1, Parish, Court, State and Publication, 
as the filtering criteria will be reducing the scope of the possible searches.</b></font>
</h5>

<form name="SaveGNCode" id="SaveGNCode" action="/GNCodeInterFace/SaveUpdateJVGNCode.do">
<!-- input type="button" value="Normalized" onClick=getAlert();> -->
<table border="1" >

	<tr>
		<td colspan="2"><font color="red"><b>Court Name:</b></font></td>
		<td colspan="2"><input type="text" size="30" id="court" name="court" value="${SaveUpdateForm.court}"
			onkeyup="showResult(this.value,this.id,'GNCODE_LEGACY_JV_LOOKUP')" />
		<div id="courtList">&nbsp;</div>
		</td>
	</tr>
	<tr><td colspan="2"><font color="red"><b>County Name:</b></font></td>
		<td colspan="2"><input type="text" size="30" id="county" name="county" value="${SaveUpdateForm.county}"
			onkeyup="showResult(this.value,this.id,'GNCODE_LEGACY_JV_LOOKUP')" />
		<div id="countyList">&nbsp;</div>
		</td>
	</tr>
	<tr><td colspan="2"><font color="red"><b>County1 Name:</b></font></td>
		<td colspan="2"><input type="text" size="30" id="county1" name="county1" value="${SaveUpdateForm.county1}"
			onkeyup="showResult(this.value,this.id,'GNCODE_LEGACY_JV_LOOKUP')" />
		<div id="county1List">&nbsp;</div>
		</td>
	</tr>
	<tr><td colspan="2"><font color="red"><b>Parish Name:</b></font></td>
		<td colspan="2"><input type="text" size="30" id="parish" name="parish" value="${SaveUpdateForm.parish}"
			onkeyup="showResult(this.value,this.id,'GNCODE_LEGACY_JV_LOOKUP')" />
		<div id="parishList">&nbsp;</div>
		</td>
	</tr>
	<tr><td colspan="2"><font color="red"><b>Publication Name:</b></font></td>
		<td colspan="2"><input type="text" size="30" id="publication" name="publication" value="${SaveUpdateForm.publication}"
			onkeyup="showResult(this.value,this.id,'GNCODE_LEGACY_JV_LOOKUP')" />
		<div id="publicationList">&nbsp;</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><font color="red"><b>State Name:</b></font></td>
		<td colspan="2"><input type="text" size="30" id="state_name" name="state_name" value="${SaveUpdateForm.state_name}" title="this.value"
			onkeyup="showResult(this.value,this.id,'GNCODE_LEGACY_JV_LOOKUP')" />
		<div id="state_nameList">&nbsp;</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">Normalized Name:</td>
		<td colspan="2" id="normalized_courtCell"><input type="text" size="30" id="normalized_court" name="normalized_court" title="normalized_court" 
						disabled="disabled"/>
		<div id="normalized_courtList">&nbsp;</div>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="checkbox" name="GNCODECHKID" id="GNCODECHKID" value="GNCODECHKID" onclick="getGNCODEIDChange(this.name,'LEGACY_JV')"/>Get GNCode From <font color="red"><b>GNCode Value</b></font>
		</td>
		<td colspan="3" align="center">
			<input id="GNCODEINFO" type="button" value="Get GNCode Information from Normalized Name" onClick="getGNCodeInfoData(this.id,'LEGACY_JV')">
		</td>
	</tr>
	<tr>
		<td>GNCode ID:</td>
		<td><input type="text" size="30" id="gncode_id" name="gncode_id" value="${SaveUpdateForm.gncode_id}"
			disabled="disabled"/></td>
		<td><font color="red"><b>GNCode Value:</b></font></td>
		<td><input type="text" size="30" id="gncode_value" name="gncode_value" value="${SaveUpdateForm.gncode_value}"
			onkeyup="showResult(this.value,this.id,'gncode_mapping_info')" />
			<div id="gncode_valueList">&nbsp;</div></td>
	</tr>
	<tr>
		<td>State:</td>
		<td><input type="text" size="30" id="state" name="state" value="${SaveUpdateForm.state}"
			onkeyup="showResult(this.value,this.id,'gncode_mapping_info')" />
			<div id="stateList">&nbsp;</div></td>
		<td>Court Type:</td>
		<td><input type="text" size="30" id="court_type" name="court_type" value="${SaveUpdateForm.court_type}"
			onkeyup="showResult(this.value,this.id,'gncode_mapping_info')" />
			<div id="court_typeList">&nbsp;</div></td>
	</tr>
	<tr>
		<td>System:</td>
		<td><input type="text" size="30" id="system" name="system" value="${SaveUpdateForm.system}"
			onkeyup="showResult(this.value,this.id,'gncode_mapping_info')" />
			<div id="systemList">&nbsp;</div></td>
		<td>Subject Matter:</td>
		<td><input type="text" size="30" id="subject_matter" name="subject_matter" value="${SaveUpdateForm.subject_matter}"
			onkeyup="showResult(this.value,this.id,'gncode_mapping_info')" />
			<div id="subject_matterList">&nbsp;</div></td>
	</tr>
	<tr>
		<td>Location:</td>
		<td><input type="text" size="30" id="location" name="location" value="${SaveUpdateForm.location}"
			onkeyup="showResult(this.value,this.id,'gncode_mapping_info')" />
			<div id="locationList">&nbsp;</div></td>
		<td>Court Level:</td>
		<td><input type="text" size="30" id="court_level" name="court_level" value="${SaveUpdateForm.court_level}"/>
			<div id="court_levelList">&nbsp;</div></td>
	</tr>
	<tr>
		<td>Court Long Name:</td>
		<td><input type="text" size="30" id="court_long_name" name="court_long_name" value="${SaveUpdateForm.court_long_name}"/>
			<div id="court_long_nameList">&nbsp;</div></td>
		<td>Court Short Name:</td>
		<td><input type="text" size="30" id="court_short_name" name="court_short_name" value="${SaveUpdateForm.court_short_name}"/>
			<div id="court_short_nameList">&nbsp;</div></td>
	</tr>
	<tr><td colspan="4" align="center">
			<input type="button" value="Save GNCode Updates" onclick="saveJVGNCodeForm('SaveGNCode')">
			<input type="hidden" id ="reset" name="reset" value="0" />
			<input type="button" value="Reset Form" onclick="resetGNCodeForm('SaveGNCode')">
		</td>
	</tr>
</table>
</form>

    <c:if test="${formSaved=='0'}" >
    <br>
        <font color="red">
        <B>
        	${message}
        </B>
        </font>
    </c:if>

</body>
</html>
