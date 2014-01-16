<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table align="right">
	<tr>
	<td>
	<sec:authorize access="!isAuthenticated()">
	<a href="${pageContext.request.contextPath}/createmembers">Register</a> / 
	<a href="<c:url value ='/login' />">Log In</a>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
	<a href="<c:url value ='/j_spring_security_logout' />">Log Out</a>
	</sec:authorize>
	</td>
	<td><sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/admin">Admin</a>
	</sec:authorize>
	</td>
	</tr>
	</table></br>
	<h1>Tennis Club Links</h1>
	<hr>
	<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/members">View Members</a> * 
	</sec:authorize>
	<a href="">Membership</a> * 	<a href="">Tournaments</a> * 	<a href="">Training</a> * 	<a href="">FAQ/Contact</a>
		
</body>
</html>