	function send_message() {
		websocket = new WebSocket(
				"ws://localhost:8080/gonggan/websocket/echo.do");
		websocket.onopen = function(evt) {
			onOpen(evt)
		};
		websocket.onmessage = function(evt) {
			onMessage(evt)
		};
	}
	
	
	function send() {
		//var memberId = memberId;
		var memberId = "test";
		var msg = $("#msg").val();
		websocket.send(memberId + " : " + msg);
		$("#msg").val("");
	}
	
	function onOpen(evt) {
		alert("연결되었습니다! ");
	}
	
	function onMessage(evt) {
		var data = evt.data;
		
		//if (data.substring(0, 4) == "msg:") {
			//appendMessage(data.substring(4));
		appendMessage(data);
		//}
    }
	
	function onError(evt) {
		writeToScreen('ERROR: ' + evt.data);
	}
	
	function appendMessage(msg) {

		if (msg.split(" : ")[0] == "test")
			$("#chatArea").append("<div class='sender'>"
					+ msg.split(" : ")[1] + "<div>");
		else
			$("#chatArea").append("<div class='receiver'>"
					+ msg.split(" : ")[1] + "<div>");
		
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $(".massage").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
		
	}