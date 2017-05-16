var postId;
var Ca = /\+/g;
var str = "";
var blogOwner = "jieun";

function visit() {
	if (loginUser != "")
		$.ajax({
		      url: "/gonggan/blogvisit.do",
		      data: {blogOwner : blogOwner,
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
	      data: {},
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
					+ "<a data-fancybox data-src='postDetail.do?postId=" + postId + "&writerId=" + jsonArr.list[j].writerId + "'>"
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

	$("#todayHeader").hide();
	$('.CalendarHeader').hide();
	$('#listbody').removeClass("cal");
	
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);
	
	for (var j in jsonArr.list){
		postId = jsonArr.list[j].postId;
		document.getElementById("listbody").innerHTML += "<tr><td colspan='7'>"
			+ "<img "
			+ ( jsonArr.list[j].photoPath == imgVal ? "" : ("' src='/gonggan/uploadImages/" + jsonArr.list[j].photoPath) ) + "'>" + "</td></tr>";
		reqCommentList(postId);
	}
	
	/*
	$.ajax({
	      url: "/gonggan/postlist.do",
	      data: {},
	      success: function(data) {
	    	  commentList(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
	  */

}

function reqCommentList(postId) {

	var jsonObj;
	var jsonArr;
	
	$.ajax({
	      url: "/gonggan/colist.do",
	      data: {postId : postId},
	      success: function(data) {
	    	  callbackCommentList(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}

function callbackCommentList(data){

	jsonObj = JSON.stringify(data);
	jsonArr = JSON.parse(jsonObj);

	var tr = document.createElement("tr");
	var td = document.createElement("td");
	
	td.innerHTML = "<a href='javascript:moreComment();'>더보기</a>";
	td.class = "moreComment";
	td.colspan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);

	for (var j in jsonArr.list){
	    tr = document.createElement("tr");
		td = document.createElement("td");
		td.innerHTML = "<a href=''>" + jsonArr.list[j].writerId +"</a>";
	    tr.appendChild(td);
	    td = document.createElement("td");
		td.innerHTML = decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " "));
		td.colspan = "6";
	    tr.appendChild(td);
	    document.getElementById("listbody").appendChild(tr);
	}

    tr = document.createElement("tr");
	td = document.createElement("td");
	td.innerHTML = "<label class='checkbox-wrap'><input type='checkbox' id='' onclick='like();'><i class='like-icon'></i></label>&nbsp;"
	+ "<input id='commentTb' type='text' placeholder='댓글 달기'>&nbsp;"
	+ "<a href='javascript:sendComment();'><img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;"
	+ "<a href='javascript:dotdotdot();'><img class='smallIcon2' src='images/thesee_icon.png'></a></td></tr>";
	td.colspan = "7";
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);
	
    /*
	//document.getElementById("listbody").innerHTML +=
	str =
		"<tr><td class='moreComment' colspan='7'><a href='javascript:moreComment();'>더보기</a></td></tr>"
		+ "<tbody id='commentTbody'>";

	for (var j in jsonArr.list){
		//document.getElementById("listbody").innerHTML +=
		str +=
			"<tr><td><a href=''>" +jsonArr.list[j].writerId  +"</a></td>"
			+ "<td colspan='6'>" + decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " "))
			+ "</td></tr>";
	}
	*/

	/*
	//document.getElementById("listbody").innerHTML += 
	str += "</tbody>"
		+ "<tr><td colspan='7'>"
		+ "<label class='checkbox-wrap'><input type='checkbox' id='' onclick='like();'><i class='like-icon'></i></label>&nbsp;"
		+ "<input id='commentTb' type='text' placeholder='댓글 달기'>&nbsp;"
		+"<a href='javascript:sendComment();'><img  src='images/dettext_icon.png' width='45px' ></a>&nbsp; &nbsp;"
		+"<a href=''><img class='smallIcon2' src='images/thesee_icon.png'></a></td></tr>";
	*/

}

function sendComment() {
	alert("코멘트");
}

function moreComment() {
	
	alert("더");
	
	var tr = document.createElement("tr");
	var td = document.createElement("td");
	
	td.innerHTML = "안녕";

    tr.appendChild(td);
    document.getElementById("commentTbody").appendChild(tr);
	
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