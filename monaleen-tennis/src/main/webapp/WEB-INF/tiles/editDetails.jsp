<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<link href= "${pageContext.request.contextPath }/static/css/style.css" rel="stylesheet" type="text/css">
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

	<table class="members">
	<c:forEach var="row" items="${user}">
    <tr><td>Name: </td><td>${row.name}</td></tr>
    <tr><td>Email: </td><td>${row.username}</td></tr>
    <tr><td>Contact Number: </td><td>${row.contact_num}</td></tr>
	</c:forEach>
	</table>


	MOVE THIS LATER
	
	<table class="members">
	<tr><td>Name</td><td>Grade</td><td>Email</td><td>Member Type</td><td>Contact Number</td></tr>
	<c:forEach var="row" items="${user}">
    <tr><td>${row.name}</td>
    <td>${row.grade}</td>
    <td><a href = "mailto:${row.username}">Email</a></td>
    <td>${row.member_type}</td>
    <td>${row.contact_num}</td>
    </tr>
	</c:forEach>
	</table>