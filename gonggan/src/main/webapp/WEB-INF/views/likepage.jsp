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
</head>
<body class="likepage">
<div>
	<table width="100%" >
		<tr>
			<td colspan="2" class="text-center">
				<h3><a href="javascript:history.back();" class="back">&lt;</a> like</h3>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-center">
				<div class="div2">
				<input type="text" id="searchPost" placeholder="search" size="50" >
				<a href="#">
				<img src=images/search.png width="5%">
				</a>
				</div>
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<hr>
			</th>
		</tr>
		<tbody>
			<c:if test="${!empty goodList}">
				<c:forEach items="${goodList}" var="i" begin="0">
					<tr>
						<td>${i.member_id}</td>
						<td>
							<button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
			<tr>
				<td>zzzz</td>
				<td><button id="neighborBtn" class="neighborN" onclick="likeNeigh();">이웃친구</button></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>