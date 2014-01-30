<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
Tournament Page
<table class="members">
	<tr>
		<td>ID</td>
		<td>Name</td>
		<td>Type</td>
		<td>Singles/Doubles</td>
		<td>Level (Senior/Junior)</td>
		<td>Style</td>
	</tr>

	<c:forEach var="row" items="${regTour}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/tournamentUnregister"
			commandName="regTour">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Unregister" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>

</table>

<hr />

<table class="members">
	<tr>
		<td>ID</td>
		<td>Name</td>
		<td>Type</td>
		<td>Singles/Doubles</td>
		<td>Level (Senior/Junior)</td>
		<td>Style</td>
	</tr>
	<c:forEach var="row" items="${unregTour}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/tournamentRegister"
			commandName="unregTour">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Register" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
</table>
