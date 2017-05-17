package com.kh.gonggan.blog.model.service;

import java.util.List;

import com.kh.gonggan.blog.model.vo.Blog;
import com.kh.gonggan.member.model.vo.Member;

public interface BlogService {

	Blog selectBlog(String writer_id);
	List<Member> selectVisitorList(String writer_id);

}
