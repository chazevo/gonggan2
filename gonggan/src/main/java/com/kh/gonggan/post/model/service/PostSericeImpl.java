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
	public List<Post> selectAll(int rownum, int rownum2) {
		System.out.println("service : selectList run...");
		return  postDao.selectAll(rownum, rownum2);	
	}
	
	@Override
	public List<Post> selectLikeAll(int rownum, int rownum2) {
		return  postDao.selectLikeAll(rownum, rownum2);	
	}
	
	@Override
	public Post postDetail(int post_id) {
		return  postDao.postDetail(post_id);
		
	}
	
	@Override
	public List<Post> selectUserAll(String writer_id){
		return  postDao.selectUserAll(writer_id);
	}
	@Override
	public String selectPostWriter(int post_id){
		return postDao.selectPostWriter(post_id);
	}
	@Override
	   public List<Post> selectAll_index2(){
	      return postDao.selectAll_index2();
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
	

}
