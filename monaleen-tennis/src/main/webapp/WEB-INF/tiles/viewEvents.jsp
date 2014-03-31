<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<center><h2>Current Events</h2></center>
<c:if test="${not empty eventsEnabled }">
<table class="members" align="center">
		<tr><th>Enabled Events</th></tr>
	<tr>
	<th>ID</th>
		<th>Name</th>
		<th>Author</th>
		<th>Action</th>
	</tr>
<c:forEach var="row" items="${eventsEnabled}">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/changeEventStatus"
		commandName="eventsEnabled">
		<tr>
			<td><input type="hidden" value="${row.id}" name="eventID" />${row.id}</td>
			<td>${row.name}</td>
			<td>${row.author}</td>
			<td><input value="Disable" type="submit" name="${row.id}" />
	</sf:form>

</c:forEach>
</table>
<center><font color="red">${message }</font></center>
</c:if>


<c:if test="${not empty eventsDisabled }">
<table class="members" align="center">
	<tr><th>Disabled Events</th></tr>
	<tr>
	<th>ID</th>
		<th>Name</th>
		<th>Author</th>
		<th>Action</th>
	</tr>
<c:forEach var="row" items="${eventsDisabled}">
	<sf:form method="post"
		action="${pageContext.request.contextPath}/changeEventStatus"
		commandName="eventsDisabled">
		<tr>
			<td><input type="hidden" value="${row.id}" name="eventID" />${row.id}</td>
			<td>${row.name}</td>
			<td>${row.author}</td>
			<td><input value="Enable" type="submit" name="${row.id}" />
	</sf:form>

</c:forEach>
</table>
</c:if>