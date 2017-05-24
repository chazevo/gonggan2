<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>NaverLoginTest</title>
</head>
<body>
    <br>
    <form action="kConnect.do" method="post">
			<label>아이디:<input type="text" name="member_id"></label><br>
			<label>암 호:<input type="password" name="member_pw"></label><br>
			<input type="text" value="${kakao_id}" name="member_kakao_id">
			<input type="submit" value="카카오아이디와 연동">
	</form>
</body>
</html>