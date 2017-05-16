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
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("memberId1", memberId1);
			map.put("memberId2", memberId2);
			
			return (List<Message>) sqlSession.selectList("messagemapper.msglist", map);
			
		}//메시지 리스트 조회


		public int insertMessage(String memberId1, String memberId2, String msg_text, String check_yn) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("memberId1", memberId1);
			map.put("memberId2", memberId2);
			map.put("msg_text", msg_text);
			map.put("check_yn", check_yn);
			
			return sqlSession.insert("messagemapper.msginsert", map);
		}
		


}
