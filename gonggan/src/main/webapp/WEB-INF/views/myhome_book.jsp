<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar, com.kh.gonggan.member.model.vo.Member, com.kh.gonggan.blog.model.vo.Blog"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
String currentView = "myhome";
String writer = session.getId();
//세션저장 (플래그, 값)
session.setAttribute("currentView", currentView);
session.setAttribute("wr_id", writer);
session.setAttribute("writer_id", request.getParameter("writer_id"));
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
<script src="js/jquery-timeago.js" type="text/javascript"></script>
<script src="js/myhome_category.js"></script>
<script type="text/javascript">
	var loginUser = '${sessionScope.loginUser.getMember_id()}';
	var writer_id = "${blog.getWriter_id()}";
	var blog_open_yn = '${blog.blog_open_yn}';
	var hide_comment = '${blog.hide_comment}';
	var blog_id = '${blog.blog_id}';
	
	var category = 'book';

	var rownum = 1;
	var maxRownum = ${booklistSize};
	var initPosition;
	var prevPosition;
	
	jQuery.timeago.settings.strings = {
			suffixAgo: "전",
			suffixFromNow: "후",
			seconds: "1분 이내",
			minute: "1분",
			minutes: "%d분",
			hour: "1시간",
			hours: "%d시간",
			day: "하루",
			days: "%d일",
			month: "한달",
			months: "%d달",
			year: "1년",
			years: "%d년"
	};
	
	jQuery(document).ready(function(){
		jQuery("abbr.timeago").timeago();
	});

	window.onload = function() {
		
		visit();
		requestCategoryList(category, 1);
		
		$(".book").css('background-color', '#E6E6E6'); 
		
		$(".blogOwnerClick").hide();
		
		$(".hover").hover(function(){
			//$(this).css("backgroundColor", "gray");
			if ($(".hover").hasClass("grayTd"))
				$(this).removeClass("grayTd");
			else
				$(this).addClass("grayTd");
		});
		
		$("#blogOwnerClick").click(function() {
			if ($(".blogOwnerClick").hasClass("hidden")) {
				$(".blogOwnerClick").removeClass("hidden");
				$(".blogOwnerClick").show();
				}
			else {
				$(".blogOwnerClick").addClass("hidden");
				$(".blogOwnerClick").hide();
			}
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
		});
		
		$("#div_Loading").hide();

		$(window).scroll(function() {
			
			initPosition = $(window).scrollTop()
			
			if (initPosition > prevPosition) {
				if  ($(document).height() == $(window).scrollTop() + $(window).height()) {
					
					if(maxRownum >= rownum) {
						alert("maxRownum : " + maxRownum + ", rownum : " + rownum);
						$("#div_Loading").show();
						
						requestCategoryList(category, rownum);
					}
				}
			}
			
			prevPosition = initPosition;
		});
		
		/*
		$(".hover").onblur(function(){
			$(this).css("backgroundColor", "white");
		});
		*/
	}
</script>
</head>
<body class="myhome">
	<c:if test='${empty param || param.writer_id eq "" }'>
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

	<!-- 다정다정 -->
   
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
   
   <!-- END다정다정 -->
   
		<!--<div class="container-fluid">--><div>
		<!-- container-fluid : 화면 너비가 resize 되더라도 화면에 가득 참  -->
			<div class="header-content"
				style="<c:if test='${!empty blog.getBackground()}'>background:url(backgroundImages/${blog.getBackground()});</c:if><c:if test='${! empty blog.background_color}'>background-color:${blog.background_color };</c:if>">
				<div class="header-content-inner">
					<h2>
						<a href="myhome.do?writer_id=${param.writer_id} "
							style="color:${blog.getColor() }">
							<!--당신만의 공간에서 당신의 글을 만들어보세요.-->
							${blog.getTitle() }
						</a>
					</h2>
					<h4 style="color:${blog.contents_color}">${blog.getContents() }</h4>
				</div>
				<div class="header-content-inner2">
					<c:if test="${ param.writer_id eq sessionScope.loginUser.getMember_id()}">
					<a href="controll.do?writer_id=${sessionScope.loginUser.getMember_id() }">
					<img class="smallIcon"
					src="images/KakaoTalk_Photo_2017-04-24-10-28-40_21.png"></a>
					</c:if>
				</div>
			</div>
			<div class="navbar-header idView">
			<!-- navbar-header : 메뉴 버튼 클릭 시 토글 width 100% -->
				<c:if test='${blog.blog_open_yn eq "Y" }'>
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#menu">
					<span class="sr-only">Toggle navigation</span> Menu <i class="menu"></i>
					<!-- sr-only : 숨김 -->
				</button>
				</c:if>
				<a href="javascript:void(0);" id="blogOwnerClick" style="display:inline-block">
					<c:if test='${empty member.getProfile_photo() }'>
					<img src="images/default.png" height="40px" class="img-circle">
					</c:if>
					<c:if test='${!empty member.getProfile_photo() }'>
					<img src="images/profileImages/${ member.getProfile_photo()}" height="40px" class="img-circle">
					</c:if>
					&nbsp;${param.writer_id }
				</a> &nbsp;
				<a data-fancybox data-src="/gonggan/messageList.do?memberId1=${sessionScope.loginUser.getMember_id()}&memberId2=${member.getMember_id() }">
					<img src="images/chat_icon.png" height="28px"  id="chat_icon">
				</a>
				<div class="blogOwnerClick hidden">
					<div>
						<c:if test='${sessionScope.loginUser.getMember_id() ne param.writer_id}'>
							<c:if test='${neighYn=="N" }'>
							<a class="hover" href="javascript:Neig();">이웃 신청</a>
							</c:if>
							<c:if test='${neighYn=="Y" }'>
							<a class="hover" href="javascript:rejectNeig('${sessionScope.loginUser.getMember_id()}','${param.writer_id}');">이웃 취소</a>
							</c:if>
						</c:if>
						<hr>
						<a class="hover" href="">프로필 보기</a>
					</div>
					<img src="images/idclick_icon.png" width="100%" height="100%">
				</div>
			</div>
			<div class="collapse navbar-collapse" id="menu">
				<c:if test='${blog.blog_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
				<ul class="nav navbar-nav navbar-right">
					<c:if test='${blog.diary_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
					<li>
						<a href="myhome_diary.do?writer_id=${param.writer_id }">
							<c:if test='${blog.languages eq "kor" }'>일기</c:if>
							<c:if test='${blog.languages eq "eng" }'>diary</c:if>
							<c:if test='${blog.languages eq "jp" }'>日記</c:if>
						</a>
					</li>
					</c:if>
					<c:if test='${blog.place_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
					<li>
						<a href="myhome_place.do?writer_id=${param.writer_id }">
							<c:if test='${blog.languages eq "kor" }'>장소</c:if>
							<c:if test='${blog.languages eq "eng" }'>place</c:if>
							<c:if test='${blog.languages eq "jp" }'>場所</c:if>
						</a>
					</li>
					</c:if>
					<c:if test='${blog.review_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
					<li>
						<a href="myhome_review.do?writer_id=${param.writer_id }">
							<c:if test='${blog.languages eq "kor" }'>리뷰</c:if>
							<c:if test='${blog.languages eq "eng" }'>review</c:if>
							<c:if test='${blog.languages eq "jp" }'>レビュー</c:if>
						</a>
					</li>
					</c:if>
					<c:if test='${blog.music_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
					<li>
						<a href="myhome_music.do?writer_id=${param.writer_id }">
							<c:if test='${blog.languages eq "kor" }'>음악</c:if>
							<c:if test='${blog.languages eq "eng" }'>music</c:if>
							<c:if test='${blog.languages eq "jp" }'>音楽</c:if>
						</a>
					</li>
					</c:if>
					<c:if test='${blog.movie_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
					<li>
						<a href="myhome_movie.do?writer_id=${param.writer_id }">
							<c:if test='${blog.languages eq "kor" }'>영화</c:if>
							<c:if test='${blog.languages eq "eng" }'>movie</c:if>
							<c:if test='${blog.languages eq "jp" }'>映画</c:if>
						</a>
					</li>
					</c:if>
					<c:if test='${blog.news_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
					<li>
						<a href="myhome_news.do?writer_id=${param.writer_id }">
							<c:if test='${blog.languages eq "kor" }'>뉴스</c:if>
							<c:if test='${blog.languages eq "eng" }'>news</c:if>
							<c:if test='${blog.languages eq "jp" }'>ニュース</c:if>
						</a>
					</li>
					</c:if>
					<li>
						<a class="book" href="javascript:$('#listbody').html(''); requestCategoryList(category = 'book', rownum=1);"
							onclick="$('#list').attr('checked', 'checked');">
							<c:if test='${blog.languages eq "kor" }'>책</c:if>
							<c:if test='${blog.languages eq "eng" }'>book</c:if>
							<c:if test='${blog.languages eq "jp" }'>本</c:if>
						</a>
					</li>
				</ul>
				</c:if>
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
						<c:if test='${blog.blog_open_yn eq "Y" || sessionScope.loginUser.getMember_id() eq param.writer_id}'>
						<table width="100%">
						<tr>
							<th class="th">
								<c:if test="${sessionScope.loginUser.getMember_id() eq param.writer_id}">
									<a href="uploadform.do?writer_id=${param.writer_id}">포스트 쓰기 </a>
								</c:if>
								<label class='radio-wrap'>
									<input type='radio' name='listType' id='calendar'  
										onclick='location.href = "myhome.do?writer_id=${param.writer_id}";' checked>
									<i class='calendar-icon'></i>
								</label>
								<label class='radio-wrap'>
									<input type='radio'  name='listType' id='list' onclick='$("#listbody").html(""); rownum = 1; category = "all"; requestList();'>
									<i class='list-icon'></i>
								</label>
							</th>
						</tr>
						<tr>
							<td>
								<table id="" width="100%" >
									<tbody id="listbody"></tbody>
								</table>
							</td>
						</tr>
						</table>
						</c:if>
						
						<c:if test='${blog.blog_open_yn eq "N" && sessionScope.loginUser.getMember_id() ne param.writer_id}'>
						<div style="text-align:center"><br>비공개 블로그 입니다.</div> 
						</c:if>
						
						<!--
						<div id="dotdotdotDiv">
							<a class="hover dotdotdot" href="">부적절한 컨텐츠 신고</a>
							<a class="hover dotdotdot" href="" >공유</a>
							<a class="hover dotdotdot" href="">쪽지 보내기</a>
						</div>
						-->
					</div>
				</div>
			</div>
		</section>
		<div id="div_Loading" style="font-size:11pt; width:100%;text-align:center">
			<img height='70px' src="images/InternetSlowdown_Day.gif">
		</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br>
</body>
</html>