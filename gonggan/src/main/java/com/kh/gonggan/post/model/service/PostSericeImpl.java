package com.kh.gonggan.post.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.post.model.dao.PostDao;
import com.kh.gonggan.post.model.vo.Post;

@Service("postService")
public class PostSericeImpl implements PostService{
	
	@Autowired
	PostDao postDao;
	

	@Override
	public List<Post> selectAll() {
		System.out.println("service : selectList run...");
		return  postDao.selectAll();
		
	}
	@Override
	public Post postDetail(int post_id) {
		return  postDao.postDetail(post_id);
		
	}
	

}
