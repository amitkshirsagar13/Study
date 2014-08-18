<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${loginMessage != null}">${loginMessage}</c:when>
	<c:when test="${loginMessage == null}">
			Hi There ${systemUser.name} ...How r u...?
				<hr />
			Role: ${systemUser.systemRole.roleName} Status: ${systemUser.status}
			<hr />

		<a href="getSystemUserList">Get UserList</a>

		<c:if test="${systemUserList != null}">
			<table>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Role</th>
					<th>Status</th>
				</tr>
				<c:forEach items="${systemUserList}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>${item.emailId}</td>
						<td>${item.systemRole.roleName}</td>
						<td>${item.status}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</c:when>
</c:choose>