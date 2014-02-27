<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<table align="center">
<sf:form method="post"
			action="${pageContext.request.contextPath}/timetableDefaults"
			commandName="timetable">
			
		<tr><th>Court Name</th><td><sf:input name ="name" type="text" path="name"/></td></tr>
			
		<tr><th>Slots per Day</th><td><sf:input name ="slots" type="text" path="slots"/><td></tr>
		
		<tr><th>Start Time</th><td><sf:input name ="startTime" type="text" path="startTime"/></td></tr>
		
		<tr><th>End Time</th><td><sf:input name ="endTime" type="text" path="endTime"/></td></tr>
		
		<tr><th>Total Weeks</th><td> <sf:select path="total"><option value="52">52 Weeks</option></sf:select></td></tr>
		
		<tr><th>Weeks to Display</th><td><sf:input name ="prev" type="text" path="prev"/></td></tr>
		
		<tr><th>Action</th><td><input value="Create" type="submit"/></td></tr>
			
</sf:form>
</table>