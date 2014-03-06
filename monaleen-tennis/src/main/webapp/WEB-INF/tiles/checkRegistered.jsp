<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<table align="center">
	<tr>
		<th>Registered Users</th>
		<c:forEach var="reg" items="${registered}">
			<tr><td>${reg}</td></tr>
		</c:forEach>
	</tr>
</table>