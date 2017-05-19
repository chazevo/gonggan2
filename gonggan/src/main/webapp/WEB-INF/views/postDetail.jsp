<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Calendar, com.kh.gonggan.member.model.vo.Member, com.kh.gonggan.post.model.vo.Post
    , com.kh.gonggan.comment.model.vo.Comment" %>
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
	var loginUser = '${sessionScope.loginUser.getMember_id()}';
	var postId = '${postId}';
	
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
<body>
<div style="overflow:auto;width:500px;height:500px;">
<table width="100%" align="center" class="postDetail">
	<colgroup>
		<col width="70%" />
		<col width="30%" />
	</colgroup>
	<tr>
		<td id="userId" colspan="2">
			<a id="loginUser" href="#" >
			<img src="images/default.png" height="40px" class="img-circle">&nbsp;<b>${writerId} 님 </b></a>
			
			<div class="navbar-right hour">
				2시간
			</div>
			
		</td>
	</tr> 
	<tr>
		<td id="photo" colspan="2">
			<img alt="" src="uploadImages/${postDetail.getPhotoPath()}" width="100%">
			<div>
			</div>
		</td>
	</tr> 
	<tr>
		<td id="good" colspan="2">
			<c:if test="${ goodCnt ne '0' }">
				<b><a href="goodList.do?postId=${postId }">좋아요 ${goodCnt }개</a></b>
			</c:if>
			<c:if test="${ goodCnt eq '0' }"> 
			<b>좋아요 ${goodCnt }개</b>
			</c:if>
			<div id=dotdotdotDiv>
				<a class="hover dotdotdot" href="">부적절한 컨텐츠 신고</a>
				<a class="hover dotdotdot" href="" >공유</a>
				<a class="hover dotdotdot" href="">쪽지 보내기</a>
			</div>
		</td>
	</tr>
	<tbody id="listbody">
	<c:if test="${!empty commentList}">
	<c:forEach items="${commentList}" var="i" begin="0">
	<tr id="co${i.comment_num }">
		<td>
			<b><a href="selectBlog.do?writer_id=${i.writer_id}"  target="_blank">${i.writer_id }</a></b> &nbsp;&nbsp;
			${i.comment_content}
		</td>
		<td class="commentDate">
			${i.comment_date} &nbsp;&nbsp; 
			 <c:if test="${empty loginUser eq i.writer_id || loginUser eq writerId }">
			<a href="javascript:deleteComment(${i.comment_num });"><img src="images/delete_sign_filled1600.png" width="10%"></a>
		</c:if>
		</td>
	</tr>
	</c:forEach>
	</c:if>
	</tbody>
	<tr>
		<td id="comm" colspan="2">
			<label class='checkbox-wrap'>
				<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
				<i class='like-icon'></i>
			</label>&nbsp;
			<input id='comment_content' type='text' placeholder='댓글 달기' onkeydown="if(event.keyCode==13) sendComment();">&nbsp;
			<a href='javascript:sendComment();'><img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;
			<a href='javascript:dotdotdot();'><img class='smallIcon2' src='images/thesee_icon.png'></a>
		</td>
	</tr>
</table>
</div>
</body>
</html>