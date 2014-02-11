<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h1>This is empty right now. Just checking if JSTL Works</h1>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/finalizeTimetable"
	commandName="timetable">
		<input type="hidden" value="${timetable.id }" id="timetableID">
	
		
	<c:forEach begin="0" end="${count - 1}" varStatus="loop">
		<input type="hidden" value="${count }" id="slots">
		<sf:select path="monday[${loop.index}]" id="monday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		
		<sf:select path="tuesday[${loop.index}]" id="tuesday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		
		<sf:select path="wednesday[${loop.index}]" id="wednesday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		
		<sf:select path="thursday[${loop.index}]" id="thursday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		
		<sf:select path="friday[${loop.index}]" id="friday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		
		<sf:select path="saturday[${loop.index}]" id="saturday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		
		<sf:select path="sunday[${loop.index}]" id="sunday${loop.index }" multiple="false">
			<sf:options items="${events }"/>
		</sf:select>
		<hr>
	</c:forEach>
	<input value="Submit" type="submit" />
</sf:form>