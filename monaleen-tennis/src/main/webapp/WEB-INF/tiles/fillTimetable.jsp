<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h1>This is empty right now. Just checking if JSTL Works</h1>
<c:out value="${timetable.name}" />
<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/finalizeTimetable"
	commandName="timetable">
	<table>
		<tr>
			<td>Start Time</td>
			<td>Monday</td>
			<td>Tuesday</td>
			<td>Wednesday</td>
			<td>Thursday</td>
			<td>Friday</td>
			<td>Saturday</td>
			<td>Sunday</td>
		</tr>

		<c:forEach begin="0" end="${count - 1}" varStatus="loop">
			<tr>
				<td>${timetable.startTime}</td>
				<td><input type="hidden" name="monday${loop.index}" /><sf:input name="monday${loop.index}" path="monday"
						type="text" /></td>
				<td><sf:input name="tuesday${loop.index}" path="tuesday"
						type="text" /></td>
				<td><sf:input name="wednesday${loop.index}" path="wednesday"
						type="text" /></td>
				<td><sf:input name="thursday${loop.index}" path="thursday"
						type="text" /></td>
				<td><sf:input name="friday${loop.index}" path="friday"
						type="text" /></td>
				<td><sf:input name="saturday${loop.index}" path="saturday"
						type="text" /></td>
				<td><sf:input name="sunday${loop.index}" path="sunday"
						type="text" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td><input value="Register" type="submit" /></td>
		</tr>
	</table>
</sf:form>
