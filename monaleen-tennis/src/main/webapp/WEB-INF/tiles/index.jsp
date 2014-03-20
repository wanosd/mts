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
			<li><a href="home.html" title="">Coaching</a></li>
			<li><a href="services.html" title="">Social Events</a></li>
			<li><a href="#" title="">Online Timetable</a></li>
			<li><a href="#" title="">Local and Inter Club Tournaments</a></li>
		</ul>
	</div>

	<p class="clear">
		<img src="${pageContext.request.contextPath}/static/css/images/green/pic1.jpg" alt="" title="" class="left_img">
		"Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
		eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad
		minim veniam, quis nostrud exercitation ullamco laboris nisi ut
		aliquip ex ea commodo consequat.
	</p>
	<div class="read_more_link">
		<a href="">read more</a>
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
		<h3>Whatever</h3>
		<p>Stuff</p>
		<div class="read_more_link">
			<a href="#">read more</a>
		</div>
	</div>

	<div class="search_box">
		<div class="search_title">Dolore magna aliqua</div>
		<input class="search_input" type="text"> <input
			src="${pageContext.request.contextPath}/static/css/images/green/search.gif" class="submit" type="image">
		<div class="subsearch">"Lorem ipsum dolor sit amet.</div>

	</div>


	<div class="contact_information">
		<h4>Free Customers Support</h4>
		<p>
			<img src="${pageContext.request.contextPath}/static/css/images/green/phone_icon.gif" alt="" title=""
				class="box_img"> 0700 679 122 489<br> 0700 679 122 489
		</p>
		<br> <br>
		<p>
			<img src="${pageContext.request.contextPath}/static/css/images/green/contact_icon.gif" alt="" title="" class="box_img">
			monaleentennisgaaclub@gmail.com
		</p>

	</div>







</div>
<!--end of right content-->



<div style="clear: both;"></div>

