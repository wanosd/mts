<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h1>This is empty right now. Just checking if JSTL Works</h1>

<c:forEach begin="0" end="${count - 1}" varStatus="loop"> 
		 Index: ${loop.index}<br/>
</c:forEach>
