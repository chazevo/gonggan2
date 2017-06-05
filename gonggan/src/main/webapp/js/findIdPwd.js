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
				if (data == "실패") {
					alert("해당 회원이 존재하지 않습니다.");
					document.getElementById("email").select();
				}
				else
					alert("아이디는 " + data + " 입니다.");
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
			if (data == "실패")
				alert("해당 회원이 존재하지 않습니다");
			else
				alert($("#id").val() + "님의 이메일로 비밀번호가 전송되었습니다. ");
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
}