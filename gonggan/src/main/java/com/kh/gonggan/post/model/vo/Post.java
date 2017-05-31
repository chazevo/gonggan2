package com.kh.gonggan.post.model.vo;

import java.util.Date;
import java.util.List;

import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.news.model.vo.News;

public class Post {

	private int post_id;
	private int music_num;
	private int diary_num;
	private int movie_num;
	private int news_num;
	private int review_num;
	private String writer_id;
	private String diary_content;
	private String movie_content;
	private String music_content;
	private String news_content;
	private String review_content;
	private String category;
	private String shar_yn;
	private String open_yn;
	private int goodCnt;
	private String photo_path;
	private Date post_date;
	private int comment_cnt;
	
	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public Post(int post_id, int music_num, int diary_num, int movie_num, int news_num, int review_num,
			String writer_id, String diary_content, String movie_content, String music_content, String news_content,
			String review_content, String category, String shar_yn, String open_yn, int goodCnt, String photo_path,
			Date post_date, int comment_cnt) {
		super();
		this.post_id = post_id;
		this.music_num = music_num;
		this.diary_num = diary_num;
		this.movie_num = movie_num;
		this.news_num = news_num;
		this.review_num = review_num;
		this.writer_id = writer_id;
		this.diary_content = diary_content;
		this.movie_content = movie_content;
		this.music_content = music_content;
		this.news_content = news_content;
		this.review_content = review_content;
		this.category = category;
		this.shar_yn = shar_yn;
		this.open_yn = open_yn;
		this.goodCnt = goodCnt;
		this.photo_path = photo_path;
		this.post_date = post_date;
		this.comment_cnt = comment_cnt;
	}

	public Post(){}

	public Post(int post_id, int music_num, int diary_num, int movie_num, int news_num, int review_num,
			String writer_id, String diary_content, String movie_content, String music_content, String news_content,
			String review_content, String category, String shar_yn, String open_yn, int goodCnt, String photo_path,
			Date post_date) {
		super();
		this.post_id = post_id;
		this.music_num = music_num;
		this.diary_num = diary_num;
		this.movie_num = movie_num;
		this.news_num = news_num;
		this.review_num = review_num;
		this.writer_id = writer_id;
		this.diary_content = diary_content;
		this.movie_content = movie_content;
		this.music_content = music_content;
		this.news_content = news_content;
		this.review_content = review_content;
		this.category = category;
		this.shar_yn = shar_yn;
		this.open_yn = open_yn;
		this.goodCnt = goodCnt;
		this.photo_path = photo_path;
		this.post_date = post_date;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getMusic_num() {
		return music_num;
	}

	public void setMusic_num(int music_num) {
		this.music_num = music_num;
	}

	public int getDiary_num() {
		return diary_num;
	}

	public void setDiary_num(int diary_num) {
		this.diary_num = diary_num;
	}

	public int getMovie_num() {
		return movie_num;
	}

	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}

	public int getNews_num() {
		return news_num;
	}

	public void setNews_num(int news_num) {
		this.news_num = news_num;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getDiary_content() {
		return diary_content;
	}

	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}

	public String getMovie_content() {
		return movie_content;
	}

	public void setMovie_content(String movie_content) {
		this.movie_content = movie_content;
	}

	public String getMusic_content() {
		return music_content;
	}

	public void setMusic_content(String music_content) {
		this.music_content = music_content;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShar_yn() {
		return shar_yn;
	}

	public void setShar_yn(String shar_yn) {
		this.shar_yn = shar_yn;
	}

	public String getOpen_yn() {
		return open_yn;
	}

	public void setOpen_yn(String open_yn) {
		this.open_yn = open_yn;
	}

	public int getGoodCnt() {
		return goodCnt;
	}

	public void setGoodCnt(int goodCnt) {
		this.goodCnt = goodCnt;
	}

	public String getPhoto_path() {
		return photo_path;
	}

	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}

	public Date getPost_date() {
		return post_date;
	}

	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", music_num=" + music_num + ", diary_num=" + diary_num + ", movie_num="
				+ movie_num + ", news_num=" + news_num + ", review_num=" + review_num + ", writer_id=" + writer_id
				+ ", diary_content=" + diary_content + ", movie_content=" + movie_content + ", music_content="
				+ music_content + ", news_content=" + news_content + ", review_content=" + review_content
				+ ", category=" + category + ", shar_yn=" + shar_yn + ", open_yn=" + open_yn + ", goodCnt=" + goodCnt
				+ ", photo_path=" + photo_path + ", post_date=" + post_date + "]";
	}
	
	
}
