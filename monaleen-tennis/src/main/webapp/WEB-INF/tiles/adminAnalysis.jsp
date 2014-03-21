<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<center><h2>User NoShows</h2></center>
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
<center><h2>Current Court Bookings</h2></center>

<table align="center">
	<tr>
		<th>Day</th>
		<th>Free Slots</th>
		<th>User Bookings</th>
		<th>Club Bookings</th>
	
	</tr>
	<tr><th>Monday</th>
	<c:forEach var="entry" items="${monday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Tuesday</th>
	<c:forEach var="entry" items="${tuesday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Wednesday</th>
	<c:forEach var="entry" items="${wednesday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Thursday</th>
	<c:forEach var="entry" items="${thursday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Friday</th>
	<c:forEach var="entry" items="${friday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Saturday</th>
	<c:forEach var="entry" items="${saturday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Sunday</th>
	<c:forEach var="entry" items="${sunday}">
	<td>${entry.value}</td>
	</c:forEach></tr>
</table>

<c:if test="${not empty nextmon }">
<center><h2>Next Week Bookings</h2></center>

<table align="center">
	<tr>
		<th>Day</th>
		<th>Free Slots</th>
		<th>User Bookings</th>
		<th>Club Bookings</th>
	
	</tr>
	<tr><th>Monday</th>
	<c:forEach var="entry" items="${nextmon}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Tuesday</th>
	<c:forEach var="entry" items="${nexttue}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Wednesday</th>
	<c:forEach var="entry" items="${nextwed}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Thursday</th>
	<c:forEach var="entry" items="${nextthur}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Friday</th>
	<c:forEach var="entry" items="${nextfri}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Saturday</th>
	<c:forEach var="entry" items="${nextsat}">
	<td>${entry.value}</td>
	</c:forEach></tr>
	<tr><th>Sunday</th>
	<c:forEach var="entry" items="${nextsun}">
	<td>${entry.value}</td>
	</c:forEach></tr>
</table>
</c:if>
