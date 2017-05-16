<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='css/css.css'/> 
<script type="text/javascript">
	var postId = '<%= request.getParameter("postId") %>';
	window.onload = function() {
		//alert(postId);
	}
	
	function like(){
		alert("좋아요");
	}
</script>
</head>
<body>
<table align="center" class="postDetail">
	<tr><td id="userId">${param.postId} </td></tr> 
	<tr><td id="good"><label class='checkbox-wrap'><input type='checkbox' id='' onclick='like();'><i class='like-icon'></i></label>&nbsp;</td></tr>
	<tr><td id="comm">댓글</td></tr>
 
</table>
</body>
</html>