<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<hr>
	<h2>Current Members</h2>
	<a href="${pageContext.request.contextPath }/blockMembers">Suspend Members</a>
	<table class="members">
	<tr><th>Name</th><th>Address Line 1</th><th>Address Line 2</th><th>Address Town</th><th>Address County</th><th>Grade</th><th>Email</th><th>Member Type</th><th>Contact Number</th><th>Emergency Contact</th><th>Emergency Contact Num</th></tr>
	
	<c:forEach var="row" items="${users}">
    <tr><td>${row.name}</td>
    <td>${row.ad_line1}</td>
    <td>${row.ad_line2}</td>
    <td>${row.ad_city}</td>
    <td>${row.ad_county}</td>
    <td>${row.grade}</td>
    <td><a href = "mailto:${row.username}">Contact</a></td>
    <td>${row.member_type}</td>
    <td>${row.contact_num}</td>
    <td>${row.em_con_name}</td>
    <td>${row.em_con_num}</td>
    </tr>
	</c:forEach>
	</table>
	<hr>
	<h2>Pending Members</h2>
	<a href="${pageContext.request.contextPath }/approveMembers">Approve Members</a>
		<table class="members">
		<tr><th>Name</th><th>Address Line 1</th><th>Address Line 2</th><th>Address Town</th><th>Address County</th><th>Grade</th><th>Email</th><th>Member Type</th><th>Contact Number</th><th>Emergency Contact</th><th>Emergency Contact Num</th></tr>
	<c:forEach var="row" items="${nonusers}">
    <tr><td>${row.name}</td>
    <td>${row.ad_line1}</td>
    <td>${row.ad_line2}</td>
    <td>${row.ad_city}</td>
    <td>${row.ad_county}</td>
    <td>${row.grade}</td>
    <td><a href = "mailto:${row.username}">Contact</a></td>
    <td>${row.member_type}</td>
    <td>${row.contact_num}</td>
    <td>${row.em_con_name}</td>
    <td>${row.em_con_num}</td>
    </tr>
	</c:forEach>
	</table>
	<hr>

	<h2>Administration</h2>
		<table class="members">
		<tr><th>Name</th><th>Address Line 1</th><th>Address Line 2</th><th>Address Town</th><th>Address County</th><th>Grade</th><th>Email</th><th>Member Type</th><th>Contact Number</th><th>Emergency Contact</th><th>Emergency Contact Num</th></tr>
	
	<c:forEach var="row" items="${admin}">
    <tr><td>${row.name}</td>
    <td>${row.ad_line1}</td>
    <td>${row.ad_line2}</td>
    <td>${row.ad_city}</td>
    <td>${row.ad_county}</td>
    <td>${row.grade}</td>
    <td><a href = "mailto:${row.username}">Contact</a></td>
    <td>${row.member_type}</td>
    <td>${row.contact_num}</td>
    <td>${row.em_con_name}</td>
    <td>${row.em_con_num}</td>
    </tr>
	</c:forEach>
	</table>
	<hr>
