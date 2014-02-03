<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<table>
	<tr>
		<c:forEach var="reg" items="${registered}">
			<td>${reg},</td>
		</c:forEach>
	</tr>
</table>