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
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=JVmBHBSdqNcd5JKBkRhO"></script>
<title>uploadform.jsp</title>
<script>

	var loginUser = "${sessionScope.loginUser.getMember_id()}";
	
	Date.prototype.currTime = function(f) {
		
		if (!this.valueOf()) return " ";
		var d = this;
		
		return f.replace(/(E|hh|mm|ss|a\/p)/gi, function($1) {
		// $1, $2…: 그룹
		// 정규표현식을기재할 때 /(a)(v)/이런 식으로 기재하면
		// a는$1이 되고 v는$2
		
			switch ($1) {
			case "hh":
				//return ((h = d.getHours() % 12) ? h : 12).addZero(2);
				return ((h = d.getHours() % 12) ? h : 12) + "";
			case "mm":
				//return d.getMinutes().addZero(2);
				return d.getMinutes() + "";
			case "ss":
				//return d.getSeconds().addZero(2);
				return d.getSeconds() + "";
			case "a/p":
				return d.getHours() < 12 ? '오전' : '오후';
			default:
				return $1;
			}
		});
		
	};

	/*
	String.prototype.string = function(len) {
		var s = '', i = 0;
		while (i++ < len) {
			s += this;
		}
		return s;
	};
	
	String.prototype.addZero = function(len) {
		return "0".string(len - this.length) + this;
	};
	
	Number.prototype.addZero = function(len) {
		return this.toString().addZero(len);
	};
	*/
	
	$(document).ready(function() {
		
		//document.getElementById("textarea").focus();
		$("[data-toggle='tooltip']").tooltip();
		
		$("#imageOp_selected>a:not(:first-child)").hide();
		$("#loading").hide();
		
		colorchart();
		colorchart2();
		colorchart3();

		$("#editor").click(function() {
			alert();
			if ($(this).text() == "내용을 입력해주세요." )  $(this).text("");
		});
		
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

		$("#weatherLink").click(function() {
			if ($("#weatherDiv").hasClass("hidden")) {
				$("#weatherDiv").removeClass("hidden");
				$("#weatherDiv").show();
				requestWlocationList();
				viewSearchTime();
			}
			else {
				$("#weatherDiv").addClass("hidden");
				$("#weatherDiv").hide();
			}
		});
		
		$("#imo_icon").click(function() {
			if ($("#imo_icon_area").hasClass("hidden")) {
				$("#imo_icon_area").removeClass("hidden");
				$("#imo_icon_area").show();
				je_doc.body.focus();
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
		
		$("#blogOwnerClick").click(function() {
			if ($(".blogOwnerClick").hasClass("hidden")) {
				$(".blogOwnerClick").removeClass("hidden");
				$(".blogOwnerClick").show();
				}
			else {
				$(".blogOwnerClick").addClass("hidden");
				$(".blogOwnerClick").hide();
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
		/*
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
*/
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
<form id="form" action="upload.do" method="post" onsubmit="return false;">
<input type="hidden" name="loginUser" value="${sessionScope.loginUser.getMember_id() }">
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
						<c:if test='${empty sessionScope.loginUser.getProfile_photo() }'>
						<img src="images/default.png" height="40px" class="img-circle">&nbsp;
						</c:if>
						<c:if test='${!empty sessionScope.loginUser.getProfile_photo() }'>
						<img src="images/profileImages/${sessionScope.loginUser.getProfile_photo()}" height="40px" class="img-circle">&nbsp;
						</c:if>
						&nbsp;${sessionScope.loginUser.getMember_id() } 님 </a>
				</div>
			</nav>
			<div id="loginUserDetail" class="hidden">
				<div id="box_icon">
					<table id="idclick_table">
						<tr id="center_align">
							<td>
								<a href="mypage.do?writer_id=${sessionScope.loginUser.getMember_id()}">마이페이지</a>&nbsp;&nbsp; |  &nbsp;&nbsp;
								<a href="myhome.do?writer_id=${sessionScope.loginUser.getMember_id() }">내블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
								<a href="neighborBlogPost.do">이웃 블로그</a>&nbsp;&nbsp; | &nbsp;&nbsp;
								<a href="logOut.do?writer_id=${sessionScope.loginUser.getMember_id() }">로그아웃</a> 
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
   
			<div class="header-content"
				style="<c:if test='${!empty blog.getBackground()}'>background:url(backgroundImages/${blog.getBackground()});</c:if><c:if test='${! empty blog.background_color}'>background-color:${blog.background_color };</c:if>">
				<div class="header-content-inner">
					<h2>
						<a href="myhome.do?writer_id=${param.writer_id} "
							style="color:${blog.getColor() }">
							<!--당신만의 공간에서 당신의 글을 만들어보세요.-->
							${blog.getTitle() }
						</a>
					</h2>
					<h4 style="color:${blog.contents_color}">${blog.getContents() }</h4>
				</div>
				<div class="header-content-inner2">
					<c:if test="${ param.writer_id eq sessionScope.loginUser.getMember_id()}">
					<a href="controll.do?writer_id=${sessionScope.loginUser.getMember_id() }">
					<img class="smallIcon"
					src="images/KakaoTalk_Photo_2017-04-24-10-28-40_21.png"></a>
					</c:if>
				</div>
			</div>
			<div class="navbar-header idView">
			<!-- navbar-header : 메뉴 버튼 클릭 시 토글 width 100% -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#menu">
					<span class="sr-only">Toggle navigation</span> Menu <i class="menu"></i>
				<!-- sr-only : 숨김 -->
				</button>
				<a href="javascript:void(0);" id="blogOwnerClick" style="display:inline-block" >
					<c:if test='${empty member.getProfile_photo() }'>
					<img src="images/default.png" height="40px" class="img-circle">
					</c:if>
					<c:if test='${!empty member.getProfile_photo() }'>
					<img src="images/profileImages/${ member.getProfile_photo()}" height="40px" class="img-circle">
					</c:if>
					${param.writer_id } 님
				</a>&nbsp;
				<div class="blogOwnerClick hidden">
					<div>
						<a class="hover" href="javascript:reqNeig();">이웃 신청</a>
						<hr><a class="hover" href="">프로필 보기</a>
					</div>
					<img src="images/idclick_icon.png" width="100%" height="100%">
				</div>
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
				<table width="100%"  id="uploadtable" align="center"> 
					<colgroup>
						<col width="12%" />
						<col width="28%" />
						<col width="15%" />
						<col width="15%" />
						<col width="30%" />
					</colgroup>
					<tr>
						<td align="center">분류</td>
						<td colspan="4">
							<select name="category" id="category">
								<option value="free" selected>자유</option>
								<option value="diary" >일기</option>
								<option value="news">뉴스</option>
								<option value="place">장소</option>
								<option value="review">리뷰</option>
								<option value="book">책</option>
								<option value="movie">영화</option>
								<option value="music">뮤직</option>
							</select>
							&nbsp;<a href="javascript:temp();">임시저장 불러오기 </a>
							<input type="hidden" id="temp">
						</td>
					</tr>
					<tr>
						<td>
						<!--
							<select onchange="je_doc.execCommand('fontname', false, this.value)">
								<option value="">선택</option>
								<option value="nanumbarungothic">나눔고딕</option>
								<option value="Arial">굴림체</option>
								<option value="Gungseo">궁서체</option>
								<option value="Georgia">Georgia</option>
								<option value="Dotum">돋움체</option>
							</select>
							-->
						</td> 	
						<td colspan="2" style="padding:5px;">
							<img src="images/bold_icon.png" width="12px"
											onclick="je_doc.execCommand('bold', 'false', 'null')">&nbsp; &nbsp;
								
							<img src="images/italic_icon.png" width="10px"
											onclick="je_doc.execCommand('italic', 'false', 'null')">&nbsp; &nbsp;
								
							<img src="images/underline_icon.png" width="15px"
											onclick="je_doc.execCommand('underline', 'false', 'null')">&nbsp; &nbsp;
								
							<img src="images/cencleline_icon.png" width="15px"
											onclick="je_doc.execCommand('strikethrough', 'false', 'null')">&nbsp; &nbsp;
							<img alt="" src="images/text_color.png" width="7%"  id="colorChoice">
							<div id="colorchart" class="hidden"></div>
							<img alt="" src="images/highlighter.jpg" width="7%"  id="colorChoice3">
							<div id="colorchart3" class="hidden"></div>
							<ul>
								<li>
									<a href="javascript:void(0);">
										<img src="images/65988-200.png" width="30px">
									</a>
									<ul>
										<li style="font-size:70%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 1);">
												가나다
											</a>
										</li>
										<li style="font-size:80%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 2);">
												가나다
											</a>
										</li>
										<li style="font-size:100%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 3);">
												가나다
											</a>
										</li>
										<li style="font-size:120%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 4);">
												가나다
											</a>
										</li>
										<li style="font-size:150%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 5);">
												가나다
											</a>
										</li>
										<li style="font-size:240%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 6);">
												가나다
											</a>
										</li>
										<li style="font-size:310%;">
											<a href="javascript:je_doc.execCommand('FontSize', 'false', 7);">
												가나다
											</a>
										</li>
									</ul>
								</li>
							</ul>
							<!--
							<select id="fontsize"
								onchange="je_doc.execCommand('FontSize', 'false', this.value)">
								<option value="">글자크기 </option>
								<option value="1" style="font-size:1;">가나다</option>
								<option value="2" style="font-size:2;">가나다</option>
								<option value="3" style="font-size:3;">가나다</option>
								<option value="4" style="font-size:4;">가나다</option>
								<option value="5" style="font-size:5;">가나다</option>
								<option value="6" style="font-size:6;">가나다</option>
								<option value="7" style="font-size:7;">가나다</option>
							</select>
							 -->
							<!--
							<select id=contenttextcolor2 onchange='contenttextcolor2()'>
								<option class='imageOp'  selected  >글자색상 선택</option>
								<option class='imageOp'  value='black'  style="background-color:black; color:white;">검정색</option>
								<option class='imageOp'  value='red'  style="background-color:red; color:white;-webkit-appearance:button">빨강색 </option>
								<option class='imageOp'  value='white'  style="background-color:white; color:black">흰색 </option>
								<option class='imageOp'  value='yellow'  style="background-color:yellow; color:black">노란색 </option>
								<option class='imageOp'  value='blue'  style="background-color:blue; color:white">파란색 </option>
								<option class='imageOp'  value='pink' style="background-color:pink; color:white">분홍색 </option>
								<option class='imageOp'  value='green' style="background-color:green; color:white"> 초록색 </option>
								<option class='imageOp'  value='orange' style="background-color:orange; color:white">주황색</option>
							</select> 
							-->
						</td>
						<td align="center">
							<img src="images/align_left_icon.png" id="content_allign_left"  width="18px" 
										onclick="je_doc.execCommand('justifyleft', 'false', 'null')"> &nbsp; &nbsp;
							<img  src="images/align_center_icon.png" id="content_allign_center" width="18px"
										onclick="je_doc.execCommand('justifycenter', 'false', 'null')"> &nbsp; &nbsp;
							<img src="images/align_right_icon.png" id="content_allign_right" width="18px"
										onclick="je_doc.execCommand('justifyright', 'false', 'null')"> &nbsp;
						</td>
						<td align="center">
								<img  src="images/link_icon.png" width="42px"
											onclick='var s=prompt();if(s!="") je_doc.execCommand("createlink",false,s);'> &nbsp;&nbsp;
							
								<img  src="images/urlErase_icon.png" width="42px"
											onclick='je_doc.execCommand("unlink",false, null);'>&nbsp; &nbsp;
								
								<img  src="images/efErase_icon.png" width="42px"
											onclick="je_doc.execCommand('removeformat', 'false', 'null')"> &nbsp;	

												
								<img  src="images/imo_icon.png" width="18px" id="imo_icon">&nbsp; 
								<!-- 이모티콘 -->
								<div id="imo_icon_area" class="hidden">
									<table id="idclick_table">
										<tr>
											<th>이모티콘 선택</th>
										</tr>
										<tr id="center_align">
											<td >
												<img  src="images/emoticon/1.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/1.png')">&nbsp; &nbsp;
												<img  src="images/emoticon/2.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/2.png')">&nbsp; &nbsp;
												<img  src="images/emoticon/3.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/3.png')">&nbsp; &nbsp;
												<img  src="images/emoticon/4.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/4.png')">&nbsp; &nbsp;
												<img  src="images/emoticon/5.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/5.png')">&nbsp; &nbsp;	
												<img  src="images/emoticon/6.png" width="20px" id="imo_icon"
													onclick="je_doc.execCommand('InsertImage', 'false', '/gonggan/images/emoticon/6.png')">&nbsp; &nbsp;	
											</td>
										</tr> 
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td style="text-align:center">
								<a href="javascript:je_doc.execCommand('InsertHorizontalRule', 'null');">
								<img src="images/minus-gross-horizontal-straight-line-symbol-icon.svg" width="25px" >
							</a>&nbsp;
							<a href="javascript:void(0);" onclick="$('#nrow').val('1'); $('#ncol').val('1');"
								data-target="#layerpop" data-toggle="modal">
								<img src="images/table_presentation_powerpoint_keynote_speech_business-512.png"
									height="25px">
							</a>
							<div class="modal fade" id="layerpop">
							<!--
							$('#modal_id').modal({backdrop: 'static'});
							modal창 밖을 클릭했을때 무조건 닫히는 현상을 방지
							(닫기버튼을 눌러야만 닫힌다)
							-->
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- header -->
										<div class="modal-header">
											<!-- 닫기(x) 버튼 -->
											<button type="button" class="close" data-dismiss="modal">×</button>
											<!-- header title -->
											<h4 class="modal-title">테이블 삽입 </h4>
										</div>
										<!-- body -->
										<div class="modal-body text-center">
											테이블 행과 열 갯수를 입력해주세요<br>
											행 :&nbsp;<input class="text-center" type="number" id="nrow" value="1" style="width:8%"><br>
											열 :&nbsp;
											<input class="text-center" type="number" id="ncol" value="1" style="width:8%"
											onkeydown="if (event.keyCode == 13) {if (insertTable($('#nrow').val(), $('#ncol').val())) $('#layerpop').modal('hide');}">
										</div>
										<!-- Footer -->
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												onclick="if (insertTable($('#nrow').val(), $('#ncol').val())) $('#layerpop').modal('hide');">
												OK
											</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">취소  </button>
										</div>
									</div>
								</div>
							</div>
								
							</td>
						<td id="imageOp_selected">
							<!-- <select onchange="imageChange();">
									<option class="imageOp"  value="diary" selected>배경이미지 </option>
									<option class="imageOp"  value="news">이미지 삽입</option>
									</select>
									<div id="content_backgound" ></div> -->
								<a href='javascript:void(0);' data-target="#layerpop3" data-toggle="modal">
									<img  src="images/photo-video-slr-camera-icon-512x512-pixel-12.png" width="20px">
								</a>
								 <div class="modal fade" id="layerpop3">
									<div class="modal-dialog">
										<div class="modal-content">
											<!-- header -->
											<div class="modal-header">
												<!-- 닫기(x) 버튼 -->
												<button type="button" class="close" data-dismiss="modal">×</button>
												<!-- header title -->
												<h4 class="modal-title">이미지 삽입 </h4>
											</div>
											<!-- body -->
											<div class="modal-body text-center">
												<img id='loading' height='70px' src="images/InternetSlowdown_Day.gif"
													style='position:absolute;margin:auto;left:0;right:0;top:0;bottom:0'>
												<div style="height:100px;position:relative">
													<div id='imgpreview' style='width:40%;height:150px;position:absolute;left:0;right:0;margin:auto'></div>
												</div>
												<br>
												이미지를 선택해주세요<br><br>
												<form id='frm2' name="frm2" method="post" enctype="multipart/form-data">
													<input type="text" id="filename" class="fileInputTextbox" readonly="readonly" disabled>
													<div class="fileInputDiv">
														<input type="button" value="첨 부 파 일" class="fileInputBtn" >
														<input type="file" name="file" id="file"
															onchange="javascript:$('#filename').val($(this).val()); preview('file');">
													</div>
												</form>
												<br><br>
												<input type="hidden" name="pimg">
											<!-- Footer -->
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													onclick="uploadPostImg(); $('#layerpop2').modal('hide');">
													OK
												</button>
												<button type="button" class="btn btn-default" data-dismiss="modal"
													onclick="refreshImgFilediv();">
													취소
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<a href="javascript:void(0)"
								data-target="#layerpop2" data-toggle="modal">
								<img src="images/pickture_icon.png"
									height="20px">
							</a>&nbsp;
							<a id="colorChoice2" href="javascript:void(0);">
								<img  src="images/fill_color-512.png" width="25px;" >
							</a>
							<div id="colorchart2" class="hidden"></div>
							<div class="modal fade" id="layerpop2">
							<!--
							$('#modal_id').modal({backdrop: 'static'});
							modal창 밖을 클릭했을때 무조건 닫히는 현상을 방지
							(닫기버튼을 눌러야만 닫힌다)
							-->
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- header -->
										<div class="modal-header">
											<!-- 닫기(x) 버튼 -->
											<button type="button" class="close" data-dismiss="modal">×</button>
											<!-- header title -->
											<h4 class="modal-title">배경 이미지 삽입 </h4>
										</div>
										<!-- body -->
										<div class="modal-body text-center">
											<img id='loading' height='70px' src="images/InternetSlowdown_Day.gif"
												style='position:absolute;margin:auto;left:0;right:0;top:0;bottom:0'>
											<div style="height:150px;position:relative">
												<div id='bgpreview' style='width:40%;height:150px;position:absolute;left:0;right:0;margin:auto'></div>
											</div>
											<br>
											배경 이미지를 선택해주세요<br>
											<form id='frm' name="frm" method="post" enctype="multipart/form-data">
												<input type="text" id="filename2" class="fileInputTextbox" readonly="readonly" disabled>
												<div class="fileInputDiv">
													<input type="button" value="첨 부 파 일" class="fileInputBtn" >
													<input type="file" name="file2" id="file2"
														onchange="javascript:$('#filename2').val($(this).val()); uploadDiaryBg();">
												</div>
											</form>
											<input type="hidden" name="bg">
											<!--<a onclick="imagesInsertThis();">첨부</a>&nbsp; &nbsp;	&nbsp;-->
		
											<div>
												<input type="radio" name="imgBB" value="auto" onchange="imgBBchange(this);" checked>
												&nbsp;기본
												<label class='radio-wrap'>
													<input type='radio' name='imgBB'  value='cover'  onchange="imgBBchange(this);" >
													<i class='cover-icon'></i>
												</label>
												<label class='radio-wrap'>
													<input type='radio' name='imgBB'  value='contain'  onchange="imgBBchange(this);">
													<i class='contain-icon'></i>
												</label>
											</div>
										</div>
										<!-- Footer -->
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												onclick="preview('file2');">
												미리보기
											</button>
											<button type="button" class="btn btn-default"
												onclick="diaryBg(); $('#layerpop2').modal('hide');">
												OK
											</button>
											<button type="button" class="btn btn-default" data-dismiss="modal"
												onclick="refreshDiaryFilediv();">
												취소
											</button>
										</div>
									</div>
								</div>
							</div>
						</td>
						<td align="center">
							<a id="weatherLink" href="javascript:void(0);">
								<img src="images/weathericon.png"
									height="32px">
							</a>
							<div id="weatherDiv" class="hidden" >
								<div id="wmapDiv">
									<script>
										
										var mapOptions = {
												center: new naver.maps.LatLng(35.7995704, 127.925399),
												level: 0,
												maxZoom: 0, //지도의 최대 줌 레벨
												minZoom: 0,
												mapTypeId: "normal",
												mapTypes: new naver.maps.MapTypeRegistry({
											        normal: naver.maps.NaverMapTypeOption.getNormalMap({
											            hd: true,
											            tileSize: [256 * 2, 256 * 2] // 512
											        })
											    }),
												logoControl: false,
												mapDataControl: false,
												scaleControl: false,
												draggable: false,
												
											};
										
										var position = new naver.maps.LatLng(37.3849483, 127.1229117);
	
										var map = new naver.maps.Map('wmapDiv', mapOptions);
										
										var CustomOverlay = function(options) {
											this._element = $('<div style="position:absolute;left:0;top:0;width:124px;background-color:#F2F0EA;text-align:center;">' +
											'<img src="./img/example/brown.png" style="width: 120px; height:130px">' +
											'<span style="font-weight: bold;"> Brown </span></div>')
										// 그냥 텍스트는 안나타남 (태그 지정해야)	
											
										    this.setPosition(options.position);
										    this.setMap(options.map || null);
										};
										
										CustomOverlay.prototype = new naver.maps.OverlayView();
										CustomOverlay.prototype.constructor = CustomOverlay;
	
										CustomOverlay.prototype.setPosition = function(position) {
										    this._position = position;
										    this.draw();
										};
	
										CustomOverlay.prototype.getPosition = function() {
										    return this._position;
										};
	
										CustomOverlay.prototype.onAdd = function() {
										    var overlayLayer = this.getPanes().overlayLayer;
	
										    this._element.appendTo(overlayLayer);
										};
	
										CustomOverlay.prototype.draw = function() {
										    if (!this.getMap()) {
										        return;
										    }
	
										    var projection = this.getProjection(),
										        position = this.getPosition(),
										        pixelPosition = projection.fromCoordToOffset(position);
	
										    this._element.css('left', pixelPosition.x);
										    this._element.css('top', pixelPosition.y);
										};
										
										CustomOverlay.prototype.onRemove = function() {
										    var overlayLayer = this.getPanes().overlayLayer;
	
										    this._element.remove();
										    this._element.off();
										};
	
										
									</script>
								</div>
								<div></div>
							</div>
						</td>
						<td align="center" id="dateTd">
							<!--날짜-->
						</td>
						<td id="dateTd2">
							<input type="text" name="toDate" id="toDate" size="15"
								onchange="javascript:changeDate()">
						</td>
					<tr>
						<td style="text-align:center">
							<a data-fancybox data-src='searchAll.do'><img src="images/580413-200.png" width="25px"></a>&nbsp;
							<a data-fancybox data-src="map.do"><img src="images/marker.png" width="20px"></a>
						</td>
						<td>
						</td>
						<td>
							<input type="button" data-toggle="collapse" id="tagadddbtn" value="태그">
							<div id="tag" class="collapse">					
								<input id="tagaddtext" type="text"  size="15" 
									onkeydown="if(event.keyCode ==13) tagaddfunc();">
								<button id="tagaddOkBtn"  onclick="tagaddfunc();">확인</button>
							</div>
						</td>
						<td colspan="4" id="tagview"></td>
					</tr>
					<tr>
						
					</tr>
		

					<tbody id="bookTbody" style="display:none">
						<tr>
							<td colspan="5" align="right">
								<a data-toggle="collapse" data-target="#book" id="bookSearchLink">
									책 찾아보기
								</a>
								<div id="book" class="collapse text-center">
									<!-- <h4>도서 검색 </h4> --><br>
									<c:if test="${! empty bestSellerList }">
									<c:forEach items="${bestSellerList}" var="i" begin="0" end="4">
									<a href="javascript:recieveBook('${i.coverSmallUrl}', '${i.title }', '${i.author }', '.', ${i.pubDate });">
										<img src="${i.coverSmallUrl}" style="display:inline-block;">
									</a>
									</c:forEach>
									</c:if>
									<br>
									<input type="text" id="bookSearchText" name="keyword" value="${keyword }" placeholder="도 서 검 색" 
										onkeydown="if(event.keyCode == 13) bookSearch();">
									<a href="javascript:bookSearch();">
										<img src=images/search.png width="5%" >
									</a>
									<a href="javascript:je_doc.execCommand('undo', false, null)">
										선택취소
										<!-- 이거는 execCommand로 한 명령만 취소 가능 -->
									</a>
									<br><br>
									<div class="searchAll">
										<table align='center' width="70%" id="bookSearchRes"
											style='border-spacing: 15px;border-collapse: separate;'>
										</table>
									</div>
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
									<table width="100%" style='border:1px solid #E6E6E6'>
										<tr>
											<td width="25%" align="center" style='color:gray'>주간 박스오피스 </td>
											<td style='padding-left:15px;'>
												<input type="text" id="movieSearchText" name="keyword" value="${keyword }"
													placeholder="영 화 검 색" 
													onkeydown="if(event.keyCode == 13) searchMovie();">
												<a href="javascript: $('#movieSearchRes').html(''); searchMovie();">
													<img src=images/search.png width="5%" >
												</a>
											</td>
										<tr>
											<td align="center">
												<c:forEach items="${weeklyResult.boxOfficeResult.weeklyBoxOfficeList}" var="i" begin="0">
												<a href="javascript:$('#movieSearchText').val('${i.movieNm }'); $('#movieSearchText').focus();">
													${i.rank} ${i.movieNm } (${i.audiAcc }명)
												</a><br>
												</c:forEach>
											</td>
											<td width="65%" style='padding-left:15px;'>
												<div style="height:300px;overflow:scroll;">
													<table width='100%'>
														<colgroup>
															<col width='20%'>
															<col width='90%'>
														</colgroup>
														<tbody id='movieSearchRes'></tbody>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody id="newsTbody" style="display:none">
						<tr>
							<td colspan="5" align="right">
								<a data-toggle="collapse" data-target="#news" id="newSearchLink">
									기사 찾아보기<img>
								</a>
								<div id="news" class="collapse">
									<table width="100%">
										<tr>
											<td width="25%" align="center">실시간 인기검색어</td>
											<td>
												<input type="text" id="newsSearchText" name="keyword" style="color:#E6E6E6"
													value="${keyword }" placeholder="기 사 검 색" 
													onkeydown="if(event.keyCode == 13) newsSearch();">
												<a href="javascript:newsSearch();">
													<img src=images/search.png width="5%" >
												</a>
											</td>
										</tr>
										<tr>
											<td align="center"  style="vertical-align:top;padding:10px">
												<!--대선후보<br>아이유 컴백<br> -->
												<c:set var="cnt" value="0" />
												<c:forEach items="${popKeyword}" var="i" begin="0">
												<c:set var="cnt" value="${cnt + 1 }" />
												<a href="javascript:$('#newsSearchText').val('${i }'); $('#newsSearchText').focus();">
													${cnt} ${i }
												</a><br>
												</c:forEach>
											</td>
											<td width="65%">
												<div style="height:300px;overflow:scroll;">
													<table id="newSearchRes">
														<%--
														<c:if test="${!empty searchNewsList}">
														<c:forEach items="${searchNewsList}" var="i" begin="0">
														<tr>
															<td>
																<a href="javascript:selectNews('${i.title}', '${i.originallink}', '${i.description }', '${i.pubDate }');">
																	${i.title}
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="javascript:selectNews('${i.title}', '${i.originallink}', '${i.description }', '${i.pubDate }');">
																	${i.description}
																</a>
															</td>
														</tr>
														<tr>
															<td>
																<a href="${i.originallink }"  target="_blank">상세정보</a>
															</td>
														</tr>
														</c:forEach>
														</c:if>
														--%>
													</table>
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
							<td>별점</td>
							<td>
								<span>
									<img id="image1" onmouseover="show(1);" onclick="mark(1);" onmouseout="noshow(1);" src="images/notStar.png" width="15px">
									<img id="image2" onmouseover="show(2);" onclick="mark(2);" onmouseout="noshow(2);" src="images/notStar.png" width="15px">
									<img id="image3" onmouseover="show(3);" onclick="mark(3);" onmouseout="noshow(3);" src="images/notStar.png" width="15px">
									<img id="image4" onmouseover="show(4);" onclick="mark(4);" onmouseout="noshow(4);" src="images/notStar.png" width="15px">
									<img id="image5" onmouseover="show(5);" onclick="mark(5);" onmouseout="noshow(5);" src="images/notStar.png" width="15px">
								</span>
								<br>
								<span id="startext">평가하기</span>
							</td>
						</tr>
					</tbody>
					<tbody id="musicTbody" style="display:none">
					<!--
						<tr>
							<td>가수</td><td><input type='text' size="14"></td>
							<td>제목</td><td><input type='text'></td>
						</tr>
					-->
						<tr>
							<td colspan="5" align="right">
								<a data-toggle="collapse" data-target="#lyrics">음악 찾아보기</a>
								<div id="lyrics" class="collapse">
								<div class="div2">
									<input type="text" id="musicSearchText" name="keyword" value="${keyword }" placeholder="검 색" 
										onkeydown="if(event.keyCode == 13) { $('#musicSearchRes').html(''); searchMusic(); }">
									<a href="javascript:$('#musicSearchRes').html(''); searchMusic();">
										<img src=images/search.png width="5%" >
									</a>
								</div>
								<div class="searchAll" style="height:300px;overflow:scroll;">
									<a id="fancy" style="display:none"></a>
									<table width="70%" align='center'>
										<tbody id="musicSearchRes"></tbody>
									</table>
								</div>
								</div>
							</td>
						</tr>
					</tbody>
					<tr>
								
	   <!-- END다정다정 -->		
								<!-- <img src="images/efErase_icon.png" id="content_allign_center" width="42px"
											onclick="contentalligncenter();">ㄴㄹㄴㅇ색&nbsp; &nbsp;	&nbsp; 
								 -->
								
									
			
											
					</tr>
					<tr>
						 <td colspan="5" align="center" height="30px"><div id="dansunline"></div></td>
					</tr>
							
						
					<tr>
						<td class="uploadContent" colspan="5">
							 <!-- <textarea id="textarea" rows="20" id="content" ></textarea> --> 
							 <iframe id='editor' src="uploadHtml.do" style='width:100%;height:400px;'></iframe>
						</td>
					</tr>
					
					<tr>
						<td colspan="5">
							<input type="radio" name="open" value="public" checked>&nbsp;전체공개&nbsp;
							<input type="radio" name="open" value="onlyMe">&nbsp;나만보기&nbsp;
							<input type="radio" name="open" value="neighbor">&nbsp;이웃공개
						</td>
					</tr>
					<tr>
						<td colspan="5" class="footerDiv">
							<div>
								<input type="hidden" name="place_name">
								<input type="hidden" name="movie_title">
								<input type="hidden" name="diary_title">
								<input type="hidden" name="music_title">
								<input type="hidden" name="music_info">
								<div class="grad"  onclick="content_OK()">등 록</div>&nbsp;
								<div class="grad" onclick="cancel();">취 소</div>
							</div>
						</td>
					</tr>
				</table>
				
				<!-- <input type="button" onclick="seeHTML();" value=" 소스보기 " /><br>-->
				
				<textarea id="dhtmlText" name="content" style="width:0" ></textarea>
				<!--
				<img src="images/marker.png" id="kae" draggable="true" ondragstart="drag(event);">
				<iframe src="clipboard.do" id="clipboard"></iframe>
				-->
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
				<br><br><br><br><br><br><br><br><br><br><br>
			</div>
		</section>
	</div>
</form>
</body>
</html>
