<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table width="100%">
	<tr>
		<td>
			<table>
				<tr>
					<th>Monday</th>
				</tr>
				<c:forEach var="row" items="${court.monday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table >
				<tr>
					<th>Tuesday</th>
				</tr>
				<c:forEach var="row" items="${court.tuesday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>

		<td>
			<table >
				<tr>
					<th>Wednesday</th>
				</tr>
				<c:forEach var="row" items="${court.wednesday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>

		<td>
			<table >
				<tr>
					<th>Thursday</th>
				</tr>
				<c:forEach var="row" items="${court.thursday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		<td>
			<table >
				<tr>
					<th>Friday</th>
				</tr>
				<c:forEach var="row" items="${court.friday}">
					<tr>
						<td>${row}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
<td>
		<table >
			<tr>
				<th>Saturday</th>
			</tr>
			<c:forEach var="row" items="${court.saturday}">
				<tr>
					<td>${row}</td>
				</tr>
			</c:forEach>
		</table>
</td>
<td>
		<table >
			<tr>
				<th>Sunday</th>
			</tr>
			<c:forEach var="row" items="${court.sunday}">
				<tr>
					<td>${row}</td>
				</tr>
			</c:forEach>

		</table>
</td>
	</tr>
</table>