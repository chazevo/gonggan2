
function goSubmit() {
	if ($("#pass").val() =="") {
		alert("비밀번호를 입력해주세요");
		return;
	}
	else if ($("#id").val() =="") {
		alert("비밀번호를 입력해주세요");
		return;
	}
	else
		$("#login").submit();
}  