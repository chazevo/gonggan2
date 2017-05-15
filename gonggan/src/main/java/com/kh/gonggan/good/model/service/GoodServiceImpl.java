package com.kh.gonggan.good.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.good.model.dao.GoodDao;
import com.kh.gonggan.good.model.vo.Good;

@Service("GoodService")
public class GoodServiceImpl implements GoodService{
	@Autowired
	GoodDao goodDao;
	
	@Override
	public Good goodCount(int postId){
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
	public int goodCheck(int postId, String memberId){
		return goodDao.goodCheck(postId,memberId);
	}//good 체크 확인 여부

}
