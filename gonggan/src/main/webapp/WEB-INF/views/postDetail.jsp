<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar, com.kh.gonggan.member.model.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel='stylesheet' href='css/css.css'/> 
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script type="text/javascript" src="js/postDetail.js"></script> 
<script type="text/javascript">
	var postId = '<%= request.getParameter("postId") %>';
	var writerId = '<%= request.getParameter("writerId") %>';
	var loginUser = '${sessionScope.loginUser.getMember_id()}';
	
	window.onload = function() {
		if (loginUser != "")
			checkGood(loginUser, postId);
		//alert(postId);
		
		$(".hover").hover(function(){
			//$(this).css("backgroundColor", "gray");
			if ($(".hover").hasClass("grayTd"))
				$(this).removeClass("grayTd");
			else
				$(this).addClass("grayTd");
		});
	}
	
</script>
</head>
<body style="overflow:auto;width:600px;">
<table width="100%" align="center" class="postDetail">
	<tr>
		<td id="userId">
			<a id="loginUser" href="#" >
			<img src="images/default.png" height="40px" class="img-circle">&nbsp;<b>${param.writerId} 님 </b></a>
			
			<div class="navbar-right hour">
				2시간
			</div>
			
		</td>
	</tr> 
	<tr>
		<td id="photo"><img alt="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="100%">
		</td>
	</tr> 
	<tr>
		<td id="good">
			<b><a href="#">좋아요 19개</a></b>
			<div id=dotdotdotDiv>
				<a class="hover dotdotdot" href="">부적절한 컨텐츠 신고</a>
				<a class="hover dotdotdot" href="" >공유</a>
				<a class="hover dotdotdot" href="">쪽지 보내기</a>
			</div>
		</td>
	</tr>
	<tr>
		<td id="comm">
			<label class='checkbox-wrap'>
				<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${param.postId });'>
				<i class='like-icon'></i>
			</label>&nbsp;
			<input id='' type='text' placeholder='댓글 달기'>&nbsp;
			<a href='javascript:sendComment();'><img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;
			<a href='javascript:dotdotdot();'><img class='smallIcon2' src='images/thesee_icon.png'></a>
			
		</td>
	</tr>
</table>
</body>
</html>