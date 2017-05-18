function likeNeigh() {
	
	if($("#neighborBtn").hasClass("neighborN")) {
		$("#neighborBtn").removeClass("neighborN");
		$("#neighborBtn").addClass("neighborY");
	}else{
		$("#neighborBtn").removeClass("neighborY");
		$("#neighborBtn").addClass("neighborN");
	}
	
}