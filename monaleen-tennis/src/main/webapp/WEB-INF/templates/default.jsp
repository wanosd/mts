<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href= "${pageContext.request.contextPath }/static/css/images/green/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/script/jquery.js"></script>	
<title><tiles:insertAttribute name="title"></tiles:insertAttribute>
</title>
</head>
<body>
<div id="main_container">

<div id="header" align="left">
<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>


<div id="adbanner">
<tiles:insertAttribute name="adbanner"></tiles:insertAttribute>
</div>
<hr/>

<div id="main_content">
<tiles:insertAttribute name="content"></tiles:insertAttribute>
</div>

<hr/>
<div class="footer">
<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</div>

</div>
</body>
</html>