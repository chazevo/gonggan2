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
	public List<Post> selectCalendarAll(String writer_id, int year, int month) {
		return  postDao.selectCalendarAll(writer_id, year, month);
	}

	@Override
	public List<Post> selectAll(int rownum, int rownum2) {
		System.out.println("service : selectList run...");
		return  postDao.selectAll(rownum, rownum2);	
	}
	
	@Override
	public List<Post> selectLikeAll(int rownum, int rownum2) {
		return  postDao.selectLikeAll(rownum, rownum2);	
	}
	
	@Override
	public List<Post> selectLikeNpost(int rownum, int rownum2, String writer_id) {
		return  postDao.selectLikeNpost(rownum, rownum2, writer_id);	
	}
	
	@Override
	public List<Post> selectLikeCategoryPost(int rownum, int rownum2, String category) {
		return  postDao.selectLikeCategoryPost(rownum, rownum2, category);	
	}

	@Override
	public List<Post> postContentSearch(String keyword, int rownum, int rownum2) {
		return  postDao.postContentSearch(keyword, rownum, rownum2);
	}
	
	@Override
	public List<Post> postWriterSearch(String keyword, int rownum, int rownum2) {
		return  postDao.postWriterSearch(keyword, rownum, rownum2);
	}
	
	@Override
	public Post postDetail(int post_id) {
		return  postDao.postDetail(post_id);
		
	}

	@Override
	public int postContentSearchMaxRnum(String keyword) {
		return  postDao.postContentSearchMaxRnum(keyword);
	}

	@Override
	public int postWriterSearchMaxRnum(String keyword) {
		return  postDao.postWriterSearchMaxRnum(keyword);
	}
	
	@Override
	public List<Post> selectUserAll(String writer_id, int rownum, int rownum2){
		return  postDao.selectUserAll(writer_id, rownum, rownum2);
	}
	
	@Override
	public List<Post> selectUserDiary(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserDiary(writer_id, rownum, rownum2);
	}

	@Override
	public List<Post> selectUserMovie(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserMovie(writer_id, rownum, rownum2);
	}

	@Override
	public List<Post> selectUserMusic(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserMusic(writer_id, rownum, rownum2);
	}

	@Override
	public List<Post> selectUserReview(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserReview(writer_id, rownum, rownum2);
	}

	@Override
	public List<Post> selectUserPlace(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserPlace(writer_id, rownum, rownum2);
	}

	@Override
	public List<Post> selectUserBook(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserBook(writer_id, rownum, rownum2);
	}

	@Override
	public List<Post> selectUserNews(String writer_id, int rownum, int rownum2) {
		return  postDao.selectUserNews(writer_id, rownum, rownum2);
	}
	
	@Override
	public String selectPostWriter(int post_id){
		return postDao.selectPostWriter(post_id);
	}
	
	@Override
	   public List<Post> selectAll_index2() {
	      return postDao.selectAll_index2();
	   }
	
	@Override
	public List<Post> selectNeighborAll_index2(String member_id) {
	      return postDao.selectNeighborAll_index2(member_id);
	}
	
	@Override
	public List<Post> selectMusic(int rownum, int rownum2) {
		return postDao.selectMusic(rownum, rownum2);
	}
	
	@Override
	public List<Post> selectMovie(int rownum, int rownum2) {
		return postDao.selectMovie(rownum, rownum2);
	}
	
	@Override
	public List<Post> selectDiary(int rownum, int rownum2) {
		return postDao.selectDiary(rownum, rownum2);
	}
	
	@Override
	public List<Post> selectReview(int rownum, int rownum2) {
		return postDao.selectReview(rownum, rownum2);
	}
	
	@Override
	public List<Post> selectNews(int rownum, int rownum2) {
		return postDao.selectNews(rownum, rownum2);
	}
	
	@Override
	public List<Post> selectBook(int rownum, int rownum2) {
		return postDao.selectBook(rownum, rownum2);
	}
	
	@Override
	public int maxRownum(String category) {
		return postDao.maxRownum(category);
	}

	@Override
	   public List<Post> likeInOrder(String writer_id){
	      return postDao.likeInOrder(writer_id);
	   }
	@Override
	   public List<Post> commentInOrder(String writer_id){
	      return postDao.commentInOrder(writer_id);
	   }
	
	@Override
	public List<Post> selectUserNeighbor(String loginUser, int rownum, int rownum2) {
		return  postDao.selectUserNeighbor(loginUser, rownum, rownum2);
	}
	
	@Override
	public int pinsert(String loginUser, String category,String content,String title){
		return postDao.pinsert(loginUser,category,content,title);
	}
}
