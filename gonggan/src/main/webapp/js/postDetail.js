var Ca = /\+/g;

function checkGood(loginUser, postId){
	$.ajax({
		url: "/gonggan/checkGood.do",
		data: {loginUser:loginUser,
			postId:postId },
		success: function(data) {
			if (data == "good")
				document.getElementById("like").checked=true;
			else if (data == "nogood")
				document.getElementById("like").checked=false;
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
}


function like(obj, loginUser, postId){
	
	if (loginUser == ""){
		alert("로그인 하셔야 가능합니다 ! ");

	  if (obj.checked == true)
		  obj.checked = false;
	  else 
		  obj.checked = true;
	
	  return ;
	}
	
	if (obj.checked == true) {
		$.ajax({
		      url: "/gonggan/insertGood.do",
		  data: {loginUser:loginUser,
			  	 postId:postId},
		  success: function(data) {
			  alert("좋아요 함 ");
		  },
		  error: function(data,status,error){
		     console.log("error : " + error);
		  }
		});
	}
	else {
		$.ajax({
		      url: "/gonggan/deleteGood.do",
		  data: {loginUser:loginUser,
			  	 postId:postId},
		  success: function(data) {
			  alert("좋아요 취소함 ");
		  },
		  error: function(data,status,error){
		     console.log("error : " + error);
		  }
		});
	}
}

function sendComment(){
	if (loginUser == "")
		alert("로그인을 해주세요");
	else {
		
		if ($('#comment_content').val() == "") {
			alert("내용을 입력해주세요");
			return;
		}
		
		$.ajax({
		      url: "/gonggan/coinsert.do",
		  data: {writer_id:loginUser,
			  	 postId:postId,
			  	comment_content:$('#comment_content').val()
			  	},
		  success: function(data) {
			  callbackSendComment(data);
		  },
		  error: function(data,status,error){
		     console.log("error : " + error);
		  }
		});
	}
}

function callbackSendComment(data){

	var tr, td;
	var comment_date = new Date();
	var month = comment_date.getMonth() + 1;
	var date = comment_date.getDate();
	
	tr = document.createElement("tr");
	tr.id = "co" + data;
	
	td = document.createElement("td");
	td.colSpan = "3";
	td.innerHTML ='<b><a href="selectBlog.do?writer_id=' + loginUser
			+ '" target="_blank">' + loginUser + '</a></b> &nbsp;&nbsp;'
			+ $("#comment_content").val()
			+"<span class='commentDate'>&nbsp;"
			+ comment_date.getFullYear() + "-"
			+ (month<10 ? "0"+ month : month)  + "-" + (date<10 ? "0"+date : date)
			+ "</span>"
			+ '&nbsp;&nbsp;<a href="javascript:deleteComment(' + data + ')">'
			+ '<img src="images/delete_sign_filled1600.png" width="2%"></a>';
	tr.appendChild(td);
	
	document.getElementById("listbody").appendChild(tr);
	
	$("#comment_content").val("");
	
}

function deleteComment(comment_num){
	
	if (confirm("댓글을 삭제하시겠습니까?") == true)
		$.ajax({
			url: "/gonggan/codelete.do",
			data: {writer_id:loginUser,
				postId:postId,
				comment_num:comment_num
			},
			success: function(data) {
				callbackCommentDelete(data, comment_num);
			},
			error: function(data,status,error){
				console.log("error : " + error);
			}
		});
}

function callbackCommentDelete(data, comment_num){
	$("#co" + comment_num).remove();
	alert(data);
}

function dotdotdot() {

	if ($("#comm>div").css('display') == "block")
		$("#comm>div").css("display", "none");
	else {
		$("#comm>div").css("display", "block");
	}
	
}
