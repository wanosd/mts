<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<center><h2>Members Email</h2></center>

<form id="details" method="post" action="${pageContext.request.contextPath}/sendAllEmail" >
<table class="formtable" align="center">
<tr><th>Subject</th><td><input name = "subject"  type="text"/></td></tr>
<tr><th>Message</th><td><textarea name = "message"></textarea></td></tr>
<tr><th>Send Email</th><td><input value="Send" type="submit"/></td></tr>
</table>
</form>