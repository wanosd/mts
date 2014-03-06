
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<h1>Create News</h1>
<sf:form id="details" method="post"
	action="${pageContext.request.contextPath}/saveNewsStory"
	commandName="news">
	<table class="formtable" align="center">
		<tr>
			<th>Summary</th>
			<td><sf:input name="summary" path="summary"
					type="text" /><br />
			<sf:errors path="summary" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<th>Content</th>
			<td><sf:textarea name="content" path="content"
					type="text" /><br />
			<sf:errors path="content" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
		<th>Action</th>
			<td><input value="Publish" type="submit" /></td>
		</tr>

	</table>
</sf:form>

