var reg = /<(?:img)[^>]*(src)?=.*\/(.*\.(?:jpg|jpeg|gif|bmp|png))[^<]*>/i;
var Ca = /\+/g;
var je_doc={};
var currTimeFormat = 'a/p hh시 mm분 ss초';
var temperature;
var locked = 0 ;
var prev_val;

$(function() {
	dateFunc();
});

function dateFunc() {
	$( "#toDate" ).datepicker({
		inline: true,
		prevText: 'prev',
		nextText: 'next',
		showButtonPanel: true,    /* 버튼 패널 사용 */
		changeMonth: true,        /* 월 선택박스 사용 */
		changeYear: true,        /* 년 선택박스 사용 */
		showOtherMonths: true,    /* 이전/다음 달 일수 보이기 */
		selectOtherMonths: true,    /* 이전/다음 달 일 선택하기 */
		showOn: "button",
		buttonImage: "images/calendar_icon.png",
		buttonImageOnly: false,
		minDate: '-30y',
		closeText: '닫기',
		currentText: '오늘',
		showMonthAfterYear: true,        /* 년과 달의 위치 바꾸기 */
		/* 한글화 */
		/*
		monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
		*/
		showAnim: 'slideDown',
		/* 날짜 유효성 체크 */
		onClose: function( selectedDate ) {
			$('#fromDate').datepicker("option","minDate", selectedDate);
		}
	});
}

function viewSearchTime() {
	var nowTime = new Date().currTime(currTimeFormat);
	$("#wmapDiv").next().html("<div style='font-size:90%;text-align:right'>"
			+ nowTime + " 기준</div>");
}


/*-------------------------------------------------------- */

	 
	 function callback(data){

			var jsonObj = JSON.stringify(data);
			var jsonArr = JSON.parse(jsonObj);
			xVal = jsonArr.x;
			yVal = jsonArr.y;
			console.log(xVal);
			var map = new naver.maps.Map('map',{
				center: new naver.maps.LatLng(xVal,yVal),
				zoom: 10
				
			});
			console.log(xVal);
}
function uploadImg(){
	/*$.ajax({
		url:"imgupload.do",
		type:"POST",
		contentType: false,
		processData: false,
		data: {file : $("#file").serialize()},
		success:function(data){
            document.getElementById('editor').contentWindow.document.body.innerHTML += 
            	"<img src='uploadImages/" + data + "'>";
		}
		
	});*/
	
	$('#imgUpload').ajaxForm({
		url: "imgupload.do",
		enctype: "multipart/form-data", // 여기에 url과 enctype은 꼭 지적해주어야 하는 부분이며 multipart로 지적해주지 않으면 controller로 파일을 보낼 수 없음
		success: function(result){
			alert(result);
			je_doc.execCommand('InsertImage', 'false', '/gonggan/uploadImages/' + result);
            //document.getElementById('editor').contentWindow.document.body.innerHTML += "<img src='uploadImages/" + result + "'>";
		}
		});
		// 여기까지는 ajax와 같다. 하지만 아래의 submit명령을 추가하지 않으면 백날 실행해봤자 액션이 실행되지 않는다.
		$("#imgUpload").submit();
	
}


function recieveMovie(image, title, director , actor, pubDate) {
	   document.getElementById('editor').contentWindow.document.body.innerHTML += 
	      "<table align='center' width='80%'>" 
	      + "<tr><td align='center'><h3><b>" + title + "</b><h3></td></tr>"
	      + "<tr><td width='30%'align='center'>"
	      + "<img src='" + image + "' width='50%'></td></tr>"
	      + "<tr><td align='center' style='color:gray;'><b>감독</b> "+ director + "</td></tr>" 
	      + "<tr><td align='center' style='color:gray;'><b>출연</b>  "+ actor + "</td></tr>"
	      +"<tr><td align='center' style='color:gray;'><b> 개봉</b> "+pubDate+" </td></tr>"
	      + "</td></tr></table>";
	}

function recieveMusic(videoId, title, thumbnail){
	document.getElementById('editor').contentWindow.document.body.innerHTML += 
		"<center><b><h4>" +title+"</h4></b></center>" +
		"<table align='center' width='60%' style='border:1px solid gray; color:gray;'>" +
			"<tr><td><img src='" + thumbnail + "' width='100%' height='280px'></td></tr>"
		+ "<tr><td>" + title + "</td></tr></table>";
}

function recieveNews(title, originallink, description, pubDate) {
	document.getElementById('editor').contentWindow.document.body.innerHTML += 
		"<div style='border:1px solid gray; color:gray;margin-bottom:20px;'>"
			+ "<table align='center' width='80%'><tr>"
			+ "<td><h3><b>" + title+"<b></h3></td></tr>"
			+ "<tr><td>" + description + "</td>"
			+ "</tr></table></div>내용을 입력해주세요.";
}

function recieveBook(image, title, author, publisher, pubdate) {
	document.getElementById('editor').contentWindow.document.body.innerHTML += 
		"<table style='background-color:#fdfdfd;border:1px solid #E6E6E6; color:gray;' align='center' width='50%'>"
		+ "<colgroup><col width='30%'><col style='width:*'></colgroup>"
		+ "<tr><td rowspan='4'>"
		+ "<img src='" + image + "' width='100%'></td>"
		+ "<td style='max-width:0px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;'>"
		+ "<h3>" + title + "</h3></td>"
		+ "<tr><td style='font-weight:lighter'><b>저자</b> "+ author + "</td></tr>" 
		+ "<tr><td style='font-weight:lighter'><b>출판</b>  "+ publisher + "</td></tr>"
		+"<tr><td style='font-weight:lighter'><b> 발매</b> "+pubdate+" </td></tr>"
		+ "</td></tr></table>" +
				"<center><div sytle='margin: 0 auto;'><img src='images/bookicon.png' width='100px'><div></center>" +
				"<center><h3 style='margin-bottom:10px;'><b>"+ title +"를 읽고나서</b></h3></center>" +
						"<hr style='background:linear-gradient(to right, #DEACC6, #91B2DF);width:90%;height:2px; border:0px'>" +
						"내용을 입력해주세요.<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>" +
						"<center><div sytle='margin: 0 auto;'><img src='images/star.png' width='100px'><div></center>" +
						"<center><b><h3 style='margin-bottom:10px;'>추천해요!</h3></b></center>" +
						"<hr style='background:linear-gradient(to right, #DEACC6, #91B2DF);width:90%;height:2px; border:0px'>" +
						"내용을 입력해주세요.";
}

function recieveMap(image) {
	je_doc.body.focus();
	je_doc.execCommand('justifycenter', 'false', 'null');
	je_doc.execCommand('InsertImage', 'false', '/gonggan/uploadImages/' + image);
	document.getElementById('editor').contentWindow.document.body.innerHTML += 
					"<br><br><br><br><br><br><br>"
					+ "<table width='80%' align='center'><tr><td>"
					+ "<div style='padding:5px 20px; background:linear-gradient(to right, #DEACC6, #91B2DF);" +
					" color:white; border-radius:20px;'><b>어떤곳?</b></div></td></tr>" +
					"<tr><td style='padding-left:20px;'>내용을 입력해주세요.<br><br><br></td></tr>" +
					"<tr><td><div style='padding:5px 20px; background:linear-gradient(to right, #DEACC6, #91B2DF);" +
					" color:white; border-radius:20px;'><b>무슨 일로?</b></div><br></td></tr>" +
					"<tr><td style='padding-left:20px;'>내용을 입력해주세요.<br><br><br></td></tr>" +
					"<tr><td><div style='padding:5px 20px; background:linear-gradient(to right, #DEACC6, #91B2DF); color:white;" +
					"border-radius:20px;'><b>무엇을 했나요?</b></div><br></td></tr>" +
					"<tr><td style='padding-left:20px;'>내용을 입력해주세요.<br><br><br></td></tr></table>";
}

function tagaddfunc(){
	
	if($("#tagaddtext").val() != "" ) {
		$("#tagview").html($("#tagview").html() + "<span>" + $("#tagaddtext").val()
				+ " <a href='javascript:void(0);' onclick='deleteTag(this);'><img src='images/x_icon.png' width='15px'></a>&nbsp;</span>");
	}
	$("#tagaddtext").val("");
	
}

function deleteTag(obj){
	
	var span = obj.parentNode;
	var td = span.parentNode;
	
	td.removeChild(span);
	$("#tagaddtext").focus();
}




/* 이미지 option선택시 함수 */
function imageChange(){
	

		$("#content_backgound").html($("#content_backgound").html()+'<select  onchange="dairyBg();">'+
				'<option class="imagess"  value="1" selected>하트 </option>'+
				'<option class="imagess"  value="1">별</option></select>');
	
}

function diaryBg(){
	je_doc = document.getElementById('editor').contentWindow.document;
	if($(".imagess").val()==0){
		je_doc.body.background="transparent";
	}
	if($(".imagess").val()==1){
		// 반복해서
		je_doc.body.background="/gonggan/images/x_icon.png";
		je_doc.body.style.backgroundSize="contain";
	
	}
}

function imgBB(obj){
	je_doc = document.getElementById('editor').contentWindow.document;
	
	if (obj.value=="auto")
		je_doc.body.style.backgroundSize="auto";
	else if (obj.value=="contain")
		je_doc.body.style.backgroundSize="contain";
	else if (obj.value=="cover")
		je_doc.body.style.backgroundSize="cover";
}


function seeHTML() {
	$("#dhtmlText").val(document.getElementById('editor').contentWindow.document.body.innerHTML);
}


/* */




/* dhtml editor함수 */
function run(){
	
	je_doc = document.getElementById('editor').contentWindow.document;
	je_doc.designMode="on";
	je_doc.addEventListener("keydown", showDeleteImgPath, false);

	$('#category').focus(
			function() {
				prev_val = $(this).val();
			}).change(function() {
				$(this).blur() // Firefox fix as suggested by AgDude
				if($(this).val() != 'default') {
					if (je_doc.body.innerHTML != "")
						changeForm();
					else if (confirm('현재 작성 내역을 임시 저장하고 폼을 바꾸시겠습니까?'))
							changeForm();
					else
						$(this).val(prev_val);
				}
			});
}

function imagesInsertThis(){
	je_doc = document.getElementById('editor').contentWindow.document;
	je_doc.designMode = "on";
	var image = $("#filename").val();
	alert($("#filename").val());
	je_doc.execCommand('InsertImage', 'false', image);
	
}

function contenttextcolor(color) {
	je_doc.execCommand("forecolor", false, color);	
}

function contenttextcolor2(){

	var color = $("#contenttextcolor2").val();
	//var s = prompt("글자색", color);
	if(color != "") je_doc.execCommand("forecolor", false, color);	

}

function colorchart(){
	var clr = new Array('00', '60', '80', '90', 'b0', 'a0', 'c0', 'ff');
	
	var table;
	var tr;
	var td;
	
	for (var i=0 ; i<1 ; i++) { 
		
		table = document.createElement("table");
		//table.width = '100%';
		table.border = '1';
		//table.cellpadding = '8';
		//html($("#colorchart").html() + "<table width='100%' border='1' cellpadding='8'>");

		for (var j=0 ; j<16 ; j++) {
			tr = document.createElement("tr");
			//$("#colorchart").html($("#colorchart").html() + '<tr>');
			
			for (var k=0 ; k<16 ; k++) {
				td = document.createElement("td");
				td.className = "colorchartTd";
				td.width = "10px";
				td.height = "10px";
				td.bgColor = "#" + clr[i] + clr[j] + clr[k];
				//td.innerHTML = '<tt>#' + clr[i] + clr[j] + clr[k] + '</tt>';
				tr.appendChild(td);
				/*
				$("#colorchart").html($("#colorchart").html() + "<td bgcolor='#" + clr[i] + clr[j] + clr[k] + "'>");
				$("#colorchart").html($("#colorchart").html() + '<tt>#');
				$("#colorchart").html($("#colorchart").html() + clr[i] + clr[j] + clr[k] + '</tt></td>');
				*/
			}
			table.appendChild(tr);
			//$("#colorchart").html($("#colorchart").html() + "</tr>");
		}
		
		document.getElementById("colorchart").appendChild(table);
		//$("#colorchart").html($("#colorchart").html() + "</table><br>");
	}
	
	/*
	for (i=0 ; i<8 ; i++) { 
		$("#colorchart").html($("#colorchart").html() + "<table width='100%' border='1' cellpadding='8'>");
		for (j=0 ; j<8 ; j++) {
			$("#colorchart").html($("#colorchart").html() + '<tr>');
			for (k=0 ; k<8 ; k++) {
				$("#colorchart").html($("#colorchart").html() + "<td bgColor='#" + clr[i] + clr[j] + clr[k] + "'>");
				$("#colorchart").html($("#colorchart").html() + '<tt>#');
				$("#colorchart").html($("#colorchart").html() + clr[i] + clr[j] + clr[k] + '</tt></td>');
			}
			$("#colorchart").html($("#colorchart").html() + "</tr>");
		}
		$("#colorchart").html($("#colorchart").html() + "</table><br>");
	}
	*/
}

function colorchart2(){
	var clr = new Array('00', '60', '80', '90', 'b0', 'a0', 'c0', 'ff');
	
	var table;
	var tr;
	var td;
	
	for (var i=0 ; i<1 ; i++) { 
		
		table = document.createElement("table");
		table.border = '1';

		for (var j=0 ; j<16 ; j++) {
			tr = document.createElement("tr");
			
			for (var k=0 ; k<16 ; k++) {
				td = document.createElement("td");
				td.width = "10px";
				td.height = "10px";
				td.bgColor = "#" + clr[i] + clr[j] + clr[k];
				tr.appendChild(td);
			}
			table.appendChild(tr);
		}
		
		//document.getElementById("colorchart2").appendChild(table);
	}

}
function colorchart3(){
	var clr = new Array('00', '60', '80', '90', 'b0', 'a0', 'c0', 'ff');
	
	var table;
	var tr;
	var td;
	
	for (var i=0 ; i<1 ; i++) { 
		
		table = document.createElement("table");
		table.border = '1';

		for (var j=0 ; j<16 ; j++) {
			tr = document.createElement("tr");
			
			for (var k=0 ; k<16 ; k++) {
				td = document.createElement("td");
				td.width = "10px";
				td.height = "10px";
				td.bgColor = "#" + clr[i] + clr[j] + clr[k];
				tr.appendChild(td);
			}
			table.appendChild(tr);
		}
		
		document.getElementById("colorchart3").appendChild(table);
	}

}
function bgcolor3(color) {
	if (navigator.appVersion.indexOf("MSIE")!=-1) je_doc.execCommand("backcolor",false, color);
	else je_doc.execCommand("hilitecolor",false, color);		
}

function bgcolor(color) {
	//je_doc.execCommand('Backcolor', color);
}


/* */

/*-------------------------------------------------------- */
function changeDate() {
	var diaryyear = $("#toDate").val().split("/")[2];
	var diarymonth = $("#toDate").val().split("/")[0];
	var diarydate = $("#toDate").val().split("/")[1];
	
	var dateObj = new Date();
	
	dateObj.setYear(diaryyear);
	dateObj.setMonth(diarymonth - 1);
	dateObj.setDate(diarydate);
	
	je_doc.getElementById("diarydateTd").innerText = diarydate;
	je_doc.getElementById("diarydayTd").innerHTML = dateToday(dateObj);
}

function temp() {
	je_doc.body.innerHTML = $("#temp").val();
}

function changeForm() {
	
	var today = new Date();
	
	$("#temp").val(je_doc.body.innerHTML);
	je_doc.body.innerHTML = "";
	$("#textarea").focus();

	if (document.getElementById("category").value == "review") {
		$("#reviewTbody").css("display", "table-row-group");
	}
	else
		$("#reviewTbody").css("display", "none");

	if (document.getElementById("category").value == "music") {
		$("#musicTbody").css("display", "table-row-group");
		document.getElementById("dateTd2").innerHTML =
			"<input type='checkbox' checked>&nbsp;유튜브 연결";
	}
	else
		$("#musicTbody").css("display", "none");

	if (document.getElementById("category").value == "diary") {
		document.getElementById("dateTd").innerHTML = "날짜";
		document.getElementById("dateTd2").innerHTML =
			"<input type='text' name='toDate' id='toDate' size='15' onchange='javascript:changeDate()'>";
		dateFunc();
		je_doc.body.innerHTML += "<table style='border-collapse:collapse;width:100%;'>"
				+ "<colgroup><col width='5%'/><col width='5%'/><col width='10%'/><col width='62%'/><col width='8%'/><col width='10%'/></colgroup>"
				+ "<tr><td id='diarydateTd' style='font-family: \"Francois One\", sans-serif; font-size:400%; border-left:1px solid #E6E6E6; border-top:1px solid #E6E6E6; border-bottom:1px solid #E6E6E6; padding:5px;' rowspan='2'>"
				+ (today.getDate() <10 ? "0"+ today.getDate() : today.getDate() )
				+"</td>"
				+"<td id='diarydayTd' style='font-family: \"Francois One\", sans-serif; font-size:200%; border-top:1px solid #E6E6E6; border-bottom:1px solid #E6E6E6; border-right:1px solid #E6E6E6;padding:5px; -webkit-transform:rotate(270deg);' rowspan='2'>"
				+ dateToday(today) + "</td>"
				+"<td style='border-top:1px solid #E6E6E6; border-bottom:1px solid #E6E6E6; padding:5px;'>제목</td>"
				+ "<td id='title' style='border-top:1px solid #E6E6E6;color:#E6E6E6;'>"
				+ "제목을 입력해주세요.</td>"
				+ "<td style='border-top:1px solid #E6E6E6; border-bottom:1px solid #E6E6E6; '>"
				+ "<span style=''>날씨</span></td>"
				+ "<td id='weathertd' style='border-top:1px solid #E6E6E6; border-bottom:1px solid #E6E6E6; border-right:1px solid #E6E6E6;'>"
				+ "<span style='color:#E6E6E6'></span></td></tr>"
				+"<tr><td style='padding:5px;border-bottom:1px solid #E6E6E6;'></td>"
				+ "<td style='border-top:1px solid #E6E6E6; border-bottom:1px solid #E6E6E6; border-right:1px solid #E6E6E6;' colspan='3'>"
				+ "<span style='color:#E6E6E6'>기분을 입력해주세요..</span></td>" +
						"</tr></table>" +
						"<div id='contentArea' style='padding:10px;'>내용을 입력해주세요.</div>";
	}
	else {
		document.getElementById("dateTd").innerHTML = "";
		document.getElementById("dateTd2").innerHTML = "";
	}
	
	if (document.getElementById("category").value == "news") {
		$("#newsTbody").css("display", "table-row-group");
	}
	else
		$("#newsTbody").css("display", "none");

	if (document.getElementById("category").value == "movie") {
		$("#movieTbody").css("display", "table-row-group");
		je_doc.body.innerHTML += "<div style='border:1px solid red;display:table;background:url(images/template/KakaoTalk_Photo_2017-06-12-09-43-40_56.jpeg);background-size:100% 100%;width:100%;height:170px'>"
				+ "<h2 style='display:table-cell;height:100%;width:100%;vertical-align:middle;text-align:center'>"
				+ "<b><span style='color:black'>GET</span>"
				+ "<span style='color:white'>OUT</span></b></h2>"
				+"</div><br>흑인 남자가 백인 여자친구 집에 초대 받으면서 벌어지는 이야기<br>"
				+ "<hr><table><tr><td><img src='images/template/504455d3eeb43cd167dbc4c1f24b72ce.jpg'>"
				+ "</td></tr><tr><td>GETOUT 2017</td></tr></table>"
				+ "<table><tr><td style='background:url(images/template/KakaoTalk_Photo_2017-06-12-09-43-41_47.jpeg);background-size:100% 100%'>"
				+ "<h2 style='text-align:center'><b><span style='color:black'>SYNO</span>"
				+ "<span style='color:white'>PSIS</span></h2></td></tr>"
				+ "<tr><td>ABOUT MOVIE <br>"
				+ "공개 6일 만에 메인 예고편 조회수 1,000만 뷰 돌파! 개봉 요청 쇄도! <br>"
				+ "관객들의 폭발적 반응이 개봉시킨 영화! <br>"
				+ "영화 <겟 아웃>은 흑인 남자가 백인 여자친구 집에 초대 받으면서 벌어지는 이야기로 북미 개봉 직후 박스오피스 1위를 차지한 바 있다."
				+ " 무서운 흥행세를 발휘한 <겟 아웃>은 SNS를 통해 게재된 예고편을 통해서 국내 관객들에게도"
				+ " 폭발적인 반응을 불러일으켰다. 국내 개봉이 확정되기도 전에 SNS에 게재된 해외 예고편은"
				+ " 무려 370만 뷰의 조회수를 돌파한 것은 물론, 7만 개를 웃도는 댓글수를 기록하기도 했다. "
				+ "예고편을 접한 국내 관객들은 예측불허의 전개에 호기심을 드러내면서도 "
				+ "영화가 선사하는 미스터리한 분위기에 놀라움을 자아내기도 했다. 압도적 몰입감과 궁금증을 불러일으키는 "
				+ "예고편은 곧 네티즌들의 국내 개봉을 요청하는 목소리로 이어졌다. "
				+ "해외에서 시작된 뜨거운 흥행세와 해외 언론 매체들의 극찬 세례에 궁금증을 감추지 못한 네티즌들은 "
				+ "배급사인 UPI코리아를 통해 국내 개봉을 요청하며 영화에 대한 높은 기대를 드러냈다. "
				+ "UPI코리아는 “독보적인 장르의 <겟 아웃>을 어떻게 알려야 할지 고민이 많아 사실상 한국 개봉은 미정이었다."
				+ " 하지만 국내 팬들의 열화와 같은 관심과 개봉 요청 쇄도로 개봉을 결정하게 되었다”며 "
				+ "이례적인 개봉 결정의 이유를 밝혔다. 특히 관객들의 뜨거운 관심 속에 개봉을 확정 지은 <겟 아웃>은 "
				+ "메인 예고편을 공개한지 6일 만에 누적 조회수 약 1,143만 뷰를 돌파, 상반기 최고 화제작으로 등극했다. "
				+ "이렇듯 관객들의 폭발적 반응이 개봉시킨 영화 <겟 아웃>은 지금껏 본 적 없는 새로움과 신선한 충격으로 "
				+ "관객들을 사로잡을 예정이다. <br></td></tr>"
				+ "<tr><td style='background:url(images/template/KakaoTalk_Photo_2017-06-12-09-43-41_47.jpeg);background-size:100% 100%'>"
				+ "<h2 style='text-align:center'><b><span style='color:black'>REV</span>"
				+ "<span style='color:white'>IEW</span></h2></td></tr>"
				+ "<tr><td>1. 영화 처음 납치 장면 납치당하는 남자가 6개월간 실종된 안드레이다.<br>"
				+ "2. 영화 처음 안드레가 납치되는 장면범인이 뒤에서 목을 졸라서 납치하는데 "
				+ "로즈의 남동생이 식사시간에 주짓수를 말하고 헤드락을 걸려고 헀던 것과 일치하고 영화 ...<br>"
				+ "</td></tr></table>";
	}
	else
		$("#movieTbody").css("display", "none");

	if (document.getElementById("category").value == "place") {
		$("#placeTbody").css("display", "table-row-group");
	}
	else
		$("#placeTbody").css("display", "none");

	if (document.getElementById("category").value == "book") {
		$("#bookTbody").css("display", "table-row-group");
	}
	else
		$("#bookTbody").css("display", "none");
	
	/*
	else {
		document.getElementById("appendArea").deleteRow(0);
	}
	*/
	
}

function dateToday(date) {
	var returnValue;
	switch(date.getDay()) {
	case 0:
		returnValue = "<span style='color:red'>SUN</span>";
		break;
	case 1:
		returnValue = "MON";
		break;
	case 2:
		returnValue = "TUE";
		break;
	case 3:
		returnValue = "WED";
		break;
	case 4:
		returnValue = "THU";
		break;
	case 5:
		returnValue = "FRI";
		break;
	case 6:
		returnValue = "<span style='color:blue'>SAT<span>";
		break;
	}
	return returnValue;
}

function content_OK() {

	if (je_doc.body.innerHTML != "") {
		if (confirm("포스트를 게시하시겠습니까? ") == true) {
			if($("select [name=category]").val()=="diary") {
				$("input[name=title]").val(je_doc.getElementById("title").innerText);
				$("#dhtmlText").val(je_doc.body.innerHTML);
				$('#form').submit();
			} else 
				$('#form').submit();
		} else return;
	}
	else alert("본문 내용을 입력해주세요");
}

function cancel() {
	if (confirm("포스팅을 취소하시겠습니까? ") == true)
		location.href = "myhome.do?writer_id=" + loginUser;
	else return;
}

function searchMovie() {
	if ($("#movieSearchText").val() == "")
		alert("검색어를 입력해주세요");
	else{
		$.ajax({
			url:"moviesearch2.do",
			data: {keyword : $('#movieSearchText').val() },
			success:function(data){
	            callbackMovieSearch(data);
			}
		});
	}
}

function callbackMovieSearch(data) {
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	
	var tr, td, a;
	
	var title, actor, director, pubDate,image;
	
	for (var i in jsonArr.list) {
		
		title = decodeURIComponent((jsonArr.list[i].title).replace(Ca, " "));
		actor = decodeURIComponent((jsonArr.list[i].actor).replace(Ca, " "));
		director = decodeURIComponent((jsonArr.list[i].director).replace(Ca, " "));
		pubDate = jsonArr.list[i].pubDate;
		image = jsonArr.list[i].image;
		
		tr = document.createElement("tr");
		td = document.createElement("td");
		td.rowSpan = "3";
		a = document.createElement("a");
		a.innerHTML = "<img src=" + image + ">";
		td.appendChild(a);
		tr.appendChild(td);

		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveMovie(\" "+ image +"\", \""+title+"\", \" "+director+"\",\" "+actor+"\",\" "+pubDate+"\");";
		a.innerHTML ="<b>"+ title+"</b>";
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("movieSearchRes").appendChild(tr);

		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveMovie(\" "+ image +"\", \""+title+"\", \" "+director+"\",\" "+actor+"\",\" "+pubDate+"\");";
		a.innerHTML = pubDate+" 개봉";
	        
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("movieSearchRes").appendChild(tr);
		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveMovie(\" "+ image +"\", \""+title+"\", \" "+director+"\",\" "+actor+"\",\" "+pubDate+"\");";
		a.innerHTML = actor+director;
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("movieSearchRes").appendChild(tr);
 
 
		//jsonArr.list.originallink;
		//jsonArr.list.pubDate;
	}

}


function searchMusic() {
	
	$.ajax({
		url: "lyrics.do",
		data: { title: $("#musicSearchText").val() },
		success: function(data) {
			$("#lyrics div").html(data);
			
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
	
	$.ajax({
		url: "musicpost2.do",
		data: { title: $("#musicSearchText2").val() },
		success: function(data) {
			callbackMusicSearch(data);
			
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
	
}
function callbackMusicSearch(data) {
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	var tr, td, a;
	   
	var title, description, originallink, pubDate;
	   
	for (var i in jsonArr.list) {

		   
		title = decodeURIComponent((jsonArr.list[i].title).replace(Ca, " "));
		description = decodeURIComponent((jsonArr.list[i].description).replace(Ca, " "));
		originallink = jsonArr.list[i].originallink;
		pubDate = jsonArr.list[i].pubDate;
		   
		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveNews(title, originallink, description, "
			+ "pubDate); alert(title);";
		a.innerHTML = (parseInt(i, 10) + 1) + ". " + title;
		   
		/*
	   td.innerHTML = "<a href='javascript:recieveNews("
		   + title + ", " + originallink + ", " + description + ", "
		   + pubDate + "); alert(" + title + ");'>"
		   + (parseInt(i, 10) + 1) + ". "
	   		+ title + "</a>";
		 */
		
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("newSearchRes").appendChild(tr);

		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveNews(title, originallink, description, "
			+ "pubDate); alert(title);";
		a.innerHTML ="<ul><li>"+ description+"</li></ul>";
		   
		/*
		   td.innerHTML = "<ul><li><a href='javascript:recieveNews(\'"
			   + title + "\', \'" + originallink + "\', \'" + description + "\', \'"
			   + pubDate + "\'); alert(title);'>"
		   		+ description + "</a></li></ul>";
		 */
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("newSearchRes").appendChild(tr);
		   
		   
		//jsonArr.list.originallink;
		//jsonArr.list.pubDate;
	}
}

function searchBook() {
	
	$.ajax({
		url: "http://book.interpark.com/api/bestSeller.api?key=interpark&categoryId=100",
		data: { output:"json"},
		success: function(data) {
			$("#book div").html(data);
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
	
}

function showDeleteImgPath(evt) {
	var ord =  evt.keyCode;
	//alert(ord + "????");
	if ( ord == 8 || ord == 46 ) { // ord는 delete키 키코드 번호
		
		var capturetags;
		var reg = /<(?:img)[^>]*(src)?=.*\/(.*\.(?:jpg|jpeg|gif|bmp|png))[^<]*>/i;
		var delimgfile = "";
		var url;
	
		var hTextRange = je_doc.getSelection();
		var range = je_doc.createRange();
		hTextRange.addRange(range);
		je_doc.execCommand("Copy");
		
	}
} // if ( capturetags != "" )

function requestWlocationList() {

	$.ajax({
		url : "wloc.do",
		success:function(data){
            callbackWlocationList(data);
		}
		
	});
}

function callbackWlocationList(data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	var lat, lon;
	var icon;
	
	//for (var i in jsonArr.list) {
	for (var i=7 ; i==7 ; i++) {
		
		icon = wstateIcon(requestWeatherStatus(lat = jsonArr.list[i].lat, lon = jsonArr.list[i].lon));
		
		var overlay = new CustomOverlay({
	        position: new naver.maps.LatLng(lat, lon)
	    });
		
		//je_doc.body.focus(); je_doc.execCommand(\'InsertImage\', false, \'images/weatherIcons/' + icon + '\');
		overlay._element.html('<a href="javascript:wiconInnerHTML(\'' + icon + '\');">'
				+ '<div style="position:absolute;left:0;top:0;width:25%;background-color:white;font-size:50%;text-align:center;">'
                + '<span style="font-weight: bold;">'
                + decodeURIComponent(jsonArr.list[i].city) + '</span><br>'
                + "<img id='weatherIconClose' src='images/weatherIcons/" + icon + "' width='60%'>"
                + '<br>' + temperature.substring(0,2) + '℃</div>');
	    overlay.setMap(map);
	}
	
	
}

function  wiconInnerHTML(icon) {
	je_doc.getElementById('weathertd').innerHTML = "<img src='images/weatherIcons/" + icon +"' width='50%'>";

			$("#weatherDiv").hide();

	
}

function requestWeatherStatus(lat, lon) {

	var status;
	
	$.ajax({
		async:false,
		url : "weather.do",
		data: {lat: lat, lon: lon},
		success:function(data){
			status = callbackWeatherStatus(data);
		}
		
	});
	
	return status;
}

function callbackWeatherStatus(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	temperature = jsonArr.weather.minutely[0].temperature.tc
	
	return decodeURIComponent(jsonArr.weather.minutely[0].sky.name);
	
	
	/*
	{"weather":{"minutely":[{"station":{"longitude":"126.9657900000","latitude":"37.5714100000","name":"서울","id":"108","type":"KMA"},
											"wind":{"wdir":"222.70","wspd":"2.70"},
											"precipitation":{"sinceOntime":"0.00","type":"0"},
											"sky":{"code":"SKY_A01","name":"맑음"},
											"rain":{"sinceOntime":"0.00","sinceMidnight":"0.00","last10min":"0.00","last15min":"0.00","last30min":"0.00","last1hour":"0.00","last6hour":"0.00","last12hour":"0.00","last24hour":"0.00"},
											"temperature":{"tc":"24.30","tmax":"27.00","tmin":"15.00"},
											"humidity":"31.50",
											"pressure":{"surface":"996.50","seaLevel":"1006.20"},
											"lightning":"0",
											"timeObservation":"2017-06-03 17:32:00"}]},
		"common":{"alertYn":"Y","stormYn":"N"},
		"result":{"code":9200,"requestUrl":"/weather/current/minutely?lon=126.965891&lat=37.5713793&version=1","message":"성공"}
	}
	*/
}

function wstateIcon(status) {
	
	var icon;
	
	switch(status) {
	case "맑음":
		icon = "01.png";
		break;
	case "구름조금":
		icon = "02.png";
		break;
	case "구름많음":
		icon = "03.png";
		break;
	case "구름많고 비":
		icon = "12.png";
		break;
	case "구름많고 눈":
		icon = "13.png";
		break;
	case "구름많고 비 또는 눈":
		icon = "14.png";
		break;
	case "흐림":
		icon = "17.png";
		break;
	case "흐리고 비":
		icon = "21.png";
		break;
	case "흐리고 눈":
		icon = "32.png";
		break;
	case "흐리고 비 또는 눈":
		icon = "04.png";
		break;
	case "흐리고 낙뢰":
		icon = "29.png";
		break;
	case "뇌우, 비":
		icon = "26.png";
		break;
	case "뇌우, 눈":
		icon = "27.png";
		break;
	case "뇌우, 비 또는 눈":
		icon = "28.png";
		break;
	}
	
	return icon;
}

function insertTable(nrow, ncol) {;
	if (nrow == "" || ncol == "" || nrow < 1 || ncol < 1) {
		if (nrow == "" && ncol == "")
			alert("행 수와 열 수를 입력해주세요!");
		else if (nrow == "")
			alert("행 갯수를 입력해주세요! ");
		else if (ncol == "")
			alert("행 갯수를 입력해주세요! ");
		else
			alert("1 이상의 값만 입력해주세요");
		return false;
	}
	
	var strTemporaryID = Math.random().toString();
	// 새로운 ID를 부여해주지 않으면,
	//테이블을 하나만 삽입할때는 별다른 이상이 없으나
	//2개, 3개 삽입할 경우 먼저번 삽입된 테이블 객체와 새로 삽입될 테이블 객체를 구분 못해 에러 발생
	
	var temp;
	
	var table = je_doc.createElement("table");
	var strTableID = Math.random().toString();
	
	je_doc.body.focus();
	je_doc.execCommand("InsertHorizontalRule", "", strTemporaryID);
	// hr 삽입 (임시객체)
	temp = je_doc.getElementById(strTemporaryID);
	
	table.id = strTableID;
	table.border = "1";
	table.cellSpacing = "0";
	table.width = "100%";
	je_doc.body.replaceChild(table, temp);
	
	for (var i=0; i<nrow; i++) {
		var tr = table.insertRow();
		for (var j=0; j<ncol; j++) tr.insertCell();
	}

	return true;

}

function allowDrop(ev) {
	ev.preventDefault(); // 요소 위에 다른 요소가 올 수 있도록 허용 
}

function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
	// 드래그 시 클릭된 img의  text 형식 id값 가져옴 
	
}

function drop(ev) {
	var id = ev.dataTransfer.getData("text");
	//ev.preventDefault(); // 요소 위에 다른 요소가 올 수 있도록 허용
	ev.target.appendChild(document.getElementById(id));
}

function newsSearch() {

	$.ajax({
		url:"newssearch2.do",
		data: {keyword : $('#newsSearchText').val() },
		success:function(data){
            callbackNewsSearch(data);
		}
	});
}

function callbackNewsSearch(data) {

    var jsonObj = JSON.stringify(data);
    var jsonArr = JSON.parse(jsonObj);

    var tr, td, div, a;
    
    var title, description, originallink, pubDate;
    
    for (var i in jsonArr.list) {

       
       title = decodeURIComponent((jsonArr.list[i].title).replace(Ca, " "));
       description = decodeURIComponent((jsonArr.list[i].description).replace(Ca, " "));
       originallink = jsonArr.list[i].originallink;
       pubDate = jsonArr.list[i].pubDate;
    
       tr = document.createElement("tr");
       td = document.createElement("td");
       
       div = document.createElement("div");
       div.style.border = "1px solid #E6E6E6";
       div.style.margin = "5px";
       div.style.padding = "7px";
       
       a = document.createElement("a");
       a.style.display = 'block';
       a.style.marginBottom = '5px';
       a.href = "javascript:recieveNews(\" "+ title +"\", \""+originallink+"\", \" "+description+"\",\" "+pubDate+"\");";
       a.innerHTML = (parseInt(i, 10) + 1) + ". " + title;
       div.appendChild(a);
       
       a = document.createElement("a");
       a.href = "javascript:recieveNews(\" "+ title +"\", \""+originallink+"\", \" "+description+"\",\" "+pubDate+"\");";
       a.innerHTML ="<ul><li>"+ description+"</li></ul>";
       div.appendChild(a);
       
       td.appendChild(div);
       tr.appendChild(td);
       document.getElementById("newSearchRes").appendChild(tr);
       
       
      //jsonArr.list.originallink;
      //jsonArr.list.pubDate;
    }
}

function bookSearch() {

	$.ajax({
		url:"booksearch2.do",
		data: {keyword : $('#bookSearchText').val() },
		success:function(data) {
			callbackBookSearch(data);
		}
	});
}

function callbackBookSearch(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	var tr, td, a;

	var image, title, author, publisher, pubdate;

	for (var i in jsonArr.list) {
     
		image = decodeURIComponent((jsonArr.list[i].image).replace(Ca, " "));
		title = decodeURIComponent((jsonArr.list[i].title).replace(Ca, " "));
		author = decodeURIComponent((jsonArr.list[i].author).replace(Ca, " "));
		publisher = decodeURIComponent((jsonArr.list[i].publisher).replace(Ca, " "));
		pubdate = jsonArr.list[i].pubdate;

		tr = document.createElement("tr");
		td = document.createElement("td");
		td.rowSpan="4";
		a = document.createElement("a");
		a.href = "javascript:recieveBook('" + image + "','" + title + "', '" + author + "','" + publisher + "','" +  pubdate + "');";
		a.innerHTML = "<img src='" + image + "'>";
     
		td.appendChild(a);
		tr.appendChild(td);
     
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveBook('" + image + "','" + title + "', '" + author + "','" + publisher + "','" +  pubdate + "');";
		a.innerHTML = title;
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("bookSearchRes").appendChild(tr);
    
		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveBook('" + image + "','" + title + "', '" + author + "','" + publisher + "','" +  pubdate + "');";
		a.innerHTML =author;
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("bookSearchRes").appendChild(tr);
     
		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveBook('" + image + "','" + title + "', '" + author + "','" + publisher + "','" +  pubdate + "');";
		
		a.innerHTML =publisher;
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("bookSearchRes").appendChild(tr);
     
		tr = document.createElement("tr");
		td = document.createElement("td");
		a = document.createElement("a");
		a.href = "javascript:recieveBook('" + image + "','" + title + "', '" + author + "','" + publisher + "','" +  pubdate + "');";
		a.text =pubdate;
		td.appendChild(a);
		tr.appendChild(td);
		document.getElementById("bookSearchRes").appendChild(tr);
   
         
		//jsonArr.list.originallink;
		//jsonArr.list.pubDate;
	}
}

function show(star) {
	
	var i;
	var image;
	var el;
	var stateMsg;
	var e = document.getElementById('startext');
	
	if(locked) return;
   
	for(i=1 ; i<= star; i++) {
		image="image"+i;
		el = document.getElementById(image);
		el.src="images/star1.png";
	}
   
	switch(star){
	case 1 :
		stateMsg = "괜히봤어요.";
		break;
	case 2 :
		stateMsg = "기대하진 말아요";
		break;
	case 3 :
		stateMsg = "무난했어요";
		break;
	case 4 :
		stateMsg = "기대해도 좋아요!";
		break;
	case 5 : 
		stateMsg = "너무 멋져요!";
		break;
	default :
		stateMsg = "";
	}
   
	e.innerHTML = stateMsg;
   
}

function noshow(star) {
	
	var i;
	var image;
	var el;

	if(locked) return;
	
	for(i=1; i <= star; i++) {
		image="image"+i;
		el = document.getElementById(image);
		el.src="images/notStar.png";
	}
}

function lock(star) {
	show(star);
	locked = 1;
}

function mark(star) {
	lock(star);
	alert("선택2"+star);
	
	for(var i=1; i<=star; i++)
		je_doc.body.innerHTML += "<img src='images/star1.png' width='50px;'>";
	
	je_doc.body.innerHTML += stateMsg;
}
