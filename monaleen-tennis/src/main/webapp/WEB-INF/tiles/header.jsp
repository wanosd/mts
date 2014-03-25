<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="logo">
	<table>
		<tr>
			<sec:authorize access="isAuthenticated()"><td>
					<a href="${pageContext.request.contextPath}/members" class="home">members</a>
					</td>
				</sec:authorize>
			<sec:authorize access="isAuthenticated()"><td>
					<a href="${pageContext.request.contextPath}/profile" class="home">edit
						details</a></td>
				</sec:authorize>
			<sec:authorize access="!isAuthenticated()"><td>
					<a href="${pageContext.request.contextPath}/createmembers"
						class="register" class="home">register</a></td>
						<td>
					<a href="<c:url value ='/login' />" class="login">log in</a></td>
				</sec:authorize> 
				<sec:authorize access="isAuthenticated()"><td>
					<a href="<c:url value ='/j_spring_security_logout' />"
						class="login" class="home">log out</a></td>
				</sec:authorize>
			<sec:authorize access="hasRole('ROLE_ADMIN')"><td>
					<a href="${pageContext.request.contextPath}/admin" class="admin"
						class="home">admin</a></td>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_COMMITTEE')"><td>
					<a href="${pageContext.request.contextPath}/admin" class="admin"
						class="home">committee</a></td>
				</sec:authorize>
		</tr>
	</table>

	<a href="https://spring.io/"><img src="${pageContext.request.contextPath}/static/css/images/spring-logo.jpg"
		alt="" title="" border="0"></a> 
		<a href="http://hibernate.org/"><img
		src="${pageContext.request.contextPath}/static/css/images/hibernate-logo.png"
		alt="" title="" border="0"></a> <a href="http://tiles.apache.org/"><img
		src="${pageContext.request.contextPath}/static/css/images/tiles-logo.png"
		alt="" title="" border="0"></a> <a href="http://tomcat.apache.org/"><img
		src="${pageContext.request.contextPath}/static/css/images/tomcat-logo.jpg"
		alt="" title="" border="0"></a>
</div>

<div id="menu" align="left">
	<ul>
		<li><a href="${pageContext.request.contextPath}/" class="home">home</a></li>
		<li class="linkslist"><a
			href="${pageContext.request.contextPath}/timetable" class="home">timetable</a></li>
		<li class="linkslist"><a href="${pageContext.request.contextPath}/membership" class="home">membership</a></li>
		<li class="linkslist"><a
			href="${pageContext.request.contextPath}/tournaments" class="home">tournaments</a></li>
		<li class="linkslist"><a
			href="${pageContext.request.contextPath}/news" class="home">news</a></li>
		<li class="linkslist"><a href="" class="home">training</a></li>
		<li class="linkslist"><a href="${pageContext.request.contextPath}/contactus" class="home">faq/contact</a></li>

	</ul>
</div>


