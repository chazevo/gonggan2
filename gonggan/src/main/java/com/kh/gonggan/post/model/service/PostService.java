package com.kh.gonggan.post.model.service;

import java.util.List;

import com.kh.gonggan.post.model.vo.Post;

public interface PostService {

	Post postDetail(int post_id);
	List<Post> selectUserAll(String writer_id);
	String selectPostWriter(int post_id);
	List<Post> selectAll(int rownum, int rownum2);
	List<Post> selectLikeAll(int rownum, int rownum2);
	List<Post> selectAll_index2();


}
