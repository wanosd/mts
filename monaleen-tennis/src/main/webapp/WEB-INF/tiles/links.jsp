<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="links">
	<ul class="linkslist">
		<li><a href="${pageContext.request.contextPath}/" class="home">Home</a></li>
		<li><sec:authorize access="isAuthenticated()">
				<a href="${pageContext.request.contextPath}/members" class="home">Members</a>
			</sec:authorize></li>
		<li><sec:authorize access="isAuthenticated()">
				<a href="${pageContext.request.contextPath}/profile" class="home">Edit
					Details</a>
			</sec:authorize></li>
			<li><sec:authorize access="!isAuthenticated()">
				<a href="${pageContext.request.contextPath}/register" class="home">Register</a>
			</sec:authorize></li>
			<li><sec:authorize access="!isAuthenticated()">
				<a href="${pageContext.request.contextPath}/" class="home">Blank Page</a>
			</sec:authorize></li>
		<li><a href="" class="home">Membership</a></li>
		<li><a href="${pageContext.request.contextPath}/tournaments" class="home">Tournaments</a></li>
		<li><a href="${pageContext.request.contextPath}/news" class="home">News</a></li>
		<li><a href="" class="home">Training</a></li>
		<li><a href="" class="home">FAQ/Contact</a></li>
	</ul>
</div>