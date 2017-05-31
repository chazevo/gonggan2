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

	public List<Post> selectUserAll(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userplist", map);
	}
	
	public List<Post> selectUserMusic(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.usermusic", map);
	}
	
	public List<Post> selectUserMovie(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.usermovie", map);
	}

	public List<Post> selectUserReview(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userreview", map);
	}

	public List<Post> selectUserBook(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userbook", map);
	}

	public List<Post> selectUserPlace(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userplace", map);
	}

	public List<Post> selectUserNews(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.usernews", map);
	}

	public List<Post> selectUserDiary(String writer_id, int rownum, int rownum2) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("writer_id", writer_id);
		map.put("rownum", rownum + "");
		map.put("rownum2", rownum2 + "");
		return (List<Post>) sqlSession.selectList("postmapper.userdiary", map);
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
	
	public int maxRownum(String category) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("category", category);
		return (int) sqlSession.selectOne("postmapper.maxrnum", map);
	}
	
	public List<Post> likeInOrder(String writer_id) {
	      return (List<Post>) sqlSession.selectList("postmapper.likeinorder", writer_id);
	   }//나의 포스트에 좋아요 순위
}
