<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<c:if test="${empty tour }">
<center>No Tournaments Currently Active</center>
</c:if>


<c:if test="${not empty tour }">
<table class = "members" align="center">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Type</th>
		<th>Singles/Doubles</th>
		<th>Level (Senior/Junior)</th>
		<th>Style</th>
		<th>Action</th>
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
</c:if>
		