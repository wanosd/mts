<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
Tournament Page
<c:if test="${empty tournamentDisabled }">
	<center>No Tournaments To Disable</center>
</c:if>
<c:if test="${not empty tournamentDisabled }">
	<table class="members" align="center">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Singles/Doubles</th>
			<th>Level (Senior/Junior)</th>
			<th>Style</th>
			<th>Action</th>
		</tr>
		<c:forEach var="row" items="${tournamentDisabled}">
			<sf:form method="post"
				action="${pageContext.request.contextPath}/tourStatusChange"
				commandName="tournamentDisabled">
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
</c:if>


<c:if test="${empty tournamentEnabled }">
	<center>No Tournaments To Enable</center>
</c:if>

<c:if test="${not empty tournamentEnabled }">
	<table class="members" align="center">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Singles/Doubles</th>
			<th>Level (Senior/Junior)</th>
			<th>Style</th>
			<th>Action</th>
			<th>Preview</th>
		</tr>
		<c:forEach var="row" items="${tournamentEnabled}">

			<tr>
				<td>${row.id}</td>
				<td>${row.tournamentName}</td>
				<td>${row.tournamentGender}</td>
				<td>${row.tournamentType}</td>
				<td>${row.tournamentCategory}</td>
				<td>${row.tournamentStyle}</td>
				<td><sf:form method="post"
						action="${pageContext.request.contextPath}/tourStatusChange"
						commandName="tournamentEnabled">
						<input type="hidden" value="${row.id}" name="tournamentID" />
						<input value="Disable" type="submit" />
					</sf:form></td>
				<td><sf:form method="post"
						action="${pageContext.request.contextPath}/sortPreview"
						commandName="tournamentEnabled">
						<input type="hidden" value="${row.id}" name="tournamentID" />
						<input value="Preview" type="submit" />
					</sf:form></td>
			</tr>

		</c:forEach>
	</table>
	<form method="post"
		action="${pageContext.request.contextPath}/sortPreview">
		<input type="hidden" value="${row.id}" name="tournamentID" />${row.id}<input
			value="Preview" type="submit" />
	</form>

</c:if>

<hr />
<c:if test="${empty tournamentStarted }">
	<center>No Tournaments to Deactivate</center>
</c:if>
<c:if test="${not empty tournamentStarted }">
	<table class="members" align="center">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Singles/Doubles</th>
			<th>Level (Senior/Junior)</th>
			<th>Style</th>
			<th>Action</th>
		</tr>
		<c:forEach var="row" items="${tournamentStarted}">
			<sf:form method="post"
				action="${pageContext.request.contextPath}/tourStartChange"
				commandName="tournamentStarted">
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
</c:if>

<c:if test="${empty tournamentUnstarted }">
	<center>No Tournaments To Activate</center>
</c:if>
<c:if test="${not empty tournamentUnstarted }">
	<table class="members" align="center">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Type</th>
			<th>Singles/Doubles</th>
			<th>Level (Senior/Junior)</th>
			<th>Style</th>
			<th>Action</th>
		</tr>
		<c:forEach var="row" items="${tournamentUnstarted}">
			<sf:form method="post"
				action="${pageContext.request.contextPath}/tourStartChange"
				commandName="tournamentUnstarted">
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
</c:if>

