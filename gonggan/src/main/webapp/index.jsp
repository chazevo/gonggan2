<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.gonggan.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
   Member loginUser = (Member) session.getAttribute("loginUser");
%>   
<!DOCTYPE html >
<html>
<head>
<meta  charset="UTF-8">
<title>welcome! sample</title>
</head>
<body>
<h1>Sample Spring Project</h1>

<!-- <a href="start.do">start</a> -->
<c:if test="${empty sessionScope.loginUser }">
   <jsp:forward page="start.do"></jsp:forward>
</c:if>
<c:if test="${!empty sessionScope.loginUser }">
   <jsp:forward page="login.do"></jsp:forward>
</c:if>
<%-- <jsp:forward page="start.do">
  <jsp:param name="member_id" value="<%= loginUser.getMember_id()%>" ></jsp:param>
  <jsp:param name="member_pw" value="<%= loginUser.getMember_pw()%>" ></jsp:param>
</jsp:forward> --%>
</body>
</html>