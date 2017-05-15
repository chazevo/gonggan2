package com.kh.gonggan.comment.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.comment.model.dao.CommentDao;
import com.kh.gonggan.comment.model.vo.Comment;

@Service("commentService")
public class CommentServiceImpl implements CommentService{
	//현재 클래스를 스프링에서 관리하는 service bean으로 등록
	@Autowired
	CommentDao commentDao; //객체를 스프링에서 생성하여 주입시킴
	
	@Override
	public List<Comment> selectPostComments(String postId) {
		System.out.println("service : selectList run...");
		return  commentDao.selectPostComments(postId);
	}
	
	@Override
	public int insertComment(Comment comment){
		 return commentDao.insertComment(comment);
	}//코멘트 등록
	
	@Override
	public int deleteComment(int commentNum){
		return commentDao.deleteComment(commentNum);
	}//코멘트 삭제
	
	@Override
	public int updateComment(Comment comment){
		return commentDao.updateComment(comment);
	}//코멘트 수정
	
}
