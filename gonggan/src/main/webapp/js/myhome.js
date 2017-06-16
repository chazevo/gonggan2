var postId;
var category = "all";
var Ca = /\+/g;
var str = "";
var rownum = 1;

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

function checkAlarm() {
	if (loginUser != "")
		$.ajax({
			url: "/gonggan/checkalarm.do",
			data: {loginUser : loginUser
			},
			success: function(data) {
				if (data > 0)
					alert("모든 알람 확인 완료!");
			},
			error: function(data,status,error) {
				console.log("error : " + error);
			}
		});
}

function visit() {
	if (loginUser != writer_id)
		$.ajax({
			url: "/gonggan/bvisit.do",
			data: {blog_id : blog_id,
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

function requestCategoryList(category, val) {
	
	var rownum2;
	
	if (maxRownum - val < 8)
		rownum2 = maxRownum;
	else rownum2 = rownum + 7;
	
	alert(val + ", " + rownum2);
	$.ajax({
				url: "/gonggan/postlist.do",
				//url: "/gonggan/userpostlist.do",
				data: { writer_id : writer_id,
					rownum: val, rownum2: rownum2,
					category: category
				},
				success: function(data) {
					callbackList2(data);
					rownum = rownum2+1;
				},
				error: function(data,status,error){
					console.log("error : " + error);
				}
	   });
}

function drawCalendar(firstday) {

	var str;
	
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
	
	return str;
}

function callbackList(data) {

	var flag;
	var str;
	var week = 1;
	var cnt = firstday - 1;
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$('.CalendarHeader').show();
	$("#todayHeader").show();
	$('#listbody').addClass("cal");
	
	str = drawCalendar(firstday);
	
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);

	for (var i=1 ; i<=lastdate ; i++) {
		
		flag = false;
		cnt++;
		
		for (var j in jsonArr.list){
			//jsonArr.list[i].imagePath;
			//alert(jsonArr.list[i].year + "년 " + jsonArr.list[i].month + "월 " + jsonArr.list[i].date + "일 ");
			
			if (jsonArr.list[j].date == i) {
				
				postId = jsonArr.list[j].postId;
				
				str += "<td class='" + (jsonArr.list[j].date == today ? "today" : "") +  "'>"
					+ "<a data-fancybox data-src='pdetail.do?postId=" + postId + "&writerId=" + jsonArr.list[j].writerId + "'>"
					+ ( jsonArr.list[j].photoPath == imgVal ?
							"<span style='color:pink;font-weght:bold;vertical-align:top;'>" + i + "</span>" : "")
					+ (jsonArr.list[j].photoPath == imgVal ? "" :
						"<img height='100%' "
						+ " src='/gonggan/uploadImages/" + jsonArr.list[j].photoPath + "'>")
					+ "</a></td>";
				flag = true;
				break;
			}
		}
		
		if (flag == false) str += "<td>" + i + "</td>";
		
		//document.getElementById("listbody").innerHTML += str;
		// innerHTML 에 태그가 들어가면 자동으로 닫는태그 삽입됨 
		
		//if (document.getElementById("listbody").children[week-1].children.length == 7) {
		if (cnt == 7) {
			str += "</tr><tr>";
			week++;
			cnt = 0;
		}
	}
	
	//while (document.getElementById("listbody").children[week-1].children.length <= 7) {
	if (cnt != 0)
		while (cnt < 7) {
	
			str += "<td></td>";
			cnt++;
		}
	str += "</tr>";
	document.getElementById("listbody").innerHTML += str;
	
}


function callbackList2(data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);	

	var tr;
	var td;
	
	$("#todayHeader").hide();
	$('.CalendarHeader').hide();
	$('#listbody').removeClass("cal");
	
	/*
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);
	*/
	
	for (var j in jsonArr.list){
		
		postId = jsonArr.list[j].postId;
		
		/*
		tr = document.createElement("tr");
		td = document.createElement("td");

		td.innerHTML = "<img "
			+ ( jsonArr.list[j].photoPath == imgVal ? "" : ("' src='/gonggan/uploadImages/" + jsonArr.list[j].photoPath) ) + "'>" + "</td></tr>";
		td.colSpan="7";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
		*/

		tr = document.createElement("tr");
		td = document.createElement("td");

		td.innerHTML = decodeURIComponent((jsonArr.list[j].content).replace(Ca, " "));
		td.className = "contentArea"
		td.colSpan="7";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);

		if (hide_comment == "N")
			reqCommentList(postId);
		
	}
	if (rownum >= maxRownum) {
		$("#div_Loading").html("더이상 포스트가 존재하지 않습니다.");
		$("#div_Loading").show();
		return;
	}
	$("#div_Loading").fadeOut(100);
}

function reqCommentList(postId) {
	 var rownum2;

	 /*
	   if (maxRownum - val < 8)
	      var rownum2 = maxRownum;
	   else rownum2 = rownum + 7;
	   */

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
	td.className = "commentArea";
	td.style.paddingTop = "20px";
	td.colSpan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);

	for (j=0 ; j<3 ; j++){
		if (j > jsonArr.list.length - 1) break;
	    tr = document.createElement("tr");
		td = document.createElement("td");
		td.className = "commentArea";
		td.colSpan = "7";
		td.innerHTML = "<a href='myhome.do?writer_id=" + jsonArr.list[j].writerId + "'><b>"
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
	td.className = 'divisionPadding commentArea';
	td.style.paddingBottom = "20px";
	td.innerHTML = "<label class='checkbox-wrap'>"
	+ "<input type='checkbox' id='' onclick='like();'><i class='like-icon'></i></label>&nbsp;"
	+ "<input id='commentTb' type='text' placeholder='댓글 달기' "
	+ "onkeyup='if( event.keyCode==13 ) sendComment(postId, $(this).parent());'>&nbsp;"
	+ "<a href='javascript:void(0);' onclick='sendComment(postId, $(this).parent());'>"
	+ "<img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;"
	+ "<a href='javascript:void(0);' onclick='dotdotdot($(this));'>"
	+ "<img class='smallIcon2' src='images/thesee_icon.png'></a>"
	+ "<div class='dotdotdotDiv right'>"
	+ "<a class='hover dotdotdot' href=''>부적절한 컨텐츠 신고</a>"
	+ "<a class='hover dotdotdot' href='' >공유</a>"
	+"<a class='hover dotdotdot' onclick='javascript:postdelete(postId,loginUser);'>게시글 삭제하기</a>"
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

function sendComment(postId, parent, loginUser) {

	if (parent.children("input").val() == "") {
		alert("내용을 입력해주세요 ");
	}
	else {
		
		
		$.ajax({
		      url: "/gonggan/coinsert.do",
		  data: {writer_id:loginUser,
			  	 postId:postId,
			  	comment_content:parent.children("input").val()
			  	},
		  success: function(data) {
			  parent.parent().before("<tr><td class='commentArea'><a href='myhome.do?writer_id="+loginUser+"'><b>"
					  +loginUser+"</b></a>&nbsp;"+ parent.children("input").val()+"&nbsp;</td></tr>");
			  alert(postId);
		
		  },
		  error: function(data,status,error){
		     console.log("error : " + error);
		  }
		});

	}
}

function moreComment(postId, obj, data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	
	var tr = document.createElement("tr");
	var td = document.createElement("td");

	for(var i=0; i< 4; i++){
		if (j > jsonArr.list.length - 1) break;
    tr.appendChild(td);
    obj.after("<tr><td colspan='7' class='commentArea'><a href='myhome.do?writer_id=" + jsonArr.list[j].writerId + "'><b>"
    		+ decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " "))+
    		"&nbsp;<abbr class='timeago' title='" + jsonArr.list[j].commentDate + "'>"+ jsonArr.list[j].commentDate + "</abbr></td></tr>");
	}
	
}


function postdelete(postId, loginUser) {
	if (confirm("정말 삭제하시겠습니까?") == false)
		return;
	else
		$.ajax({
			url: "/gonggan/pdelete.do",
			data: {postId:postId,
				loginUser:loginUser
			},
			success: function(data) {
				alert("삭제 되었습니다.");
			},
			error: function(data,status,error){
				console.log("error : " + error);
			}
		});
}

function lastMonth() {

	var date = new Date();
	var currdate = new Date();
	var currdate_date = currdate.getDate();

	if (month-1 == currdate.getMonth() + 1) {
		date.setDate(1);
		firstday = date.getDay() + 1;
		today = currdate_date;
		month--;
	}
	
	else if ((currdate.getMonth() + 1) != month-1) {
		
		if (month-1 > 0) {
			date.setMonth(--month - 1);
			date.setDate(1);
		}
		
		firstday = date.getDay() + 1;
		today = 99;
		
	}
	
	lastdate = new Date(2017, month-1, 0).getDate();
	
	$("#year").text(year);
	$("#month").text(month<10 ? "0" + month : month);
	$("#today").text((currdate.getMonth() + 1) == month ? 
			(currdate_date<10 ? "0" + currdate_date : currdate_date) : "");
	
	requestCalList(year, month);
}

function nextMonth() {
	var date = new Date();
	var currdate = new Date();
	var currdate_date = currdate.getDate();

	if (month + 1 == currdate.getMonth() + 1) {
		date.setDate(1);
		firstday = date.getDay() + 1;
		today = currdate_date;
		month++;
	}
	
	else if ((currdate.getMonth() + 1) > month + 1) {
		
		if (month+1 <= 12) {
			date.setMonth(month++);
			date.setDate(1);
		}
		firstday = date.getDay() + 1;
		today = 99;
	}

	lastdate = new Date(2017, month-1, 0).getDate();
	
	$("#year").text(year);
	$("#month").text(month<10 ? "0" + month : month);
	$("#today").text((currdate.getMonth() + 1) == month ? 
			(currdate_date<10 ? "0" + currdate_date : currdate_date) : "");
	
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

function Neig() {
	alert(loginUser + "loginUser" + writer_id + "writer_id");
	$.ajax({
		url: "/gonggan/neighyn.do",
		data: {loginUser : loginUser,
			writer_id: writer_id
		},
		success: function(data) {
			reqNeig(data);
		},
		error: function(data,status,error) {
			console.log("error : " + error);
		}
	});
}

function reqNeig(data) {

	var obj = $(".blogOwnerClick>div").children("a:nth-child(1)");

	if (data=="N") {
		if (obj.text() == "이웃 신청") {
			likeNeigh(loginUser,writer_id);
			obj.text("신청 취소");
		}
	}
	else if (data == "Y") {
		if (obj.text() == "신청 취소") {
			rejectNeig(loginUser,writer_id);
			obj.text("이웃 신청");
		}
	}
}

function likeNeigh(loginUser,writer_id) {
	$.ajax({
		url: "/gonggan/nrequest.do",
		data: {member_id1 : loginUser,
			member_id2: writer_id
		},
		success: function(data) {
			alert("친구 요청 성공");
		},
		error: function(data,status,error) {
			console.log("error : " + error);
		}
	});
	
}
	
function rejectNeig(loginUser,writer_id) {
	if (confirm("정말 이웃신청을 삭제하시겠습니까? ") == true) {
		$.ajax({
			url: "/gonggan/neigdelete.do",
			data: {member_id: loginUser,member_id2: writer_id},
			success: function(data) {
				reqRejectNeig(data);
			},
			error: function(data,status,error) {
				console.log("error : " + error);
			}
		});
	} else return;
}

function reqRejectNeig(data) {

	var obj = $(".blogOwnerClick>div").children("a:nth-child(1)");

	if (data=="N") {
		if (obj.text() == "이웃 신청") {
			likeNeigh(loginUser,writer_id);
			obj.text("신청 취소");
		}
	}
	else if (data == "Y") {
		if (obj.text() == "신청 취소") {
			rejectNeig(loginUser,writer_id);
			obj.text("이웃 신청");
		}
	}
}


