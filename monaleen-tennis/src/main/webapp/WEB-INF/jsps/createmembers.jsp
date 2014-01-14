<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href= "${pageContext.request.contextPath }/static/css/style.css" rel="stylesheet" type="text/css">
<title>Create Member</title>
</head>
<body>


<sf:form method="post" action="${pageContext.request.contextPath}/register" commandName="member">
<table class="formtable">
<tr><td>Name</td><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td>Password</td><td><sf:input name = "password" path="password" type="password"/><br/><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><td>Email</td><td><sf:input name = "username" path="username" type="text"/><br/><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
<tr><td>Gender</td><td><sf:select name="gender" path="gender" class="select"><option value = "M">Male</option><option value = "F">Female</option></sf:select></td></tr>
<tr><td>Membership Type</td><td><sf:select name="member_type" path="member_type"><option value = "Senior">Senior</option><option value = "Junior">Junior</option><option value = "Student">Student</option></sf:select></td></tr>
<tr><td>Grade</td><td><sf:select name="grade" path="grade"><option value = "Graded">Graded</option><option value = "Intermediate">Intermediate</option><option value = "Beginner">Beginner</option></sf:select></td></tr>
<tr><td>Address Line 1</td><td><sf:input name = "ad_line1" path="ad_line1" type="text"/><br/><sf:errors path="ad_line1" cssClass="error"></sf:errors></td></tr>
<tr><td>Address Line 2</td><td><sf:input name = "ad_line2" path="ad_line2" type="text"/><br/><sf:errors path="ad_line2" cssClass="error"></sf:errors></td></tr>
<tr><td>City/Town</td><td><sf:input name = "ad_city" path="ad_city" type="text"/><br/><sf:errors path="ad_city" cssClass="error"></sf:errors></td></tr>
<tr><td>County</td><td><sf:input name = "ad_county" path="ad_county" type="text"/><br/><sf:errors path="ad_county" cssClass="error"></sf:errors></td></tr>
<tr><td>Contact Number</td><td><sf:input name = "contact_num" path="contact_num" type="text"/><br/><sf:errors path="contact_num" cssClass="error"></sf:errors></td></tr>
<tr><td>Emergency Contact Name</td><td><sf:input name = "em_con_name" path="em_con_name" type="text"/><br/><sf:errors path="em_con_name" cssClass="error"></sf:errors></td></tr>
<tr><td>Emergency Contact Number</td><td><sf:input name = "em_con_num" path="em_con_num" type="text"/><br/><sf:errors path="em_con_num" cssClass="error"></sf:errors></td></tr>
<tr><td><input value="Register" type="submit"/></td></tr>

</table>
</sf:form>



</body>
</html>