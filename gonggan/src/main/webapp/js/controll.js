function reqVisitor() {
	$.ajax({
	      url: "/gonggan/selectVisitorList.do",
	      data: {writer_id : writer_id},
	      success: function(data) {
	    	  callbackVisitorList(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}

function reqNeighborVisitor() {
	$.ajax({
	      url: "/gonggan/selectNeigborVisitorList.do",
	      data: {writer_id : writer_id},
	      success: function(data) {
	    	  callbackNeighborVisitorList(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}

function callbackVisitorList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$("#todayCnt").text(jsonArr.list.length);

	/*
	for (var j in jsonArr.list){
		jsonArr.list[j].gender
		jsonArr.list[j].birth
		jsonArr.list[j].member_id
	}
	*/
}

function callbackNeighborVisitorList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);

	$("#todayNeighborCnt").text(jsonArr.list.length);
	
	
	for (var j in jsonArr.list){
		$("#visitedNeighbor").html(jsonArr.list[j].member_id);

	}
	

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