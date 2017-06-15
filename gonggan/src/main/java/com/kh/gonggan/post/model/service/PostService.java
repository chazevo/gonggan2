package com.kh.gonggan.post.model.service;

import java.util.List;

import com.kh.gonggan.post.model.vo.Post;

public interface PostService {

	Post postDetail(int post_id);
	int postContentSearchMaxRnum(String keyword);
	int postWriterSearchMaxRnum(String keyword);
	List<Post> postContentSearch(String keyword, int rownum, int rownum2);
	List<Post> postWriterSearch(String wirter_id, int rownum, int rownum2);
	List<Post> selectUserAll(String writer_id, int rownum, int rownum2);
	List<Post> selectUserMusic(String writer_id, int rownum, int rownum2);
	List<Post> selectUserMovie(String writer_id, int rownum, int rownum2);
	List<Post> selectUserReview(String writer_id, int rownum, int rownum2);
	List<Post> selectUserBook(String writer_id, int rownum, int rownum2);
	List<Post> selectUserNews(String writer_id, int rownum, int rownum2);
	List<Post> selectUserPlace(String writer_id, int rownum, int rownum2);
	List<Post> selectUserDiary(String writer_id, int rownum, int rownum2);
	String selectPostWriter(int post_id);
	List<Post> selectCalendarAll(String writer_id, int year, int month);
	List<Post> selectAll(int rownum, int rownum2);
	List<Post> selectLikeAll(int rownum, int rownum2);
	List<Post> selectLikeNpost(int rownum, int rownum2, String writer_id);
	List<Post> selectLikeCategoryPost(int rownum, int rownum2, String category);
	List<Post> selectSearchLikeList(int rownum, int rownum2, String keyword);
	List<Post> selectAll_index2();
	List<Post> selectAll_myhome(String writer_id);
	List<Post> selectNeighborAll_index2(String member_id);
	List<Post> selectMusic(int rownum, int rownum2);
	List<Post> selectMovie(int rownum, int rownum2);
	List<Post> selectDiary(int rownum, int rownum2);
	List<Post> selectReview(int rownum, int rownum2);
	List<Post> selectNews(int rownum, int rownum2);
	List<Post> selectBook(int rownum, int rownum2);
	List<Post> selectPlace(int rownum, int rownum2);
	List<Post> selectFree(int rownum, int rownum2);
	List<Post> likeInOrder(String writer_id);
	int maxRownum(String category);
	List<Post> commentInOrder(String writer_id);
	List<Post> selectUserNeighbor(String loginUser, int rownum, int rownum2);
	int pinsert(String loginUser, String category, String content,
			String diary_title, String music_title, String movie_title,
			String open, String bg, String music_info, String place_name, String pimg);
	int postDelete(int post_id, String loginUser);
	List<Post> selectUserFree(String writer_id, int rownum, int rownum2);
}
