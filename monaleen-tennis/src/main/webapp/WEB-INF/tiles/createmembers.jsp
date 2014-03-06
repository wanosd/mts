<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/script/jquery.js"></script>
<script type="text/javascript">

function onLoad(){
	$("#password").keyup(checkPasswordsMatch);
	$("#con_password").keyup(checkPasswordsMatch);
	$("#details").submit(canSubmit);
}

function checkPasswordsMatch(){
	var password = $("#password").val();
	var con_password = $("#con_password").val();
	
	if (password.length < 3 || con_password.length < 3){
		return;
	}
	
	if (password == con_password){
		$("#matchpass").text("Passwords Match");
		$("#matchpass").removeClass("error");
		$("#matchpass").addClass("valid");
		
	}
	else if (password != con_password){
		$("#matchpass").text("Passwords Don't Match");
		$("#matchpass").removeClass("valid");
		$("#matchpass").addClass("error");	
	}
}

function canSubmit(){
	var password = $("#password").val();
	var con_password = $("#con_password").val();
	
	if (password == con_password){
		return true;
	}
	else{
		alert("Passwords Do Not Match!");
		return false;
	}
}

$(document).ready(onLoad);
</script>
<br>
<center>
<p>Please enter details. You will be contacted email when your account has been approved.</p>
<p> Please ensure that the correct email is entered, and take a note of your password</p>
</center>
<hr>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/register" commandName="member">
<table class="formtable" align="center">
<tr><th>Name</th><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><th>Password</th><td><sf:input id="password" name = "password" path="password" type="password"/><br/><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><th>Confirm Password</th><td><input id = "con_password" name = "con_password" type="password"/><div id="matchpass"></div></td></tr>
<tr><th>Email</th><td><sf:input name = "username" path="username" type="text"/><br/><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
<tr><th>Gender</th><td><sf:select name="gender" path="gender" class="select"><option value = "M">Male</option><option value = "F">Female</option></sf:select></td></tr>
<tr><th>Membership Type</th><td><sf:select name="member_type" path="member_type"><option value = "Senior">Senior</option><option value = "Junior">Junior</option><option value = "Student">Student</option></sf:select></td></tr>
<tr><th>Grade</th><td><sf:select name="grade" path="grade"><option value = "Graded">Graded</option><option value = "Intermediate">Intermediate</option><option value = "Beginner">Beginner</option></sf:select></td></tr>
<tr><th>Address Line 1</th><td><sf:input name = "ad_line1" path="ad_line1" type="text"/><br/><sf:errors path="ad_line1" cssClass="error"></sf:errors></td></tr>
<tr><th>Address Line 2</th><td><sf:input name = "ad_line2" path="ad_line2" type="text"/><br/><sf:errors path="ad_line2" cssClass="error"></sf:errors></td></tr>
<tr><th>City/Town</th><td><sf:input name = "ad_city" path="ad_city" type="text"/><br/><sf:errors path="ad_city" cssClass="error"></sf:errors></td></tr>
<tr><th>County</th><td><sf:input name = "ad_county" path="ad_county" type="text"/><br/><sf:errors path="ad_county" cssClass="error"></sf:errors></td></tr>
<tr><th>Contact Number</th><td><sf:input name = "contact_num" path="contact_num" type="text"/><br/><sf:errors path="contact_num" cssClass="error"></sf:errors></td></tr>
<tr><th>Emergency Contact Name</th><td><sf:input name = "em_con_name" path="em_con_name" type="text"/><br/><sf:errors path="em_con_name" cssClass="error"></sf:errors></td></tr>
<tr><th>Emergency Contact Number</th><td><sf:input name = "em_con_num" path="em_con_num" type="text"/><br/><sf:errors path="em_con_num" cssClass="error"></sf:errors></td></tr>
<tr><th>Register</th><td><input value="Register" type="submit"/></td></tr>
</table>
</sf:form>
<br>
<hr>
