<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<h2>News</h2>
	
	<c:forEach var="row" items="${news}">
	<table>
	
    <tr><td><u>${row.summary}</u></td>
    <tr><td>${row.content}</td></tr>
    <tr><td><i>${row.author}</i></td></tr>
	</table>
	<hr/>
	</c:forEach>
