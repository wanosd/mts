<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table width="100%">
	<tr>
		<td>
			<table>
				<tr>
					<th>Monday</th>
				</tr>

				<c:forEach var="row" varStatus="loop" items="${court.monday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><a href="${loop.index }">Book This Slot</a></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td>
			<table>
				<tr>
					<th>Tuesday</th>
				</tr>
				<c:forEach var="row" items="${court.tuesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner">${loop.index}isBookable</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>

		<td>
			<table>
				<tr>
					<th>Wednesday</th>
				</tr>
				<c:forEach var="row" items="${court.wednesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner">${loop.index}isBookable</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>

		<td>
			<table>
				<tr>
					<th>Thursday</th>
				</tr>
				<c:forEach var="row" items="${court.thursday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner">${loop.index}isBookable</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td>
			<table>
				<tr>
					<th>Friday</th>
				</tr>
				<c:forEach var="row" items="${court.friday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner">${loop.index}isBookable</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td>
			<table>
				<tr>
					<th>Saturday</th>
				</tr>
				<c:forEach var="row" items="${court.saturday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner">${loop.index}isBookable</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td>
			<table>
				<tr>
					<th>Sunday</th>
				</tr>
				<c:forEach var="row" items="${court.sunday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner">${loop.index}isBookable</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</table>
		</td>
	</tr>
</table>