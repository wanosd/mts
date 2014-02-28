<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table class = "members" align="center">

	<tr>
		<th>Court Name</th>
		<th>Selection</th>
	</tr>
	
	<c:forEach var="row" items="${timetable}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/editTimetable"
			commandName="timetable">
			<tr>
				<td><input type="hidden" value="${row.series}" name="series" />${row.name}</td>
				<td><input value="Select" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
	</table>