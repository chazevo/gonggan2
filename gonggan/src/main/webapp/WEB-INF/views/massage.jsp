<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head> 
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/css.css'/>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("msg").focus();
	}
</script>
</head>
<body class="massage">
<div class="searchPost">
	<p>GONGAN_JJ</p><a href=""><img class="right" src="images/massage_x_icon.png" ></a>
	<hr>
	<div class="sender">안녕하니? 쪽지를 보낸당 뭐하고있닝? 쪽지좀 읽어주련?</div>
	<div class="receiver">안녕하니? 쪽지를 보낸당 뭐하고있닝? 쪽지좀 읽어주련?</div>
	<div class="sender">안녕하니? 쪽지를 보낸당 뭐하고있닝? 쪽지좀 읽어주련?</div>
	<hr>
	<div class="div4">
		<input id="msg" type="text" onkeyup="if(event.keyCode == 13) alert('챗');" >
	</div>
	&nbsp;<button  class="sendMsg" onclick="">보내기</button>
</div>
</body>
</html>