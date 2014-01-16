<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Approval</title>
</head>
<body>

<h2>Results</h2>
	<table class="members">
	<tr><td>Name</td><td>Grade</td><td>Email</td><td>Member Type</td><td>Contact Number</td></tr>
	<c:forEach var="row" items="${toApprove}">
    <tr><td>${row.name}</td>
    <td>${row.grade}</td>
    <td><a href = "mailto:${row.username}">Email</a></td>
    <td>${row.member_type}</td>
    <td>${row.contact_num}</td>
    <td><a href="">Link to Approve Once Payment Received</a></tr>
	</c:forEach>
	</table>

</body>
</html>