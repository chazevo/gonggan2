package com.kh.gonggan.post.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.post.model.vo.Post;

@Repository("postDao")
public class PostDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public PostDao(){}

	public List<Post> selectAll() {
		return (List<Post>) sqlSession.selectList("postmapper.plist");
	}
	public Post postDetail(int post_id) {
		return (Post) sqlSession.selectOne("postmapper.pdetail",post_id);
	}

	public List<Post> selectUserAll(String writer_id) {
		return (List<Post>) sqlSession.selectList("postmapper.userplist",writer_id);
	}
	
}
