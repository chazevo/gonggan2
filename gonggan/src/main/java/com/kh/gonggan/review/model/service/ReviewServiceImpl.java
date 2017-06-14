package com.kh.gonggan.review.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.review.model.dao.ReviewDao;
import com.kh.gonggan.review.model.vo.Review;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewDao reviewDao;
	
	@Override
	public List<Review> selectAll(int rownum, int rownum2) {
		return reviewDao.selectAll(rownum, rownum2);
	}
	
	@Override
	public List<Review> selectAll_index2() {
		return reviewDao.selectAll_index2();
	}

	@Override
	public List<Review> selectAll_myhome(String writer_id) {
		return reviewDao.selectAll_myhome(writer_id);
	}
	@Override
	public Review reviewDetail(int postId) {
		return reviewDao.reviewDetail(postId);
	}

}