<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Existing Roles</h2>
<table class="members" align="center">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Number of Bookings</th>
		<th>Delete</th>
	</tr>
	<c:forEach var="row" items="${existing}">
		<tr>
			<td>${row.id}</td>
			<td>${row.name}</td>
			<td><sf:form id="details" method="post" action="${pageContext.request.contextPath}/updateRole" commandName="role"><input name="booking" value="${row.bookings_allowed }"><input type="hidden" name="roleID" value="${row.id}"><input value="Update" type="submit"/></sf:form></td>
			<td><sf:form id="details" method="post" action="${pageContext.request.contextPath}/deleteRole" commandName="role"><input type="hidden" name="roleID" value="${row.id}"><input value="Delete" type="submit"/></sf:form></td>
		</tr>
	</c:forEach>
</table>
<center><font color="red">${message }</font></center>

<hr>
<h2>Create New Role</h2>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/saveNewRole" commandName="role">
<table class="formtable" align="center">
<tr><th>Name</th><th>Number of Bookings</th><th>Action</th></tr>
<tr><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td>
<td><sf:input id="bookings_allowed" name = "bookings_allowed" path="bookings_allowed" type="text"/><br/><sf:errors path="bookings_allowed" cssClass="error"></sf:errors></td>
<td><input value="Register" type="submit"/></td></tr>
</table>
</sf:form>

