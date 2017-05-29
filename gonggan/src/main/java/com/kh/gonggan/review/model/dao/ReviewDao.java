package com.kh.gonggan.review.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.music.model.vo.Music;
import com.kh.gonggan.review.model.vo.Review;

@Repository("ReviewDao")
public class ReviewDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public ReviewDao(){}

	public List<Review> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Review>) sqlSession.selectList("Reviewmapper.Reviewlist");
	}
	
	public List<Review> selectAll_index2(){
		return (List<Review>) sqlSession.selectList("reviewmapper.reviewlist_index2");
	}

	public Review reviewDetail(int postId) {
		return (Review) sqlSession.selectList("reviewmapper.reviewdetail", postId);
	}
}