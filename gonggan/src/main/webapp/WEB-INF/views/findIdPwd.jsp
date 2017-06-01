<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel='stylesheet' href='css/css.css'/> 

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/findIdPwd.js"></script> 
<script type="text/javascript">
</script>
</head>
<body>
<div id="indexBg">
<nav class="navbar navbar-default">
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
				<table class="findTable" align="center" border="1">
				<colgroup>
					<col width="20%" />
					<col width="68%" />
					<col width="12%" />
				</colgroup>
				<tr><td colspan="3" class="text-center">
					<a href="start.do">
						<img class="logoImg" src="images/KakaoTalk_Photo_2017-04-22-18-12-10_54.png">
					</a>
				</td></tr>
				<tr><td colspan="3">
					<p class="text-center">아이디 / 비밀번호 찾기</p>
				</td></tr>
				<tr>
					<td><p>아이디 찾기</p></td>
					<td>
						이메일을 입력해주세요.&nbsp;
						<input id="email" class="find" type="text" id="" size="15">
					</td>
					<td>
						<a href="javascript:idFind();">
							<div id="okBtn">확인</div>
						</a>
					</td>
				</tr>
			<!-- 	<form action="selectPw.do"> -->
				<tr>
					<td rowspan="3"><p>비밀번호 찾기</p></td>
					<td>
						아이디를 입력해주세요.&nbsp;
						<input type="text" name="id"  id="id" class="find" size="12">
					</td>
					<td rowspan="3">
						<a href="javascript:pwdFind();">
							<div id="okBtn">확인</div>
						</a>
					</td>
				</tr>
				<tr>
					<td>
						이메일을 입력해주세요.&nbsp;
						<input class="find" type="text" name="email2" id="email2" size="15">
					</td>
				</tr>
	<!-- 			<tr>
					<td>
						핸드폰 번호를 입력해주세요.&nbsp;
						<input class="find" type="text" id="phone" size="13">
					</td>
				</tr> -->
				<!-- </form> -->
				</table>
			</div>
		</div>
	</header>
</div>
</body>
</html>