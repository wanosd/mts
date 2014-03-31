<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form id="details" method="post" action="${pageContext.request.contextPath}/saveEvent" commandName="event">
<table class="formtable" align="center">
<tr><th>Event Name</th><th>Action</th></tr>
<tr><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td><td><input value="Create" type="submit"/></td></tr>
</table>
</sf:form>