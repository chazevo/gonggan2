package com.kh.gonggan;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler {

 private Map<String, WebSocketSession> users =
		 new ConcurrentHashMap<String, WebSocketSession>();
 
 private HashMap<String, String> users2 = new HashMap<String, String>();

 @Override
 public void afterConnectionEstablished(
   WebSocketSession session) throws Exception {
  log(session.getId() + " 연결 됨!!");
  users.put(session.getId(), session);
 }

 @Override
 public void afterConnectionClosed(
   WebSocketSession session, CloseStatus status) throws Exception {
  log(session.getId() + " 연결 종료됨");
  users.remove(session.getId());
 }

 @Override
 protected void handleTextMessage(
		 WebSocketSession session, TextMessage message) throws Exception {
	 String memberId = message.getPayload().split(" :")[0];
	 
	 if (!users2.containsValue(memberId))
		 users2.put(session.getId(), memberId);
	 
	 System.out.println("유저 아이디 : " + memberId);
	 
	 //메시지 DB에 저장하는 부분 
	 
	 log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
	 for (WebSocketSession s : users.values()) {
		 //s.sendMessage(new TextMessage(session.getRemoteAddress().getHostName() + ":" + message.getPayload()));
		 s.sendMessage(message);
		 log(s.getId() + "에 메시지 발송: " + message.getPayload());
	 }
 }

 @Override
 public void handleTransportError(
   WebSocketSession session, Throwable exception) throws Exception {
  log(session.getId() + " 익셉션 발생: " + exception.getMessage());
 }

 private void log(String logmsg) {
  System.out.println(new Date() + " : " + logmsg);
 }

}