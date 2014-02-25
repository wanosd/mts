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
		<c:forEach var="row" items="${tournamentDisabled}">
			<sf:form method="post" action="${pageContext.request.contextPath}/tourStatusChange" commandName="tournamentDisabled">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Enable" type="submit" /></td>
			</tr>
			</sf:form>
		</c:forEach>
	</table>
		<table class="members">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Type</td>
			<td>Singles/Doubles</td>
			<td>Level (Senior/Junior)</td>
			<td>Style</td>
		</tr>
		<c:forEach var="row" items="${tournamentEnabled}">
			<sf:form method="post" action="${pageContext.request.contextPath}/tourStatusChange" commandName="tournamentEnabled">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Disable" type="submit" /></td>
			</tr>
			</sf:form>
		</c:forEach>
	</table>
	
	<hr/>
	
		<table class="members">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Type</td>
			<td>Singles/Doubles</td>
			<td>Level (Senior/Junior)</td>
			<td>Style</td>
		</tr>
		<c:forEach var="row" items="${tournamentStarted}">
			<sf:form method="post" action="${pageContext.request.contextPath}/tourStartChange" commandName="tournamentStarted">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Make Inactive" type="submit" /></td>
			</tr>
			</sf:form>
		</c:forEach>
	</table>
	
		<table class="members">
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Type</td>
			<td>Singles/Doubles</td>
			<td>Level (Senior/Junior)</td>
			<td>Style</td>
		</tr>
		<c:forEach var="row" items="${tournamentUnstarted}">
			<sf:form method="post" action="${pageContext.request.contextPath}/tourStartChange" commandName="tournamentUnstarted">
			<tr>
				<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><input value="Activate Tournament" type="submit" /></td>
			</tr>
			</sf:form>
		</c:forEach>
	</table>
	
