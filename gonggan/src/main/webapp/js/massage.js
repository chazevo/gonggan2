

function send_message() {
		websocket = new WebSocket(
				"ws://localhost:8577/gonggan/websocket/echo.do");
		websocket.onopen = function(evt) {
			onOpen(evt)
		};
		websocket.onmessage = function(evt) {
			onMessage(evt)
		};
}

function send() {
	
	var msg = $("#msg").val();
	$("#msg").val("");
	websocket.send("memberId1:" + loginUser + " "
			+ "memberId2:" + memberId2 + " : " + msg);
}

function onOpen(evt) {
	alert("연결되었습니다! ");
	websocket.send("memberId1:" + loginUser + " "
			+ "memberId2:" + memberId2 + " : " + "open");
	$("#chatArea").append("<div class='infomsg'>"
			+ new Date() + "</div>");
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

	alert(loginUser + "에게로 메시지 왔다 " + msg.split(":")[1].split(" ")[0] + "로부터 ");
	
	if (msg.split(":")[1].split(" ")[0] == loginUser)
		$("#chatArea").append("<div class='sender'>"
				+ msg.split(" : ")[1] + "</div>");
	else if (msg.split(":")[1].split(" ")[0] == memberId2)
		$("#chatArea").append("<div class='receiver'>"
				+ msg.split(" : ")[1] + "</div>");
	
	var chatAreaHeight = $("#chatArea").height();
	var maxScroll = $(".massage").height() - chatAreaHeight;
	$("#chatArea").scrollTop(maxScroll);
	
}
	
function popupClose() {
	parent.$.fancybox.close();
}