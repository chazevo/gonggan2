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
<body class="gradBg" style="width:600px;">
<div>
	<table class="likeTable" width="100%" >
		<tr>
			<td class="text-center">
				<h3><a href="javascript:history.back();" class="back">&lt;</a> like</h3>
			</td>	
		</tr>
		<tr>
			<td class="text-center">
				<div class="div2"  style="margin:auto;">
					<input type="text" id="searchPost" onkeyup="searchGood();" placeholder="search" >
					<a href="javascript:searchGood();">
						<img src=images/search.png width="5%" >
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<th>
				<hr>
			</th>
		</tr>
	</table>
	<table width="60%" align="center">
      <colgroup>
         <col width="40%">
         <col width="60%">
      </colgroup>
      <tbody id="listbody">
      <c:if test="${!empty goodList}">
      <c:set var="index" value='0'></c:set>
         <c:forEach items="${goodList}" var="i" begin="0">
            <tr>
               <td>
	               	<c:if test='${empty memberList[index].getProfile_photo() }'>
	               		<a href="myhome.do?writer_id=${i.member_id}" target="_blank">
							<img src="images/default.png" height="40px" class="img-circle">
							&nbsp;${i.member_id}
						</a>
					</c:if>
					<c:if test='${!empty memberList[index].getProfile_photo() }'>
						<a href="myhome.do?writer_id=${i.member_id}" target="_blank">
							<img src="images/profileImages/${ memberList[index].getProfile_photo()}" height="40px" class="img-circle">
							&nbsp;${i.member_id}
						</a>
					</c:if>
				</td>
               <c:if test="${i.member_id ne sessionScope.loginUser.getMember_id()}">
                  <c:if test='${neighYn[index]=="Y"}'>
                     <td>
                        <button id="neighborBtn" class="neighborY" onclick="rejectNeig('${i.member_id}')">이웃친구</button>
                     </td>
                  </c:if>
                  <c:if test='${neighYn[index]=="N"}'>
                     <td>
                        <button id="neighborBtn" class="neighborN" onclick="likeNeigh('${i.member_id}');">이웃친구</button>
                     </td>
                  </c:if>
               </c:if>
            </tr>
         <c:set var="index" value='${index+1 }'></c:set>
         </c:forEach>
      </c:if>
   </tbody>
   </table>
</div>
</body>
</html>