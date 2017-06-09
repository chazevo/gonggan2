<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel='stylesheet' href='css/css.css' />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="js/likepage.js"></script>
<script>
	var loginUser ='${sessionScope.loginUser.getMember_id()}';
	var post_id = '${param.postId }';
</script>
</head>
<body class="gradBg">
<div>
	<table class="likeTable" width="100%" >
		<tr>
			<td colspan="2" class="text-center">
				<h3><a href="javascript:history.back();" class="back">&lt;</a> like</h3>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<div class="div2"  style="margin:auto;">
					<input type="text" id="searchPost" onkeyup="searchGood();" placeholder="search" >
					<a href="javascript:searchGood();">
					<img src=images/search.png width="5%" >
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<hr>
			</th>
		</tr>
		<tbody id="listbody">
         <c:if test="${!empty goodList}">
            <c:forEach items="${goodList}" var="i" begin="0">
               <tr>
                  <td><a href="myhome.do?writer_id=${i.member_id}" target="_blank">${i.member_id}</a></td>
                  <c:if test="${i.member_id eq sessionScope.loginUser.getMember_id()}">
                     <td></td>
                  </c:if>
                  <c:if test="${empty neigList}">
                  <c:if test="${i.member_id ne sessionScope.loginUser.getMember_id()}">
                     <td>
                        <button id="neighborBtn" class="neighborY">이웃친구</button>
                     </td>
                  </c:if>
                  </c:if>
                  <c:if test="${!empty neigList}">
                     <td>
                        <button id="neighborBtn" class="neighborN" onclick="likeNeigh('${i.member_id}');">이웃친구</button>
                     </td>
                  </c:if>
               </tr>
            </c:forEach>
         </c:if>
      </tbody>
	</table>
</div>
</body>
</html>