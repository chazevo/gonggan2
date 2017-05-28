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
	public List<Review> selectAll_index2(){
		return reviewDao.selectAll_index2();
	}

}
