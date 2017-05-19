package com.kh.gonggan.good.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.good.model.dao.GoodDao;
import com.kh.gonggan.good.model.vo.Good;

@Service("GoodService")
public class GoodServiceImpl implements GoodService{
	@Autowired
	GoodDao goodDao;
	
	@Override
	public int goodCount(int postId){
		return goodDao.goodCount(postId);
	}//good 카운트 세기
	
	@Override
	public int goodInsert(int postId, String memberId){
		return goodDao.goodInsert(postId,memberId);
	}//good 카운트 증가
	@Override
	public int goodDelete(int postId, String memberId){
		
		return goodDao.goodDelete(postId,memberId);
	}//good 카운트 감소
	@Override
	public Object goodCheck(int postId, String memberId){
		return goodDao.goodCheck(postId,memberId);
	}//good 체크 확인 여부
	@Override
	public List<Good> goodList(int postId){
		return goodDao.goodList(postId);
	}
	
	@Override
	public List<Good> goodSearch(String member_id, int post_id){
		return goodDao.goodSearch(member_id,post_id);
		
	}

}
