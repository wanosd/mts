<h2>Stuff Admin needs to do</h2>

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
		<td>Delete New Story - TO DO!!</td>
		<td><a href="${pageContext.request.contextPath}/deleteTimetable"
			class="adminLink">Delete Timetable</a></td>
		<td><a href="${pageContext.request.contextPath}/viewEvents"
			class="adminLink">View and Enable Event</a></td>
	</tr>
	<%--Row 3 --%>
	<tr>
		<td>Edit Member Details - TO DO!</td>
		<td><a href="${pageContext.request.contextPath}/deleteTournament"
			class="adminLink">Delete Tournament</a></td>
		<td></td>
		<td><a href="${pageContext.request.contextPath}/reset"
			class="adminLink">Reset Timetable - Removes ALL bookings</a></td>
		<td>Remove Event - TO DO!</td>
	</tr>
	<%--Row 4 --%>
	<tr>
		<td>View All Member Details</td>
		<td>Tournament Results/Groups or something, perhaps?</td>
		<td></td>
		<td><a href="${pageContext.request.contextPath}/timetableStatus"
			class="adminLink">Timetable Status</a></td>
		<td></td>
	</tr>
	<%--Row 5 --%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td><a href="${pageContext.request.contextPath}/chooseEdit"
			class="adminLink">Edit Timetable</a></td>
		<td></td>
	</tr>

</table>