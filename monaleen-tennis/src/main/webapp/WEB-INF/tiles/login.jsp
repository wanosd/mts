<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body onload='document.f.j_username.focus();'>
	<center><h3>Login with Username and Password</h3></center>
	<c:if test="${param.error != null }">
	<p class = "errorLogin">
	Login Error
	</p>
	
	</c:if>
	
	<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<table class="formtable" align="center">
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' /></td>
			</tr>
			<tr>
				<td>Remember Me?</td>
				<td><input type='checkbox' name='_spring_security_remember_me' checked="checked" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>
</body>
