<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="logo">
	<table>
		<tr>
			<td><sec:authorize access="isAuthenticated()">
					<a href="${pageContext.request.contextPath}/members" class="home">members</a>
				</sec:authorize></td>
			<td><sec:authorize access="isAuthenticated()">
					<a href="${pageContext.request.contextPath}/profile" class="home">edit
						details</a>
				</sec:authorize></td>
			<td><sec:authorize access="!isAuthenticated()">
					<a href="${pageContext.request.contextPath}/createmembers"
						class="register" class="home">register</a>
					<a href="<c:url value ='/login' />" class="login">log in</a>
				</sec:authorize> <sec:authorize access="isAuthenticated()">
					<a href="<c:url value ='/j_spring_security_logout' />"
						class="login" class="home">log out</a>
				</sec:authorize></td>
			<td><sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/admin" class="admin"
						class="home">admin</a>
				</sec:authorize></td>
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

		<li class="linkslist"><a href="" class="home">membership</a></li>
		<li class="linkslist"><a
			href="${pageContext.request.contextPath}/tournaments" class="home">tournaments</a></li>
		<li class="linkslist"><a
			href="${pageContext.request.contextPath}/news" class="home">news</a></li>
		<li class="linkslist"><a href="" class="home">training</a></li>
		<li class="linkslist"><a href="" class="home">faq/contact</a></li>

	</ul>
</div>

<div class="green_box">
	<div class="clock">
		<img
			src="${pageContext.request.contextPath}/static/css/images/green/clock.png"
			alt="" title="">
	</div>
	<div class="text_content">
		<h1>What is your biological clock?</h1>
		<p class="green">"Lorem ipsum dolor sit amet, consectetur
			adipisicing elit, sed do eiusmod tempor incididunt ut labore et
			dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
			exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
			Duis aute irure dolor in reprehenderit in voluptate velit esse cillum
			dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat
			non proident, sunt in culpa qui officia deserunt mollit anim id est
			laborum."</p>
		<div class="read_more">
			<a href="#">read more</a>
		</div>
	</div>

	<div id="right_nav">
		<ul>
			<li><a href="home.html" title="">Lorem ipsum dolor sit amet
					cons</a></li>
			<li><a href="services.html" title="">Lorem ipsum dolor sit
					amet cons</a></li>
			<li><a class="current" href="#" title="">Lorem ipsum dolor
					sit amet cons</a></li>
			<li><a href="#" title="">Lorem ipsum dolor sit amet cons</a></li>
			<li><a href="contact.html" title="">Lorem ipsum dolor sit
					amet cons</a></li>
		</ul>
	</div>


</div>
<!--end of green box-->

<%--
<sec:authorize access="!isAuthenticated()">
	<a href="${pageContext.request.contextPath}/createmembers" class="register" class="home">Register</a>
	<a href="<c:url value ='/login' />" class="login">Log In</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value ='/j_spring_security_logout' />" class="login" class="home">Log
		Out</a>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/admin" class="admin" class="home">Admin</a>
</sec:authorize>
</div>
 --%>