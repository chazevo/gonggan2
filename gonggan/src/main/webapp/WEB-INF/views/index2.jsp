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
<meta name="viewport"
   content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>Insert title here</title>
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
<script type="text/javascript" src="js/index2.js"></script>
<script src="js/jquery.fancybox.js"></script>
<script type="text/javascript">

var loginUser = '${sessionScope.loginUser.getMember_id()}';

var imgVal = <%= imgVal %>;

var maxRownum;
var plistSize = ${plistSize};
var nplistSize = ${nplistSize};
var reviewlistSize = ${reviewlistSize};
var newslistSize = ${newslistSize}; 
var musiclistSize = ${musiclistSize};
var reviewlistSize = ${reviewlistSize};
var diarylistSize = ${diarylistSize};
var movielistSize = ${movielistSize};
var booklistSize = ${booklistSize};
var placelistSize = ${placelistSize};
var freelistSize = ${freelistSize};
var psearchSize = 0;

var initPosition;
var prevPosition;

window.onload = function() {

	$("#searchNeiDiv").hide();
   
	//trace(loginUser);
	
	maxRownum = plistSize;
	requestList(rownum);
	
	/*
	$("#div_Loading").click(function() {
		//alert("rownum : " + rownum);
		if(maxRownum >= rownum)
			requestList(rownum);
	});
	*/

	
	$(window).scroll(function() {
		//if  ($(window).scrollTop() >= $(window).height() - $(window).height() / 3) {
			//if  ($(window).scrollTop() >= 500) {
				// $(document).height() 현재 페이지 높이
				// $(window).height() 윈도우 크기
				
		initPosition = $(window).scrollTop()
		
		if (initPosition > prevPosition) {
			if  ($(document).height() == $(window).scrollTop() + $(window).height()) {
				
				if(maxRownum >= rownum) {
					alert("maxRownum : " + maxRownum + ", rownum : " + rownum);
					$("#div_Loading").show();
					
					if (sort == "date") {
							if (category == "all")
								requestList(rownum);
							else if (category == "psearch")
								searchPost(rownum);
							else if (category == "neighborlist")
								requestNeighborPostList(rownum, loginUser);
							else
								requestCategoryList(rownum, category);
					}
					else if (sort == "like")
						requestLikeList(rownum, category, loginUser);
						
					//$(window).scrollTop($(window).height() / 2);
					
					//setTimeout(function() {}, 1000);
				}
			}
		}
		
		prevPosition = initPosition;
	});
		
	document.getElementById("searchPost").focus();
}
</script>
<style>
</style>
</head>
<body>
<!--<nav class="navbar navbar-default">-->
<div><a id="fancy" style="display:none"></a>
	<!--<div class="container-fluid">--><div>
	<!-- container-fluid : 화면 너비가 resize 되더라도 화면에 가득 참  -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#menu">
				<span class="sr-only">Toggle navigation</span> Menu <i class="menu"></i>
				<!-- sr-only : 숨김 -->
			</button>
			<a class="" href="index2.do">
				<img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
				<img class="smallLogoImg" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px">
			</a>
		</div>
		<div class="collapse navbar-collapse" id="menu">
			<!-- collapse 제거 -> 화면 크기 작아졌을 때 생기는 menu 아이콘을 클릭하지 않아도 메뉴가 펼쳐짐  -->
			<!-- navbar-collapse 제거-> 메뉴 사라짐  -->
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = diarylistSize; sort = 'date'; requestCategoryList(rownum = 1, category = 'diary'); diarycount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">
					일기</a>
				</li>
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = placelistSize; sort = 'date'; requestCategoryList(rownum = 1, category = 'place'); placecount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">
					장소</a>
				</li>
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = reviewlistSize; requestCategoryList(rownum = 1, category = 'review'); reviewcount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">
						리뷰</a>
				</li>
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = musiclistSize; requestCategoryList(rownum = 1, category = 'music'); musiccount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">음악</a>
				</li>
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = movielistSize; requestCategoryList(rownum = 1, category = 'movie'); moviecount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">영화</a>
				</li>
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = newslistSize; requestCategoryList(rownum = 1, category = 'news'); newscount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">뉴스</a>
				</li>
				<li>
					<a href="javascript:$('#blogHomeContentDiv').html(''); $('select').val('date'); maxRownum = booklistSize; requestCategoryList(rownum = 1, category = 'book'); bookscount = 0;"
						onclick="$('li a').css('background-color', 'transparent'); $(this).css('background-color', '#E6E6E6');">책</a>
				</li>
			</ul>
		</div>
	</div>
</div><!--</nav>-->
<div class="divCenter">
	<!--<div class="container-fluid">-->
		<!-- container-fluid : 화면category == "contentsearch" 너비가 resize 되더라도 화면에 가득 참  -->
		<div class="blogHomeHeader">
			<table class="table1st" style="<c:if test='${empty sessionScope.loginUser}'>width:65%;</c:if>">
				<c:if test="${!empty sessionScope.loginUser}">
				<colgroup>
					<col width="60%" />
					<col width="40%" />
 				</colgroup>
				</c:if>
 				<tr>
 					<td class="<c:if test="${!empty sessionScope.loginUser}">disapear</c:if>"
 						style="height:200px;">
 						<img src="images/KakaoTalk_Photo_2017-04-26-10-19-25.png"
 							width="100%" height="100%">
					</td>
					<c:if test="${!empty sessionScope.loginUser}">
					<td class="table2nd">
						<table>
							<colgroup>
								<col style="width:*;" />
								<col width="5%" />
							</colgroup>
							<tr>
								<td colspan="2">
									<div class="div2" id="searchNeiDiv">
										<input type="text" id="searchNeighbor" placeholder="검색" size="12"
											onkeyup="searchNeighbor();">
										<a href="javascript:searchNeighbor();">
											<img src=images/KakaoTalk_Photo_2017-04-26-21-33-40_100.png
												width="10%">
										</a>
									</div>
								</td>
							</tr>
							                     <tr>
                        <th>
                           <span id="postAlarm">내 포스트 알람_</span>
                           <font color="#2D86C9"><b id="postAlarmCnt">${mAlarmListsize }</b></font>
                        </th>
                        <td><a href="">▶</a></td>
                     </tr>
                     <tbody id="listbody_neighbor"></tbody>
                     <tbody id="listbody_mytrace"></tbody>
                     <tbody id="listbody_newPost">
                     <c:forEach  items="${mAlarmList}" var="i" begin="0">
                     <c:if test='${!empty mAlarmList}'>
                        <tr>
                           <td>
                               <c:if test='${i.type_cg eq "C"}' >
                                 <a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id()} '>댓글</a>을 남기셨습니다. |
                                 <a href="myhome.do?writer_id=${i.writer_id}"><font color="#2D86C9">${i.writer_id }</font></a>
                              </c:if>
                           </td>
                           <td></td>
                        </tr>
                        <tr>
                           <td>
                               <c:if test='${i.type_cg eq "G"}' >
                                 <a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${sessionScope.loginUser.getMember_id()} '>좋아요</a>누르셨습니다. |
                                 <a href="myhome.do?writer_id=${i.writer_id}"><font color="#2D86C9">${i.writer_id }</font></a>
                              </c:if>
                           </td>
                           <td></td>
                        </tr>
                       </c:if>
                       <c:if test='${empty mAlarmList}'>
                                       알람이 없습니다.
                      </c:if>
                        </c:forEach>
                     </tbody>
							<tr>
								<td colspan="2" align="center"  class="title">
									<img width="98%" height="2px" src="images/KakaoTalk_Photo_2017-04-26-10-46-42_84.png">
								</td>
							</tr>
							<tr>
								<td class="title">
									서로이웃 신청_
									<font color="#2D86C9">
										<b id="neighborReqListSize">${neighborReqListSize }</b>
									</font>
								 </td>
								 <td  class="title"><a href="">▶</a></td>
							</tr>
							<tbody id="listbody_newNeighbor">
								<tr>
									<td>
										<table>
											<tr>
												<c:if test="${!empty (neighborReqList) }">
												<c:forEach items="${ neighborReqList}" var="i"  begin ="0" >
												<td>
													<a href="myhome.do?writer_id=${i.member_id }">${i.member_id }</a>
													<a onclick="acceptNeig('${sessionScope.loginUser.getMember_id() }', '${i.member_id }'); $(this).parent().html('');">
														<div class="neighborYN">수락</div>
													</a>
													<a onclick="rejectNeig('${sessionScope.loginUser.getMember_id() }', '${i.member_id }'); $(this).parent().html('');">
														<div class="neighborYN">거절</div>
													</a>
												</td>
												</c:forEach>
												</c:if>
												<td></td>
											</tr>
										</table>
									</td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</td>
					</c:if>
				</tr>
			</table>
			<div class="myDiv" style="<c:if test='${empty sessionScope.loginUser}'>width:30%;</c:if>">
				<c:if test="${empty (loginUser) }">
				<form action = "login.do" method="post" id="login">
					<table align="center">
						<tr>
							<td>아이디&nbsp;</td>
							<td><input type="text" name="member_id" width="100%"></td>
						</tr>
						<tr>
							<td>비밀번호&nbsp;</td>
							<td>
								<input type="password" name="member_pw" width="100%"
									onkeydown="if (event.keyCode == 13) goSubmit();">
							</td>
						</tr>
					</table>
					<a href="javascript:goSubmit();">로그인</a><a href="join.do">회원가입</a>
					<a href="findIdPwd.do">아이디 찾기</a>
					<a href="findIdPwd.do">비밀번호 찾기</a>
				</form>
				
				</c:if>
				<c:if test="${!empty (loginUser) }">
				<c:out value="${loginUser.getMember_id()}님"></c:out>
				<a href="/gonggan/logOut.do">로그아웃</a>
				<!-- <a href="/gonggan/update.do">정보수정</a> -->
				<hr class="whiteHr">
				<b><a href="/gonggan/mypage.do?writer_id=${sessionScope.loginUser.getMember_id()}">내 블로그 소식</a></b>
				<a href='javascript:$("select").val("date"); plistcount = 0; maxRownum = plistSize; trace("${ sessionScope.loginUser.getMember_id()}");'>나의 흔적</a> <!-- 내가 쓴 댓글들  -->
				<a href="javascript:$('select').val('date'); nplistcount = 0; maxRownum = nplistSize; neighborList(loginUser);" id="neighborList">이웃 블로그</a>   <!-- 이웃 블로그 목록, 이웃 새글 -->
				<a href="uploadform.do?writer_id=${sessionScope.loginUser.getMember_id()}">포스트 쓰기</a>
				<a href="myhome.do?writer_id=${sessionScope.loginUser.getMember_id() }"><div class="goToMyBlog">내 블로그 </div></a>
				<img src="images/KakaoTalk_Photo_2017-04-26-10-24-13.png" width="50px">
				</c:if>
			</div>
		</div>
		<section>
			<div>
				<div class="searchPost">
					<table>
						<tr>
							<td>
								<div class="text-center div1">
									<select id="select2">
										<option value="0">내용</option>
										<option value="1">작성자</option>
									</select>
								</div>
							</td>
							<td>
								<div class="div2">
									<input type="text" id="searchPost" placeholder="검색" size="12"
										onkeydown="if (event.keyCode == 13) { category = 'psearch'; $('#select').val('date'); psearchRownum(); }">
									<a href="javascript: category = 'psearch'; $('#select').val('date'); psearchRownum(); ">
										<img src=images/KakaoTalk_Photo_2017-04-26-21-33-40_100.png
											width="10%">
									</a>
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="div3">
					<select onchange="sorting();" id="select">
						<option value="date">최신순</option>
						<option value="like">좋아요</option>
					</select>
				</div>
			</div>
			<div class="blogHomeContentDiv" id="blogHomeContentDiv">
				<%-- 
				<c:set var="musiccount" value="0" />
				<c:set var="diarycount" value="0" />
				<c:set var="moviecount" value="0" />
				<c:set var="newscount" value="0" />
				<c:set var="reviewcount" value="0" />
				
				<c:forEach items="${plist}" var="i" begin="0" varStatus="status">
				<c:if test="${i.category eq 'diary'}">
				<c:set var="diarycount" value="${diarycount + 1}" />
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a data-fancybox data-src='pdetail.do?postId=${post_id} &writerId=${post_id} '>
									${dlist[diarycount-1].diary_content}
								</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">${i.writer_id}</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>&nbsp;<a href="">70</a>
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				
				<c:if test="${i.category eq 'movie'}">
				<c:set var="moviecount" value="${moviecount + 1}" />
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">
									${movielist[moviecount-1].title}
								</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">${i.writer_id}</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>&nbsp;<a href="">70</a>
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				
				<c:if test="${i.category eq 'music'}">
				<c:set var="musiccount" value="${musiccount + 1}" />
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">
									${musiclist[musiccount-1].title}
								</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">${i.writer_id}</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>&nbsp;<a href="">70</a>
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				
				<c:if test="${i.category eq 'news'}">
				<c:set var="newscount" value="${newscount + 1}" />
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">
									${newslist[newscount-1].title}
								</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">${i.writer_id}</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>&nbsp;<a href="">70</a>
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				
				<c:if test="${i.category eq 'review'}">
				<c:set var="reviewcount" value="${reviewcount + 1}" />
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">
									${reviewlist[reviewcount-1].review_content}
								</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">${i.writer_id}</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='' onclick='like();'>
									<i class='like-icon'></i>
								</label>&nbsp;<a href="">70</a>
							</td>
						</tr>
					</table>
				</div>
				</c:if>
				</c:forEach>
				--%>
				<!-- <div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">서울에서 독일 기자가 타본 기아 K7</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">aekwdja</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">'저질 체력' 어쩌다 이렇게 되었을까?</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">dani_jj</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<a href="goodList.do?postId=${postId }"> ${goodCnt }</a>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">${goodCnt }</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">서울에서 독일 기자가 타본 기아 K7</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">부릉부릉달</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">'저질 체력' 어쩌다 이렇게 되었을까?</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">cocoBabi</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">유럽여행에서 유용했던 나만의 팁</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">hikari_s</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">편견을 깨는 시도가 돋보이는 영화 '오두막'</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">guguru</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">유럽여행에서 유용했던 나만의 팁</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">Angelica</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
				<div>
					<table>
						<colgroup>
							<col width="40%" />
							<col width="60%" />
						</colgroup>
						<tr>
							<td colspan="2" class="blogHomeContent">
								<a href="">편견을 깨는 시도가 돋보이는 영화 '오두막'</a>
							</td>
						</tr>
						<tr class="trBottom">
							<td><a href="">zoozo</a></td>
							<td class="rightAlign">
								<label class='checkbox-wrap'>
									<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
									<i class='like-icon'></i>
								</label>&nbsp;
								<c:if test="${ goodCnt ne '0' }">
								<b><a href="goodList.do?postId=${postId }"> ${goodCnt }</a></b>
								</c:if>
								<c:if test="${ goodCnt eq '0' }">
								<b>${goodCnt }</b>
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</div> -->
		</div>
		<div id="div_Loading" style="font-size:11pt; width:100%;text-align:center">
			<img height='70px' src="images/InternetSlowdown_Day.gif">
		</div>
	</section>
</div>
</body>
</html>