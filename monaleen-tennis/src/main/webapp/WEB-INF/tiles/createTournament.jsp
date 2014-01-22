<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/registerTournament" commandName="tournament">
<table class="formtable">
<tr><td>Tournament Name</td><td><sf:input name = "tournamentName" path="tournamentName" type="text"/><br/></td></tr>
<tr><td>Tournament Gender</td><td><sf:select name="tournamentGender" path="tournamentGender" class="select"><option value = "MIXED">Mixed Tournament</option><option value = "M">Mens Tournament</option><option value = "F">Ladies Tournament</option></sf:select></td></tr>
<tr><td>Tournament Type</td><td><sf:select name="tournamentType" path="tournamentType"><option value = "S">Singles</option><option value = "D">Doubles</option></sf:select></td></tr>
<tr><td>Tournament Category</td><td><sf:select name="tournamentCategory" path="tournamentCategory"><option value = "Open">Open Tournament</option><option value = "Senior">Senior Members Tournament</option><option value = "Junior">Junior Members Tournament</option></sf:select></td></tr>
<tr><td><input value="Create Tournament" type="submit"/></td></tr>

</table>
</sf:form>

