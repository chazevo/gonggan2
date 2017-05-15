package com.kh.gonggan.comment.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.comment.model.vo.Comment;
import com.kh.gonggan.post.model.vo.Post;

@Repository("commentDao")
public class CommentDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public CommentDao(){}

	public List<Comment> selectPostComments(String postId){
		return (List<Comment>) sqlSession.selectList("commentmapper.clist", postId);
	}
	
	public int insertComment(Comment comment){
		return sqlSession.insert("commentmapper.cinsert", comment);
	}//코멘트 등록
	
	public int deleteComment(int commentNum)
	{
		return sqlSession.delete("commentmapper.cdelete", commentNum);
	}	//코멘트 삭제
	
	public int updateComment(Comment comment){
		return sqlSession.update("commentmapper.cupdate",comment);
	}//코멘트 수정
	
}
