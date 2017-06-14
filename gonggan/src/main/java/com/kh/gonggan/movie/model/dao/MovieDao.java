package com.kh.gonggan.movie.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.music.model.vo.Music;
import com.kh.gonggan.post.model.vo.Post;
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
	
	public List<Movie> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Movie>) sqlSession.selectList("moviemapper.movielist", map);
	}
	
	public List<Movie> selectAll_index2() {
		      return (List<Movie>) sqlSession.selectList("moviemapper.movielist_index2");
	}

	public List<Movie> selectAll_myhome(String writer_id) {
	      return (List<Movie>) sqlSession.selectList(
	    		  "moviemapper.movielist_myhome", writer_id);
}
	
	public Movie movieDetail(int postId) {
		return (Movie) sqlSession.selectOne("moviemapper.moviedetail", postId);
	}
}
