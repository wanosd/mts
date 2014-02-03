<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>This is empty right now. Just checking if JSTL Works</h1>

<table>
<b><tr><td>Monday</td><td>Tuesday</td><td>Wednesday</td><td>Thursday</td><td>Friday</td><td>Saturday</td><td>Sunday</td></tr></b>
<c:forEach begin="0" end="${count - 1}" varStatus="loop"> 
		<tr><td>Monday ${loop.index}</td><td>Tuesday ${loop.index}</td><td>Wednesday ${loop.index}</td><td>Thursday ${loop.index}</td><td>Friday ${loop.index}</td><td>Saturday ${loop.index}</td><td>Sunday ${loop.index } </td></tr>
		
</c:forEach>
</table>