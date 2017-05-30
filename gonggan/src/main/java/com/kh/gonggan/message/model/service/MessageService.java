package com.kh.gonggan.message.model.service;

import java.util.List;

import com.kh.gonggan.message.model.vo.Message;

public interface MessageService {

   List<Message> messageList(String memberId1, String memberId2);
   int insertMessage(String memberId1, String memberId2, String msg_text, String check_yn);
   List<Message> lastMessage(String writer_id);
}