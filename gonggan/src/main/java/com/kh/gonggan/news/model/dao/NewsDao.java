package com.kh.gonggan.news.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.music.model.vo.Music;
import com.kh.gonggan.news.model.vo.News;

@Repository("newsDao")
public class NewsDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public NewsDao(){}
	
	public List<News> searchNews() {
		//System.out.println("dao: selectLogin run...");
		//return (List<Movie>) sqlSession.selectOne("moviemapper.loginSelect");
		return null;
	}
	
	public List<News> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<News>) sqlSession.selectList("newsmapper.newslist");
	}
	
	public List<News> selectAll_index2(){
		return (List<News>) sqlSession.selectList("newsmapper.newslist_index2");
	}
	
	public List<News> selectAll_myhome(String writer_id) {
		return (List<News>) sqlSession.selectList(
				"newsmapper.newslist_myhome", writer_id);
	}

	public News newsDetail(int postId) {
		return (News) sqlSession.selectOne("newsmapper.newsdetail", postId);
	}
}
