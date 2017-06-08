var postId;
var Ca = /\+/g;
var str = "";

/*function trace(loginUser) {
	$.ajax({
	      url: "/gonggan/trace.do",
	      data: {loginUser:loginUser},
	      success: function(data) {
	    		  callbacktrace(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}

function callbacktrace(data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);	

	var tr;
	var td;
	
	
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);
	
	for (var j=0 ; j<7 ; j++){
		
		loginUser = jsonArr.list[j].loginUser;
		
		tr = document.createElement("tr");
		td = document.createElement("td");
		//document.getElementById("listbody").innerHTML += "<tr><td colSpan='7'>"

		td.innerHTML = " <a data-fancybox data-src='pdetail.do?postId="+jsonArr.list[j].postId+"&writerId=" + jsonArr.list[j].postWriter +"'>"
		+ decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " ")) +"</a><b>댓글</b>을 남기셨습니다. " ;
		tr.appendChild(td);
		document.getElementById("listbody_mytrace").appendChild(tr);
		
		if (jsonArr.list.length-1 == j) break;
		
	}

}*/
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

function requestCalList(year, month) {
	$.ajax({
		url: "/gonggan/calpostlist.do",
		data: { writer_id : writer_id,
			year:year, month:month
		},
		success: function(data) {
			callbackList(data);
		}
	});
}

function requestList() {
	
	$.ajax({
				url: "/gonggan/postlist.do",
				//url: "/gonggan/userpostlist.do",
				data: { writer_id : writer_id,
					rownum: 1, rownum2: 1,
					category: "all"
				},
				success: function(data) {
					callbackList2(data);
				},
				error: function(data,status,error) {
					console.log("error : " + error);
				}
	});
}

function requestCategoryList(category) {

	$.ajax({
				url: "/gonggan/postlist.do",
				//url: "/gonggan/userpostlist.do",
				data: { writer_id : writer_id,
					rownum: 1, rownum2: 1,
					category: category
				},
				success: function(data) {
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

		td.innerHTML = "<img "
			+ ( jsonArr.list[j].photoPath == imgVal ? "" : ("' src='/gonggan/uploadImages/" + jsonArr.list[j].photoPath) ) + "'>" + "</td></tr>";
		td.colSpan="7";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);

		tr = document.createElement("tr");
		td = document.createElement("td");

		td.innerHTML = decodeURIComponent((jsonArr.list[j].content).replace(Ca, " "));
		td.colSpan="7";
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
	
	td.innerHTML = "<a href='javascript:void(0);' onclick='moreComment(" + postId + ", $(this).parent().parent());'>"
			+ "더보기</a>";
	td.className = "moreComment";
	td.colSpan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);

	for (j=0 ; j<3 ; j++){
		if (j > jsonArr.list.length - 1) break;
	    tr = document.createElement("tr");
		td = document.createElement("td");
		td.colSpan = "7";
		td.innerHTML = "<a href='selectBlog.do?writer_id=" + jsonArr.list[j].writerId + "'><b>"
			+ jsonArr.list[j].writerId +"</b></a>&nbsp;"
			+ decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " "))
			+ "&nbsp;<abbr class='timeago' title='" + jsonArr.list[j].commentDate + "'>"
	    		+ jsonArr.list[j].commentDate + "</abbr>";
		tr.appendChild(td);
		//tbody.appendChild(tr);
		document.getElementById("listbody").appendChild(tr);
	}
	//document.getElementById("listbody").appendChild(tbody);

    tr = document.createElement("tr");
	td = document.createElement("td");
	td.className = 'divisionPadding';
	td.innerHTML = "<label class='checkbox-wrap'>"
	+ "<input type='checkbox' id='' onclick='like();'><i class='like-icon'></i></label>&nbsp;"
	+ "<input id='commentTb' type='text' placeholder='댓글 달기' "
	+ "onkeyup='if( event.keyCode==13 ) sendComment(postId);'>&nbsp;"
	+ "<a href='javascript:sendComment(postId);'>"
	+ "<img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;"
	+ "<a href='javascript:void(0);' onclick='dotdotdot($(this));'>"
	+ "<img class='smallIcon2' src='images/thesee_icon.png'></a>"
	+ "<div class='dotdotdotDiv right'>"
	+ "<a class='hover dotdotdot' href=''>부적절한 컨텐츠 신고</a>"
	+ "<a class='hover dotdotdot' href='' >공유</a>"
	+ "<a data-fancybox data-src='messageList.do?"
	+ "memberId1=" + loginUser + "&memberId2=" + writer_id
	+ "' class='hover dotdotdot fb'>"
	+ "쪽지 보내기</a></div></td></tr>";
	td.colSpan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);

	jQuery("abbr.timeago").timeago();

	$(".fb").fancybox({
  	  //'modal' : true,
  	  //'openEffect' : 'none',
  	  //'closeEffect' : 'none',
  	  //'scrolling' : false,
  	  'autoSize':false,
  	  'closeBtn' : false,
  	  'fullScreen' : false
  	 });
	
}

function sendComment(postId) {
	alert(postId + " 코멘트");
}

function moreComment(postId, obj) {
	
	var tr = document.createElement("tr");
	var td = document.createElement("td");
	
	td.innerHTML = postId + "의 댓글 더 보기 ";
	td.colSpan = "7";

    tr.appendChild(td);
    obj.after("<tr><td colspan='7'>" + postId + "의 댓글 더 보기</td></tr>");
	
}

function lastMonth() {
	
	var date = new Date();
	var date2 = new Date();
	
	if (month-1 > 0) {
		date.setMonth(--month);
		date.setDate(1);
	}
	
	firstday = date.getDay();
	today = 99;
	lastdate = new Date(2017, month, 0).getDate();
	
	$("#year").text(year);
	$("#month").text(month<10 ? "0" + month : month);
	$("#today").text((date2.getMonth() + 1) == month ? date2.getDate() : "");
	
	requestCalList(year, month);
}

function nextMonth() {
	
	var date = new Date();
	var date2 = new Date();

	if (month+1 <= 12) {
		date.setMonth(++month);
		date.setDate(1);
	}
	
	firstday = date.getDay();
	today = 99;
	lastdate = new Date(2017, month, 0).getDate();

	$("#year").text(year);
	$("#month").text(month<10 ? "0" + month : month);
	$("#today").text((date2.getMonth() + 1) == month ? date2.getDate() : "");
	
	requestCalList(year, month);
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

function dotdotdot(obj){
	
	if (obj.next().css('display') == "block")
		obj.next().css("display", "none");
	else {
		$(".dotdotdotDiv").css("display", "none");
		obj.next().css("display", "block");
	}
}

function reqNeig() {
	
	var obj = $(".blogOwnerClick>div").children("a:nth-child(1)");
	
	if (obj.text() == "이웃 신청") {
		obj.text("신청 취소");
	}
	
	 else if (obj.text() == "신청 취소") {
		obj.text("이웃 신청");
	}
}