<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div id="tables" align="justify">
	<c:if test="${not empty regTour }">
		<center>
			<h2>Tournaments Currently Registered For</h2>
		</center>
		<table class="members" align="center">
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Singles/Doubles</th>
				<th>Level (Senior/Junior)</th>
				<th>Style</th>
				<th>Registered Members</th>
				<th>Action</th>
				<th>Preview Teams</th>
			</tr>

			<c:forEach var="row" items="${regTour}">

				<tr>
					<td>${row.tournamentName}</td>
					<td>${row.tournamentGender}</td>
					<td>${row.tournamentType}</td>
					<td>${row.tournamentCategory}</td>
					<td>${row.tournamentStyle}</td>
					<td><a
						href="${pageContext.request.contextPath}/checkRegistered?id=${row.id}">View</a>
					<td><sf:form method="post"
							action="${pageContext.request.contextPath}/tournamentUnregister"
							commandName="regTour">
							<input type="hidden" value="${row.id}" name="tournamentID" />
							<input value="Unregister" type="submit" />
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
	</c:if>

	<hr />
	<c:if test="${not empty unregTour }">
		<center>
			<h2>Tournaments Not Registered For</h2>
		</center>
		<table class="members" align="center">
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Singles/Doubles</th>
				<th>Level (Senior/Junior)</th>
				<th>Style</th>
				<th>Action</th>
			</tr>
			<c:forEach var="row" items="${unregTour}">

				<tr>
					<td><input type="hidden" value="${row.id}" name="tournamentID" />${row.tournamentName}</td>
					<td>${row.tournamentGender}</td>
					<td>${row.tournamentType}</td>
					<td>${row.tournamentCategory}</td>
					<td>${row.tournamentStyle}</td>
					<td><sf:form method="post"
							action="${pageContext.request.contextPath}/tournamentRegister"
							commandName="unregTour">
							<input type="hidden" value="${row.id}" name="tournamentID" />
							<input value="Register" type="submit" />
						</sf:form></td>
				</tr>	
			</c:forEach>
		</table>
	</c:if>
</div>
