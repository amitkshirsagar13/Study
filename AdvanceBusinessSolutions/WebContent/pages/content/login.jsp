<html>
<head><%@taglib uri="http://www.springframework.org/tags/form"
	prefix="form"%>
</head>
<body>
	Hello there... Login Please...
	<form:form method="POST" action="login">
		<table>
			<tr>
				<td><form:label path="emailId">Email</form:label></td>
				<td><form:input path="emailId" /></td>
			</tr>
			<tr>
				<td><form:label path="password">password</form:label></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>