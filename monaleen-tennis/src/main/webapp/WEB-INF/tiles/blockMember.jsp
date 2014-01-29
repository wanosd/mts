<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h2>Results</h2>

	<table class="members">
		<tr>
			<td>Name</td>
			<td>Grade</td>
			<td>Email</td>
			<td>Member Type</td>
			<td>Contact Number</td>
		</tr>
		<c:forEach var="row" items="${toBlock}">
			<sf:form method="post" action="${pageContext.request.contextPath}/blockFinalize" commandName="toBlock">
				<tr>
					<td><input type="hidden" value="${row.username}"
						name="username" />${row.name}</td>
					<td>${row.grade}</td>
					<td><a href="mailto:${row.username}">Email</a></td>
					<td>${row.member_type}</td>
					<td>${row.contact_num}</td>
					<td><input value="Suspend" type="submit"
						name="${row.username}" />
			</sf:form>

		</c:forEach>
	</table>


