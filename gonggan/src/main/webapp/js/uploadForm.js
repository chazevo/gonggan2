
$(function() {
	$( "#toDate" ).datepicker({
		inline: true,
		dateFormat: "yy-mm-dd",    /* 날짜 포맷 */
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
/*-------------------------------------------------------- */
function tagaddfunc(){
	
	if($("#tagaddtext").val() != "" ) {
		$("#tagview").html($("#tagview").html() + "<span>" + $("#tagaddtext").val()
				+ " <a href='#' onclick='deleteTag(this);'><img src='images/x_icon.png' width='15px'></a>&nbsp;</span>");
	}
	$("#tagaddtext").val("");
	
}

function deleteTag(obj){
	
	var span = obj.parentNode;
	var td = span.parentNode;
	
	td.removeChild(span);
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
var je_doc={};

function run(){
	
	je_doc = document.getElementById('editor').contentWindow.document;
	je_doc.designMode="on";
	
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
	var clr = new Array('00', '20', '40', '60', '80', 'a0', 'c0', 'ff');
	
	var table;
	var tr;
	var td;
	
	for (var i=0 ; i<8 ; i++) { 
		
		table = document.createElement("table");
		//table.width = '100%';
		table.border = '1';
		table.cellpadding = '8';
		//html($("#colorchart").html() + "<table width='100%' border='1' cellpadding='8'>");

		for (var j=0 ; j<8 ; j++) {
			tr = document.createElement("tr");
			//$("#colorchart").html($("#colorchart").html() + '<tr>');
			
			for (var k=0 ; k<8 ; k++) {
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
	if (confirm("포스트를 게시하시겠습니까? ") == true)
		;
	else return;
}

function cancel() {
	if (confirm("포스팅을 취소하시겠습니까? ") == true)
		;
	else return;
}