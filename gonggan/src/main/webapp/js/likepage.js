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
		      error: function(data,status,error){
		         console.log("error : " + error);
		      }
		   });
		
	} else {
		$("#neighborBtn").removeClass("neighborY");
		$("#neighborBtn").addClass("neighborN");
		$("#neighborBtn").text("이웃친구");
	}
	
}

function searchGood(){
	$.ajax({
	      url: "/gonggan/gsearch.do",
	      data: {member_id : $('#searchPost').val(),
	    	  	  post_id: post_id
	    	  },
	      success: function(data) {
	    	  callbackSearchGood(data);
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}
function callbackSearchGood(data){

	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	var tr;
	var td;
	
	while (document.getElementById("listbody").rows.length > 0 )
		document.getElementById("listbody").deleteRow(0);
	
	for (var j in jsonArr.list) {

		alert(jsonArr.list[j].member_id);
		if(jsonArr.list[j].member_id == loginUser){
			tr = document.createElement("tr");
			td = document.createElement("td");

			td.innerHTML ='<a href="myhome.do?writer_id=${i.member_id}" target="_blank">' + jsonArr.list[j].member_id+'</a>';
			tr.appendChild(td);
			td = document.createElement("td");
			td.innerHTML = " ";
			tr.appendChild(td);
			document.getElementById("listbody").appendChild(tr);
		}else if(jsonArr.list[j].member_id != loginUser ){
			alert(jsonArr.list[j].member_id);
			tr = document.createElement("tr");
			td = document.createElement("td");

			td.innerHTML ='<a href="myhome.do?writer_id=${i.member_id}" target="_blank">' + jsonArr.list[j].member_id+'</a>';
			tr.appendChild(td);
			td = document.createElement("td");
			td.innerHTML = '<button id="neighborBtn" class="neighborY">이웃친구</button></td>';
			tr.appendChild(td);
			document.getElementById("listbody").appendChild(tr);
		}else if(jsonArr.list[j].member_id == " " ){
			tr = document.createElement("tr");
			td = document.createElement("td");

			td.innerHTML ='<a href="myhome.do?writer_id=${i.member_id}" target="_blank">' + jsonArr.list[j].member_id+'</a>';
			tr.appendChild(td);
			td = document.createElement("td");
			td.innerHTML = '<button id="neighborBtn" class="neighborN" onclick="likeNeigh(\''
				+jsonArr.list[j].member_id + '\');">'
				+ '이웃친구zz</button></td>';
			tr.appendChild(td);
			document.getElementById("listbody").appendChild(tr);
		}
		

        
	}
}