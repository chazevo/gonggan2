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
	
	public int updateComment(Comment comment){
		return sqlSession.update("commentmapper.cupdate",comment);
	}//코멘트 수정

	public int insertComment(String comment_content, String writer_id, int postId) {
		Comment comment = new Comment(comment_content, writer_id, postId);
		int comment_num = (int) sqlSession.selectOne("commentmapper.cnum", comment);
		comment.setComment_num(comment_num);
		sqlSession.insert("commentmapper.cinsert", comment);
		return comment_num;
	}

	public int deleteComment(int comment_num, String writer_id, int postId) {
		Comment comment = new Comment(comment_num, writer_id, postId);
		return sqlSession.delete("commentmapper.cdelete", comment);
	}

	public List<Comment> checkCommentAlram(String member_id) {
		return (List<Comment>) sqlSession.selectList("commentmapper.ccheckalram", member_id);
	}
	public List<Comment> commentCheckAlram(String member_id) {
		return (List<Comment>) sqlSession.selectList("commentmapper.commentcheckalram", member_id);
	}
	public List<Comment> myCommentList(String member_id) {
		return (List<Comment>) sqlSession.selectList("commentmapper.tracelist", member_id);
	}

	
}
