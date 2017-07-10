<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
						<c:choose>
    						<c:when test="${isAuthorized == 0}">
									<p><a href="javascript:PopUpShow()">Log In via telegram</a></p>
    						</c:when>    
    						<c:otherwise>
									<h1 id="title">Username</h1>
									<p><a href="index?logout=1">Log Out</a></p>
		    				</c:otherwise>
						</c:choose>
					</div>
					<!-- Nav -->
						<nav id="nav">
							<ul>
								<li><a href="#top" id="top-link" class="skel-layers-ignoreHref"><span class="icon fa-home">Intro</span></a></li>
								<li><a href="history" class="skel-layers-ignoreHref"><span class="icon fa-th">History</span></a></li>
							</ul>
						</nav>

				</div>

				<div class="bottom">

					<!-- Social Icons -->
						<ul class="icons">
							<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
							<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
							<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
							<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
							<li><a href="#" class="icon fa-envelope"><span class="label">Email</span></a></li>
						</ul>

				</div>

			</div>
		
		

		<!-- Main -->
			<div id="main">

				<!-- Intro -->
					<section id="top" class="one dark cover">
						<div class="container">
							
							<c:choose>
   		 						<c:when test="${isAuthorized == 0}">
									
								<header>
									<h2 class="alt"><strong>VkFilesBot</strong><br /> To watch your bot history, please, log in. <br /></h2>
								</header>

								<footer>
									<a href="javascript:PopUpShow()" class="button scrolly">Log in via Telegram</a>
								</footer>

    							</c:when>    
    							<c:otherwise>
									<header>
										<h2 class="alt">Hello, <strong>Username</strong>!<br /> Now, you can see your requests to the bot.<br /></h2>
									</header>

									<footer>
										<a href="history" class="button scrolly">My requests history</a>
									</footer>
			    				</c:otherwise>
							</c:choose>
							
						</div>
					</section>

				
				<!-- About Me -->
					<section class="three">
						<div class="container">

							<header>
								<h2>About Us</h2>
							</header>

							<a href="#" class="image featured"><img src="images/pic08.jpg" alt="" /></a>

							<p>Tincidunt eu elit diam magnis pretium accumsan etiam id urna. Ridiculus
							ultricies curae quis et rhoncus velit. Lobortis elementum aliquet nec vitae
							laoreet eget cubilia quam non etiam odio tincidunt montes. Elementum sem
							parturient nulla quam placerat viverra mauris non cum elit tempus ullamcorper
							dolor. Libero rutrum ut lacinia donec curae mus vel quisque sociis nec
							ornare iaculis.</p>

						</div>
					</section>
		</div>
		<!-- Footer -->
			<div id="footer">

				<!-- Copyright -->
					<ul class="copyright">
						<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
					</ul>

			</div>

		
		<div class="b-popup" id="popup1">
    		<div class="b-popup-content">
        		<div align="right" style="margin=10%">
        		<a href="javascript:PopUpHide()">Close</a>
        		</div>
        		<hr/>
        		To sign in, follow these steps:
        		<br/>
        		1. Go to <u><a href="https://telegram.me/VkFiles_Bot?start=ws567sdgf7sdf123ssd213sa"  target="_blank">
        		this link</a></u> 
        		into your conversation with our bot.
        		<br/>
        		2. Press "Start" button
        		<br/>
        		3. <u><a href="index">Refresh this page.</a></u>
    		
    		</div>
		</div>


		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.scrollzer.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>
			<script>
    		$(document).ready(function(){  
     			PopUpHide();
    		});
    		
    		function PopUpShow(){
        		$("#popup1").show();
    		}
	  		
    		function PopUpHide(){
    	    	$("#popup1").hide();
    		}
</script>
	</body>
</html>