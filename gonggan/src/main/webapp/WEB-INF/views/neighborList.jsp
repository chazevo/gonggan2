<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<c:forEach items="${neighborlist}" var="i" begin="0" varStatus="status">
	<tr><td></td><td>아이디</td><td>이웃 맺은 날짜</td></tr>
	<tr><td>${status.count}</td><td><a href="selectBlog.do?writer_id=${i.member_id}">${i.member_id}</a></td><td></td></tr>
	</c:forEach>
	</table>
</body>
</html>