<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="links">
	<ul class="linkslist">
		<li><sec:authorize access="isAuthenticated()">
				<a href="${pageContext.request.contextPath}/members">View
					Members</a>
			</sec:authorize></li>
		<li><sec:authorize access="isAuthenticated()">
				<a href="${pageContext.request.contextPath}/profile">Edit
					Details</a>
			</sec:authorize></li>
		<li><a href="">Membership</a></li>
		<li><a href="${pageContext.request.contextPath}/tournaments">Tournaments</a></li>
		<li><a href="${pageContext.request.contextPath}/news">News</a></li>
		<li><a href="">Training</a></li>
		<li><a href="">FAQ/Contact</a></li>
	</ul>
</div>