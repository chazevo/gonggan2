var postId;
var Ca = /\+/g;
var str = "";

function visit() {
	if (loginUser != "" || loginUser != writer_id)
		$.ajax({
		      url: "/gonggan/bvisit.do",
		      data: {writer_id : writer_id,
		    	  visitor_id : loginUser},
		      success: function(data) {
		    	  
		      },
		      error: function(data,status,error){
		         console.log("error : " + error);
		      }
		   });
}

function requestList() {
	
	$.ajax({
				url: "/gonggan/postlist.do",
				//url: "/gonggan/userpostlist.do",
				data: { writer_id : writer_id },
				success: function(data) {
	    	  if (document.getElementById("calendar").checked == true )
	    		  callbackList(data);
	    	  else if (document.getElementById("calendar").checked == false )
	    		  callbackList2(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}

function callbackList(data) {

	var flag;
	var str;
	var week = 1;

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$('.CalendarHeader').show();
	$("#todayHeader").show();
	$('#listbody').addClass("cal");
	
	switch (firstday) {
	case 1: 
		str = "<tr>";
		break;
	case 2:
		str = "<tr><td></td>";
		break;
	case 3:
		str = "<tr><td></td><td></td>";
		break;
	case 4:
		str = "<tr><td></td><td></td><td></td>";
		break;
	case 5:
		str = "<tr><td></td><td></td><td></td><td></td>";
		break;
	case 6:
		str = "<tr><td></td><td></td><td></td><td></td><td></td>";
		break;
	case 7:
		str = "<tr><td></td><td></td><td></td><td></td><td></td><td></td>";
		break;
	}
	
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);

	for (var i=1 ; i<=lastdate ; i++) {
		flag = false;
		
		for (var j in jsonArr.list){
			//jsonArr.list[i].imagePath;
			//alert(jsonArr.list[i].year + "년 " + jsonArr.list[i].month + "월 " + jsonArr.list[i].date + "일 ");
			
			if (jsonArr.list[j].date == i) {
				
				postId = jsonArr.list[j].postId;
				
				str += "<td>"
					+ "<a data-fancybox data-src='pdetail.do?postId=" + postId + "&writerId=" + jsonArr.list[j].writerId + "'>"
					+ "<img width='40px' class='" + (jsonArr.list[j].date == today ? "today" : "")
					+ ( jsonArr.list[j].photoPath == imgVal ? "" : ("' src='/gonggan/uploadImages/" + jsonArr.list[j].photoPath) ) + "'>"
					+ "</a></td>";
				flag = true;
				break;
			}
		}
		
		if (flag == false) str += "<td>" + i + "</td>";
		
		document.getElementById("listbody").innerHTML = str;
		if (document.getElementById("listbody").children[week-1].children.length == 7) {
			str += "</tr><tr>";
			week++;
		}
	}
	
	if (document.getElementById("listbody").children[week-1].children.length < 7)
		do {
			str += "<td></td>";
			document.getElementById("listbody").innerHTML = str;
		} while (document.getElementById("listbody").children[week-1].children.length <= 7);
	str += "</tr>";
	document.getElementById("listbody").innerHTML = str;
	
}


function callbackList2(data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);	

	var tr;
	var td;
	
	$("#todayHeader").hide();
	$('.CalendarHeader').hide();
	$('#listbody').removeClass("cal");
	
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);
	
	for (var j in jsonArr.list){
		
		postId = jsonArr.list[j].postId;
		
		tr = document.createElement("tr");
		td = document.createElement("td");
		//document.getElementById("listbody").innerHTML += "<tr><td colspan='7'>"
		
		td.innerHTML = "<img "
			+ ( jsonArr.list[j].photoPath == imgVal ? "" : ("' src='/gonggan/uploadImages/" + jsonArr.list[j].photoPath) ) + "'>" + "</td></tr>";
		td.colspan="7";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
		
		reqCommentList(postId);
		
	}
}

function reqCommentList(postId) {

	$.ajax({
		async: false,
		//Ajax를 통해 서버측에 데이터를 요청하고, 이 데이터의 결과를 모두 수신 받은 다음 단계로 진행하도록
		url: "/gonggan/colist.do",
		data: {postId : postId},
		success: function(data) {
			callbackCommentList(data, postId);
		},
		error: function(data,status,error){
			console.log("error : " + error);
	      }
	});
}

function callbackCommentList(data, postId){

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	var tr = document.createElement("tr");
	var td = document.createElement("td");
	//var tbody = document.createElement("tbody");
	
	td.innerHTML = "<a href='javascript:moreComment(" + postId + ");'>더보기</a>";
	td.class = "moreComment";
	td.colspan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);

	for (j=0 ; j<3 ; j++){
		if (j > jsonArr.list.length - 1) break;
	    tr = document.createElement("tr");
		td = document.createElement("td");
		td.innerHTML = "<a href='myhome.do?writer_id=" + jsonArr.list[j].writerId + "'>"
			+ jsonArr.list[j].writerId +"</a>";
		td.colspan = "2";
		tr.appendChild(td);
	    td = document.createElement("td");
		td.innerHTML = decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " "));
		td.colspan = "5";
		tr.appendChild(td);
		//tbody.appendChild(tr);
		document.getElementById("listbody").appendChild(tr);
	}
	//document.getElementById("listbody").appendChild(tbody);

    tr = document.createElement("tr");
	td = document.createElement("td");
	td.class = 'divisionPadding';
	td.innerHTML = "<label class='checkbox-wrap'>"
	+ "<input type='checkbox' id='' onclick='like();'><i class='like-icon'></i></label>&nbsp;"
	+ "<input id='commentTb' type='text' placeholder='댓글 달기' "
	+ "onkeyup='if( event.keyCode==13 ) sendComment(postId);'>&nbsp;"
	+ "<a href='javascript:sendComment(postId);'>"
	+ "<img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;"
	+ "<a href='javascript:dotdotdot();'>"
	+ "<img class='smallIcon2' src='images/thesee_icon.png'></a></td></tr>";
	td.colspan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);
    
}

function sendComment(postId) {
	alert(postId + " 코멘트");
}

function moreComment(postId) {
	
	var tr = document.createElement("tr");
	var td = document.createElement("td");
	
	td.innerHTML = postId + "의 댓글 더 보기 ";
	td.colspan = "7";

    tr.appendChild(td);
    
	
}

function lastMonth() {
	
	var date = new Date();
	date.setMonth(--month);
	date.setDate(1);
	
	firstday = date.getDay();
	today = 99;
	lastdate = new Date(2017, month, 0).getDate();
	
	$("#year").text(year);
	$("#month").text(month<10 ? "0" + month : month);
	$("#today").text("");
	
	requestList();
}

function nextMonth() {
	
	var date = new Date();
	date.setMonth(++month);
	date.setDate(1);
	
	firstday = date.getDay();
	today = 99;
	lastdate = new Date(2017, month, 0).getDate();

	$("#year").text(year);
	$("#month").text(month<10 ? "0" + month : month);
	$("#today").text("");
	
	requestList();
}

function checkboxControl(type, object) {
	/*
	if (object.checked == true && type == "list")
		document.getElementById("calendar").check = false;
	else if (object.checked == false && type == "list")
		document.getElementById("calendar").check = true;
	else if (object.checked == true && type == "calendar")
		document.getElementById("list").check = false;
	else if (object.checked == false && type == "calendar")
		document.getElementById("list").check = true;
		
		*/
}

function dotdotdot(){
	
	if (document.getElementById("dotdotdotDiv").style.display == "block")
		$('#dotdotdotDiv').css("display","none");
	else
		$('#dotdotdotDiv').css("display","block");
}