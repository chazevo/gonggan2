package com.kh.gonggan.movie.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.movie.model.vo.Movie;
@Repository("movieDao")
public class MovieDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MovieDao(){}
	
	public List<Movie> searchMovie() {
		//System.out.println("dao: selectLogin run...");
		//return (List<Movie>) sqlSession.selectOne("moviemapper.loginSelect");
		return null;
	}
	   public List<Movie> selectAll_index2(){
		      return (List<Movie>) sqlSession.selectList("moviemapper.movielist_index2");
	}

}
