<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<table align="center">
	<tr>
		<th>Series ID</th>
		<th>Series Name</th>
		<th>Edit</th>
		<th>Reset</th>
		<th>Edit Look Ahead</th>
	</tr>

	<c:forEach var="entry" items="${map}">
			<tr><td><c:out value="${entry.key}" /></td>
			<td><c:out value="${entry.value}" /></td>
			<td><sf:form method="post" action="${pageContext.request.contextPath}/chooseEdit" commandName="timetable"><input type="hidden" name="seriesID" value="${entry.key }"><input type="submit" value="Choose"></sf:form></td>
			<td><sf:form method="post" action="${pageContext.request.contextPath}/reset" commandName="timetable"><input type="hidden" name="seriesID" value="${entry.key }"><input type="submit" value="Choose"></sf:form></td></tr>
			<td><sf:form method="post" action="${pageContext.request.contextPath}/editLookAhead" commandName="timetable"><input type="hidden" name="seriesID" value="${entry.key }"><input type="submit" value="Choose"></sf:form></td></tr>
	
	</c:forEach>

</table>