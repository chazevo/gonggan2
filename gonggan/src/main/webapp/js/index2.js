function like() {
	
}

function acceptNeig(member_id, member_id2) {
	
	$(this).parent().remove();
	
/*	$.ajax({
	      url: "/gonggan/naccept.do",
	      data: {member_id: member_id, member_id2: member_id2},
	      success: function(data) {
		     $("#neighborReqListSize").text($("#neighborReqListSize").text() - 1);
	    	 alert(data);
	    	 $(this).parent().remove();
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });*/
}

function rejectNeig(member_id, member_id2) {
	$.ajax({
	      url: "/gonggan/nreject.do",
	      data: {member_id: member_id, member_id2: member_id2},
	      success: function(data) {
		    	 alert(data);
		    	 $("#neighborReqListSize").text($("#neighborReqListSize").text() - 1);
		    	 $(this).parent().remove();
	      },
	      error: function(data,status,error){
	         console.log("error : " + error);
	      }
	   });
}