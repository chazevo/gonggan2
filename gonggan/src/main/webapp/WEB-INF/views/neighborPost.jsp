<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.gonggan.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
Member loginUser = (Member) session.getAttribute("loginUser");
session.setAttribute("currentView", "index2");

int imgVal = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이웃 포스트</title>

<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" media="(max-width: 900px)" href="css/css.css" />
<link rel='stylesheet' href='css/css.css'/> 
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/neighborPost.js"></script>
<script src="js/jquery.fancybox.js"></script>

<script type="text/javascript">

var imgVal = <%= imgVal %>;

var loginUser = '${sessionScope.loginUser.getMember_id()}';

var initPosition;
var prevPosition;
var maxRownum = ${nplistSize};

window.onload = function() {
	$("#div_Loading").hide();
	requestNeighborPostList(rownum, loginUser);
	
	$(".hover").hover(function(){
		//$(this).css("backgroundColor", "gray");
		if ($(".hover").hasClass("grayTd"))
			$(this).removeClass("grayTd");
		else
			$(this).addClass("grayTd");
	});
	
	$("#loginUser").click(function() {
		if ($("#loginUserDetail").hasClass("hidden")) {
			$("#loginUserDetail").removeClass("hidden");
			$("#loginUserDetail").show();
			}
		else {
			$("#loginUserDetail").addClass("hidden");
			$("#loginUserDetail").hide();
		}
		checkAlarm();
	});
	
	$(window).scroll(function() {
		//if  ($(window).scrollTop() >= $(window).height() - $(window).height() / 3) {
			//if  ($(window).scrollTop() >= 500) {
				// $(document).height() 현재 페이지 높이
				// $(window).height() 윈도우 크기
				
		initPosition = $(window).scrollTop()
		
		if (initPosition > prevPosition) {
			if  ($(document).height() == $(window).scrollTop() + $(window).height()) {
				
				if(maxRownum >= rownum) {
					$("#div_Loading").show();
					
					requestNeighborPostList(rownum, loginUser);
					
					//$(window).scrollTop($(window).height() / 2);
					
					//setTimeout(function() {}, 1000);
				}
			}
		}
		
		prevPosition = initPosition;
	});
		}
</script>
</head>

<body>
<a id="fancy" style="display:none"></a>
	<c:if test='${empty sessionScope.loginUser}'>
		<jsp:forward page="error.jsp"></jsp:forward>
	</c:if>
	<div class="divCenter">
		<nav class="navbarCustom navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">
					<img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
					<img class="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
			</div>
			<div class="navbar-right navbar-brand" >
				<c:if test="${empty sessionScope.loginUser }">
					<a class="loginbox"  href="index.jsp" >
						l o g i n
					</a>
				</c:if>
				<c:if test="${!empty sessionScope.loginUser }">
					<a id="loginUser" class="navbar-brand" >
						<c:if test='${empty sessionScope.loginUser.getProfile_photo() }'>
						<img src="images/default.png" height="40px" class="img-circle">&nbsp;
						</c:if>
						<c:if test='${!empty sessionScope.loginUser.getProfile_photo() }'>
						<img src="images/profileImages/${sessionScope.loginUser.getProfile_photo()}" height="40px" class="img-circle">&nbsp;
						</c:if>&nbsp;
						${sessionScope.loginUser.getMember_id() } 님
					</a>
				</c:if>
			</div>
		</nav>
		<div id="loginUserDetail" class="hidden">
			<table id="idclick_table">
				<tr id="center_align">
					<td>
						<a href="mypage.do?writer_id=${sessionScope.loginUser.getMember_id() }">마이페이지</a>&nbsp;&nbsp; |  &nbsp;&nbsp;
						<a href="myhome.do?writer_id=${sessionScope.loginUser.getMember_id() }">내블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
						<a href="neighborBlogPost.do">이웃 블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
						<a href="logOut.do?writer_id=${sessionScope.loginUser.getMember_id() }">로그아웃</a> 
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
				            <font><a href="#"> 긍정의아이콘|토리|</a></font> <a href="#">이 토리와 함께 추억쌓기 놀이 | 게시글에 좋아요를 누르셨습니다.</a>
				      </td>
				</tr> -->
         		<tr>
               <td class="hover">
               <c:if test='${!empty mAlarmList}'>
                  <c:forEach  items="${mAlarmList}" var="i" begin="0">
                     <tr>
                        <td>
                           <c:if test='${i.type_cg eq "C"}' >
                              <font><a href="myhome.do?writer_id=${i.writer_id} ">${i.writer_id }</a>님이 </font>
                              <a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id()} '><b>댓글</b></a>을 남기셨습니다.
                           </c:if>
                        </td>
                     </tr>
                     <tr>
                        <td>
                           <c:if test='${i.type_cg eq "G"}' >
                              <font><a href="myhome.do?writer_id=${i.writer_id} ">${i.writer_id }</a>님이</font> 
                              <a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id()} '><b>좋아요</b></a>을 남기셨습니다.
                           </c:if>
                        </td>
                        </tr>
                  </c:forEach>
               </c:if>
               <c:if test='${empty mAlarmList}'>
                     알람이 없습니다.
               </c:if>
               </td>
            </tr>
			</table>
		</div>
   
		<section style="margin-top:50px;">
			<img src="images/KakaoTalk_Photo_2017-06-16-01-38-26.png" width="100px" style="display:block">
			<img src='images/neighborline.png' width="100%" >
			<div class="text-center blogHomeContentDiv" id="blogHomeContentDiv">
			</div>
		</section>
		<div id="div_Loading" style="font-size:11pt; width:100%;text-align:center">
			<img height='70px' src="images/InternetSlowdown_Day.gif">
		</div>
	</div>
</body>
</html>