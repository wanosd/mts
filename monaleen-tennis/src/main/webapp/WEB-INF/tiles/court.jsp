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
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="monday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.tuesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="tuesday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.wednesday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="wednesday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.thursday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="thursday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.friday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="friday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
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
				<c:forEach var="row" varStatus="loop" items="${court.saturday}">
					<c:choose>
						<c:when test='${row eq "Free Court"}'>
							<tr>
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="saturday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="sunday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
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
								<td class="inner"><form action="${pageContext.request.contextPath}/bookCourt" method="POST">
										<input type="hidden" value="${loop.index}" name="position" />
										<input type="hidden" value="monday" name="day" />
										<input type="hidden" value="${court.id }" name="ttid" />
										<input type="submit" value="Book">
									</form>
									</td>
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