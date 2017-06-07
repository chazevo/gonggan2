package com.kh.gonggan.blog.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.blog.model.dao.BlogDao;
import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.member.model.vo.Member;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDao blogDao; // 객체를 스프링에서 생성하여 주입시킴

	public int insertBlog(Blog blog) {
		return blogDao.insertBlog(blog);
	}
	
	public Blog selectBlog(String writer_id) {
		return blogDao.selectBlog(writer_id);
	}
	public List<Member> selectVisitorList(String writer_id){
		return blogDao.selectVisitorList(writer_id);
		
	}
	public List<Member> selectNeigborVisitorList(String wirter_id){
		return blogDao.selectNeigborVisitorList(wirter_id);
	}
	public List<Member> selectMonNeigborVisitorList(String writer_id){
		return blogDao.selectMonNeigborVisitorList(writer_id);
	}
	public List<Member> selectMonNeiList(String writer_id){
		return blogDao.selectMonNeiList(writer_id);
	}
	
	public void blogvisit(String writer_id, String visitor_id) {
		blogDao.blogvisit(writer_id, visitor_id);
	}
	
	public int blogSetting(Blog blog) {
		return blogDao.blogSetting(blog);
	}
	
	public int blogSetting_color(Blog blog) {
		return blogDao.blogSetting_color(blog);
	}
	
	public int blogSetting_background(Blog blog) {
		return blogDao.blogSetting_background(blog);
	}
	
}
