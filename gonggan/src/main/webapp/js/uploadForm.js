var reg = /<(?:img)[^>]*(src)?=.*\/(.*\.(?:jpg|jpeg|gif|bmp|png))[^<]*>/i;
var je_doc={};
var currTimeFormat = 'a/p hh시 mm분 ss초';
var temperature;

$(function() {
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
		buttonImageOnly: true,
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
});


function viewSearchTime() {
	var nowTime = new Date().currTime(currTimeFormat);
	$("#weatherDiv").html($("#weatherDiv").html() + nowTime + " 기준");
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
		"<table align='center' border='1' width='80%'><tr><td rowspan='4' width='30%'>"
		+ "<img src='" + image + "' width='100%'></td>"
		+ "<td>" + title + "</td>"
		+ "<tr><td>감독 "+ director + "</td></tr>" 
		+ "<tr><td>출연  "+ actor + "</td></tr>"
		+"<tr><td> 개봉 "+pubDate+" </td></tr>"
		+ "</td></tr></table>";
}

function recieveNews(title, originallink, description, pubDate) {
	document.getElementById('editor').contentWindow.document.body.innerHTML += 
		"<table align='center' border='1' width='80%'><tr>"
		+ "<td>" + title+"</td></tr>"
		+ "<tr><td>" + description + "</td>"
		+"</tr></table>";
}

function recieveBook(image, title, author, publisher, pubdate) {
	document.getElementById('editor').contentWindow.document.body.innerHTML += 
		"<table align='center' border='1' width='80%'><tr><td rowspan='4' width='30%'>"
		+ "<img src='" + image + "' width='100%'></td>"
		+ "<td>" + title + "</td>"
		+ "<tr><td>저자 "+ author + "</td></tr>" 
		+ "<tr><td>출판  "+ publisher + "</td></tr>"
		+"<tr><td> 발매 "+pubdate+" </td></tr>"
		+ "</td></tr></table>";
}

function recieveMap(image) {
	je_doc.body.focus();
	je_doc.execCommand('InsertImage', 'false', '/gonggan/uploadImages/' + image);
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
				td.class = "colorchartTd";
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
		
		document.getElementById("colorchart2").appendChild(table);
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
function changeTitle() {
	document.getElementById("title").value = document.getElementById("toDate").value;
	alert(document.getElementById("title").value);
}

function changeForm() {
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
			"<input type='text' name='toDate' id='toDate' onchange='javascript:changeTitle()'>";
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

function content_OK() {
	
	console.log(je_doc.body.innerHTML == " ");
	
	if (je_doc.body.innerHTML != "") {
		if (confirm("포스트를 게시하시겠습니까? ") == true)
			;
		else return;
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
	for (var i=0 ; i<1 ; i++) {
		
		icon = wstateIcon(requestWeatherStatus(lat = jsonArr.list[i].lat, lon = jsonArr.list[i].lon));
		
		var overlay = new CustomOverlay({
	        position: new naver.maps.LatLng(lat, lon)
	    });
		
		overlay._element.html('<a href="javascript:je_doc.body.focus(); je_doc.execCommand(\'InsertImage\', false, \'images/weatherIcons/' + icon + '\');">'
				+ '<div style="position:absolute;left:0;top:0;width:25%;background-color:white;font-size:50%;text-align:center;">'
                + '<span style="font-weight: bold;">'
                + decodeURIComponent(jsonArr.list[i].city) + '</span><br>'
                + "<img src='images/weatherIcons/" + icon + "' width='60%'>"
                + '<br>' + temperature.substring(0,2) + '℃</div>');
	    overlay.setMap(map);
	}
	
	
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
