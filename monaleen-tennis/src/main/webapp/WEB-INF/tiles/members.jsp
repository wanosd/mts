<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<center><h2>Current Members</h2></center>
<table class="members" align="center">
	<tr>
		<th>Name</th>
		<th>Grade</th>
		<th>Email</th>
		<th>Member Type</th>
		<th>Contact Number</th>
	</tr>
	<c:forEach var="row" items="${users}">
		<tr>
			<td>${row.name}</td>
			<td>${row.grade}</td>
			<td><a href="mailto:${row.username}">Email</a></td>
			<td>${row.member_type}</td>
			<td>${row.contact_num}</td>
		</tr>
	</c:forEach>
</table>
