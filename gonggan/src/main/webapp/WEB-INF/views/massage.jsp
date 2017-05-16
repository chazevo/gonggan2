<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head> 
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/css.css'/>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script type="text/javascript" src="js/massage.js"></script> 
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("msg").focus();
		
		$('#msg').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13') send();
			event.stopPropagation();	
		});
		
		send_message();
	}
	
</script>
</head>
<body class="massage">
<div class="searchPost">
	<p>GONGAN_JJ</p><a href=""><img class="right" src="images/massage_x_icon.png" ></a>
	<hr>
	<div id="chatArea">
		
		<c:if test="${!empty msgList} ">
			<c:forEach items="${ msgList}" var="i" begin ="0" >
				<c:if test="${i.msg_sender eq sessionScope.loginUser}">
					<div class="sender">${i.msg_text }</div>
				</c:if>
				<c:if test="${i.msg_receiver eq sessionScope.loginUser}">
					<div class="receiver">${i.msg_text }</div>
				</c:if>
			</c:forEach>
		</c:if>
		
		<!-- <div class="sender">안녕하니? 쪽지를 보낸당 뭐하고있닝? 쪽지좀 읽어주련?</div>
		<div class="receiver">안녕하니? 쪽지를 보낸당 뭐하고있닝? 쪽지좀 읽어주련?</div>
		<div class="sender">안녕하니? 쪽지를 보낸당 뭐하고있닝? 쪽지좀 읽어주련?</div> -->
	</div>
	<hr>
	<div class="div4">
		<input id="msg" type="text" onkeyup="" >
	</div>
	&nbsp;<button  class="sendMsg" onclick="send();">보내기</button>
</div>
</body>
</html>