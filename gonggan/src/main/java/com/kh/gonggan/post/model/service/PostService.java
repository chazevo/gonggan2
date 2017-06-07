package com.kh.gonggan.post.model.service;

import java.util.List;

import com.kh.gonggan.post.model.vo.Post;

public interface PostService {

	Post postDetail(int post_id);
	List<Post> selectUserAll(String writer_id, int rownum, int rownum2);
	List<Post> selectUserMusic(String writer_id, int rownum, int rownum2);
	List<Post> selectUserMovie(String writer_id, int rownum, int rownum2);
	List<Post> selectUserReview(String writer_id, int rownum, int rownum2);
	List<Post> selectUserBook(String writer_id, int rownum, int rownum2);
	List<Post> selectUserNews(String writer_id, int rownum, int rownum2);
	List<Post> selectUserPlace(String writer_id, int rownum, int rownum2);
	List<Post> selectUserDiary(String writer_id, int rownum, int rownum2);
	String selectPostWriter(int post_id);
	List<Post> selectAll(int rownum, int rownum2);
	List<Post> selectLikeAll(int rownum, int rownum2);
	List<Post> selectLikeNpost(int rownum, int rownum2, String writer_id);
	List<Post> selectLikeCategoryPost(int rownum, int rownum2, String category);
	List<Post> selectAll_index2();
	List<Post> selectNeighborAll_index2(String member_id);
	List<Post> selectMusic(int rownum, int rownum2);
	List<Post> selectMovie(int rownum, int rownum2);
	List<Post> selectDiary(int rownum, int rownum2);
	List<Post> selectReview(int rownum, int rownum2);
	List<Post> selectNews(int rownum, int rownum2);
	List<Post> selectBook(int rownum, int rownum2);
	List<Post> likeInOrder(String writer_id);
	int maxRownum(String category);
	List<Post> commentInOrder(String writer_id);
	List<Post> selectUserNeighbor(String loginUser, int rownum, int rownum2);
	int pinsert(String loginUser, String category,String content);
}
