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
regis
</script>

<sf:form id="details" method="post" action="${pageContext.request.contextPath}/register" commandName="member">
<table class="formtable">
<tr><td>Name</td><td><sf:input name = "name" path="name" type="text"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td>Password</td><td><sf:input id="password" name = "password" path="password" type="password"/><br/><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><td>Confirm Password</td><td><input id = "con_password" name = "con_password" type="password"/><div id="matchpass"></div></td></tr>
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
