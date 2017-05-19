package com.kh.gonggan.comment.model.service;

import java.util.List;

import com.kh.gonggan.comment.model.vo.Comment;

public interface CommentService {

	List<Comment> selectPostComments(String postId);

	int updateComment(Comment comment);

	int insertComment(String comment_content, String writer_id, int postId);


	int deleteComment(int comment_num, String writer_id, int postId);
}
