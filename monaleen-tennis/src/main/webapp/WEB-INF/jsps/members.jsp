<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monaleen Tennis Club - Members</title>
</head>
<body>
	Members Page Welcome
	
	<h2>Results</h2>
	<table>
	<tr><td>ID</td><td>Name</td><td>Gender</td></tr>
	<c:forEach var="row" items="${users}">
    <tr><td>${row.id}</td>
    <td>${row.name}</td>
    <td>${row.gender}</td></tr>
	</c:forEach>
	</table>
	
	
</body>
</html>