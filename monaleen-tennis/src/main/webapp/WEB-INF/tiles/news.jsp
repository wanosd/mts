<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<h2>News</h2>
	<table width="500">
	<tr><td>Summary</td><td>Content</td><td>Posted By</td></tr>
	<c:forEach var="row" items="${news}">
    <tr><td>${row.summary}</td>
    <td>${row.content}</td>
    <td><a href = "mailto:${row.author}">${row.author}</a></td>

	</c:forEach>
	</table>
	