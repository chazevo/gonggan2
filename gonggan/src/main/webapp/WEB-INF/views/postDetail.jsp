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
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script type="text/javascript" src="js/postDetail.js"></script> 
<script src="js/jquery.fancybox.js"></script>
<script src="js/jquery-timeago.js" type="text/javascript"></script>

<script type="text/javascript">
	var loginUser = '${sessionScope.loginUser.getMember_id()}';
	var postId = '${postId}';
	var hideComment = '${HideComment}';
	
	window.onload = function() {
		//visit();
		
		if (hideComment != 'Y')
			document.getElementById("comment_content").focus();
		
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
		
		$(".fb").fancybox({
			//'modal' : true,
			//'openEffect' : 'none',
			//'closeEffect' : 'none',
			//'scrolling' : false,
			overlay : {
      speedOut : 0
     },
			beforeShow : function() {
        $('.fancybox-overlay').css({'background-color' :'#ec2d2d'});
    },
			'overlayShow':false,
			'overlayOpacity':0,
			//'autoSize':false,
			'closeBtn' : false,
			'fullScreen' : false
		});
		
	}
	
	$("#hidden_link").fancybox().trigger('click');
	
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
	
</script>
</head>
<body style="width:700px;">
<div style="overflow:auto;width:100%;height:500px;">
<table width="100%" align="center" class="postDetail">
	<colgroup>
		<col width="5%" />
		<col width="80%" />
		<col width="15%" />
	</colgroup>
	<tr>
		<td id="userId" colspan="3">
			<a id="loginUser" href="myhome.do?writer_id=${writerId}" target="_blank">
			<img src="images/default.png" height="40px" class="img-circle">&nbsp;<b>${writerId}</b></a>
			
			<span class="navbar-right hour">
				<abbr class="timeago" title="${postDate}">${postDate}</abbr>
			</span>
			
		</td>
	</tr> 
	<tr>
		<td id="photo" colspan="3" class="contentArea">
			<c:if test='${postDetail.getPhoto_path() ne null}'>
			<img alt="" src="uploadImages/${postDetail.getPhoto_path()}" width="100%">
			</c:if>
			<div>
				<c:if test='${postDetail.getCategory() eq "diary"}'>
					${postDetail.getDiary_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "music"}'>
					${postDetail.getMusic_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "book"}'>
					${postDetail.getBook_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "movie"}'>
					${postDetail.getMovie_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "news"}'>
					${postDetail.getNews_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "review"}'>
					${postDetail.getReview_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "place"}'>
					${postDetail.getPlace_content()}
				</c:if>
				<c:if test='${postDetail.getCategory() eq "free"}'>
					${postDetail.getFree_content()}
				</c:if>
			</div>
		</td>
	</tr>
	<c:if test='${HideComment eq "Y" }'>
	<tr>
		<td colspan="3">
			<div class='dotdotdotDiv'
				style="bottom:0;right:0;margin-bottom:15px;margin-right:15px;">
				<a class="hover dotdotdot" href="">부적절한 컨텐츠 신고</a>
				<a class="hover dotdotdot" href="" >공유</a>
				<c:if test='${!empty sessionScope.loginUser }'>
				<a data-fancybox class="fb hover dotdotdot"
					href="messageList.do?memberId1=${sessionScope.loginUser.getMember_id()}&memberId2=${param.writerId }">
					쪽지 보내기
				</a>
				</c:if>
				<c:if test='${empty sessionScope.loginUser }'>
				<a class="hover dotdotdot" href="javascript:alert('로그인이 필요합니다.');">
					쪽지 보내기
				</a>
				</c:if>
				<c:if test="${sessionScope.loginUser.getMember_id() eq writerId}">
				<a class="hover dotdotdot"
					onclick="javascript:postdelete(${postId}, '${sessionScope.loginUser.getMember_id()}');">
					게시글 삭제하기
				</a>
				</c:if>
			</div>
		</td>
	</tr>
	</c:if>
	<tr>
		<td id="good" colspan="2">
			<label class='checkbox-wrap'>
				<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
				<i class='like-icon'></i>
			</label>&nbsp;
			<c:if test="${ goodCnt ne '0' }">
				<b><a href="goodList.do?postId=${postId }">좋아요 ${goodCnt }개</a></b>
			</c:if>
			<c:if test="${ goodCnt eq '0' }"> 
			<b>좋아요 ${goodCnt }개</b>
			</c:if>
		</td>
		<td>
			<a href='javascript:void(0);' onclick='dotdotdot($(this));'>
				<img width="15%" src='images/thesee_icon.png'>
			</a>
		</td>
	</tr>
	<tbody id="listbody">
		<c:if test='${HideComment eq "N" }'>
		<c:if test="${!empty commentList}">
		<c:forEach items="${commentList}" var="i" begin="0">
		<tr id="co${i.comment_num }">
			<td colspan="3" class='commentArea'>
				<b>
					<a href="myhome.do?writer_id=${i.writer_id}"  target="_blank">
						${i.writer_id }
					</a>
				</b> &nbsp;&nbsp;
				${i.comment_content}&nbsp;
				<span class="commentDate">
					${i.comment_date} &nbsp;&nbsp; 
				 	<c:if test="${sessionScope.loginUser.getMember_id() eq i.writer_id || (sessionScope.loginUser.getMember_id() eq writerId) }">
					<a href="javascript:deleteComment(${i.comment_num });">
						<img src="images/delete_sign_filled1600.png" width="2%">
					</a>
					</c:if>
				</span>
			</td>
		</tr>
		</c:forEach>
		</c:if>
		</c:if>
	</tbody>
	<c:if test='${HideComment eq "N" }'>
	<tr>
		<td colspan="3" class='commentArea'>
			<div class='dotdotdotDiv'
				style="bottom:0;right:0;margin-bottom:15px;margin-right:15px;">
				<a class="hover dotdotdot" href="">부적절한 컨텐츠 신고</a>
				<a class="hover dotdotdot" href="" >공유</a>
				<c:if test='${!empty sessionScope.loginUser }'>
				<a data-fancybox class="fb hover dotdotdot"
					href="messageList.do?memberId1=${sessionScope.loginUser.getMember_id()}&memberId2=${param.writerId }">
					쪽지 보내기
				</a>
				</c:if>
				<c:if test='${empty sessionScope.loginUser }'>
				<a class="hover dotdotdot" href="javascript:alert('로그인이 필요합니다.');">
					쪽지 보내기
				</a>
				</c:if>
				<c:if test="${sessionScope.loginUser.getMember_id() eq writerId}">
				<a class="hover dotdotdot"
					onclick="javascript:postdelete(${postId}, '${sessionScope.loginUser.getMember_id()}');">
					게시글 삭제하기
				</a>
				</c:if>
			</div>
		</td>
	</tr>
	</c:if>
	<c:if test='${HideComment eq "N" }'>
	<tr id="comm" class='commentArea'>
		<td>
			<label class='checkbox-wrap'>
				<input type='checkbox' id='like' onclick='like(this, "${sessionScope.loginUser.getMember_id()}", ${postId });'>
				<i class='like-icon'></i>
			</label>&nbsp;
		</td>
		<td>
			<input id='comment_content' type='text' placeholder='댓글 달기' style="width:100%"
				onkeydown="if(event.keyCode==13) sendComment();">&nbsp;
		</td>
		<td>
			<a href='javascript:sendComment();'>
				<img  src='images/dettext_icon.png' width='45px' >
			</a>
			&nbsp;<a href='javascript:void(0);' onclick='dotdotdot($(this));'>
				<img width="15%" src='images/thesee_icon.png'>
			</a>
		</td>
	</tr>
	</c:if>
	
</table>
</div>
</body>
</html>