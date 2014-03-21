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

<div class="green_box">
	<div class="clock">
		<img
			src="${pageContext.request.contextPath}/static/css/images/green/clock.png"
			alt="" title="">
	</div>
	<div class="text_content">
		<h1>Final Year Project - Chris O'Brien</h1>
		<p class="green">The goal of this Final Year Project is to develop a Web Application with a clearly defined stack in order to better support non functional requirements. For the scope
		of this project, the NFRs being looked at are:
		<ul>
		<li>Security</li>
		<li>Productivity</li>
		<li>Extensibility</li>
		<li>Maintainability</li>
		<li>Usability</li>
		</ul></p>
		<div class="read_more">
			<a href="#">read more</a>
		</div>
	</div>

	<div id="right_nav">
		<ul>
			<li><a href="home.html" title="">Links to stuff</a></li>
			<li><a href="services.html" title="">Links to stuff</a></li>
			<li><a class="current" href="#" title="">Links to stuff</a></li>
			<li><a href="#" title="">Links to stuff</a></li>
			<li><a href="contact.html" title="">Links to stuff</a></li>
		</ul>
	</div>


</div>
<!--end of green box-->
