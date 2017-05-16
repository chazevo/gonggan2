package com.kh.gonggan.message.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.message.model.vo.Message;
import com.kh.gonggan.message.model.service.MessageService;

@Controller
public class MessageController {

		//메소드가 controller가 됨 컨트롤러를 메소드 단위로 작성하면 된다.
		//공통으로 사용하는 것은 common에 넣어놓으면 됨
		@Autowired
		private MessageService messageService;
		
		@RequestMapping("messageList.do")
		public ModelAndView memberList(@RequestParam String memberId1,
				@RequestParam String memberId2, ModelAndView mv){
			
			List<Message> msgList = messageService.messageList(memberId1, memberId2);

			mv.setViewName("massage");
			mv.addObject("msgList", msgList);
			
			return mv;
		
		}
		
	

}
