<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="com.kh.gonggan.member.model.vo.Member"%>


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

<body class="ddd"><nav class="navbarCustom navbar-default">
   <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">
         <img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
         <img class="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
   </div>
   <div class="navbar-right">
      <%-- <c:if test="${empty sessionScope.loginUser }">
               <a class="loginbox"  href="index.jsp" >
               l o g i n
            </a>
            </c:if>
            <c:if test="${!empty sessionScope.loginUser }">
               <a id="loginUser" class="navbar-brand">
                  <img src="images/default.png" height="40px" class="img-circle">&nbsp;
                  ${sessionScope.loginUser.getMember_id() } 님
               </a>
            </c:if> --%>
   </div>
</nav>
<h1 align="center">이웃 포스트</h1>
<hr class="nPosthr">
   <div class="text-center blogHomeContentDiv" id="blogHomeContentDiv">
   
</body>
</html>