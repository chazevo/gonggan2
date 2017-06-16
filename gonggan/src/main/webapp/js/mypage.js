var profilePhotoDeleteYn;
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

function changeProfileImg(/*obj, inputId, outputId*/) {
	
	var file = document.getElementById('file').files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function() {
		//var output = document.getElementById(outputId);
		//output.src = reader.result;
		$(".modal-body>img").attr('src' , reader.result);
	}
}

function changeProfileImg2() {
	var file = document.getElementById('file').files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function() {
		//var output = document.getElementById(outputId);
		//output.src = reader.result;
		$("#profileImg").attr('src', reader.result);
	}
	$(".x").text("X");
}

function editProfile() {
	if ($("#editProfile").text() == "수정") {
		$(".x").css("display", "block");
		document.getElementById("introducingArea").readOnly = false;
		$("#introducingArea").focus();
		$("#editProfile").text("완료");
		$("#editProfileCancel").css("display", "inline-block");
	} else if ($("#editProfile").text() == "완료") {
	if (confirm("변경 내용을 반영하시겠습니까?") == true) 
		/*
		$("#profileForm").ajaxSubmit({
			url: "profileupdate.do",
			type: 'POST',
			success: function(data) {
				alert(data);
			},
			error: function(data,status,error) {
				console.log("error : " + error);
			}
		});
		*/
		$("#profileForm").submit();
	else return;
	
	$(".x").css("display", "none");
	document.getElementById("introducingArea").readOnly = true;
	$("#editProfile").text("수정");
	$("#editProfileCancel").css("display", "none");
	}
}

function editProfile2() {
	if(confirm("취소하시겠습니까?")== true) {
		$(".x").css("display", "none");
		document.getElementById("introducingArea").readOnly = true;
		$("#editProfile").text("수정");
		$("#editProfileCancel").css("display", "none");
		location.reload();
	} else return;
}


function editInfo() {
	
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
		if (confirm("변경 내용을 반영하시겠습니까?") == true) {

			if ($("#email").val() == "") {
				alert("이메일을 입력해주세요");
				return;
			}
			else if ($("#pwd").val() == "" || $("#pwd2").val() == "") {
				alert("비밀번호를 입력해주세요");
				return;
			}
			else if ($("#pwd").val() != $("#pwd2").val()) {
				alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
				return;
			}
			else
				$.ajax({
					url: "/gonggan/joinemailcheck.do",// 이메일 중복 체크 컨트롤러
					data: { email:$("#email").val()
					},
					success: function(data) {
						if (data == '실패') {
							alert("이미 가입된 이메일 입니다.");
							return;
						}
						else if (data == "성공")
							$("#update").submit();
	               
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

function refreshFilediv() {
	profilePhotoDeleteYn = true;
	$(".fileInputDiv").html("<input type='button' value='첨 부 파 일' class='fileInputBtn'>"
			+ "<input type='file' id='file' name='file' accept='.gif,.jpeg,.jpg,.png'>");
	$('#filename').val("");
	$('#layerpop img').attr("src", "images/myproimg_default.png");
	document.getElementById("file").onchange = function() {
		$('#filename').val($(this).val());
		changeProfileImg();
	};
}

function profileImgDelete(obj) {
	if (obj.text() == "X") {
		if (confirm("현재 프로필 사진을 삭제하시겠습니까? ") == true) {
			$("#profileImg").attr('src' , "images/myproimg_default.png");
			$('.x').text('╋');
			refreshFilediv();
		}
	}
	else
		$("#layerpop").modal('show');
}

function deleteMem() {
	if(confirm("정말 탈퇴하시겠습니까?") == true) {
		if ($("#deleteMemberPwd").val() != $("#deleteMemberPwd2").val())
			alert("비밀번호가 일치하지 않습니다.");
		else if ($("#pwd").val() != $("#deleteMemberPwd").val())
			alert("비밀번호가 올바르지 않습니다.");
		else
			$.ajax({
				url: "/gonggan/delete.do",
				data: {member_id: loginUser
				},
				success: function(data) {
					alert("탈퇴가 완료되었습니다");
					$('#layerpop2').modal('hide');
					location.href="start.do";
				},
				error: function(data,status,error) {
					console.log("error : " + error);
				}
			});
	} else return;
}
