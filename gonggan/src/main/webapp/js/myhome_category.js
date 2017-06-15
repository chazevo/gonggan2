
var Ca = /\+/g;

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

function requestCategoryList(category, val) {
	
	var rownum2;
	
	if (maxRownum - val < 8)
		rownum2 = maxRownum;
	else rownum2 = rownum + 7;
	
	$.ajax({
				url: "/gonggan/postlist.do",
				//url: "/gonggan/userpostlist.do",
				data: { writer_id : writer_id,
					rownum: val, rownum2: rownum2,
					category: category
				},
				success: function(data) {
					callbackList2(data);
			         rownum = rownum2 + 1;
				},
				error: function(data,status,error){
					console.log("error : " + error);
				}
	   });
}


function callbackList2(data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);	

	var tr;
	var td;
	
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
    tr.appendChild(td);
    document.getElementById("listbody").appendChild(tr);

	for (j=0 ; j<3 ; j++){
		if (j > jsonArr.list.length - 1) break;
	    tr = document.createElement("tr");
		td = document.createElement("td");
		td.className = "commentArea";
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
    obj.after("<tr><td class='commentArea'><a href='myhome.do?writer_id=" + jsonArr.list[j].writerId + "'><b>"
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
			url: "/gonggan/nreject.do",
			data: {member_id: loginUser,member_id2: writer_id},
			success: function(data) {
				alert("이웃 취소");
			},
			error: function(data,status,error) {
				console.log("error : " + error);
			}
		});
	} else return;
}


