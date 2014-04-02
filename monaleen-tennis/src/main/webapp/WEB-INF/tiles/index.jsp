<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<div id="left_content">
	<h2>Monaleen GAA Tennis Club</h2>
	<p>Monaleen GAA Tennis Club is a small, two-court club attached to
		Monaleen GAA Club and located in its grounds on Schoolhouse Road.
	<p>With affiliation to Tennis Ireland, a busy calendar sees the
		club involved in Munster Inter Club competitions, club opens, intra
		club competitions and social tennis events.</p>

	<p>These social events are an ideal way for new members to meet and
		play with other members without the need to have partners and
		opponents arranged in advance. Friday morning tennis even has a crèche
		facility! Just bring your racquet and come along and say Hello!</p>


	<div id="left_nav">
		<ul>
			<li><a href="${pageContext.request.contextPath}/training" title="">Coaching</a></li>
			<li><a href="${pageContext.request.contextPath}/news" title="">Social Events</a></li>
			<li><a href="${pageContext.request.contextPath}/timetable" title="">Online Timetable</a></li>
			<li><a href="${pageContext.request.contextPath}/tournaments" title="">Local and Inter Club Tournaments</a></li>
		</ul>
	</div>

	<p class="clear">
		<img src="${pageContext.request.contextPath}/static/css/images/green/ul_logo.gif" alt="" title="" class="left_img">
		This web application was developed as part of a final year project for Computer Systems, LM051, at the University of Limerick.
	</p>
	<div class="read_more_link">
		<a href="http://www.csis.ul.ie/currentcourse/LM051/">read more about this course</a>
	</div>



</div>
<!--end of left content-->



<div id="right_content">
	<h2>Recent News</h2>
	<div class="products_box">
		<img src="${pageContext.request.contextPath}/static/css/images/green/box_icon.gif" alt="" title="" class="box_img">
		<h3>${newsHeader }</h3>
		<p>${newsContent }</p>
		<div class="read_more_link">
			<a href="${pageContext.request.contextPath}/news">more news</a>
		</div>
	</div>


	<div class="products_box">
<img src="${pageContext.request.contextPath}/static/css/images/green/box_icon.gif" alt="" title="" class="box_img">
		<h3>Training</h3>
		<p>No training scheduled.</p>
		<div class="read_more_link">
			<a href="${pageContext.request.contextPath}/training">read more</a>
		</div>
	</div>

	<div class="search_box">
		<div class="search_title">Any Questions?!</div><img
			src="${pageContext.request.contextPath}/static/css/images/green/search.gif">
		<div class="subsearch">Contact us on .....</div>

	</div>


	<div class="contact_information">
		<h4>Support</h4>
		<p>
			<img src="${pageContext.request.contextPath}/static/css/images/green/contact_icon.gif" alt="" title="" class="box_img">
			monaleentennisgaaclub@gmail.com
		</p>

	</div>







</div>
<!--end of right content-->



<div style="clear: both;"></div>

