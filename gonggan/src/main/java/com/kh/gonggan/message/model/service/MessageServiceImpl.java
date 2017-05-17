package com.kh.gonggan.message.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.message.model.dao.MessageDao;
import com.kh.gonggan.message.model.service.MessageService;
import com.kh.gonggan.message.model.vo.Message;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	//현재 클래스를 스프링에서 관리하는 service bean으로 등록
	@Autowired
	MessageDao messageDao; //객체를 스프링에서 생성하여 주입시킴

	@Override
	public List<Message> messageList(String memberId1, String memberId2){
		return messageDao.messageList(memberId1, memberId2);
	}
	@Override
	public int insertMessage(String memberId1, String memberId2, String msg_text, String check_yn){
		return messageDao.insertMessage(memberId1,  memberId2, msg_text, check_yn);
	}
	

	
}
