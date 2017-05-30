package com.kh.gonggan.post.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.post.model.vo.Post;

@Repository("postDao")
public class PostDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public PostDao(){}

	public List<Post> selectUserAll(String writer_id) {
		return (List<Post>) sqlSession.selectList("postmapper.userplist", writer_id);
	}
	
	public Post postDetail(int post_id) {
		return (Post) sqlSession.selectOne("postmapper.pdetail", post_id);
	}

	public String selectPostWriter(int post_id) {
		
		return (String) sqlSession.selectOne("postmapper.selectpostwriter", post_id);
	}

	public List<Post> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.plist", map);
	}

	public List<Post> selectLikeAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.plikelist", map);
	}
	
	public List<Post> selectAll_index2() {
	      return (List<Post>) sqlSession.selectList("postmapper.plist_index2");
	   }
	
	public List<Post> selectMusic(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.musiclist", map);
	}

	public List<Post> selectMovie(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.movielist", map);
	}

	public List<Post> selectReview(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.reviewlist", map);
	}

	public List<Post> selectDiary(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.diarylist", map);
	}

	public List<Post> selectNews(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.newslist", map);
	}

	public List<Post> selectBook(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Post>) sqlSession.selectList("postmapper.booklist", map);
	}
}
