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
	var categoryval = ('${category}'== '' ? 0 : '${category}');
	var initPosition, prevPosition;
	
	window.onload = function() {

		$(".moreBtb").hide();
		
		$(".moreBtb").click(function() {
			if (start <= 100)
				requestList(start);
		});

		$(window).scroll(function() {
		
			initPosition = $(window).scrollTop()
		
			if (initPosition > prevPosition) {
				if  ($(window).scrollTop() >= $(window).height() - $(window).height() / 3) {
					alert();
					if(start <= 100) {
						$(".moreBtb").show();
						//requestList(start);
					}
				}
			}
		
			prevPosition = initPosition;
		});
		
		$("input[name=keyword]").focus();
		
		$("th a").click(function() {
				$("th a").next().addClass('hidden');
				$("th a").removeClass('selectedTab');
				$("th a").next().hide();
				$(this).next().removeClass('hidden');
				$(this).addClass('selectedTab');
				$(this).next().show();
		});
		
		$("th a").hover(function() {
			if ($(this).next().hasClass('hidden')) {
				$(this).next().removeClass('hidden');
				$(this).next().show();
			}
			else {
				$("th a").each(function(index, element) {
					if (!$(this).hasClass("selectedTab")) {
						$(this).next().addClass('hidden');
						$(this).next().hide();
					}
				});
			}
		});
		
	}
</script>
<title>Insert title here</title>
</head>
<body class="gradBg searchAll">
<div>
	<table width="100%" class="searchAllTable" >
		<colgroup>
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
			<col width="20%" />
		</colgroup>
		<tr>
			<th class="text-center">
				<div>
					<a class="selectedTab" href="javascript:categoryval=0; changeCategory();">책</a>
					<hr class="<c:if test="${category ne 0 && !empty param }">hidden</c:if>">
				</div>
			</th>
			<th  class="text-center">
				<div>
					<a href="javascript:categoryval=1; changeCategory();">영화</a>
					<hr class="<c:if test="${category ne 1 }">hidden</c:if>">
				</div>
			</th>
			<th class="text-center">
				<div>
					<a href="javascript:categoryval=2; changeCategory();">음악</a>
					<hr class="<c:if test="${category ne 2 }">hidden</c:if>">
				</div>
			</th>
			<th  class="text-center">
				<div>
					<a href="javascript:categoryval=3; changeCategory();">리뷰</a>
					<hr class="hidden">
				</div>
			</th>
			<th class="text-center">
				<div>
					<a href="javascript:categoryval=4; changeCategory();">뉴스</a>
					<hr class="<c:if test="${category ne 4 }">hidden</c:if>">
				</div>
			</th>
		</tr>
		<tr>
			<td colspan="5" class="text-center">
				<div class="div2"  style="margin:auto;">
					<form method="get" id="submit">
						<input type="text" id="" name="keyword" value="${keyword }" placeholder="search" 
						onkeydown="if(event.keyCode == 13) selectSubmit();">
						<a href="javascript:selectSubmit();">
						<img src=images/search.png width="5%" >
						</a>
					</form>
				</div>
			</td>
		</tr>
	</table>
	<table width="100%" >
		<tbody id="listbody">
			<c:if test="${!empty searchNewsList}">
			<!-- tbody가 없는데 왜 css가 적용되는가? -->
			<tr>
				<td style="text-align:center;">
					<c:forEach items="${searchNewsList}" var="i" begin="0">
					<table class="categorySearch news">
						<tbody class="except">
							<tr>
								<th>
									<span>
										<a href="javascript:selectNews('${i.title}', '${i.originallink}', '${i.description }', '${i.pubDate }');">
											${i.title}</a>
									</span>
								</th>
							</tr>
							<tr>
								<td style="height:110px;">
									<a href="javascript:selectNews('${i.title}', '${i.originallink}', '${i.description }', '${i.pubDate }');">
										${i.description}</a>
								</td>
							</tr>
							<tr>
								<td><a href="${i.originallink }"  target="_blank">상세정보</a></td>
							</tr>
						</tbody>
					</table>
					</c:forEach>
				</td>
			</tr>
			</c:if>
			<c:if test="${!empty searchMusicList}">
			<tr>
				<td style="text-align:center;">
					<c:forEach items="${searchMusicList}" var="i" begin="0">
					<table class="categorySearch music">
						<colgroup>
							<col width="30%" />
							<col  style="width:*;" />
						</colgroup>
						<tbody>
							<tr>
								<td style="height:100px;">
									<a href="javascript:selectMusic('${i.videoId}', '${i.title}', '${i.thumbnail_high }');">
										<img src="${i.thumbnail_default }">
									</a>
								</td>
								<th>
								<span>
									<a href="javascript:selectMusic('${i.videoId}', '${i.title}', '${i.thumbnail_high }');">
										${i.title}</a>
								</span>
								</th>
							</tr>
						</tbody>
					</table>
					</c:forEach>
				</td>
			</tr>
			</c:if>
			<c:if test="${!empty searchMovieList}">
			<tr>
				<td style="text-align:center;">
					<c:forEach items="${searchMovieList}" var="i" begin="0">
					<table class="categorySearch movie">
						<colgroup>
							<col width="30%" />
							<col  style="width:*;" />
						</colgroup>
						<tbody>
							<tr>
								<td rowspan="5">
								<!-- rowspan을 5로 해야 균형이 안깨짐 -->
									<a href="javascript:selectMovie('${i.image}', '${i.title}', '${i.director }', '${i.actor }', '${i.pubDate }');">
										<img height="100%" width="100%" src="${i.image}">
									</a>
								</td>
								<th>
									<span>
										<a href="javascript:selectMovie('${i.image}', '${i.title}', '${i.director }', '${i.actor }', '${i.pubDate }');">
											<b style="font-size:140%">${i.title}</b>
										</a>
									</span>
								</th>
							</tr>
							<tr>
								<td>${i.director }&nbsp;</td>
							</tr>
							<tr>
								<td>${i.actor }&nbsp;</td>
							</tr>
							<tr>
								<td><a href="${i.link }"  target="_blank">상세정보</a></td>
							</tr>
							<tr><td>&nbsp;</td></tr>
						</tbody>
					</table>
					</c:forEach>
				</td>
			</tr>
			</c:if>
			<c:if test="${!empty searchBookList}">
			<tr>
				<td style="text-align:center;">
					<c:forEach items="${searchBookList}" var="i" begin="0">
					<table class="categorySearch book" style="">
						<colgroup>
							<col width="30%" />
							<col  style="width:*;" />
						</colgroup>
						<tbody>
							<tr>
								<td rowspan="5">
									<a href="javascript:selectBook('${i.image}', '${i.title}', '${i.author }', '${i.publisher }', '${i.pubdate }');">
										<img height="100%" src="${i.image}" >&nbsp;
									</a>
								</td>
								<th>
									<span><a href="javascript:selectBook('${i.image}', '${i.title}', '${i.author }', '${i.publisher }', '${i.pubdate }');">
											<b style="font-size:140%">${i.title}</b>
									</a></span>
								</th>
							</tr>
							<tr>
								<td><b>저자</b>&nbsp;${i.author }</td>
							</tr>
							<tr>
								<td><b>출판</b>&nbsp;${i.publisher }</td>
							</tr>
							<tr>
								<td><b>발행</b>&nbsp;${i.pubdate }</td>
							</tr>
							<tr>
								<td style="padding-bottom:10px;">
									<a href="${i.link }"  target="_blank">상세정보</a>
								</td>
							</tr>
						</tbody>
					</table>
					</c:forEach>
				</td>
			</tr>
			</c:if>
			</tbody>
		</table>
	<a href=""><div class="moreBtb">+ 더 보 기</div></a>
</div>
</body>
</html>