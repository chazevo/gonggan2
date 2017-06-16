<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	session.setAttribute("currentView", "index2");
int imgVal = 0;
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
<script type="text/javascript" src="js/home_category.js"></script>
<script type="text/javascript">

	var loginUser = '${sessionScope.loginUser.getMember_id()}';
	var initPosition;
	var prevPosition;
	var maxRownum = '${musicMaxRownum}';
	var category = "music";
	var rownum = 1;
	var imgVal = <%= imgVal %>;
	
	window.onload = function() {
		
		requestCategoryList(rownum, category);
		$("#div_Loading").hide();
		
		$(window).scroll(function(){

			initPosition = $(window).scrollTop();
				if (initPosition > prevPosition) {
					if  ($(document).height() == $(window).scrollTop() + $(window).height())
						if(maxRownum >= rownum) {
							$("#div_Loading").show();
							requestCategoryList(rownum, category);
						}
				}
			prevPosition = initPosition
		});
	}
</script>
</head>
<body id="indexBg">
<div id="indexBg">
	<nav class="navbar navbar-default" id="mainNav">
		<div><a id="fancy" style="display:none"></a>
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
						<a href="home_diary.do">일기</a>
						</li>
						<li>
							<a href="home_place.do">장소</a>
						</li>
						<li>
							<a href="home_review.do">리뷰</a>
						</li>
						<li>
							<a href="home_music.do">음악</a>
						</li>
						<li>
							<a href="home_movie.do">영화</a>
						</li>
						<li>
							<a href="home_news.do">뉴스</a>
						</li>
						<li>
							<a href="home_book.do">책</a>
						</li>
					</ul>
			</div>
		</div>
	</nav>
	<section>
		<div id="blogHomeContentDiv" class='blogHomeContentDiv'>
		
		</div>
	</section>
	<div id="div_Loading" style="font-size:11pt; width:100%;text-align:center">
		<img height='70px' src="images/loading2.gif">
	</div>
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
