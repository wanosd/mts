<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form id="details" method="post" action="${pageContext.request.contextPath}/saveEvent" commandName="event">
<table class="formtable">
<tr><td>Name of Event: </td><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
</table>
</sf:form>