<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

You have ${bookings } bookings left! Position: ${position } Week: ${week }
<table align="center">
	<tr>
		<th>Court Name</th>
		<th>Current Week</th>
		<th>Timetable for Week Beginning</th>
	</tr>
	<tr>
		<td>${court.name }</td>
		<td><form id="timetable" action="/monaleen-tennis/gotoCourt"
				method="post">
				<input type="hidden" value="${root}" name="courtID" /> <input
					value="${date }" type="submit" />
			</form></td>
		<td>${testDate }</td>
	</tr>
</table>
<table width="100%">
	<tr>
		<td class="nooutline">
			<table>
				<tr>
					<th>Time</th>
				</tr>

				<c:forEach var="row" varStatus="loop" items="${court.monday}">

					<tr class="timetable">
						<th class="inner">${loop.index+8}to ${loop.index+9.5}</th>
					</tr>
				</c:forEach>
			</table>
		</td>

		<td class="nooutline">
			<table>
				<tr>
					<th>Monday</th>
				</tr>

				<c:forEach var="row" varStatus="loop" items="${court.monday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">
								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 1 && position <= week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a842">
											<form action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="monday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form>
										</td>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
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
										</c:when>

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="monday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td class="nooutline">
			<table>
				<tr>
					<th>Tuesday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.tuesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">

								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 2 && position <= week}">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a852">
											<form action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="tuesday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form>
										</td>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
									<c:choose>
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

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="tuesday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>

		<td class="nooutline">
			<table>
				<tr>
					<th>Wednesday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.wednesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">
								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 3 && position <= week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a862"><form
												action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="wednesday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
									<c:choose>
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

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="wednesday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>

		<td class="nooutline">
			<table>
				<tr>
					<th>Thursday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.thursday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">
								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 4 && position <= week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a872"><form
												action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="thursday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
									<c:choose>
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

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="thursday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>

							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td class="nooutline">
			<table>
				<tr>
					<th>Friday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.friday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">
								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 5 && position <= week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a882"><form
												action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="friday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
									<c:choose>
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

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="friday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td class="nooutline">
			<table>
				<tr>
					<th>Saturday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.saturday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">
								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 6 && position <= week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a892"><form
												action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="saturday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
									<c:choose>
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

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="saturday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</table>
		</td>
		<td class="nooutline">
			<table>
				<tr>
					<th>Sunday</th>
				</tr>
				<c:forEach var="row" varStatus="loop" items="${court.sunday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr class="timetable">
								<c:choose>
									<c:when test="${position < week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:when test="${dayOfWeek > 7 && position <= week }">
										<td class="inner" bgcolor="#000000"><font color="white">-------</font></td>
									</c:when>
									<c:otherwise>
										<td class="inner" bgcolor="#50a899"><form
												action="${pageContext.request.contextPath}/bookCourt"
												method="POST">
												<input type="hidden" value="${loop.index}" name="position" />
												<input type="hidden" value="sunday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Book">
											</form></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:when>
						<c:otherwise>
							<tr class="timetable">
								<td class="inner" bgcolor="#D94330"><font color="white">${row}</font>
									<c:choose>
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

										<c:otherwise>
											<form
												action="${pageContext.request.contextPath}/reportNoShow"
												method="POST">
												<input type="hidden" value="${row}" name="bookedUser" /> <input
													type="hidden" value="sunday" name="day" /> <input
													type="hidden" value="${court.id }" name="ttid" /> <input
													type="submit" value="Report User">
											</form>
										</c:otherwise>
									</c:choose></td>
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
					<input type="hidden" value="${court.prev}" name="prev" />
					<input type="hidden" value="${prev}" name="courtID" />
					<input type="hidden" value="${root}" name="root" />
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
					<input type="hidden" value="${court.prev}" name="prev" />
					<input type="hidden" value="${next}" name="courtID" />
					<input type="hidden" value="${root}" name="root" />
					<input value="Next Week" type="submit" />
				</sf:form>
			</c:if></td>
	</tr>
</table>