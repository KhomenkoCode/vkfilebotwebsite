<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<!--
	Prologue by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>VK Files Bot</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
</head>
<body>

	<!-- Header -->
	<div id="header">

		<div class="top">

			<!-- Logo -->
			<div id="logo">
				<h1 id="title">${username}</h1>
				<p>
					<a href="index?logout=1">Log Out</a>
				</p>
			</div>

			<!-- Nav -->
			<nav id="nav">
				<!--

								Prologue's nav expects links in one of two formats:

								1. Hash link (scrolls to a different section within the page)

								   <li><a href="#foobar" id="foobar-link" class="icon fa-whatever-icon-you-want skel-layers-ignoreHref"><span class="label">Foobar</span></a></li>

								2. Standard link (sends the user to another page/site)

								   <li><a href="http://foobar.tld" id="foobar-link" class="icon fa-whatever-icon-you-want"><span class="label">Foobar</span></a></li>

							-->
				<ul>
					<li><a href="index" id="top-link"
						class="skel-layers-ignoreHref"><span class="icon fa-home">Intro</span></a></li>
					<li><a href="#history" id="history-link"
						class="skel-layers-ignoreHref"><span class="icon fa-th">History</span></a></li>
				</ul>
			</nav>

		</div>

		<div class="bottom">

			<!-- Social Icons -->
			<ul class="icons">
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
				<li><a href="#" class="icon fa-dribbble"><span
						class="label">Dribbble</span></a></li>
				<li><a href="#" class="icon fa-envelope"><span
						class="label">Email</span></a></li>
			</ul>

		</div>

	</div>

	<!-- Main -->
	<div id="main">
		<section id="history" class="three"
			style="padding-top: 2%; min-height: 1000px;">
			<div class="container">

				<h2>History</h2>
				<c:choose>
					<c:when test="${numOfElements == 0}">
						<hr />
						<p style="margin: 2%;">No requests to bot founded</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="request" begin="0" end="${numOfElements-1}" step="3">
							<hr />
							<table style="margin: 0;">
								<tr>
									<td style="width: 25%; vertical-align: middle;">
										<p style="text-align: left; margin-bottom: 0;">
										
										
											<script type="text/javascript">
												function addZero(n) {
													return n > 9 ? n : "0" + n;
												}

												var dateObj = ${requests[request]};
												var currentDate = new Date(
														dateObj);
												document.write(addZero(currentDate.getDate())
																+ "."
																+ addZero(currentDate.getMonth())
																+ "."
																+ currentDate.getFullYear()
																+ " "
																+ addZero(currentDate.getHours())
																+ ":"
																+ addZero(currentDate.getMinutes()));
											</script>
											
											
											<img src="${requests[request+1]}" alt="fileTypeIMG"
												style="height: 30pt;"> ${requests[request+2]}

										</p>
									</td>
								</tr>
							</table>
						</c:forEach>
					</c:otherwise>
				</c:choose>



			</div>
		</section>

	</div>


	<!-- Footer -->
	<div id="footer">

		<!-- Copyright -->
		<ul class="copyright">
			<li>&copy; Untitled. All rights reserved.</li>
			<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
		</ul>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollzer.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>

</body>
</html>