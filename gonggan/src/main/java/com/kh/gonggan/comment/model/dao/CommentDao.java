package com.kh.gonggan.comment.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	} // 코멘트 수정

	public int insertComment(String comment_content, String writer_id, int postId) {

		Comment comment = new Comment(comment_content, writer_id, postId);
		
		int comment_num = (int) sqlSession.selectOne("commentmapper.cnum", comment);
		comment.setAlarm_num((int) sqlSession.selectOne("commentmapper.alarmnum"));
		comment.setComment_num(comment_num);
		
		if (sqlSession.insert("commentmapper.cinsert", comment) < 0)
			System.out.println("알람 테이블 insert 실패!");
		if (sqlSession.insert("commentmapper.cinsert2", comment) < 0)
			System.out.println("코멘트 달기 실패 !");
	      
		return comment_num;
	
	}

	public int deleteComment(int comment_num, String writer_id, int postId) {
      
		Comment comment = new Comment(comment_num, writer_id, postId);
		
		if (sqlSession.delete("commentmapper.cdelete", comment) < 0)
			System.out.println("알람 테이블 데이터 삭제 실패");
		return sqlSession.delete("commentmapper.adelete", comment);
		
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
	
	public List<Comment> CommentMyList(String writer_id) {
		return (List<Comment>) sqlSession.selectList("commentmapper.commentmylist", writer_id);
	}

	public List<Comment> commentNeigList(String writer_id) {
		return (List<Comment>) sqlSession.selectList("commentmapper.commentneiglist", writer_id);
	}
	
	public List<Comment> commentAll(String writer_id) {
		return (List<Comment>) sqlSession.selectList("commentmapper.commentall", writer_id);
	}

   
}