<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.gonggan.member.model.vo.Member" %>    
<%@ page import="com.kh.gonggan.post.model.vo.Post" %>
<%@page import="java.util.List"%>
<%
	List<Post> plist = (List<Post>) request.getAttribute("plist");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<%
			for (int i = 0; i < plist.size(); i++) {
	%>
	<tr>
	<td>
			<%=plist.get(i).getCategory()%>
	</td>
	<td>
			<%=plist.get(i).getWriterId()%>
	</td>	
	</tr>
	<%
			}
	%>
	</table>
</body>
</html>