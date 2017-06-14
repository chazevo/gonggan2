function likeNeigh(member_id2) {

	if ($("#neighborBtn").hasClass("neighborN")) {
		$("#neighborBtn").removeClass("neighborN");
		$("#neighborBtn").addClass("neighborY");
		$("#neighborBtn").text("이웃친구 요청");
      
		alert(member_id2);
      
		$.ajax({
			url: "/gonggan/nrequest.do",
			data: {member_id1 : loginUser,
				member_id2: member_id2
			},
			success: function(data) {
			
			},
			error: function(data,status,error) {
				console.log("error : " + error);
			}
		});
	}
   
}

function rejectNeig(writer_id) {
	if($("#neighborBtn").hasClass("neighborY")) {
		$("#neighborBtn").removeClass("neighborY");
		$("#neighborBtn").addClass("neighborN");
		$("#neighborBtn").text("이웃 취소");
      
		if (confirm("정말 이웃신청을 삭제하시겠습니까? ") == true) {
			$.ajax({
				url: "/gonggan/nreject.do",
				data: {member_id: loginUser,member_id2: writer_id},
				success: function(data) {
					alert("이웃 취소");
				},
				error: function(data,status,error){
					console.log("error : " + error);
				}
			});
		} else return;
	}
}
   
function searchGood() {
	$.ajax({
		url: "/gonggan/gsearch.do",
		data: {member_id : $('#searchPost').val(),
			post_id: post_id
		},
		success: function(data) {
			callbackSearchGood(data);
		},
		error: function(data,status,error) {
			console.log("error : " + error);
		}
	});
}

function Neig(id){

	var returnValue;

	$.ajax({
		async:false,
		url: "/gonggan/neighyn.do",
		data: {loginUser : loginUser,
			writer_id: id
		},
		success: function(data) {
			returnValue = data;
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
	
	return returnValue;
}
function selectPhoto(id){

	var returnValue;

	$.ajax({
		async:false,
		url: "/gonggan/selectMemberphoto.do",
		data: {
			writer_id: id
		},
		success: function(data) {
			returnValue = data;
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
	return returnValue;
}


function callbackSearchGood(data){

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	var tr;
	var td;
   
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);

	for (var j in jsonArr.list) {
		var result = selectPhoto(jsonArr.list[j].member_id);
		alert(selectPhoto(jsonArr.list[j].member_id));
		if(result != "없음"){

		tr = document.createElement("tr");
		td = document.createElement("td");
		td.innerHTML ='<a href="myhome.do?writer_id=${i.member_id}" target="_blank">'
			+'<img src="images/profileImages/'+result+'" height="40px" class="img-circle">'
			+ jsonArr.list[j].member_id+'</a>';
		tr.appendChild(td);
		}else if(result == "없음"){
			tr = document.createElement("tr");
			td = document.createElement("td");
			td.innerHTML ='<a href="myhome.do?writer_id=${i.member_id}" target="_blank">'
				+'<img src="images/default.png" height="40px" class="img-circle">'
				+ jsonArr.list[j].member_id+'</a>';
			tr.appendChild(td);
			
		}
		

		if(jsonArr.list[j].member_id == loginUser) {

			
			td = document.createElement("td");
			td.innerHTML = " ";
			tr.appendChild(td);
		}
		else if(jsonArr.list[j].member_id != loginUser) {
			if(Neig(jsonArr.list[j].member_id) == "Y") {
				
				td = document.createElement("td");
				td.innerHTML = '<button id="neighborBtn" class="neighborY" onclick="rejectNeig(\''
					+jsonArr.list[j].member_id + '\');">이웃친구</button></td>';
				tr.appendChild(td);
			} else if(Neig(jsonArr.list[j].member_id)=="N") {


				td = document.createElement("td");
				td.innerHTML = '<button id="neighborBtn" class="neighborN" onclick="likeNeigh(\''
					+ jsonArr.list[j].member_id + '\');">'
					+ '이웃친구</button></td>';
				tr.appendChild(td);
			}
		}

		document.getElementById("listbody").appendChild(tr);
	}
}