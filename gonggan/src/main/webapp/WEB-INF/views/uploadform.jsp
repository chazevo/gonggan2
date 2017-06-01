<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.gonggan.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<!--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<!-- 이거 있으면 부트스트랩 안먹음 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel='stylesheet' href='http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css'/> 
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='css/css.css'/> 

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/uploadForm.js"></script>
<script src="js/jquery.fancybox.js"></script>
<title>uploadform.jsp</title>
<script>

	var loginUser = "${sessionScope.loginUser.getMember_id()}";

	$(document).ready(function() {
		//document.getElementById("textarea").focus();
		$("[data-toggle='tooltip']").tooltip();
		
		colorchart();
		colorchart2();
		colorchart3();

		$("#colorchart td").click(function() {
			contenttextcolor($(this).attr('bgColor'));
		});

		$("#colorchart2 td").click(function() {
			bgcolor($(this).attr('bgColor'));
		});
		
		$("#colorchart3 td").click(function() {
			bgcolor3($(this).attr('bgColor'));
		});
		
		$("#tagadddbtn").click(function() {
			$("#tag").toggle(function() {
				document.getElementById("tagaddtext").focus();
			});
		});

		$("#newSearchLink").click(function() {
			$("#news").toggle(function() {
				document.getElementById("newsSearchText").focus();
			});
		});
		
		$("#bookSearchLink").click(function() {
			$("#book").toggle(function() {
				document.getElementById("bookSearchText").focus();
			});
		});
		
		$("#movieSearchLink").click(function() {
			$("#news").toggle(function() {
				document.getElementById("movieSearchText").focus();
			});
		});
		
		$("#imgUploadIcon").click(function() {
			if ($("#imgUploadDiv").hasClass("hidden")) {
				$("#imgUploadDiv").removeClass("hidden");
				$("#imgUploadDiv").show();
			}
			else {
				$("#imgUploadDiv").addClass("hidden");
				$("#imgUploadDiv").hide();
			}
		});
		
		$("#imo_icon").click(function() {
			if ($("#imo_icon_area").hasClass("hidden")) {
				$("#imo_icon_area").removeClass("hidden");
				$("#imo_icon_area").show();
				}
			else {
				$("#imo_icon_area").addClass("hidden");
				$("#imo_icon_area").hide();
			}
		});
		
		$('#colorChoice').click(function(){
			if($('#colorchart').hasClass("hidden")){
				$('#colorchart').removeClass("hidden");
				$('#colorchart').show();
			}else{
				$("#colorchart").addClass("hidden");
				$("#colorchart").hide();
			}
		});
		
		$('#colorChoice2').click(function(){
			if($('#colorchart2').hasClass("hidden")){
				$('#colorchart2').removeClass("hidden");
				$('#colorchart2').show();
			}else{
				$("#colorchart2").addClass("hidden");
				$("#colorchart2").hide();
			}
		});
		$('#colorChoice3').click(function(){
			if($('#colorchart3').hasClass("hidden")){
				$('#colorchart3').removeClass("hidden");
				$('#colorchart3').show();
			}else{
				$("#colorchart3").addClass("hidden");
				$("#colorchart3").hide();
			}
			
		});
		
		$("#loginUser").click(function() {
			if ($("#loginUserDetail").hasClass("hidden")) {
				$("#loginUserDetail").removeClass("hidden");
				$("#loginUserDetail").show();
				}
			else {
				$("#loginUserDetail").addClass("hidden");
				$("#loginUserDetail").hide();
			}
		});
		
		$(".hover").hover(function(){
			//$(this).css("backgroundColor", "gray");
			if ($(".hover").hasClass("grayTd"))
				$(this).removeClass("grayTd");
			else
				$(this).addClass("grayTd");
		});
		function getExtend(path) {
	         var str = path.substring(path.lastIndexOf(".") + 1);
	         return str;
	      }
		window
        .addEventListener(
              "load",
              function() {
                 var title_photo = document.getElementById("file");
                 title_photo
                       .addEventListener(
                             "change",
                             function() {
                                var ext = getExtend(this.value);
                                var result = (ext.toLowerCase() == "jpg"
                                      || ext.toLowerCase() == "jpeg"
                                      || ext.toLowerCase() == "gif" || ext
                                      .toLowerCase() == "png")
                                if (!result) {
                                   alert("알림 : 지원하지 않는 미디어 형식입니다.\n\njpg, gif, png 확장자를 가진 이미지 파일만 올려주세요.");
                                   title_photo.value = null;
                                   //var output = document.getElementById('imgout');
                                   //output.removeAttribute('src');
                                   return;
                                } else {
                                   //readImg('file', 'imgout');
                                   //$("#facecheck").val("1");
                                	uploadImg();
                                }
                             });
              });

	      function readImg(inputId, outputId) {
	         var file = document.getElementById(inputId).files[0];
	         var reader = new FileReader();
	         reader.readAsDataURL(file);
	      reader.onload = function() {
	            var output = document.getElementById(outputId);
	            //output.src = reader.result;
	            document.getElementById('editor').contentWindow.document.body.innerHTML += 
	            	"<img src='" + reader.result + "'>";
	         }
	      }
	});
</script>
</head>
<body onload='run();' id="aa">
	<c:if test="${empty param || param.writer_id ne sessionScope.loginUser.getMember_id() }">
		<jsp:forward page="error.jsp"></jsp:forward>
	</c:if>
	<div class="divCenter">
		<!--<div class="container-fluid">--><div>
		<!-- container-fluid : 화면 너비가 resize 되더라도 화면에 가득 참  -->
			<nav class="navbarCustom navbar-default">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.jsp">
						<img class="" src="images/KakaoTalk_Photo_2017-04-22-23-02-45.png" width="70px">
						<img class="" src="images/KakaoTalk_Photo_2017-04-22-18-18-54.png" width="70px"></a>
				</div>
				<div class="navbar-right">
					<a id="loginUser" class="navbar-brand" href="#" >
						<img src="images/default.png" height="40px"
									class="img-circle">&nbsp;${sessionScope.loginUser.getMember_id() } 님 </a>
				</div>
			</nav>
			<div id="loginUserDetail" class="hidden">
				<div id="box_icon">
					<table id="idclick_table">
						<tr id="center_align">
							<td>
								<a href="mypage.do">마이페이지</a>&nbsp;&nbsp; |  &nbsp;&nbsp;
								<a href="myhome.do">내블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
								<a href="#">이웃 블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
								<a href="#">로그아웃</a> 
								<div id="dansun_line"></div>
							</td>
						</tr> 
						<tr>
							<td>
								알림&nbsp; <img src="images/idclick_new_icon.png" id="idclick_new_icon">
							</td>
						</tr>
						<!-- <tr>
									<td class="hover">
		 								<font><a href="#"> 이대장 님이</a></font> <a href="#">동갑내기 부부의 세계로 가는 자전거 여행| 게시글에 댓글을 남기셨습니다.</a>
									</td>
								</tr>
						<tr>
							<td class="hover">
								<font><a href="#"> 긍정의아이콘|토리|</a></font> <a href="#">님이 토리와 함께 추억쌓기 놀이 | 게시글에 좋아요를 누르셨습니다.</a>
						      </td>
						</tr> -->
		         		<tr>
							<td class="hover">
								${sessionScope.loginUser.getMember_id() }님의 알림이 없습니다.
							</td>
						</tr>
					</table>
				</div>
			</div>
   
			<div class="header-content">
				<div class="header-content-inner">
					<h2><a href="selectBlog.do?writer_id=${param.writer_id} ">당신만의 공간에서 당신의 글을 만들어보세요.</a></h2>
				</div>
				<div class="header-content-inner2">
					<a href="controll.do">
					<img class="smallIcon"
					src="images/KakaoTalk_Photo_2017-04-24-10-28-40_21.png"></a>
				</div>
			</div>
			<div class="navbar-header idView">
			<!-- navbar-header : 메뉴 버튼 클릭 시 토글 width 100% -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#menu">
					<span class="sr-only">Toggle navigation</span> Menu <i class="menu"></i>
				<!-- sr-only : 숨김 -->
				</button>
				<a href="#" >
					<img src="images/default.png" height="40px"
								class="img-circle">&nbsp;${param.writer_id } 님 </a>
			</div>
			<div class="collapse navbar-collapse" id="menu">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="">일기</a>
					</li>
					<li>
						<a href="">장소</a>
					</li>
					<li>
						<a href="#">리뷰</a>
					</li>
					<li>
						<a href="#">음악</a>
					</li>
					<li>
						<a href="">영화</a>
					</li>
					<li>
						<a href="">뉴스</a>
					</li>
					<li>
						<a href="">책</a>
					</li>
				</ul>
			</div>
		</div>
		
		<section>	
			<div class="uploadFormDiv" > <!-- style="border:1px;"  -->
				<table border="1" width="100%"  id="uploadtable" > 
					<colgroup>
						<col width="20%" />
						<col width="20%" />
						<col width="15%" />
						<col width="15%" />
						<col width="30%" />
					</colgroup>
					<tr>
						<td>분류 </td>
						<td colspan="4">
							<select id="category" onchange="changeForm();">
								<option value="default" selected>자유</option>
								<option value="diary" >일기</option>
								<option value="news">뉴스</option>
								<option value="place">장소</option>
								<option value="review">리뷰</option>
								<option value="book">책</option>
								<option value="movie">영화</option>
								<option value="music">뮤직</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<select>
								<option value="">나눔고딕 </option>
							</select>
						</td> 	
						<td colspan="2" style="padding:5px;">
							<input type="image"  src="images/bold_icon.png" width="12px"
											onclick="je_doc.execCommand('bold', 'false', 'null')">&nbsp; &nbsp;
								
							<input type="image"  src="images/italic_icon.png" width="10px"
											onclick="je_doc.execCommand('italic', 'false', 'null')">&nbsp; &nbsp;
								
							<input type="image"  src="images/underline_icon.png" width="15px"
											onclick="je_doc.execCommand('underline', 'false', 'null')">&nbsp; &nbsp;
								
							<input type="image"  src="images/cencleline_icon.png" width="15px"
											onclick="je_doc.execCommand('strikethrough', 'false', 'null')">&nbsp; &nbsp;
							<img alt="" src="images/text_color.png" width="10%"  id="colorChoice">
							<div id="colorchart" class="hidden"></div>
							<img alt="" src="images/highlighter.jpg" width="10%"  id="colorChoice3">
							<div id="colorchart3" class="hidden"></div>

							
					<!-- 		<select id=contenttextcolor2 onchange='contenttextcolor2()'>
								<option class='imageOp'  selected  >글자색상 선택</option>
								<option class='imageOp'  value='black'  style="background-color:black; color:white">검정색</option>
								<option class='imageOp'  value='red'  style="background-color:red; color:white">빨강색 </option>
								<option class='imageOp'  value='white'  style="background-color:white; color:black">흰색 </option>
								<option class='imageOp'  value='yellow'  style="background-color:yellow; color:black">노란색 </option>
								<option class='imageOp'  value='blue'  style="background-color:blue; color:white">파란색 </option>
								<option class='imageOp'  value='pink' style="background-color:pink; color:white">분홍색 </option>
								<option class='imageOp'  value='green' style="background-color:green; color:white"> 초록색 </option>
								<option class='imageOp'  value='orange' style="background-color:orange; color:white">주황색</option>
							</select> -->
						</td>
						<td>
							<input type="image"  src="images/align_left_icon.png" id="content_allign_left"  width="18px" 
										onclick="je_doc.execCommand('justifyleft', 'false', 'null')"> &nbsp; &nbsp;
							<input type="image"  src="images/align_center_icon.png" id="content_allign_center" width="18px"
										onclick="je_doc.execCommand('justifycenter', 'false', 'null')"> &nbsp; &nbsp;
							<input type="image" src="images/align_right_icon.png" id="content_allign_right" width="18px"
										onclick="je_doc.execCommand('justifyright', 'false', 'null')"> &nbsp;
						</td>
						<td>
								<input type="image"  src="images/link_icon.png" width="42px"
											onclick='var s=prompt();if(s!="") je_doc.execCommand("createlink",false,s);'> &nbsp;&nbsp;
							
								<input type="image"  src="images/urlErase_icon.png" width="42px"
											onclick='je_doc.execCommand("unlink",false, null);'>&nbsp; &nbsp;
								
								<input type="image"  src="images/efErase_icon.png" width="42px"
											onclick="je_doc.execCommand('removeformat', 'false', 'null')"> &nbsp;	

												
								<input type="image"  src="images/imo_icon.png" width="18px" id="imo_icon">&nbsp; 
								<!-- 이모티콘 -->
								<div id="imo_icon_area" class="hidden">
									<table id="idclick_table">
										<tr>
											<th>이모티콘 선택</th>
										</tr>
										<tr id="center_align">
											<td>
												<input type="image"  src="images/emoticon/1.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/1.png')">&nbsp; &nbsp;
												<input type="image"  src="images/emoticon/2.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/2.png')">&nbsp; &nbsp;
												<input type="image"  src="images/emoticon/3.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/3.png')">&nbsp; &nbsp;
												<input type="image"  src="images/emoticon/4.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/4.png')">&nbsp; &nbsp;
												<input type="image"  src="images/emoticon/5.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/5.png')">&nbsp; &nbsp;	
												<input type="image"  src="images/emoticon/6.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/6.png')">&nbsp; &nbsp;	
											</td>
										</tr> 
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<!-- <select onchange="imageChange();">
									<option class="imageOp"  value="diary" selected>배경이미지 </option>
									<option class="imageOp"  value="news">이미지 삽입</option>
									</select>
									<div id="content_backgound" ></div> -->
								<input type="image"  src="images/pickture_icon.png" id="imgUploadIcon" width="18px"
											onclick="je_doc.execCommand('removeformat', 'false', 'null')">&nbsp; &nbsp;이미지	
							 <form action="imgupload.do" method="post" enctype="multipart/form-data" id="imgUpload"> 
								<div id="imgUploadDiv" class="hidden">	
									<input type="text" id="filename" class="fileInputTextbox" readonly="readonly" disabled>
									<div class="fileInputDiv">
										<input type="button" value="첨 부 파 일" class="fileInputBtn" >
										<input type="file" name="file" id="file" onchange="javascript:$('#filename').val($(this).val());">
									</div>
									<a onclick="imagesInsertThis();">첨부</a>&nbsp; &nbsp;	&nbsp;
								</div>
							 </form> 
							</td>
						<td>
							<a data-fancybox data-src="map.do"><img src="images/marker.png" width="12%"></a>
						</td>
						<td>
							<a href="javascript:line();"><img src="images/minus-gross-horizontal-straight-line-symbol-icon.svg" width="24%" ></a>
						</td>
						<td id="dateTd">날짜 </td>
						<td id="dateTd2">
							<input type="text" name="toDate" id="toDate" size="10" onchange="javascript:changeTitle()">
						</td>
					<tr>
						<td>
							<input type="image"  src="images/backgroundIMG_icon.png" id="content_allign_center" width="18px"
										onclick="imageChange();">&nbsp; &nbsp;배경
						</td>
						<td colspan="2" id="imageOp_selected" >
							<!-- <div id="content_backgound" ></div>  -->
							<select class="imagess"  onchange="diaryBg();">
								<option value="0" >선택 </option>
								<option value="1" >하트 </option>
								<option value="2">별</option>
							</select>
							&nbsp;
							<input type="radio" name="imgBB" value="auto" onchange="imgBB(this);" checked>&nbsp;기본
							<label class='radio-wrap'>
								<input type='radio' name='imgBB'  value='cover'  onchange="imgBB(this);" >
								<i class='cover-icon'></i>
							</label>
							<label class='radio-wrap'>
								<input type='radio' name='imgBB'  value='contain'  onchange="imgBB(this);">
								<i class='contain-icon'></i>
							</label>
							<a id="colorChoice2" href="javascript:void(0);"><img  src="images/fill_color-512.png" width="8%" ></a>
							<div id="colorchart2" class="hidden"></div>
						</td>
						<td colspan="2" >
							<a data-fancybox data-src='searchAll.do'><img src="images/580413-200.png" width="20px"></a>
						</td>
					</tr>
					<tr>
						<td>
							<button type="button" data-toggle="collapse" id="tagadddbtn">태그</button>
							<div id="tag" class="collapse">					
								<input id="tagaddtext" type="text"  size="15" 
									onkeydown="if(event.keyCode ==13) tagaddfunc();">
								<button id="tagaddOkBtn"  onclick="tagaddfunc();">확인</button>
							</div>
						</td>
						<td colspan="4" id="tagview"></td>
					</tr>
		

					<tbody id="bookTbody" style="display:none">
						<tr>
							<td colspan="5" align="center">
									<a data-toggle="collapse" data-target="#book" id="bookSearchLink">책 찾아보기</a>
									<div id="book" class="collapse text-center">
										<!-- <h4>도서 검색 </h4> --><br>
										제목&nbsp;<input type="text" id="bookSearchText"
										onkeydown="if (event.keyCode == 13) searchBook();">&nbsp;&nbsp;&nbsp;
										저자&nbsp;<input type="text">&nbsp;&nbsp;&nbsp;
										<button type="button" id="searchBtn" onclick="searchBook();">도 서 검 색</button>
										<br><br>
										<div></div>
										<button type="button" id="searchBtn">추 가 하 기</button>
									</div>
								</td>
						</tr>
					</tbody>
					<tbody id="placeTbody" style="display:none">
						<tr>
							<td colspan="5" align="right">
									<a data-toggle="collapse" data-target="#place">장소 찾아보기</a>
									<div id="place" class="collapse text-center">
										<h4>장소 검색 </h4>
										장소&nbsp;<input type="text">
									</div>
								</td>
						</tr>
					</tbody>
					<tbody id="movieTbody" style="display:none">
						<tr>
							<td colspan="5" align="right">
								<a data-toggle="collapse" data-target="#movie" id="movieSearchLink">영화 찾아보기</a>
								<div id="movie" class="collapse">
									<table width="100%" border="1">
									<tr>
										<td>제목</td>
										<td><input type="text" id="movieSearchText" onkeyup="if (event.keyCode == 13) searchMovie();"></td>
										<td rowspan="2">
											<button type="button" id="searchBtn" onclick="searchMovie();">검색</button>
										</td>
									</tr>
										<td>감독</td>
										<td><input type="text" id="directorSearchText" ></td>
									</tr>
									<tr>
										<td colspan="3">주간 박스오피스 </td>
									</tr>
									<c:forEach items="${weeklyResult.boxOfficeResult.weeklyBoxOfficeList}" var="i" begin="0">
									<tr>
										<td colspan=3>${i.rank} ${i.movieNm } (${i.audiAcc }명)</td>
									</tr>
									</c:forEach>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody id="newsTbody" style="display:none">
						<tr>
							<td colspan="5" align="center">
								<a data-toggle="collapse" data-target="#news" id="newSearchLink">기사 찾아보기<img></a>
								<div id="news" class="collapse">
									<table width="100%" border="1">
									<tr><td width="25%" align="center">인기검색어</td><td><h5 style="text-align:center;">기사 검색</h5></td></tr>
									<tr>
										<td align="center" >
											<!--대선후보<br>아이유 컴백<br> -->
											<c:set var="cnt" value="0" />
											<c:forEach items="${popKeyword}" var="i" begin="0">
											<c:set var="cnt" value="${cnt + 1 }" />
											${cnt} ${i }<br>
											</c:forEach>
										</td>
										<td width="65%" class="footerDiv" >
											<div>
												키워드&nbsp;<input type="text" id="newsSearchText">
												<button type="button" id="searchBtn">기 사 검 색</button>
											</div>
										</td>
									</tr>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody id="reviewTbody" style="display:none">
						<tr>
							<td>제품</td><td><input type="text"></td>
							<td>별점</td><td>✮✮✮✮✮</td>
						</tr>
					</tbody>
					<tbody id="musicTbody" style="display:none">
						<tr>
							<td>가수</td><td><input type='text' size="14"></td>
							<td>제목</td><td><input type='text'></td>
						</tr>
						<tr>
							<td colspan="5" align="right">
								<a data-toggle="collapse" data-target="#lyrics">가사 찾아보기</a>
								<div id="lyrics" class="collapse">
									제목&nbsp;<input type='text' id="musicSearchText" size="14">
									<button type="button" id="searchBtn" onclick="searchMusic();">검색 </button>
									<div></div>
								</div>
							</td>
						</tr>
					</tbody>
					<tr>
						
			
							
   <!-- END다정다정 -->		
							<!-- <input type="image"  src="images/efErase_icon.png" id="content_allign_center" width="42px"
										onclick="contentalligncenter();">ㄴㄹㄴㅇ색&nbsp; &nbsp;	&nbsp; 
							 -->
							
								
		
										
					</tr>
					<tr>
						 <td colspan="5" align="center" height="30px"><div id="dansunline"></div></td>
						</tr>
						
					
					<tr>
						<td class="uploadContent" colspan="5">
							 <!-- <textarea id="textarea" rows="20" id="content" ></textarea> --> 
							 <iframe id='editor' src="uploadHtml.do" style='width:100%;height:400px;' ></iframe>
						</td>
					</tr>
					
					<tr>
						<td colspan="5">
							<input type="radio" name="open" value="">&nbsp;나만보기&nbsp;
							<input type="radio" name="open" value="">&nbsp;이웃공개 
						</td>
					</tr>
					<tr>
						<td colspan="5" class="footerDiv">
							<div>
								<div class="grad"  onclick="content_OK()">등 록</div>&nbsp;
								<div class="grad" onclick="cancel();">취 소</div>
							</div>
						</td>
					</tr>
				</table>
				<button onclick="seeHTML();">소스보기</button><br>
				<textarea id="dhtmlText"></textarea>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
			</div>
		</section>
	</div>
</body>
</html>
