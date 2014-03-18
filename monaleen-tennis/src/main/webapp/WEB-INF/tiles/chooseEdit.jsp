<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table class = "members" align="center">


	<tr>
		<th>Court Name</th>
		<th>Selection</th>
	</tr>
	<tr>
	<c:forEach var="row" items="${timetable}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/editTimetable"
			commandName="timetable">
			
				<td><input type="hidden" value="${row.id}" name="courtID" />${row.name}</td>
				<td><input value="Select" type="submit" /></td>
			
		</sf:form>
		
	</c:forEach>
	</tr>
	</table>