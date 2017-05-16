<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
int year, month, today, firstday, lastdate;
String str = "";
int imgVal = 0;
Calendar cal = Calendar.getInstance();
today = cal.get(Calendar.DATE);
cal.set(Calendar.DATE, 1);
System.out.println(cal.get(Calendar.YEAR) + "년" + (cal.get(Calendar.MONTH)+1) + "월" + cal.get(Calendar.DATE) + "일");
year = cal.get(Calendar.YEAR);
month = cal.get(Calendar.MONTH)+1;
lastdate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
System.out.println(lastdate);
switch (firstday = cal.get(Calendar.DAY_OF_WEEK)) {
case 1:
	str = "일 ";
	 break;
case 2:
	str = "월";
	 break;
case 3:
	str = "화";
	break;
case 4:
	str = "수";
	break;
case 5:
	str = "목";
	break;
case 6:
	str = "금";
	break;
case 7:
	str = "토";
	break;
}
System.out.println(str);
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>myhome</title>
<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='css/css.css'/> 

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="js/jquery.fancybox.js"></script>
<script src="js/myhome.js"></script>
<script type="text/javascript">
var year = <%= year %>;
var month = <%= month %>;
var today = <%= today %>;
var firstday = <%= firstday %>;
var lastdate = <%= lastdate %>;
var imgVal = <%= imgVal %>;

	window.onload = function() {
		requestList();
		
		$("#year").text(year);
		$("#month").text(month<10 ? "0" + month : month);
		$("#today").text(today<10 ? "0" + today : today);
		
		$("#loginUser").click(function() {
			if ($("#loginUserDetail").hasClass("hidden")) {
				$("#loginUserDetail").removeClass("hidden");
				$("#loginUserDetail").show();
				}
			else {
				$("#loginUserDetail").addClass("hidden");
				$("#loginUserDetail").hide();
			}
		});
		
		$(".hover").hover(function(){
			//$(this).css("backgroundColor", "gray");
			if ($(".hover").hasClass("grayTd"))
				$(this).removeClass("grayTd");
			else
				$(this).addClass("grayTd");
		});
		
		/*
		$(".hover").onblur(function(){
			$(this).css("backgroundColor", "white");
		});
		*/
	}
</script>
</head>
<body>

	<div class="divCenter">
		<nav class="navbarCustom navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">
					<img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
					<img class="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
			</div>
			<div class="navbar-right">
				<a id="loginUser" class="navbar-brand"  href="#" >
				<img src="images/default.png" height="40px" class="img-circle">&nbsp;chazevo 님 </a>
				
			</div>
		</nav>
	
	<!-- 다정다정 -->
   
		<div id="loginUserDetail" class="hidden">
			<table id="idclick_table">
				<tr id="center_align">
					<td>
						<a href="mypage.do">마이페이지</a>&nbsp;&nbsp; |  &nbsp;&nbsp;
						<a href="#">내블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
						<a href="#">이웃 블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
						<a href="#">로그아웃</a> 
						<div id="dansun_line"></div>
					</td>
				</tr> 
				<tr>
					<td>
						알림&nbsp; <img src="images/idclick_new_icon.png" id="idclick_new_icon">
					</td>
				</tr>
				<!-- <tr>
							<td class="hover">
 								<font><a href="#"> 이대장 님이</a></font> <a href="#">동갑내기 부부의 세계로 가는 자전거 여행| 게시글에 댓글을 남기셨습니다.</a>
							</td>
						</tr>
				<tr>
				      <td class="hover">
				            <font><a href="#"> 긍정의아이콘|토리|</a></font> <a href="#">님이 토리와 함께 추억쌓기 놀이 | 게시글에 좋아요를 누르셨습니다.</a>
				      </td>
				</tr> -->
         		<tr>
					<td class="hover">
						chazevo님의 알림이 없습니다.
					</td>
				</tr>
			</table>
		</div>
   
   <!-- END다정다정 -->
   
		<!--<div class="container-fluid">--><div>
		<!-- container-fluid : 화면 너비가 resize 되더라도 화면에 가득 참  -->
			<div class="header-content">
				<div class="header-content-inner">
					<h2><a href="start.do">당신만의 공간에서 당신의 글을 만들어보세요.</a></h2>
				</div>
				<div class="header-content-inner2">
					<a href="controll.do">
					<img class="smallIcon"
					src="images/KakaoTalk_Photo_2017-04-24-10-28-40_21.png"></a>
				</div>
			</div>
			<div class="navbar-header idView">
			<!-- navbar-header : 메뉴 버튼 클릭 시 토글 width 100% -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#menu">
					<span class="sr-only">Toggle navigation</span> Menu <i class="menu"></i>
					<!-- sr-only : 숨김 -->
				</button>
				<a href="#" style="display:inline-block">
					<img src="images/default.png" height="40px"class="img-circle">&nbsp;chazevo 님
				</a> &nbsp;
				<a data-fancybox data-src="/gonggan/messageList.do"><img src="images/chat_icon.png" height="28px"  id="chat_icon"></a>
			</div>
			<div class="collapse navbar-collapse" id="menu">
				<ul class="nav navbar-nav navbar-right">
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
		
		<section>
			<div>
				<div>
					<div class="contentDiv">
					<!--
					col-xs 는 768px미만, 
					col-sm-는 786px 이상,
					col-md-는 992px 이상,
					col-lg-는 1200px 이상의 화면에서 적용하는 것
					-->
					
						<table width="100%">
						<tr>
							<th class="th">
								<a href="uploadform.do">포스트 쓰기 </a>
								<label class='radio-wrap'>
									<input type='radio' name='listType' id='calendar'  onclick='requestList();' checked>
									<i class='calendar-icon'></i>
								</label>
								<label class='radio-wrap'>
									<input type='radio'  name='listType' id='list' onclick='requestList();'>
									<i class='list-icon'></i>
								</label>
							</th>
						</tr>
						<tr id="todayHeader">
							<td>
								<h3 class="section-heading text-center" style="color:#E6E6E6;">
									<span id="year"></span>.<span id="month"></span>.<span id="today"></span>
								</h3>
							</td>
						</tr>
						<tr><td>
							<table width="100%">
								<tr class="CalendarHeader text-center">
									<td><font color="#C90000">일</font></td>
									<td>월</td><td>화</td><td>수</td><td>목</td><td>금</td>
									<td><font color="#003399">토</font></td></tr>
								<tbody id="listbody" class='cal'>
									<tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
									<tr><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td></tr>
									<tr><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td></tr>
									<tr><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td></tr>
									<tr><td>29</td><td>30</td><td>31</td><td></td><td></td><td></td><td></td></tr>
								</tbody>
							</table>
						</td></tr>
						</table>
						<div class=dotdotdotDiv>
							<a class="hover dotdotdot" href="">부적절한 컨텐츠 신고</a>
							<a class="hover dotdotdot" href="" >공유</a>
							<a class="hover dotdotdot" href="">쪽지 보내기</a>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>