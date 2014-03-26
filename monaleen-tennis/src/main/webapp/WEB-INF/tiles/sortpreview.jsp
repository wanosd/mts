<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table align="center">
	<tr>
		<th>Team Name</th>
		<th>Team Members</th>
	</tr>

	<c:forEach var="entry" items="${teams}">
		<tr>
			<td><c:out value="${entry.key}" /></td>
			<td><c:out value="${entry.value}" /></td>
		</tr>
	</c:forEach>
</table>