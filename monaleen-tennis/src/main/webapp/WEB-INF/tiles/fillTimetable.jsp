<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h1>This is empty right now. Just checking if JSTL Works</h1>

<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/finalizeTimetable"
	commandName="timetable">
<c:forEach begin="0" end="${count - 1}" varStatus="loop">
<sf:select path="monday" value="monday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>
<sf:select path="tuesday" value="tuesday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>
<sf:select path="wednesday" value="wednesday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>
<sf:select path="thursday" value="thursday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>
<sf:select path="friday" value="friday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>
<sf:select path="saturday" value="saturday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>

<sf:select path="sunday" value="sunday${loop.index}">
<c:forEach var="row" items="${events}">
<sf:option value="${row.name}">${row.name}</sf:option>
</c:forEach>
</sf:select>

<hr>
</c:forEach>
<input value="Submit" type="submit"/>
</sf:form>