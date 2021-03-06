package com.kh.gonggan;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.gonggan.message.model.service.MessageService;
import com.kh.gonggan.message.model.vo.Message;

public class SocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> users =
			 new ConcurrentHashMap<String, WebSocketSession>();
	 
	private HashMap<String, String> users2 = new HashMap<String, String>();
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void afterConnectionEstablished(
			WebSocketSession session) throws Exception {
		log(session.getId() + " 연결 됨!!");
		users.put(session.getId(), session);
		System.out.println("users : " + users.keySet());
		System.out.println(users.values());
	}
	
	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) throws Exception {
		log(session.getId() + " 연결 종료됨 (" + status + ")");
		users.remove(session.getId());
		System.out.println("users : " + users.keySet());
		System.out.println(users.values());
		users2.remove(session.getId());
		System.out.println("users2 : " + users2.keySet());
		System.out.println(users2.values());
	}
	
	@Override
	protected void handleTextMessage(
			WebSocketSession session, TextMessage message) throws Exception {
		//memberId1:jdj memberId2:jis : 메시지메시지
		 
		String sender = message.getPayload().split(":")[1].split(" ")[0];
		String receiver = message.getPayload().split(":")[2].split(" ")[0];
		String msg_text = message.getPayload().split(" : ")[1];
	
		String memberId2sSessionId;
		WebSocketSession memberId2sSession;
		
		int result = 0;
	
		//if (!users2.containsKey(sender))
		if (!users2.containsValue(sender)) {
			users2.put(session.getId(), sender);
			System.out.println("users2 : " + users2.keySet());
			System.out.println(users2.values());
		}
			 
		System.out.println("유저 아이디1 : " + sender);
		System.out.println("유저 아이디2 : " + receiver);
		System.out.println("메시지 텍스트 : " + msg_text);
		
		if (!msg_text.equals("open")) {
			log(session.getId() + "로부터 메시지 수신: " + message.getPayload());
			 
			/*
			for (WebSocketSession s : users.values()) {
				//s.sendMessage(new TextMessage(session.getRemoteAddress().getHostName() + ":" + message.getPayload()));
				s.sendMessage(message);
				log(s.getId() + "에 메시지 발송: " + message.getPayload());
			}
			*/
			 
			//users.get(users2.get(receiver)).sendMessage(message);
		
			//메시지 DB에 저장하는 부분 
			 
			//if (users2.containsKey(receiver)) {
			if (users2.containsValue(receiver)) {
				System.out.println("지금 접속중 ");
				if ((result = messageService.insertMessage(sender, receiver, msg_text, "Y")) < 0)
					System.out.println("메시지 삽입 실패");
				
				memberId2sSessionId = users2.get(receiver);
				memberId2sSession = users.get(memberId2sSessionId);
				memberId2sSession.sendMessage(message);
				System.out.println(memberId2sSessionId + "한테 전송 ");
			}
			 
			else {
				System.out.println("상대가 오프라인 ");
				if ((result = messageService.insertMessage(sender, receiver, msg_text, "N")) < 0)
					System.out.println("메시지 삽입 실패");
			}
			 
			session.sendMessage(message);
			  
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