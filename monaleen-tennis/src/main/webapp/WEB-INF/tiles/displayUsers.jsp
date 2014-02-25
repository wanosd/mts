<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<table class = "members">
	<tr>
		<th>ID</th>
		<th>Email</th>
		<th>Name</th>
		<th>Action</th>
	</tr>

	<c:forEach var="row" items="${users}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/adminEditProfile"
			commandName="users">
			<tr>
				<td><input type="hidden" value="${row.username}" name="username" />${row.id}</td>
				<td>${row.username}</td>
				<td>${row.name}</td>
				<td><input value="Edit" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
		
</table>