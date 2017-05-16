function reqVisitor() {
	
}

function tab(val) {
	
	if (val==1) {
		document.getElementById("blogControll").style.display = "block";
		$("#blogControllBtn").removeClass("gray");
		$("#blogControllBtn").addClass("gradLong");
		document.getElementById("blogStatistics").style.display = "none";
		$("#blogStatisticsBtn").removeClass("gradLong");
		$("#blogStatisticsBtn").addClass("gray");
	}
	else if (val==2) {
		document.getElementById("blogStatistics").style.display = "block";
		$("#blogStatisticsBtn").removeClass("gray");
		$("#blogStatisticsBtn").addClass("gradLong");
		document.getElementById("blogControll").style.display = "none";
		$("#blogControllBtn").removeClass("gradLong");
		$("#blogControllBtn").addClass("gray");
	}
}

function deletePost() {
	if (confirm("정말 삭제하시겠습니까? ") == true);
}
function settingComplete() {
	if (confirm("수정사항을 반영하시겠습니까?") == true);
}