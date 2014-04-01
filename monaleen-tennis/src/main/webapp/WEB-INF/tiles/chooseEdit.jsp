<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table class="members" align="center">


	<tr>
		<th>Court Name</th>
		<th>Selection</th>
	</tr>
	<c:forEach var="row" items="${timetable}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/editTimetable"
			commandName="timetable">

			<c:if test="${(loop.index % 4) == 0 && loop.index ne 0}">
				<tr>
			</c:if>
			<td><input type="hidden" value="${row.id}" name="courtID" />${row.name}</td>
			<td><input value="Select" type="submit" /></td>

			<c:if test="${(loop.index % 4) == 0 && loop.index ne 0}">
				</tr>
			</c:if>
		</sf:form>
		</select>
	</c:forEach>
</table>