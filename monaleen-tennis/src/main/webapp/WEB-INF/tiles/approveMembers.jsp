<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h2>Results</h2>
<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/approveFinalize"
	commandName="toApprove">
	<table class="members">
		<tr>
			<td>Name</td>
			<td>Grade</td>
			<td>Email</td>
			<td>Member Type</td>
			<td>Contact Number</td>
		</tr>
		<c:forEach var="row" items="${toApprove}">
			<sf:form method="post" action="${pageContext.request.contextPath}/approveFinalize" commandName="toApprove">
				<tr>
					<td><input type="hidden" value="${row.username}"
						name="username" />${row.name}</td>
					<td>${row.grade}</td>
					<td><a href="mailto:${row.username}">Email</a></td>
					<td>${row.member_type}</td>
					<td>${row.contact_num}</td>
					<td><input value="Approve" type="submit"
						name="${row.username}" />
			</sf:form>

		</c:forEach>
	</table>
</sf:form>


