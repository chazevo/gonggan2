<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" import="java.util.Calendar, com.kh.gonggan.member.model.vo.Member"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
Calendar cal = Calendar.getInstance();
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH) + 1;
int today = cal.get(Calendar.DATE);
%>
<!DOCTYPE html>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!-- 이거 있으면 부트스트랩 안먹음 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel='stylesheet' href='css/css.css'/> 
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="js/jquery.fancybox.js"></script>
<script type="text/javascript" src="js/controll.js"></script>
<title>다정 title here</title>
<script>
	var year = <%= year %>;
	var month = <%= month %>;
	var today = <%= today %>;
	var writer_id = '${blog.writer_id}';
	
	var d = document.createElement("div");
	var color = '${blog.color}';
	var contents_color = '${blog.contents_color}';
	var background_color = '${blog.background_color}';
	
	$(document).ready(function() {

		d.style.color = color;
		document.body.appendChild(d);
		color = window.getComputedStyle(d).color;

		var colorSplit1step = color.split("(")[1];
		$("input[name=color]").val(cnvrtRGBClrToHex(
				colorSplit1step.split(", ")[0],
				colorSplit1step.split(", ")[1],
				colorSplit1step.split(", ")[2].split(")")[0]));
		
		d.style.color = contents_color;
		contents_color = window.getComputedStyle(d).color;
		
		colorSplit1step = contents_color.split("(")[1];
		$("input[name=contents_color]").val(cnvrtRGBClrToHex(
				colorSplit1step.split(", ")[0],
				colorSplit1step.split(", ")[1],
				colorSplit1step.split(", ")[2].split(")")[0]));

		d.style.color = background_color;
		background_color = window.getComputedStyle(d).color;
		
		colorSplit1step = background_color.split("(")[1];
		$("input[name=background_color]").val(cnvrtRGBClrToHex(
				colorSplit1step.split(", ")[0],
				colorSplit1step.split(", ")[1],
				colorSplit1step.split(", ")[2].split(")")[0]));

		document.body.removeChild(d);

		reqVisitor();
		reqNeighborVisitor();
		reqMonNeiVisitor();
		reqMonNeiList();
      
		$("#year").text(year);
		$("#month").text(month);
		$("#today").text(today);
      
		$("input[name='blogTitle']").keyup(function() {
			$("#blogTitle").val($(this).val());
		});

		$("textarea[name='blogComment']").keyup(function() {
			$("#blogComment").val($(this).val());
		});

		var nav = $(".fixedDiv");
		var navoffset = $(".fixedDiv").offset();

		$(window).scroll(function () {
			if ($(this).scrollTop() >= navoffset.top) {
				nav.css("position", "fixed").css("top", 0);
				nav.removeClass("divisionMargin2");
				nav.addClass("divisionPadding");
				nav.css("background", "rgba(0, 0, 0, 0.3)")
					.css("width", "70%");
			}
			else if ($(this).scrollTop() == $(window).scrollTop()) {
				nav.removeClass("divisionPadding");
				nav.addClass("divisionMargin2");
				nav.css("position", "relative")
					.css("backgroundColor", "transparent");
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
      
		$("#todayNeighborCnt").click(function() {
			if ($("#visitedNeighbor").hasClass("hidden")) {
				$("#visitedNeighbor").removeClass("hidden");
				$("#visitedNeighbor").show();
			}
			else {
				$("#visitedNeighbor").addClass("hidden");
				$("#visitedNeighbor").hide();
			}
		});
      
      
      
		//$("#blogStatisticsImg").offset({left: $("#blogStatisticsImg").offset().left});
      
		$("#blogStatisticsBtn").hover(function() {
			if ($("#blogStatisticsImg").hasClass("hidden")) {
				$("#blogStatisticsImg").removeClass("hidden");
				$("#blogStatisticsImg").show();
			}
			else {
				$("#blogStatisticsImg").addClass("hidden");
				$("#blogStatisticsImg").hide();
			}
		});
      
		$("#blogControllBtn").hover(function() {
			if ($("#blogControllImg").hasClass("hidden")) {
				$("#blogControllImg").removeClass("hidden");
				$("#blogControllImg").show();
			}
			else {
				$("#blogControllImg").addClass("hidden");
				$("#blogControllImg").hide();
			}
		});
	});
</script>
</head>
<body>
<form id="form" action="bsetting.do" method="post" enctype="multipart/form-data">
	<c:if test="${empty param || param.writer_id ne sessionScope.loginUser.getMember_id() }">
		<jsp:forward page="error.jsp"></jsp:forward>
	</c:if>
		<nav class="navbarCustom navbar-default">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp">
					<img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
					<img class="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px">
				</a>
			</div>
			
			<div class="navbar-right">
				<c:if test="${!empty sessionScope.loginUser }">
				<a id="loginUser" class="navbar-brand" href="#" >
					<c:if test='${empty sessionScope.loginUser.getProfile_photo() }'>
					<img src="images/default.png" height="40px" class="img-circle">&nbsp;
					</c:if>
					<c:if test='${!empty sessionScope.loginUser.getProfile_photo() }'>
					<img src="images/profileImages/${sessionScope.loginUser.getProfile_photo()}" height="40px" class="img-circle">&nbsp;
					</c:if>
					&nbsp; ${sessionScope.loginUser.getMember_id() } 님
				</a>
				</c:if>
				<c:if test="${empty sessionScope.loginUser }">
				<a class="navbar-brand"  href="" >   로그인or회갑부분</a>
				</c:if>
			</div>
		</nav>
		<div id="loginUserDetail" class="hidden">
			<table id="idclick_table">
				<tr id="center_align">
					<td>
						<a href="mypage.do?writer_id=${sessionScope.loginUser.getMember_id() }">마이페이지</a>&nbsp;&nbsp; |  &nbsp;&nbsp;
						<a href="myhome.do?writer_id=${sessionScope.loginUser.getMember_id() }">내블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
						<a href="#">이웃 블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
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
		<div class="divCenter">
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
				<defs>
					<linearGradient id="grad1" x1="0%" y1="0%" x2="100%" y2="0%">
						<stop offset="0%" style="stop-color:#DEACC6;stop-opacity:1" />
						<stop offset="100%" style="stop-coloR:#91B2DF;stop-opacity:1" />
					</linearGradient>
				</defs>
				<text fill="url(#grad1)" font-size="50" font-family="Verdana" x="0" y="60" >
					내 </text>
				<text fill="url(#grad1)" font-size="50" font-family="Verdana" x="50" y="60">
					블</text>
				<text fill="url(#grad1)" font-size="50" font-family="Verdana" x="90" y="60">
					로</text>
				<text fill="url(#grad1)" font-size="50" font-family="Verdana" x="130" y="60">
					그 </text>
				<text fill="url(#grad1)" font-size="50" font-family="Verdana" x="180" y="60">
					관</text>
				<text fill="url(#grad1)" font-size="50" font-family="Verdana" x="220" y="60">
					리</text>
			</svg>
			<h3 class="text-center divisionMargin2" style="visibility:hidden">내 블로그 관리</h3>
			<div class="fixedDiv divisionMargin2">
				<div>
					<a href="javascript:tab(1);">
						<div id="blogControllBtn" class="gradLong">블로그 관리</div>
					</a>
					<a href="javascript:tab(2);">
						<div id="blogStatisticsBtn" class="gray">블로그 통계</div>
					</a>
				</div>
				<img id="blogControllImg" src="images/블로그관리_설명_마우스오버시.png" width="60%" class="hidden">
				<img id="blogStatisticsImg" src="images/statistics.png" width="60%" class="hidden">
			</div>
			<div id="blogControll" class="divisionMargin2">
				<fieldset>
					<legend>블로그 배너 관리</legend>
					<div class="header-content bannerEditOuter">
						<div class="bannerEdit text-center"
							style="<c:if test='${!empty blog.getBackground()}'>background:url(backgroundImages/${blog.getBackground()});background-size:cover;</c:if><c:if test='${! empty blog.background_color}'>background-color=${blog.background_color };</c:if>">
							<!-- <h2>당신만의 공간에서 당신의 글을 만들어보세요.</h2> -->
							<input id="blogTitle" type="text" value="${blog.title }" style="color:${blog.color}" disabled>
							<hr class="whiteHr">
							<!-- 당신만의 공간에서 당신의 글을 만들어보세요.-->
							<textarea id="blogComment" rows="3" style="color:${blog.contents_color}" disabled>${blog.contents }</textarea>
						</div>
					</div>
					<table width='100%'  class="divisionMargin">
						<colgroup>
							<col width="15%" />
							<col width="85%" />
						</colgroup>
						<tr>
							<td>
								배경 이미지&nbsp;
								<input type="radio" name="bgmode" value="img"
									<c:if test='${!empty blog.background }'>checked</c:if>>
							</td>
							<td>
								<input type="text" id="filename" name="background" class="fileInputTextbox"
									readonly="readonly" disabled="true">
								<div class="fileInputDiv">
									<input type="button" value="첨 부 파 일" class="fileInputBtn">
									<input type="file" id="file" name="file" accept=".gif,.jpeg,.jpg,.png"
										onchange="$('#filename').val($(this).val()); changeBgImg(this); $('input[name=bgchanged]').val('N');">
								</div>
								<input type="button" value="삭제"
									onclick="$('input[name=file]').val(''); $('.bannerEdit').css('background', 'none');">&nbsp;&nbsp;
								<input type="button" value="취소"
									onclick="$('.bannerEdit').css('background', 'url(backgroundImages/${blog.getBackground()})'); $('input[name=bgchanged]').val('N');">
								<!-- button누르면 submit 되는 이유?, 배경이미지, 배경색 선택적 적용 구현 안됨 -->
							</td>
						</tr>
						<tr>
							<td>
								배경색&nbsp;
								<input type="radio" name="bgmode" value="color"
									<c:if test='${!empty blog.background_color }'>checked</c:if>>
							</td>
							<td>
								<input type="color" name="background_color" onchange="changeBgColor(this);">&nbsp;
								<input type="button" onclick="$('input[name=background_color]').val('${blog.background_color}');" value="취소">&nbsp;
							</td>
						</tr>
						<tr>
							<td>제목</td>
							<td>
								<c:if test="${!empty blog }">
								<input type="text" name="blogTitle" class="full" value="${blog.title }" >&nbsp;
								<input type="button" value="취소" onclick="$('input[name=blogTitle], #blogTitle').val('${blog.title}')">&nbsp;
								&nbsp;<input type="color" name="color" onchange="changeTitleColor(this);">&nbsp;
								<input type="button" value="취소" onclick="cancelTitleColorChange();">
								</c:if>
							</td>
						</tr>
						<tr>
							<td>내용</td>
							<td>
								<c:if test="${!empty blog }">
								<textarea name="blogComment" rows="3" class="full" >${blog.contents }</textarea>&nbsp;
								<input type="button" value="취소" onclick="$('textarea[name=blogComment], #blogComment').val('${blog.contents }')">&nbsp;
								&nbsp;<input type="color" name="contents_color" onchange="changeContentsColor(this);">&nbsp;
								<input type="button" value="취소" onclick="cancelContentColorChange();">
								</c:if>
							</td>
						</tr>
					</table>
				</fieldset>
				<hr class="grayHr">
				<fieldset>
					<legend>블로그 공개 관리</legend>
					<input type="radio" name="blogOpenYn" value="Y"
						<c:if test='${blog.blog_open_yn eq "Y" }'>checked</c:if>>&nbsp;공개&nbsp;
					<input type="radio" name="blogOpenYn" value="N"
						<c:if test='${blog.blog_open_yn eq "N" }'>checked</c:if>>&nbsp;비공개
				</fieldset>
				<hr class="grayHr">
				<fieldset>
					<legend>댓글 달기 관리</legend>
					<input type="radio" name="hideComment" value="Y"
						<c:if test='${blog.hide_comment eq "N" }'>checked</c:if>>&nbsp;댓글기능 활성화&nbsp;
					<input type="radio" name="hideComment" value="N"
						<c:if test='${blog.hide_comment eq "Y" }'>checked</c:if>>&nbsp;댓글기능 해제
				</fieldset>
				<hr class="grayHr">
				<fieldset>
					<legend>메뉴사용 관리</legend>
					<table width='100%'  class="divisionMargin">
						<colgroup>
							<col width="10%" />
							<col width="10%" />
							<col width="60%" />
							<col width="10%" />
							<col width="10%" />
						</colgroup>
						<tr>
							<th>메뉴명</th><th>사용</th><th>설명</th>
							<th>공개범위</th><th>기본 설정값</th>
						</tr>
						<tr>
							<td>일기</td><td><input type="checkbox" checked disabled></td>
							<td>자신의 느낌과 일상을 기록하는 기본메뉴</td>
							<td>
								<input type="radio" name="diaryOpenYn" value="Y"
									<c:if test='${blog.diary_open_yn eq "Y" }'>checked</c:if>>
									&nbsp;공개
							</td>
							<td><input type="radio" name="diaryOpenYn" value="N"
									<c:if test='${blog.diary_open_yn eq "N" }'>checked</c:if>>
									&nbsp;비공개
							</td>
						</tr>
						<tr>
							<td>장소</td>
							<td><input type="checkbox"></td>
							<td>위치정보가 첨부된 포스트를 작성할 수 있는 메뉴</td>
							<td>
								<input type="radio" name="placeOpenYn" value="Y"
									<c:if test='${blog.place_open_yn eq "Y" }'>checked</c:if>>
									&nbsp;공개
							</td>
							<td><input type="radio" name="placeOpenYn" value="N"
									<c:if test='${blog.place_open_yn eq "N" }'>checked</c:if>>
									&nbsp;비공개
							</td>
						</tr>
						<tr>
							<td>리뷰</td>
							<td><input type="checkbox"></td>
							<td>사용후기, 방문후기 등 여러 후기들을 작성할 수 있는 메뉴</td>
							<td>
								<input type="radio" name="reviewOpenYn" value="Y"
									<c:if test='${blog.review_open_yn eq "Y" }'>checked</c:if>>
									&nbsp;공개
							</td>
							<td><input type="radio" name="reviewOpenYn" value="N"
									<c:if test='${blog.review_open_yn eq "N" }'>checked</c:if>>
									&nbsp;비공개
							</td>
						</tr>
						<tr>
							<td>뮤직</td>
							<td><input type="checkbox"></td>
							<td>해당 음악을 유튜브로 연결이 가능하며 글을 작성할 수 있는 메뉴</td>
							<td>
								<input type="radio" name="musicOpenYn" value="Y"
									<c:if test='${blog.music_open_yn eq "Y" }'>checked</c:if>>
									&nbsp;공개
							</td>
							<td><input type="radio" name="musicOpenYn" value="N"
									<c:if test='${blog.music_open_yn eq "N" }'>checked</c:if>>
									&nbsp;비공개
							</td>
						</tr>
						<tr>
							<td>영화</td>
							<td><input type="checkbox"></td>
							<td>영화를 첨부할 수 있고 글을 작성할 수 있는 메뉴</td>
							<td>
								<input type="radio" name="movieOpenYn" value="Y"
									<c:if test='${blog.movie_open_yn eq "Y" }'>checked</c:if>>
									&nbsp;공개
							</td>
							<td><input type="radio" name="movieOpenYn" value="N"
									<c:if test='${blog.movie_open_yn eq "N" }'>checked</c:if>>
									&nbsp;비공개
							</td>
						</tr>
						<tr>
							<td>뉴스</td>
							<td><input type="checkbox"></td>
							<td>뉴스기사를 첨부할 수 있고 글을 작성할 수 있는 메뉴</td>
							<td>
								<input type="radio" name="newsOpenYn" value="Y"
									<c:if test='${blog.news_open_yn eq "Y" }'>checked</c:if>>
									&nbsp;공개
							</td>
							<td><input type="radio" name="newsOpenYn" value="N"
									<c:if test='${blog.news_open_yn eq "N" }'>checked</c:if>>
									&nbsp;비공개
							</td>
						</tr>
					</table>
					<table width='100%' class="divisionMargin">
						<tr><th colspan="3">메뉴 언어 설정 </th></tr>
						<tr>
							<td>
								<input type="radio" name="languages" value="kor" <c:if test='${blog.languages eq "kor"}'>checked</c:if>>&nbsp;한국어
							</td>
							<td>
								<input type="radio" name="languages" value="eng" <c:if test='${blog.languages eq "eng"}'>checked</c:if>>&nbsp;영어 
							</td>
							<td>
								<input type="radio" name="languages" value="jp" <c:if test='${blog.languages eq "jp"}'>checked</c:if>>&nbsp;일본어 
							</td>
						</tr>
					</table>
				</fieldset>
				<hr class="grayHr">
				<fieldset>
					<legend>글 관리</legend>
					<table width="100%">
						<tr><th>댓글</th><th class='text-center'>삭제</th></tr>
						<tbody id="">
							<c:if test="${!empty commentAll }">
							<c:forEach items="${commentAll}" var="i"  begin ="0" varStatus="status">
							<tr>
								<td>
									<font color="#2D86C9"><a href="">${i.writer_id }</a> 님이</font>
									<a href="" >${i.comment_content }</a>
								</td>
								<td><a href="javascript:deletePost('${sessionScope.loginUser.getMember_id()}',${i.post_id },${i.comment_num });"><img src="images/x_icon.png"></a></td>
							</tr>
							</c:forEach>
							</c:if>
						</tbody>
						<tr>
							<td colspan="2" class="text-center tableFooter">
								<a href="">◀</a>︎<a href="">▶</a>
							</td>
						</tr>
					</table>
				</fieldset>
				<hr class="grayHr">
				<div class="footerDiv">
					<div>
						<a href="javascript:settingComplete();"><div class="gradLong">등 록</div></a>&nbsp;
						<a href="javascript:history.back();"><div class="gray">취 소</div></a>
					</div>
				</div>
			</div>
			<div id="blogStatistics" class="divisionMargin2" style="display:none;">
				<fieldset>
					<legend>오늘 지표</legend>
					<span id="year"></span>.<span id="month"></span>.<span id="today"></span><br>
					조회수 | <span></span>건<br>
					재방문율 | <span>13.5</span>%<br> <!-- 어제(한달전) 방문했던 오늘(이번달) 방문자수 / (한달전)어제 방문자수 -->
					방문횟수 | <span id="todayCnt"></span>건<br>
					이웃방문현황 | <a id="todayNeighborCnt"></a>명의 이웃이 방문 
					<div id="visitedNeighbor" class="hidden"></div>
				</fieldset>
				<hr class="grayHr">
				<fieldset>
					<legend>사용자 분석_월단위 </legend>
					<b>성별,연령별 분포</b>
					<div id="chart" style="height:400px"></div>
					<div id="monNeiVisitor" class="divisionMargin">
						<b>이웃 방문현황</b>
						<span id="monthNeiVisitorCnt">12</span>명의 이웃이 방문<br>
						<!--
						<a href="myhome.do?userId=">aekek_jj</a>님,
						<a href="myhome.do?userId=">dazz:)</a>님,
						<a href="myhome.do?userId=">아이우@</a>님,
						<a href="myhome.do?userId=">bacde</a>님,
						<a href="myhome.do?userId=">gkgkzoo</a>님,
						<a href="myhome.do?userId=">다랭이</a>님,
						<a href="myhome.do?userId=">skyVV</a>님,
						<a href="myhome.do?userId=">aekek_jj</a>님,
						<a href="myhome.do?userId=">dazz:)</a>님,<br>
						<a href="myhome.do?userId=">아이우@</a>님,
						<a href="myhome.do?userId=">bacde</a>님,
						<a href="myhome.do?userId=">gkgkzoo</a>님 <br>
						-->
					</div>
					<div class="divisionMargin" id="monNei">
						<b>이웃 증감수</b>
						<span id="monNeiCnt">2</span>명의 이웃이 증가 <br>
						<!-- <a href="myhome.do?userId=">bacde</a>님,
						<a href="myhome.do?userId=">gkgkzoo</a>님 -->
					</div>
				</fieldset>
				<hr class="grayHr">
				<fieldset>
					<legend>순위_월단위 </legend>
					<b>좋아요수 순위</b>
					<table width=100%>
						<colgroup>
							<col width="15%">
							<col width="65%">
							<col width="20%">
						</colgroup>
						<c:set var="count" value="0" />
						<c:set var="musiccount" value="0" />
						<c:set var="diarycount" value="0" />
						<c:set var="reviewcount" value="0" />
						<c:set var="moviecount" value="0" />
						<c:set var="newscount" value="0" />
						<c:set var="bookcount" value="0" />
						<c:forEach items="${likeInOrder}" var="i"  begin ="0" varStatus="status">
						<c:if test="${i.category eq 'music'}">
						<c:set var="musiccount" value="${musiccount + 1}" />
						<tr>
							<c:set var="count" value="${count + 1}" />
							<th>${count}</th>
							<td>
								<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>${musiclist[musiccount-1].title}</a>
							</td>
							<td>
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>
								<a data-fancybox data-src="goodList.do?postId=${i.post_id }">
									${i.goodCnt }
								</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${i.category eq 'book'}">
						<c:set var="bookcount" value="${bookcount + 1}" />
						<tr>
							<c:set var="count" value="${count + 1}" />
							<th>${count}</th>
							<td>
								<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>${booklist[bookcount-1].title}</a>
							</td>
							<td>
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>
								<a data-fancybox data-src="goodList.do?postId=${i.post_id }">
									${i.goodCnt }
								</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${i.category eq 'diary'}">
						<c:set var="diarycount" value="${diarycount + 1}" />
						<tr>
							<c:set var="count" value="${count + 1}" />
							<th>${count}</th>
							<td>
								<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>${dlist[diarycount-1].diary_title}</a>
							</td>
							<td>
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>
								<a data-fancybox data-src="goodList.do?postId=${i.post_id }">
								${i.goodCnt }</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${i.category eq 'review'}">
						<c:set var="reviewcount" value="${reviewcount + 1}" />
						<tr>
							<c:set var="count" value="${count + 1}" />
							<th>${count}</th>
							<td>
								<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
									${reviewlist[reviewcount-1].review_content}</a>
							</td>
							<td>
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>
								<a data-fancybox data-src="goodList.do?postId=${i.post_id }">
									${i.goodCnt }</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${i.category eq 'news'}">
						<c:set var="newscount" value="${newscount + 1}" />
						<tr>
							<c:set var="count" value="${count + 1}" />
							<th>${count}</th>
							<td>
								<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
									${newslist[newscount-1].title}</a>
							</td>
							<td>
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>
								<a data-fancybox data-src="goodList.do?postId=${i.post_id }">
									${i.goodCnt }</a>
							</td>
						</tr>
						</c:if>
						<c:if test="${i.category eq 'movie'}">
						<c:set var="moviecount" value="${moviecount + 1}" />
						<tr>
						<c:set var="count" value="${count + 1}" />
							<th>${count}</th>
							<td>
								<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
									${movielist[moviecount-1].title}</a>
							</td>
							<td>
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>
								<a data-fancybox data-src="goodList.do?postId=${i.post_id }">
									${i.goodCnt }</a>
							</td>
						</tr>
						</c:if>
						</c:forEach>
					</table>
					<div class="commentRankDiv divisionMargin">
						<c:set var="countcomment" value="0" />
						<b>댓글수 순위</b>
						<table width=100%>
							<colgroup>
								<col width="15%">
								<col width="65%">
								<col width="20%">
							</colgroup>
               
							<c:forEach items="${commentInOrder}" var="i"  begin ="0" varStatus="status">
							<c:if test="${i.category eq 'music'}">
							<c:set var="musiccount" value="${musiccount + 1}" />
							<tr>
								<c:set var="countcomment" value="${countcomment + 1}" />
								<th>${countcomment}</th>
								<td>
									<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
										${musiclist[musiccount-1].title}</a>
								</td>
								<td>
									<img  src="images/marker.png">
									${i.comment_cnt }
								</td>
							</tr>
							</c:if>
							<c:if test="${i.category eq 'diary'}">
							<c:set var="diarycount" value="${diarycount + 1}" />
							<tr>
								<c:set var="countcomment" value="${countcomment + 1}" />
								<th>${countcomment}</th>
								<td>
									<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
										${dlist[diarycount-1].diary_title}</a>
								</td>
								<td>
									<img  src="images/chat_icon.png">
									${i.comment_cnt }
								</td>
							</tr>
							</c:if>
							<c:if test="${i.category eq 'review'}">
							<c:set var="reviewcount" value="${reviewcount + 1}" />
							<tr>
								<c:set var="countcomment" value="${countcomment + 1}" />
								<th>${countcomment}</th>
								<td>
									<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
										${reviewlist[reviewcount-1].review_content}</a>
								</td>
								<td>
									<img   src="images/chat_icon.png">
									${i.comment_cnt }
								</td>
							</tr>
							</c:if>
							<c:if test="${i.category eq 'news'}">
							<c:set var="newscount" value="${newscount + 1}" />
							<tr>
								<c:set var="countcomment" value="${countcomment + 1}" />
								<th>${countcomment}</th>
								<td>
									<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
										${newslist[newscount-1].title}</a>
								</td>
								<td>
									<img  src="images/chat_icon.png" >
									${i.comment_cnt }
								</td>
							</tr>
							</c:if>
							<c:if test="${i.category eq 'movie'}">
							<c:set var="moviecount" value="${moviecount + 1}" />
							<tr>
								<c:set var="countcomment" value="${countcomment + 1}" />
								<th>${countcomment}</th>
								<td>
									<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id() }'>
										${movielist[moviecount-1].title}</a>
								</td>
								<td>
									<img  src="images/chat_icon.png"> 
									${i.comment_cnt }
								</td>
							</tr>
							</c:if>
							</c:forEach>
						</table>
					</div>
				</fieldset>
				<hr class="grayHr">
			</div>
		</div>
	<input type="hidden" name="bgchanged" value="N">
	</form>
</body>