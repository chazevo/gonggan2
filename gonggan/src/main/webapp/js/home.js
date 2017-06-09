var rownum = 1;
var Ca = /\+/g;

function requestCategoryList(val, category) {

	var rownum2;
	
	switch(category) {
	case "music":
		maxRownum = musicMaxRownum;
	case "movie":
		maxRownum = movieMaxRownum;
	case "review":
		maxRownum = reviewMaxRownum;
	case "news":
		maxRownum = newsMaxRownum;
	case "diary":
		maxRownum = diaryMaxRownum;
	}
	
	if (maxRownum - val < 8)
		var rownum2 = maxRownum;
	else rownum2 = rownum + 7;
	
	$.ajax({
				url: "/gonggan/postlist.do",
				//url: "/gonggan/userpostlist.do",
				data: { writer_id : "",
					rownum: rownum, rownum2: rownum2,
					category: category
				},
				success: function(data) {
					callbackList(data);
				},
				error: function(data,status,error){
					console.log("error : " + error);
				}
	   });
}

function callbackList(data) {

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	
	var div;
	var table, tr, td;

	var postId, content, writerId;
	
	$(".loginTable").hide();
	$("#about").hide();
	$("#div").show();
	$("#div").html("");
	
	for (var i in jsonArr.list) {
		
		div = document.createElement("div");
		table = document.createElement("table");
		
		postId = jsonArr.list[i].postId;
		content = reqPostDetail(postId, jsonArr.list[i].category);
		writerId = jsonArr.list[i].writerId;
		goodCnt = jsonArr.list[i].goodCnt;
		
		tr = document.createElement("tr");
		td = document.createElement("td");
		td.colspan = "2";
		td.className = "blogHomeContent";
		td.innerHTML = "<a data-fancybox data-src='pdetail.do?"
			+ "postId=" + postId
			+ "&writerId=" + writerId + "'>"
			+ decodeURIComponent(content.replace(Ca, " "))
			+ "</a>"
		tr.appendChild(td);
		table.appendChild(tr);
		
		tr = document.createElement("tr");
		tr.className = "trBottom";
		td = document.createElement("td");
		td.innerHTML = "<a href='myhome.do?writer_id=" + writerId + "'>"
			+ writerId + "</a>";
		tr.appendChild(td);
		td = document.createElement("td");
		td.className = "rightAlign";
		td.innerHTML = "<label class='checkbox-wrap'>"
			+ "<input type='checkbox' id='' "
			+ "onclick='like(this, loginUser, " + postId + ");'>"
			+ "<i class='like-icon'></i></label>&nbsp;"
			+ (goodCnt == 0? goodCnt
					: "<a data-fancybox data-src='goodList.do?postId=" + postId + "'>"
					+ goodCnt + "</a>");
		tr.appendChild(td);
		table.appendChild(tr);
		
		div.appendChild(table);
		
		document.getElementById("div").appendChild(div);
	}
	
	if (rownum >= maxRownum) {
		$("#div_Loading").html("더이상 포스트가 존재하지 않습니다.");
		$("#div_Loading").show();
		return;
	}
	$("#div_Loading").fadeOut(100);
}

function reqPostDetail(postId, category) {
	
	var content;
	
	$.ajax({
		async: false,
		url: "plistDetail.do",
		data: { postId: postId,
			category : category},
		success: function(data) {
			content = data;
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
	
	return content;
}

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