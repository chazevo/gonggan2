package com.kh.gonggan.blog.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.member.model.vo.Member;

@Repository("blogDao")
public class BlogDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public BlogDao(){}
	
	public Blog selectBlog(String writer_id) {
		return (Blog) sqlSession.selectOne("blogmapper.bselect", writer_id);
	}
	
	public int insertBlog(Blog blog) {
		return sqlSession.insert("blogmapper.binsert", blog);
	}
	
	public List<Member> selectVisitorList(String wirter_id){
		
		Object obj = sqlSession.selectOne("blogmapper.selectBlogId", wirter_id);
		int blog_id = -1;
		
		if (obj != null)
			blog_id =((Blog)obj).getBlog_id();
		
		return (List<Member>) sqlSession.selectList("membermapper.selectVisitorList", blog_id);
	}
	
	public List<Member> selectNeigborVisitorList(String writer_id) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		int blog_id = ((Blog) sqlSession.selectOne("blogmapper.selectBlogId", writer_id)).getBlog_id();
		System.out.println(blog_id);
		
		map.put("writer_id", writer_id);
		map.put("blog_id", blog_id + "");
		
		return (List<Member>) sqlSession.selectList("membermapper.selectNeigborVisitorList", map);
	}

	public List<Member> selectMonNeigborVisitorList(String writer_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		int blog_id = ((Blog) sqlSession.selectOne("blogmapper.selectBlogId", writer_id)).getBlog_id();
		System.out.println(blog_id);
		
		map.put("writer_id", writer_id);
		map.put("blog_id", blog_id + "");
		
		return (List<Member>) sqlSession.selectList("membermapper.selectMonNeigborVisitorList", map);
	}

	public List<Member> selectMonNeiList(String writer_id) {
		
		
		return (List<Member>) sqlSession.selectList("membermapper.selectMonNeiList", writer_id);
	}

	public void blogvisit(String writer_id, String visitor_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		
		int blog_id = ((Blog) sqlSession.selectOne("blogmapper.selectBlogId", writer_id)).getBlog_id();
		
		map.put("blog_id", blog_id + "");
		map.put("visitor_id", visitor_id);
		
		sqlSession.insert("blogmapper.bvisit", map);
	}
	
	public int blogSetting(Blog blog) {
		return sqlSession.insert("blogmapper.bsetting", blog);
	}
	
}
