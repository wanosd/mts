<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/registerTournament" commandName="tournament">
<table class="formtable" align="center">
<tr><th>Tournament Name</th><td><sf:input name = "tournamentName" path="tournamentName" type="text"/><br/><sf:errors path="tournamentName" cssClass="error"></sf:errors></td></tr>
<tr><th>Tournament Gender</th><td><sf:select name="tournamentGender" path="tournamentGender" class="select"><option value = "MIXED">Mixed Tournament</option><option value = "M">Mens Tournament</option><option value = "F">Ladies Tournament</option></sf:select></td></tr>
<tr><th>Tournament Type</th><td><sf:select name="tournamentType" path="tournamentType"><option value = "S">Singles</option><option value = "D">Doubles</option></sf:select></td></tr>
<tr><th>Tournament Category</th><td><sf:select name="tournamentCategory" path="tournamentCategory"><option value = "Open">Open Tournament</option><option value = "Senior">Senior Members Tournament</option><option value = "Junior">Junior Members Tournament</option></sf:select></td></tr>
<tr><th>Action</th><td><input value="Create Tournament" type="submit"/></td></tr>

</table>
</sf:form>

