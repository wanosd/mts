<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form method="post"
			action="${pageContext.request.contextPath}/timetableDefaults"
			commandName="timetable">
			
		Court Name: <sf:input name ="name" type="text" path="name"/>
			
		Number of Slots per Day <sf:input name ="slots" type="text" path="slots"/>
		
		Start Time <sf:input name ="startTime" type="text" path="startTime"/>
		
		End Time <sf:input name ="endTime" type="text" path="endTime"/>
		
		Total Weeks <sf:input name ="total" type="text" path="total"/>
		
		<input value="Create" type="submit"/>
			
</sf:form>