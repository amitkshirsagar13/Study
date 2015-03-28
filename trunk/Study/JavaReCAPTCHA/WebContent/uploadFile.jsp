<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script language="JavaScript" type="text/JavaScript">
	var currentValue = 0;
	function checkMailSection(radioButton) {
		if (radioButton.value == '1') {
			document.getElementById("mailTo").disabled = false;
			document.getElementById("mailToFile").disabled = true;
		} else if (radioButton.value == '2') {
			document.getElementById("mailTo").disabled = true;
			document.getElementById("mailToFile").disabled = false;
		}

	}
</script>
</head>
<body>
	<form action="UploadFileServlet" method="post"
		enctype="multipart/form-data">
		Single Email: <input type="radio" name="myRadios"
			onclick="checkMailSection(this);" value="1" checked="checked" />
		Batch Email: <input type="radio" name="myRadios"
			onclick="checkMailSection(this);" value="2" /><br /><br/>
		<div id="emailDiv">
			Single Email ID: <input type="text" name="mailTo" id="mailTo" disabled="true"/>
		</div>
		<div id="uploadFileDiv">
			Email Recipient File: <input type="file" name="mailToFile"
				id="mailToFile" value="" disabled="true"/>
		</div>
		<br />
		<div id="mailSubjectDiv">
			Mail Subject: <input type="text" name="mailSubject" id="mailSubject" />
		</div>
		<br />
		<div id="mailContentDiv">
			Mail Content: <br />
			<textarea name="mailContent" id="mailContent" rows="15" cols="100"></textarea>
		</div>
		<br />
		<div class="actions full">
			<input type="button" action="cancel" value="Cancel"/>
			<input type="submit" action="submit" value="Send Email"/>
		</div>
	</form>
</body>
</html>