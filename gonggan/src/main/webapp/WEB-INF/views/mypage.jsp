<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.gonggan.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
	Member loginUser = (Member) session.getAttribute("loginUser");
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
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='css/css.css'/> 

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>\
<script src="js/jquery.fancybox.js"></script>
<script src="js/mypage.js"></script>
<script type="text/javascript" src="js/index2.js"></script>
<script type="text/javascript">

	window.onload = function() {
		$("#loginUser").click(function() {
			if ($("#loginUserDetail").hasClass("hidden")) {
				$("#loginUserDetail").removeClass("hidden");
				$("#loginUserDetail").show();
			} else {
				$("#loginUserDetail").addClass("hidden");
				$("#loginUserDetail").hide();
			}
		});
	}
</script>
</head>
<body>
<nav class="navbarCustom navbar-default">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.jsp">
			<img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
			<img class="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
	</div>
	<div class="navbar-right">
		<c:if test="${empty sessionScope.loginUser }">
					<a class="navbar-brand"  href="" >
						로그인or회갑부분
					</a>
				</c:if>
				<c:if test="${!empty sessionScope.loginUser }">
					<a id="loginUser" class="navbar-brand"  href="" >
						<img src="images/default.png" height="40px" class="img-circle">&nbsp;
						${sessionScope.loginUser.getMember_id() } 님
					</a>
				</c:if>
	</div>
</nav>
<div id="loginUserDetail" class="hidden">
	<div id="box_icon">
		<table id="idclick_table">
			<tr id="center_align">
				<td>
					<a href="mypage.do">마이페이지</a>&nbsp;&nbsp; |  &nbsp;&nbsp;
					<a href="selectBlog.do?writer_id=${sessionScope.loginUser.getMember_id() }">내블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
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
					${sessionScope.loginUser.getMember_id()}님의 알림이 없습니다.
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="divCenter">
	<!--<div class="container-fluid">--><div>
	<!-- container-fluid : 화면 너비가 resize 되더라도 화면에 가득 참  -->
		<div id="loginUserDetail" class="hidden"></div>
	</div>
	<section>
		<div class="mypage">
			<div><span>${sessionScope.loginUser.getMember_id()}</span> 님</div>
			<div class="right">
				<a href="uploadform.do" class="transparentFont">포스트쓰기</a>
				<a href="selectBlog.do?writer_id=${sessionScope.loginUser.getMember_id() }">
					<div class="goToMyBlog">내 블로그</div>
				</a>
				<img src="images/KakaoTalk_Photo_2017-04-26-10-24-13.png" width="50px">
			</div>
			<table class="tbl1" width="100%">
				<colgroup>
					<col width="15%" />
					<col width="30%" />
					<col width="45%" />
				</colgroup>
				<tr>
					<td  class="td">
						<table width="100%">
							<tr>
								<td><b><a href="updateform.do">내정보수정</a></b></td>
								<td>프로필 </td>
							</tr>
							<tr>
								<td colspan="2" class="text-center">
									<div>
										<a href="">
											<img src="images/myproimg_default.png" width="60%">
										</a>
										<a href="javascript:profileImgDelete();"><div class="x">X</div></a>
									</div>
									${sessionScope.loginUser.getMember_id()}
								</td>
							</tr>
							<tr>
								<td colspan="2" class="text-center">
									<textarea id="introducingArea" readonly="readonly">내소개입니다
   안녕하세요 !! :)
   여행 공간지기입니다!</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<a href="javascript:editProfile();">
										<div class="grayBorder" id="editProfile">수정</div>
									</a>
									<a href="javascript:editProfile2();" style="display:inline-block;">
										<div class="grayBorder" id="editProfileCancel" style="display:none;">취소</div>
									</a>
								</td>
							</tr>
						</table>
					</td>
					<td class="td">
						<form action="/gonggan/update.do" method="post" id="update">
							<table width="100%">
								<tr>
									<td><b>내 정보 수정</b></td>
									<td class="relative">
										내 정보 <a href="javascript:deleteMem();" class="deleteMem">탈퇴하기</a>
									</td>
								</tr>
								<tr>
									<td>아이디 </td>
									<td><input type="text" id="memberId" name="member_id" value="${sessionScope.loginUser.getMember_id()}" readonly></td>
								</tr>
								<tr>
									<td>비밀번호</td>
									<td>
										<input type="password" id="pwd" name="member_pw" value="${sessionScope.loginUser.getMember_pw()}" readonly>
									</td>
								</tr>
								<tr>
									<td><p class="p">비밀번호 확인</p></td>
									<td>
										<input type="password" id="pwd2" readonly>
									</td>
								</tr>
								<tr>
									<td>이메일</td>
									<td>
										<input type="text" id="email" name="email" value="${sessionScope.loginUser.getEmail()}" readonly>
									</td>
								</tr>
								<tr>
									<td>핸드폰 번호</td>
									<td>
										<input type="text" id="phone" value="${sessionScope.loginUser.getMember_phone()}" readonly>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="footerDiv">
										<div>
											<a href="javascript:editInfo();" style="display:inline-block;">
												<div class="grayBorder" id="editInfo" >수정</div>
											</a> &nbsp;
											<!-- c취소하기안나옴!! 수정필요 -->
											<a href="javascript:editInfo2();" style="display:inline-block;">
												<div class="grayBorder" id="editInfoCancel" style="display:none;">취소</div>
											</a>
										</div>
									</td>
								</tr>
							</table>
						</form>
					</td>
					<form action="/gonggan/delete.do" method="post" id="update">
						<input type="hidden" value="${sessionScope.loginUser.getMember_id()}" name="member_id">
						<input type="submit" value="회원탈퇴">
					</form>
					<!-- 마이페이지의 쪽지부분 -->
					<td class="td notTableList">
						<table width="100%;">
							<tr>
								<td colspan="3">
									<a href="">
										받은쪽지함 <img style="display:inline-block" src="images/chat_icon.png"  class="smallIcon" >
									</a>&nbsp; &nbsp;
									<a href="">
										보낸쪽지함 <img style="display:inline-block" src="images/chat_icon.png"  class="smallIcon" >
									</a>
								</td>
							</tr>
							<tr>
								<td width="15%"><font><a href="">이대장</a></font></td>
								<td width="70%">
									<a href="">자전거 여행! 같이갑시다!!! 저도 자전거 여행 너무너무 정말로 가고싶네여</a>
								</td >
								<td width="15%">2017-05-30</td>
							</tr>
							<tr>
								<td><font><a href="">이대장</a></font></td>
								<td>
									<a href="">자전거 여행! 같이갑시다!!! 저도 자전거 여행 너무너무 정말로 가고싶네여</a>
								</td>
								<td>받은날짜</td>
							</tr>
							<tr>
								<td><font><a href="">이대장</a></font></td>
								<td>
									<a href="">자전거 여행! 같이갑시다!!! 저도 자전거 여행 너무너무 정말로 가고싶네여</a>
								</td>
								<td>받은날짜</td>
							</tr>
							<tr>
								<td><font><a href="">이대장</a></font></td>
								<td>
									<a href="">자전거 여행! 같이갑시다!!! 저도 자전거 여행 너무너무 정말로 가고싶네여</a>
								</td>
								<td>받은날짜</td>
							</tr>
							<tr>
								<td><font><a href="">이대장</a></font></td>
								<td>
									<a href="">자전거 여행! 같이갑시다!!! 저도 자전거 여행 너무너무 정말로 가고싶네여</a>
								</td>
								<td>받은날짜</td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- 이웃새글부분 -->
				<tr>
					<td colspan="2" class="td notTableList">
						<b>내 포스트 알람 _ 댓글 </b><br>
						<c:if test="${!empty commentMyList }">
							<c:forEach items="${ commentMyList}" var="i"  begin ="0">
							<font><a href="">${i.writer_id }</a> 님이</font> |
							<a href="">동갑내기 부부의 세계로 가는 자전거 여행!</a>
											게시글에 <b>댓글</b>을 남기셨습니다.<br>
							</c:forEach>
						</c:if>

						<!-- 댓글 좋아요 알림부분 -->
							<td class="td notTableList">
								<table>
								<tr>
									<td colspan="2" align="center">
										<img width="98%" height="2px" src="images/KakaoTalk_Photo_2017-04-26-10-46-42_84.png">
									</td>
								</tr>
								<tr>
									<td>
										<table>
													<b>내 포스트 알람_좋아요</b><br>
													게시글을 작성하고 이웃을 만들어보세요!!<br>
													<c:if test="${!empty (goodMyList)}">
													 <c:forEach items="${ goodMyList}" var="i"  begin ="0">
													 <tr>
												<td> 
													 	<font><a href="">${ i.member_id }</a> 님이</font> | 
													게시글에 <b>좋아요</b>을 누르셨습니다.<br>
														</td>
											</tr>
													 </c:forEach>
													</c:if>
											
										</table>
									</td>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</table >
				<table class="tbl2" width="100%">
					<colgroup>
						<col width="25%" />
						<col width="35%" />
						<col width="40%" />
					</colgroup>
					<tr>
						<td class="td">
							<table>
								<colgroup>
									<col width="60%" />
									<col width="20%" />
									<col width="20%" />
								</colgroup>
								<tr><td colspan="3">서로이웃신청</td></tr>
								<tr>
								<c:if test="${!empty (neighborReqList) }">
								<c:forEach items="${ neighborReqList}" var="i"  begin ="0" >
								
								<td>
									<a href="selectBlog.do?writer_id=${i.member_id }">${i.member_id }</a>
								</td>
								<td>
									<a href="javascript:acceptNeig('${sessionScope.loginUser.getMember_id() }', '${i.member_id }');">
										<div class="neighborYN">수락</div>
									</a>
								</td>
								<td>
									<a href="javascript:rejectNeig('${sessionScope.loginUser.getMember_id() }', '${i.member_id }');">
										<div class="neighborYN">거절</div>
									</a>
								</td>
								</c:forEach>
								</c:if>
							</table>
						</td>
						<td class="td">
							<table>
							   <colgroup>
									<col width="73%" />
									<col width="12%" />
									<col width="15%" />
								</colgroup>
								<tr ><td colspan="3">서로이웃 목록</td></tr>
								<tr>
									<td>백마탄환자</td><td><img src="images/chat_icon.png" width="15px" ></td><td><a href=""><div class="neighborYN">취소</div></a></td> <!-- class="smallIcon" -->
								</tr>
								<tr>   
									<td>그루터기</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
								<tr>
									<td>이대장</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
								<tr>   
									<td>깐깐징어</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
								<tr>   
									<td>긍정의아이콘토리</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
								<tr>   
									<td>백마탄환자</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
								<tr>   
									<td>꼼지락이 주부</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
								<tr>   
									<td>백마탄환자</td><td><img src="images/chat_icon.png" width="15px"></td><td><a href=""><div class="neighborYN">취소</div></a></td>
								</tr>
							</table>
						</td>
						<td class="td">
							<table>
								<tr><td><b>내가 남긴글</b></td></tr>
								<tr>
									<td>게시글을 작성하고 이웃을 만들어보세요!! </td>
								</tr>
								<tbody id="listbody">	
								
									<c:if test="${!empty mylist }">
									<c:forEach items="${ mylist}" var="i"  begin ="0" >
										<tr>
											<td>
												<a data-fancybox data-src='pdetail.do?postId=${i.post_id }&writerId=${i.writer_id} '>
													${i.getComment_content()}
												</a> 
											</td>
										</tr>
									</c:forEach>
									</c:if>
									
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</section>
	</div>
</body>
</html>