function idFind() {
	
	if (document.getElementById("email").value == "") {
		alert("아이디를 입력해주세요 ");
		return;
	}
	
	else {
		$.ajax({
			url: "/gonggan/selectIdByEmail.do",
			data: {
				email : $("#email").val()
			},
			success: function(data) {
				alert("아이디는 "+data+ " 입니다.");
			},
			error: function(data,status,error){
				console.log("error : " + error);
			}
		});
	}
}

function pwdFind() {
	
	if (document.getElementById("id").value == "") {
		alert("아이디를 입력해주세요 ");
		return;
	}
	
	$.ajax({
	      url: "/gonggan/selectPw.do",
	      data: {memberId : $("#id").val(),
	    	  	email : $("#email2").val()/*,
	    	  	phone : $("#phone").val()*/
	    	  	},
	      success: function(data) {
	    	  alert(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}