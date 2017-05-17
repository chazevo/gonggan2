<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.kh.gonggan.member.model.vo.Member"%>

<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	if (loginUser.getMember_id() != null) {
%>
   	<meta http-equiv="Refresh" content="0; URL=index2.jsp"/>  	
<%
    }
%> --%>
<title>Insert title here</title>
<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link href="css/magnific-popup.css" rel="stylesheet">
<link rel='stylesheet' href='css/css.css'/> 

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="js/magnific-popup.min.js"></script>
<script type="text/javascript" src="js/scrollreveal.min.js"></script>
<script type="text/javascript" src="js/creative.min.js"></script>
<script type="text/javascript" src="js/home.js"></script>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("id").focus();
	}
</script>
</head>
<body>
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
				<a class="" href="#">
				<img class="smallLogoImg" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
			</div>
				<div class="collapse navbar-collapse" id="menu">
				<!-- collapse 제거 -> 화면 크기 작아졌을 때 생기는 menu 아이콘을 클릭하지 않아도 메뉴가 펼쳐짐  -->
				<!-- navbar-collapse 제거-> 메뉴 사라짐  -->
					<ul class="nav navbar-nav navbar-right ">
						<li>
							<a href="">일기</a>
						</li>
						<li>
							<a href="">장소</a>
						</li>
						<li>
							<a href="#">리뷰</a>
						</li>
						<li>
							<a href="#">음악</a>
						</li>
						<li>
							<a href="">영화</a>
						</li>
						<li>
							<a href="">뉴스</a>
						</li>
						<li>
							<a href="">책</a>
						</li>
					</ul>
				</div>
			</div>
	</nav>
	<header>
		<div class="header-content" id="indexBg">
			<div class="header-content-inner">
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
				<input type="text" class=" login" placeholder="PASS" name="member_pw" id="pass" onkeypress="if( event.keyCode==13 ){javascript:goSubmit();}">
				</td></tr>
				<tr><td>
				<a href="javascript:goSubmit();"><div id="startBtn" class="divisionMargin2">S T A R T</div></a>
				<!-- <input type="submit" value="S T A R T" id="startBtn" class="divisionMargin2"> -->
				</form>
				</td></tr>
				<tr><td>
				<a href="findIdPwd.do">아이디 찾기</a> | 
				<a href="findIdPwd.do">비밀번호 찾기</a>  | 
				<a href="join.do">회원가입</a>  
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
	</section>
</div>
</body>
</html>