package com.kh.gonggan.review.model.service;

import java.util.List;

import com.kh.gonggan.review.model.vo.Review;

public interface ReviewService {
	List<Review> selectAll(int rownum, int rownum2);
	List<Review> selectAll_index2();
	List<Review> selectAll_myhome(String writer_id);
	Review reviewDetail(int postId);
}