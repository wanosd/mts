<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table align="center">
	<tr>
		<th>Username</th>
		<th>Number of NoShows Reported</th>
		<th>Action Required</th>
	</tr>

	<c:forEach var="entry" items="${map}">
		<tr>
			<td><c:out value="${entry.key}" /></td>
			<c:choose>
				<c:when test="${entry.value > 5}">
					<td bgcolor="red"><font
						color="white"><c:out value="${entry.value}"/></font></td><td>Warn User and Change Status</td>
    </c:when> 
    <c:otherwise> 
        <c:choose>
            <c:when test="${entry.value > 2 and entry.value < 6}"> 
                <td bgcolor="orange"><font color="white"><c:out value="${entry.value}"/></font></td><td>Warn User</td>
            </c:when> 
            <c:otherwise> 
               <td bgcolor="green"><font color="white"><c:out value="${entry.value}"/></font></td><td>No Action Needed</td>
            </c:otherwise>
        </c:choose>
    </c:otherwise> 
</c:choose></tr>
</c:forEach>
</table>