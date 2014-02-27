<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

You have ${bookings } bookings left!

<p>Court Name: ${court.name}</p>
<p>Current Date: ${date }</p>
<p>Week Beginning: ${testDate }</p>
<table width="100%">
	<tr>
		<td class="nothing">
			<table>
				<tr>
					<th>Monday</th>
				</tr>

				<c:forEach var="row" varStatus="loop" items="${court.monday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="monday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}
								<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="monday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
											<form action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="monday" name="day" />
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report No Show">
											</form>
										</c:when>
									</c:choose>
									<c:choose>
									<c:when test="${name ne pageContext['request'].userPrincipal.name}">
										<form action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="monday" name="day" />
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report No Show">
											</form>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td class="nothing">
			<table>
				<tr>
					<th>Tuesday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.tuesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="tuesday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="tuesday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
										</c:when>
									</c:choose>
								</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.wednesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="wednesday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="wednesday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
										</c:when>
									</c:choose>
								</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.thursday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="thursday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="thursday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
										</c:when>
									</c:choose>
								</td>

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
				<c:forEach var="row" varStatus="loop" items="${court.friday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="friday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="friday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
										</c:when>
									</c:choose>
								</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.saturday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="saturday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="saturday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
										</c:when>
									</c:choose>
								</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.sunday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form
										action="${pageContext.request.contextPath}/bookCourt"
										method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="sunday" name="day" /> <input
											type="hidden" value="${court.id }" name="ttid" /> <input
											type="submit" value="Book">
									</form></td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner">${row}<c:choose>
										<c:when
											test="${name eq pageContext['request'].userPrincipal.name && row eq realname }">
											<form action="${pageContext.request.contextPath}/unbookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="sunday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Unbook">
											</form>
										</c:when>
									</c:choose>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</table>
		</td>
	</tr>
</table>
<table align="left">
	<tr>
		<td><c:if test="${not empty prev}">
				<sf:form method="post"
					action="${pageContext.request.contextPath}/changeCourt"
					commandName="timetable">
					<input type="hidden" value="${prev}" name="courtID" />
					<input value="Previous Week" type="submit" />
				</sf:form>
			</c:if></td>
	</tr>
</table>
<table align="right">
	<tr>
		<td><c:if test="${not empty next}">
				<sf:form method="post"
					action="${pageContext.request.contextPath}/changeCourt"
					commandName="timetable">
					<input type="hidden" value="${next}" name="courtID" />
					<input value="Next Week" type="submit" />
				</sf:form>
			</c:if></td>
	</tr>
</table>