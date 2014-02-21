<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h2>Results</h2>

	<table class="members">
		<tr>
			<th>ID</th>
			<th>Summary</th>
			<th>Content</th>
			<th>Author</th>
		</tr>
		<c:forEach var="row" items="${news}">
			<sf:form method="post" action="${pageContext.request.contextPath}/confirmNewsDelete" commandName="news">
				<tr>
					<td><input type="hidden" value="${row.id}"
						name="newsID" />${row.id}</td>
					<td>${row.summary}</td>
					<td>${row.author}</td>
					<td><input value="Delete" type="submit"
						name="${row.id}" />
			</sf:form>

		</c:forEach>
	</table>


