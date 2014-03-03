<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
Timetable Page
<table class="members" align="center">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>No Slots</th>
		<th>Total Weeks</th>
		<th>Enabled?</th>
		<th>Action</th>
	</tr>
	<c:forEach var="row" items="${timetableEnabled}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/timetableStatusChange"
			commandName="timetableEnabled">
			<tr>
				<td><input type="hidden" value="${row.id}" name="timetableID" />${row.id}</td>
				<td>${row.name}</td>
				<td>${row.slots}</td>
				<td>${row.total}</td>
				<td>${row.enabled}</td>
				<td><input value="Change Status" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
</table>