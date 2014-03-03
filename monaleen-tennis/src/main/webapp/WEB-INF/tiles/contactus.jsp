<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="members" align="center">
	<c:if test="${not empty admins }">

		<tr>
			<th></th>
			<th>Site Administrators
			<th>
		</tr>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
		</tr>
		<c:forEach var="row" items="${admins}">
			<tr>
				<td>${row.name}</td>
				<td>${row.username}</td>
				<td>${row.contact_num}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${not empty committee }">

		<tr>
			<th></th>
			<th>Club Committee
			<th>
		</tr>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Phone</th>
		</tr>
		<c:forEach var="row" items="${committee}">
			<tr>
				<td>${row.name}</td>
				<td>${row.username}</td>
				<td>${row.contact_num}</td>
			</tr>
		</c:forEach>
	</c:if>


</table>
<hr>
<div align="center"><%@include file="maps.jsp"%></div>