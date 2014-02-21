<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Existing Roles</h2>
<table class="members" align="center">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Number of Bookings</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="row" items="${existing}">
		<tr>
			<td>${row.id}</td>
			<td>${row.name}</td>
			<td>${row.bookings_allowed}</td>
			<td>edit</td>
			<td>delete</td>
		</tr>
	</c:forEach>
</table>



<hr>
<h2>Create New Role</h2>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/saveNewRole" commandName="role">
<table class="formtable">
<tr><td>Name: </td><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td>Number of Bookings: </td><td><sf:input id="bookings_allowed" name = "bookings_allowed" path="bookings_allowed" type="text"/><br/><sf:errors path="bookings_allowed" cssClass="error"></sf:errors></td></tr>
<tr><td><input value="Register" type="submit"/></td></tr>
</table>
</sf:form>