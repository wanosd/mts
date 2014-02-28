<h2>Stuff Admin needs to do</h2>

Bugs / Major To Do
<ul>
<li><s>Fix Booking Bug when changing roles</s></li>
<li><s>Export to XLS</s></li>
<li><s>No Shows</s></li>
<li><s>Data Usage - Pending Number - Overview of free slots for admin</s></li>
<li>Advertising Slot</li>
<li><s>Assign Members to groups</s></li>
<li>Assign no bookings to groups</li>
<li>WAMP - ref architecutre for Spring.</li>
<li>Hibernate</li>
</ul>



<table>
	<tr>
		<th>Member Tasks</th>
		<th>Tournament Tasks</th>
		<th>News Tasks</th>
		<th>Timetable Tasks</th>
		<th>Event Tasks</th>
	</tr>
	<%-- Row 1 --%>
	<tr>
		<td><a href="${pageContext.request.contextPath}/approveMembers"
			class="adminLink">Approve Member</a></td>
		<td><a href="${pageContext.request.contextPath}/createTournament"
			class="adminLink">Create Tournament</a></td>
		<td><a href="${pageContext.request.contextPath}/createNews"
			class="adminLink">Add News Story</a></td>
		<td><a href="${pageContext.request.contextPath}/createTimetable"
			class="adminLink">Create Timetable</a></td>
		<td><a href="${pageContext.request.contextPath}/createEvent"
			class="adminLink">Create Event</a></td>
	</tr>
	<%--Row 2 --%>
	<tr>
		<td><a href="${pageContext.request.contextPath}/blockMembers"
			class="adminLink">Block Member</a></td>
		<td><a href="${pageContext.request.contextPath}/tournamentStatus"
			class="adminLink">Enable/Disable Tournament</a></td>
		<td><a href="${pageContext.request.contextPath}/deleteNews"
			class="adminLink">Delete News Story</a></td>
		<td><a href="${pageContext.request.contextPath}/deleteTimetable"
			class="adminLink">Delete Timetable</a></td>
		<td><a href="${pageContext.request.contextPath}/viewEvents"
			class="adminLink">View and Enable Event</a></td>
	</tr>
	<%--Row 3 --%>
	<tr>
		<td><a href="${pageContext.request.contextPath}/displayUsers"
			class="adminLink">Edit Member</a></td>
		<td><a href="${pageContext.request.contextPath}/deleteTournament"
			class="adminLink">Delete Tournament</a></td>
		<td>Edit News Story - TO DO!</td>
		<td><a href="${pageContext.request.contextPath}/reset"
			class="adminLink">Reset Current Timetable - Removes ALL bookings (To Fix)</a></td>
		<td>Remove Event - TO DO!</td>
	</tr>
	<%--Row 4 --%>
	<tr>
		<td><a href="${pageContext.request.contextPath}/viewAllMembers"
			class="adminLink">View All Member Details</a></td>
		<td>Tournament Results/Groups or something, perhaps?</td>
		<td></td>
		<td><a href="${pageContext.request.contextPath}/timetableStatus"
			class="adminLink">Timetable Status</a></td>
		<td></td>
	</tr>
	<%--Row 5 --%>
	<tr>
		<td><a href="${pageContext.request.contextPath}/createNewRole"
			class="adminLink">Manage Roles</a></td></td>
		<td></td>
		<td></td>
		<td><a href="${pageContext.request.contextPath}/seriesChoice"
			class="adminLink">Edit Timetable</a></td>
		<td></td>
	</tr>
		<%--Row 6 --%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><a href="${pageContext.request.contextPath}/adminAnalysis"
			class="adminLink">Timetable Analysis</a></td>
		<td></td>
	</tr>

</table>