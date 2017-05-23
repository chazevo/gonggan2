<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.gonggan.movie.model.vo.Movie" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='css/css.css' />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script type="text/javascript" src="js/searchAll.js"></script>
<script type="text/javascript">
	window.onload = function() {
		$("th a").click(function() {
			if ($(this).next().hasClass('hidden')) {
				$("th a").addClass('hidden');
				$("th a").hide();
				$(this).next().removeClass('hidden');
				$(this).next().show();
			}
		});
	}
</script>
<title>Insert title here</title>
</head>
<body class="gradBg">
<div>
	<table width="100%"  border="1" class="searchAllTable" >
		<tr>
			<th class="text-center">
				<a href="">책</a>
				<hr class="hidden">
			</th>
			<th  class="text-center">
				<a href="">영화</a>
				<hr class="hidden">
			</th>
			<th class="text-center">
				<a href="">음악</a>
				<hr class="hidden">
			</th>
			<th  class="text-center">
				<a href="">리뷰</a>
				<hr class="hidden">
			</th>
			<th class="text-center">
				<a href="">뉴스</a>
				<hr class="hidden">
			</th>
		</tr>
		<tr>
			<td colspan="5" class="text-center">
				<div class="div2"  style="margin:auto;">
					<form action="moviesearch.do"  method="get" id="submit">
						<input type="text" id="" name="keyword" value="${keyword }" placeholder="search" >
						<a href="javascript:document.getElementById('submit').submit();">
						<img src=images/search.png width="5%" >
						</a>
					</form>
				</div>
			</td>
		</tr>
		<tr>
			<th colspan="5">
				<hr>
			</th>
		</tr>
		<tbody id="listbody">
			<c:if test="${!empty searchMovieList}">
				<c:forEach items="${searchMovieList}" var="i" begin="0">
					<tr>
						<td rowspan="3">
							<a href="javascript:selectMovie('${i.image}', '${i.title}', '${i.director }', '${i.actor }', '${i.pubDate }');">
							<img src="${i.image}"></a>
						</td>
						<td colspan="4">
							<a href="javascript:selectMovie('${i.image}', '${i.title}', '${i.director }', '${i.actor }', '${i.pubDate }');">
								${i.title}</a>
						</td>
					</tr>
					<tr>
						<td colspan="4">${i.director }</td>
					</tr>
					<tr>
						<td colspan="4"><a href="${i.link }"  target="_blank">상세정보</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
</body>
</html>