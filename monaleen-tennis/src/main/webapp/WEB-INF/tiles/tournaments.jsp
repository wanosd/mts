<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
Tournament Page

<h2>Results</h2>
<table class="members">
	<tr>
		<td>Name</td>
		<td>Type</td>
		<td>Singles/Doubles</td>
		<td>Level (Senior/Junior)</td>
		<td>Style</td>
	</tr>
	<c:forEach var="row" items="${tournaments}">
		<tr>
			<td>${row.tournamentName}</td>
			<td>${row.tournamentGender}</td>
			<td>${row.tournamentType}</td>
			<td>${row.tournamentCategory}</td>
			<td>${row.tournamentStyle}</td>
		</tr>
	</c:forEach>
</table>
