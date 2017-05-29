package com.kh.gonggan.news.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.movie.model.vo.Movie;
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
	public List<News> selectAll_index2(){
	      return (List<News>) sqlSession.selectList("newsmapper.newslist_index2");
	   }

}
