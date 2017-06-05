<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.gonggan.member.model.vo.Member"%>
<%
Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

	var loginUser = '${sessionScope.loginUser.getMember_id()}';
	var maxRownum = '${plistSize}';
	var initPosition;
	var prevPosition;
	var category = 'all';
	
	window.onload = function() {
		$('#searchNeiDiv').hide();
		requestNeighborPostList(rownum, loginUser);

		$(window).scroll(function() {
			initPosition = $(window).scrollTop()

			if (initPosition > prevPosition) {
               if  ($(window).scrollTop() >= $(window).height() - $(window).height() / 5){
                  if(maxRownum >= rownum) {
                     $("#div_Loading").show();
                     alert(rownum);
                     setTimeout(function() {
                        requestNeighborPostList(rownum, loginUser);
                        $(window).scrollTop($(window).height() / 2);
                     }, 1000);
                  }
               }
            }
      });
      
   }
</script>
</head>
<body>
   <h></h>
   <div class="text-center blogHomeContentDiv" id="blogHomeContentDiv">
   
</body>
</html>