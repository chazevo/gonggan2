package com.kh.gonggan.good.model.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.gonggan.good.model.vo.Good;



@Repository("GoodDao")

public class GoodDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public GoodDao(){}
	
	
	public Good goodCount(int postId){
		return (Good) sqlSession.selectOne("goodmapper.gcount", postId);
	}//good 카운트 세기
	

	public int goodInsert(int postId, String memberId){
		
		Good gmember = new Good(postId, memberId);
		return sqlSession.insert("goodmapper.ginsert", gmember);
	}//good 카운트 증가

	public int goodDelete(int postId, String memberId){
		Good gmember = new Good(postId, memberId);
		return sqlSession.delete("goodmapper.gdelete", gmember);
	}//good 카운트 감소


	public Object goodCheck(int postId, String memberId) {
		Good gmember = new Good(postId, memberId);
		return sqlSession.selectOne("goodmapper.gcheck", gmember);
	}//good 알람 체크 여부

}
