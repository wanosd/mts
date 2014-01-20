<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	Members Page Welcome
	
	<h2>Results</h2>
	<table class="members">
	<tr><td>Name</td><td>Grade</td><td>Email</td><td>Member Type</td><td>Contact Number</td></tr>
	<c:forEach var="row" items="${users}">
    <tr><td>${row.name}</td>
    <td>${row.grade}</td>
    <td><a href = "mailto:${row.username}">Email</a></td>
    <td>${row.member_type}</td>
    <td>${row.contact_num}</td></tr>
	</c:forEach>
	</table>
