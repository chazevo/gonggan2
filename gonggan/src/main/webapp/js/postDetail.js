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

function dotdotdot(){
	
	if (document.getElementById("dotdotdotDiv").style.display == "block")
		$('#dotdotdotDiv').css("display","none");
	else
		$('#dotdotdotDiv').css("display","block");
}