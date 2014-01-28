<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a href="" class="title">Monaleen GAA Tennis Club</a>

<sec:authorize access="!isAuthenticated()">
	<a href="${pageContext.request.contextPath}/createmembers" class="register">Register</a>
	<a href="<c:url value ='/login' />" class="login">Log In</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value ='/j_spring_security_logout' />" class="login">Log
		Out</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/admin" class="admin">Admin</a>
</sec:authorize></br>