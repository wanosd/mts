<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<table class = "members">
	<tr>
		<td>Court Name</td>
	</tr>
	
	<c:forEach var="row" items="${timetable}">
		<sf:form method="post"
			action="${pageContext.request.contextPath}/gotoCourt"
			commandName="timetable">
			<tr>
				<td><input type="hidden" value="${row.id}" name="courtID" />${row.name}</td>
				<td><input value="Select" type="submit" /></td>
			</tr>
		</sf:form>
	</c:forEach>
	</table>