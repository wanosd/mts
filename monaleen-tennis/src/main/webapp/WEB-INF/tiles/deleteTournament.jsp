<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table class = "members">
	<tr>
		<td>ID</td>
		<td>Name</td>
		<td>Type</td>
		<td>Singles/Doubles</td>
		<td>Level (Senior/Junior)</td>
		<td>Style</td>
	</tr>

	<c:forEach var="row" items="${tour}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/confirmDelete"
			commandName="tour">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Delete" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
</table>
		