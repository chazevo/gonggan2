<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	session.setAttribute("currentView", "index2");
%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link href="css/magnific-popup.css" rel="stylesheet">
<link rel='stylesheet' href='css/jquery.fancybox.min.css'/> 
<link rel='stylesheet' href='css/css.css'/> 

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="js/magnific-popup.min.js"></script>
<script type="text/javascript" src="js/scrollreveal.min.js"></script>
<script type="text/javascript" src="js/creative.min.js"></script>
<script type="text/javascript" src="js/jquery.fancybox.js"></script>
<script type="text/javascript" src="js/home.js"></script>
<script type="text/javascript">

	var initPosition;
	var prevPosition;
	var maxRownum;
	var movieMaxRownum = ${movieMaxRownum};
	var musicMaxRownum = ${musicMaxRownum};
	var reviewMaxRownum = ${reviewMaxRownum};
	var placeMaxRownum = 0;
	var newsMaxRownum = ${newsMaxRownum};
	var diaryMaxRownum = ${diaryMaxRownum};
	var category = "main";
	
	window.onload = function() {
		document.getElementById("id").focus();

		$("#div").hide();
		$("#div_Loading").hide();
		
		$(window).scroll(function(){

			initPosition = $(window).scrollTop()
			if (category != 'main') {
				if (initPosition > prevPosition) {
					if  ($(window).scrollTop() >= $(window).height() - $(window).height() / 3){
						if(maxRownum >= rownum) {
							$("#div_Loading").show();
							//alert(rownum);
							setTimeout(function() { requestCategoryList(rownum, category);}, 1000);
						}
					}
				}
			}
			prevPosition = initPosition
		});
		
		(function($) {
			"use strict"; // Start of use strict

			// jQuery for page scrolling feature - requires jQuery Easing plugin
			$(document).on('click', 'a.page-scroll', function(event) {
				var $anchor = $(this);
				$('html, body').stop().animate({
					scrollTop: ($($anchor.attr('href')).offset().top - 50)
				}, 1250, 'easeInOutExpo');
				event.preventDefault();
			});

			// Highlight the top nav as scrolling occurs
			$('body').scrollspy({
				target: '.navbar-fixed-top',
				offset: 51
			});

			// Closes the Responsive Menu on Menu Item Click
			$('.navbar-collapse ul li a').click(function() {
				$('.navbar-toggle:visible').click();
			});

			// Offset for Main Navigation
			$('#mainNav').affix({
				offset: {
					top: 100
				}
			})

			// Initialize and Configure Scroll Reveal Animation
			window.sr = ScrollReveal();
			sr.reveal('.sr-icons', {
				duration: 600,
				scale: 0.3,
				distance: '0px'
			}, 200);
			sr.reveal('.sr-button', {
				duration: 1000,
				delay: 200
			});
			sr.reveal('.sr-contact', {
				duration: 600,
				scale: 0.3,
				distance: '0px'
			}, 300);

			// Initialize and Configure Magnific Popup Lightbox Plugin
			$('.popup-gallery').magnificPopup({
				delegate: 'a',
				type: 'image',
				tLoading: 'Loading image #%curr%...',
				mainClass: 'mfp-img-mobile',
				gallery: {
					enabled: true,
					navigateByImgClick: true,
					preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
				},
				image: {
					tError: '<a href="%url%">The image #%curr%</a> could not be loaded.'
				}
			});
		})(jQuery); // End of use strict

	}
	
</script>
</head>
<body>
<c:if test="${!empty sessionScope.loginUser }">
   <jsp:forward page="index2.do"></jsp:forward>
</c:if>
<div id="indexBg">
<nav class="navbar navbar-default" id="mainNav">
		<!--<div class="container-fluid">--><div>
		<!-- container-fluid : 화면 너비가 resize 되더라도 화면에 가득 참  -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#menu">
					<span class="sr-only">Toggle navigation</span> Menu <i class="menu"></i>
					<!-- sr-only : 숨김 -->
				</button>
				<a class="" href="start.do">
				<img class="smallLogoImg" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
			</div>
				<div class="collapse navbar-collapse" id="menu">
				<!-- collapse 제거 -> 화면 크기 작아졌을 때 생기는 menu 아이콘을 클릭하지 않아도 메뉴가 펼쳐짐  -->
				<!-- navbar-collapse 제거-> 메뉴 사라짐  -->
					<ul class="nav navbar-nav navbar-right ">
						<li>
						<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'diary');">일기</a>
						</li>
						<li>
							<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'place');">장소</a>
						</li>
						<li>
							<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'review');">리뷰</a>
						</li>
						<li>
							<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'music');">음악</a>
						</li>
						<li>
							<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'movie');">영화</a>
						</li>
						<li>
							<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'news');">뉴스</a>
						</li>
						<li>
							<a href="javascript:rownum=1; requestCategoryList(rownum = 1, category = 'book');">책</a>
						</li>
					</ul>
				</div>
			</div>
	</nav>
	<header>
		<div class="header-content" id="indexBg">
			<div class="header-content-inner">
				<div id="div">
					<br><br><br><br><br><br><br><br><br><br><br><br><br>
				</div>
				<table class="loginTable" align="center">
				<tr><td>
				<img class="logoImg" src="images/KakaoTalk_Photo_2017-04-22-18-12-10_54.png">
				</td></tr>
				<tr><td>
				<p>당신만의 공간에서 당신의 글을 만들어보세요. </p>
				</td></tr>
				<tr><td>
				<form action="login.do" method="post" id="login">
				<input class="divisionMargin login" type="text" id="id" placeholder="ID" name="member_id" >
				</td></tr>
				<tr><td>
				<input type="password" class=" login" placeholder="PASS" name="member_pw" id="pass" onkeypress="if( event.keyCode==13 ){javascript:goSubmit();}">
				</td></tr>
				<tr><td>
				<a href="javascript:goSubmit();"><div id="startBtn" class="divisionMargin2">S T A R T</div></a>
				<c:if test="${logmsg == 'failure'}">
					<div style="color: white">
						아이디 또는 비밀번호가 일치하지 않습니다.
					</div>
				</c:if>
				</form>
				</td></tr>
				<tr><td>
				<a href="findIdPwd.do">아이디 찾기</a> | 
				<a href="findIdPwd.do">비밀번호 찾기</a>  | 
				<a href="join.do">회원가입</a>  
				<a href="nlogin.do">NaverLogin</a>  |
				<a href="kakao.do">KakaoLogin</a>  |
				<a href="facebook.do">FacebookLogin</a>
				</td></tr>
				<tr>
					<td>
						<a href="#about" class="page-scroll">
							<img  class="divisionMargin" 
							src="images/KakaoTalk_Photo_2017-04-24-10-24-18_51.png"
							width="50%">
						</a>
					</td>
				</tr>
				
				</table>
			</div>
		</div>
	</header>
	<section id="about">
		<img src="images/introducing.jpg" width="100%">
		<!--
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<h2 class="section-heading" style="color:#ffffff; font-weight:bold">G O N G : G A N </h2>
					<h5 class="section-heading" style="color:#ffffff;" ></h5>
					<hr class="light">
					<div class ="column">
						<div class ="row">
							<img src="images/introducing.jpg" width="100%">
						</div>
						<br>
					</div>
					<a href="#services" class="page-scroll btn btn-default btn-xl sr-button">NEXT</a>
				</div>
			</div>
		</div>
		-->
	<div id="div_Loading" style="font-size:11pt; width:100%;text-align:center">
		<img height='70px' src="images/InternetSlowdown_Day.gif">
	</div>
	</section>
</div>
</body>
</html>