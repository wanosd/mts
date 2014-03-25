<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<center><h2>News</h2></center>
	<hr>
	
	<c:forEach var="row" items="${news}">
	<table align="center" width="600px" border="1">
	
    <tr><th>${row.summary}</th></tr>
    <tr><td>${row.content}</td></tr>
    <tr><td>Posted By: <i>${row.author}</i></td></tr>
	</table>
	</br>
	</c:forEach>
