package com.kh.gonggan.handler.socket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocketController {
	
	@RequestMapping("chat.do")
	public String viewChattingPage(){
		return "chat";
	}

}
