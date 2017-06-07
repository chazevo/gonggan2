package com.kh.gonggan.blog.model.service;

import java.util.List;

import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.member.model.vo.Member;

public interface BlogService {

	int insertBlog(Blog blog);
	Blog selectBlog(String writer_id);
	List<Member> selectVisitorList(String writer_id);
	List<Member> selectNeigborVisitorList(String wirter_id);
	List<Member> selectMonNeigborVisitorList(String writer_id) ;
	List<Member> selectMonNeiList(String writer_id);
	void blogvisit(String writer_id, String visitor_id);
	int blogSetting(Blog blog);
	int blogSetting_color(Blog blog);
	int blogSetting_background(Blog blog);

}
