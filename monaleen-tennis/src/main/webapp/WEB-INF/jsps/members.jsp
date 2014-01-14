<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href= "${pageContext.request.contextPath }/static/css/style.css" rel="stylesheet" type="text/css">
<title>Monaleen Tennis Club - Members</title>
</head>
<body>
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
	
	
</body>
</html>