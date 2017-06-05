function cancel(member_id, member_id2, obj) {
	
	if (obj.children(".neighborYN").text() == '취소')
		if (confirm(member_id2 + "님과 이웃친구를 끊으시겠습니까?"))
			$.ajax({
				url: "/gonggan/neigdelete.do",
				data: {member_id: member_id, member_id2: member_id2
				},
				success: function(data) {
					alert(data);
					//obj.parent().parent().remove();
					obj.children(".neighborYN").text("요청");
					
				},
				error: function(data,status,error) {
					console.log("error : " + error);
				}
			});

		else if (obj.children(".neighborYN").text() == '요청')
			/*
			$.ajax({
				url: "/gonggan/neigdelete.do",
				data: {member_id: member_id, member_id2: member_id2
				},
				success: function(data) {
					alert(data);
					//obj.parent().parent().remove();
					obj.children(".neighborYN").text("요청");
					
				},
				error: function(data,status,error) {
					console.log("error : " + error);
				}
			});
			*/
			obj.children(".neighborYN").text("취소");

}

function editProfile() {
	if ($("#editProfile").text() == "수정") {
		$(".x").css("display", "block");
		document.getElementById("introducingArea").readOnly = false;
		$("#introducingArea").focus();
		$("#editProfile").text("완료");
		$("#editProfileCancel").css("display", "block");
	} else if ($("#editProfile").text() == "완료") {
	if (confirm("변경 내용을 반영하시겠습니까?") == true) ;
	else return;
	
	$(".x").css("display", "none");
	document.getElementById("introducingArea").readOnly = true;
	$("#editProfile").text("수정");
	$("#editProfileCancel").css("display", "none");
	}
}

function editProfile2() {
	if(confirm("취소하시겠습니까?")== true){
		$(".x").css("display", "none");
		document.getElementById("introducingArea").readOnly = true;
		$("#editProfile").text("수정");
		$("#editProfileCancel").css("display", "none");
	} else return;
}


function editInfo() {
   var newEmail;
   var emailCheckEI;
   
   if ($("#editInfo").text() == "수정") {
      document.getElementById("pwd").readOnly = false;
      $(".p").css("display", "block");
      $("#pwd2").css("display", "block");
      document.getElementById("pwd2").readOnly = false;
      document.getElementById("email").readOnly = false;
      document.getElementById("phone").readOnly = false;
      $("#memberId").css("border-width", "1px");
      $("#pwd").css("border-width", "1px");
      $("#pwd2").css("border-width", "1px");
      $("#email").css("border-width", "1px");
      $("#phone").css("border-width", "1px");
      document.getElementById("pwd").focus();
      $("#editInfo").text("완료");
      $("#editInfoCancel").css("display", "block");
   }
   else if ($("#editInfo").text() == "완료") {
	   newEmail = $("#email").val();

      if (confirm("변경 내용을 반영하시겠습니까?") == true) {
         $.ajax({
            url: "/gonggan/joinemailcheck.do",// 이메일 중복 체크 컨트롤러
            data: { email:newEmail
            },
            success: function(data) {
               if (data == 'fail') {
                  emailCheckEI =  'NO';
                  alert("이미 가입된 이메일 입니다.");
                  editInfo();
               }
               else if (data == "success") {
                  emailCheckEI = 'YES';
                  if ($("#pwd").val() != $("#pwd2").val()) {
                     alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                     editInfo();
                     // 비밀번호 유효성 체크 
                  }
                  else 
                  $("#update").submit();
               }
               
            },
            error: function(data,status,error) {
               console.log("error : " + error);
            }
         });   
      }
      else return;
      
      document.getElementById("pwd").readOnly = true;
      $(".p").css("display", "none");
      $("#pwd2").css("display", "none");
      document.getElementById("email").readOnly = true;
      document.getElementById("phone").readOnly = true;
      $("#memberId").css("border-width", "0px");
      $("#pwd").css("border-width", "0px");
      $("#pwd2").css("border-width", "0px");
      $("#email").css("border-width", "0px");
      $("#phone").css("border-width", "0px");
      $("#editInfo").text("수정");
   }
}

function editInfo2() {
	if(confirm("취소하시겠습니까?")== true) {
		document.getElementById("pwd").readOnly = true;
		$(".p").css("display", "none");
		$("#pwd2").css("display", "none");
		document.getElementById("email").readOnly = true;
		document.getElementById("phone").readOnly = true;
		$("#memberId").css("border-width", "0px");
		$("#pwd").css("border-width", "0px");
		$("#pwd2").css("border-width", "0px");
		$("#email").css("border-width", "0px");
		$("#phone").css("border-width", "0px");
		$("#editInfo").text("수정");
		$("#editInfoCancel").css("display", "none");
	} else return;
}


function profileImgDelete() {
	if (confirm("현재 프로필 사진을 삭제하시겠습니까? ") == true) ;
}

function deleteMem() {
	   if(confirm("탈퇴하시겠습니까?") == true) {
	      $.ajax({
	         url: "/gonggan/delete.do",
	         data: {member_id: loginUser
	         },
	         success: function(data) {
	            alert("탈퇴되셨습니다");
	            location.href="home.do";
	         },
	         error: function(data,status,error) {
	            console.log("error : " + error);
	         }
	      });
	   } else return;
	}
