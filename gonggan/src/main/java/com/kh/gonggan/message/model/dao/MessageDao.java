package com.kh.gonggan.message.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.message.model.vo.Message;
@Repository("messageDao")
public class MessageDao {
		
		@Autowired
		private SqlSessionTemplate sqlSession;
		
		public MessageDao(){}

		
		public List<Message> messageList(String memberId1, String memberId2){
			return (List<Message>) sqlSession.selectList("messagemapper.msglist");
		}//회원 전제 조회
		


}
