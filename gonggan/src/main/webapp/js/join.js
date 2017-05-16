function onlyNumber(event){
    event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 9 || keyID == 46 || keyID == 37 || keyID == 39 ) {
     // 96~105 : 키보드 숫자 0~9
        return;
    } else {
        return false;
        // true면 onkeydown이 작동되니 눌러진것으로 받아들여 입력이 되고 false면 입력이 안됨
    }
}
function blurEvent(object) {
	
	$(object).next().css('display', 'none');
	
	if ($(object).val() != "") {
		$(object).removeClass("error");
		$(object).next().removeClass("error");
	}
}
function confirmInput() {
 
}

function goSubmit() {
	  $("#join").submit();
	 if ($("input[name='id']").val() == "") {
	  $("#idSpan > em").text("아이디를 입력해주세요.");
	  $("input[name='id']").addClass("error");
	  $("#idSpan > em").addClass("error");
	  $("#idSpan > em").css("display", "block");
	  $("input[name='id']").focus();
	  return;
	 }
	 
	 else if ($("input[name='pwd']").val() == "") {
	  $("#pwdSpan > em").text("비밀번호를 입력해주세요.");
	  $("input[name='pwd']").addClass("error");
	  $("#pwdSpan > em").addClass("error");
	  $("#pwdSpan > em").css("display", "block");
	  $("input[name='pwd']").focus();
	  return;
	 }
	 
	 else if ($("input[name='pwd2']").val() == "") {
	  $("#pwd2Span > em").text("비밀번호를 입력해주세요.");
	  $("input[name='pwd2']").addClass("error");
	  $("#pwd2Span > em").addClass("error");
	  $("#pwd2Span > em").css("display", "block");
	  $("input[name='pwd2']").focus();
	  return;
	 }
	 
	 else if ($("input[name='phone']").val() == "") {
	  $("#phoneSpan > em").text("연락처를 입력해주세요.");
	  $("input[name='phone']").addClass("error");
	  $("#phoneSpan > em").addClass("error");
	  $("#phoneSpan > em").css("display", "block");
	  $("input[name='phone']").focus();
	  return;
	 }
	 
	 else if ($("input[name='email']").val() == "") {
	  $("#emailSpan > em").text("이메일을 입력해주세요.");
	  $("input[name='email']").addClass("error");
	  $("#emailSpan > em").addClass("error");
	  $("#emailSpan > em").css("display", "block");
	  $("input[name='email']").focus();
	  return;
	 }
	 
	if ($("input[name='pwd']").val() != $("input[name='pwd2']").val()) {
		alert("패스워드가 일치하지 않습니다. ");
		$("input[name='pwd']").select();
		return;
	 }
	 
}