var Ca = /\+/g;

function trace(loginUser) {
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
	
	$("#listbody_newPost").hide();
	$("#listbody_newNeighbor").hide();
	$(".title").hide();
	$("#postAlarm").text('나의 흔적_');
	$("#postAlarmCnt").text(jsonArr.list.length + "");
	
	while (document.getElementById("listbody_mytrace").rows.length > 0 )
		document.getElementById("listbody_mytrace").deleteRow(0);
	
	for (var j=0 ; j<7 ; j++){
		
		loginUser = jsonArr.list[j].loginUser;
		
		tr = document.createElement("tr");
		td = document.createElement("td");
		//document.getElementById("listbody").innerHTML += "<tr><td colspan='7'>"
		
		td.innerHTML = "<a data-fancybox data-src='pdetail.do?postId="+jsonArr.list[j].postId+"&writerId=" + jsonArr.list[j].postWriter +"'>"
		+ decodeURIComponent((jsonArr.list[j].commentContent).replace(Ca, " ")) +"</a> " ;
		tr.appendChild(td);
		document.getElementById("listbody_mytrace").appendChild(tr);
		
		if (jsonArr.list.length-1 == j) break;
		
	}
		
	
}
function checkGood(loginUser, postId){
	
	$.ajax({
	      url: "/gonggan/checkGood.do",
	  data: {loginUser:loginUser,
		  	 postId:postId},
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
			  	 postId:1},
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
			  	 postId:1},
		  success: function(data) {
			  alert("좋아요 취소함 ");
		  },
		  error: function(data,status,error){
		     console.log("error : " + error);
		  }
		});
	}
}

function acceptNeig(member_id, member_id2) {
	
	$(this).parent().remove();
	
/*	$.ajax({
	      url: "/gonggan/naccept.do",
	      data: {member_id: member_id, member_id2: member_id2},
	      success: function(data) {
		     $("#neighborReqListSize").text($("#neighborReqListSize").text() - 1);
	    	 alert(data);
	    	 $(this).parent().remove();
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });*/
}

function rejectNeig(member_id, member_id2) {
	$.ajax({
	      url: "/gonggan/nreject.do",
	      data: {member_id: member_id, member_id2: member_id2},
	      success: function(data) {
		    	 alert(data);
		    	 $("#neighborReqListSize").text($("#neighborReqListSize").text() - 1);
		    	 $(this).parent().remove();
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}