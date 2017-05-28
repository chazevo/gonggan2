package com.kh.gonggan.review.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.review.model.vo.Review;

@Repository("ReviewDao")
public class ReviewDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public ReviewDao(){}
	
	public List<Review> selectAll_index2(){
		return (List<Review>) sqlSession.selectList("reviewmapper.reviewlist_index2");
	}
}
