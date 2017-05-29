package com.kh.gonggan.post.model.service;

import java.util.List;

import com.kh.gonggan.post.model.vo.Post;

public interface PostService {
	List<Post> selectAll();

	Post postDetail(int post_id);

	List<Post> selectUserAll(String writer_id);
	String selectPostWriter(int post_id);
	List<Post> selectAll_index2();


}
