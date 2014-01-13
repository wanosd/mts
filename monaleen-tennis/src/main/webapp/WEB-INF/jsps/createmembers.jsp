<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Member</title>
</head>
<body>


<form method="post" action="${pageContext.request.contextPath}/register">
<table>
<tr><td>Name</td><td><input name = "name" type="text"/></td></tr>
<tr><td>Password</td><td><input name = "password" type="password"/></td></tr>
<tr><td>Email</td><td><input name = "email" type="text"/></td></tr>
<tr><td>Gender</td><td><select name="gender"><option value = "M">Male</option><option value = "F">Female</option></select></td></tr>
<tr><td>Membership Type</td><td><select name="member_type"><option value = "Senior">Senior</option><option value = "Junior">Junior</option><option value = "Student">Student</option></select></td></tr>
<tr><td>Grade</td><td><select name="grade"><option value = "Graded">Graded</option><option value = "Intermediate">Intermediate</option><option value = "Beginner">Beginner</option></select></td></tr>
<tr><td>Address Line 1</td><td><input name = "ad_line1" type="text"/></td></tr>
<tr><td>Address Line 2</td><td><input name = "ad_line2" type="text"/></td></tr>
<tr><td>City/Town</td><td><input name = "ad_city" type="text"/></td></tr>
<tr><td>County</td><td><input name = "ad_county" type="text"/></td></tr>
<tr><td>Contact Number</td><td><input name = "contact_num" type="text"/></td></tr>
<tr><td>Emergency Contact Name</td><td><input name = "em_con_name" type="text"/></td></tr>
<tr><td>Emergency Contact Number</td><td><input name = "em_con_num" type="text"/></td></tr>
<tr><td><input value="Register" type="submit"/></td></tr>

</table>
</form>



</body>
</html>