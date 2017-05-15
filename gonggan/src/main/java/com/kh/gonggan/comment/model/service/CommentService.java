package com.kh.gonggan.comment.model.service;

import java.util.List;

import com.kh.gonggan.comment.model.vo.Comment;

public interface CommentService {

	List<Comment> selectPostComments(String postId);
	
	int insertComment(Comment comment);
	
	int deleteComment(int commentNum);

	int updateComment(Comment comment);
}
