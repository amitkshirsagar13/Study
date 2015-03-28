<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<script type="text/javascript">
		function handleUpdate(button) {
			var buttonName = button.id;

			var newEmail = document
					.getElementById('portlet_9_1{actionForm.newEmail}');
			var confirmNewEmail = document
					.getElementById('portlet_9_1{actionForm.confirmNewEmail}');

			var password = document
					.getElementById('portlet_9_1{actionForm.password}');
			var confirmPassword = document
					.getElementById('portlet_9_1{actionForm.confirmedPassword}');

			alert(buttonName);
			if (buttonName == 'UpdateEmail') {

				button.value = 'Change';
				button.text = 'Change';
				alert(buttonName);

			} else if (buttonName == 'UpdatePassword') {
				button.value = 'Cancel';
				button.innerHTML = 'Cancel';
				alert(buttonName);
			}
		}
	</script>
	<br />

	<input type="text" id="portlet_9_1{actionForm.newEmail}"
		disabled="disabled" />
	<input type="text" id="portlet_9_1{actionForm.confirmNewEmail}"
		disabled="disabled" />
	<input type="button" id="UpdateEmail" value="UpdateEmail"
		onClick="handleUpdate(this)" />
	<br />
	<br />
	<input type="text" id="portlet_9_1{actionForm.password}"
		disabled="disabled" />
	<input type="text" id="portlet_9_1{actionForm.confirmPassword}"
		disabled="disabled" />
	<button type="button" id="UpdatePassword" value="UpdatePassword"
		onClick="handleUpdate(this)">Change</button>


</body>
</html>
