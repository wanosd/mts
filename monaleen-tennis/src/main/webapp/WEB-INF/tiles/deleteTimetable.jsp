<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table class = "members">
	<tr>
		<td>ID</td>
		<td>Name</td>
		<td>No Slots</td>
		<td>Start Time</td>
		<td>End Time</td>
	</tr>

	<c:forEach var="row" items="${timetable}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/confirmDelete"
			commandName="timetable">
			<tr>
				<td><input type="hidden" value="${row.id}" name="timetableID" />${row.id}</td>
				<td>${row.name}</td>
				<td>${row.slots}</td>
				<td>${row.startTime}</td>
				<td>${row.endTime}</td>
				<td>${row.enabled}</td>
				<td><input value="Delete" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
</table>
		