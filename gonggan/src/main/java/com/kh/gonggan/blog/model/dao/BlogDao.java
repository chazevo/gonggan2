package com.kh.gonggan.blog.model.dao;

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
	
	public List<Member> selectVisitorList(String wirter_id){
		return (List<Member>) sqlSession.selectList("membermapper.selectVisitorList",wirter_id);
	}
	
}
