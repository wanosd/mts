<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table>
	<tr>
		<td><table class="timetable">
				<c:forEach var="row" items="${monday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table></td>
		<td>
			<table class="timetable">
				<c:forEach var="row" items="${tuesday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table class="timetable">
				<c:forEach var="row" items="${wednesday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table class="timetable">
				<c:forEach var="row" items="${thursday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table class="timetable">
				<c:forEach var="row" items="${friday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table class="timetable">
				<c:forEach var="row" items="${saturday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table class="timetable">
				<c:forEach var="row" items="${sunday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>