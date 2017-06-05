package com.kh.gonggan.blog.model.vo;

public class Blog {
	
	private int blog_id;
	private String title;
	private String writer_id;
	private String contents;
	private String background;
	private String background_color;
	private String color;
	private String contents_color;
	private String languages;
	private String blog_open_yn;
	private String diary_open_yn;
	private String place_open_yn;
	private String review_open_yn;
	private String music_open_yn;
	private String movie_open_yn;
	private String news_open_yn;
	

	public Blog(){}

	public Blog(String writer_id) {
		this.writer_id = writer_id;
	}

	public Blog(String title, String writer_id, String contents, String background,
			String  color, String background_color, String contents_color,
			String languages, String blog_open_yn,
			String diary_open_yn, String place_open_yn, String review_open_yn,
			String music_open_yn, String movie_open_yn, String news_open_yn) {
		super();
		this.title = title;
		this.writer_id = writer_id;
		this.contents = contents;
		this.background = background;
		this.color = color;
		this.background_color = background_color;
		this.contents_color = contents_color;
		this.languages = languages;
		this.blog_open_yn = blog_open_yn;
		this.diary_open_yn = diary_open_yn;
		this.place_open_yn = place_open_yn;
		this.review_open_yn = review_open_yn;
		this.music_open_yn = music_open_yn;
		this.movie_open_yn = movie_open_yn;
		this.news_open_yn = news_open_yn;
	}

	public int getBlog_id() {
		return blog_id;
	}


	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getWriter_id() {
		return writer_id;
	}


	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getBackground() {
		return background;
	}


	public void setBackground(String background) {
		this.background = background;
	}

	public String getBackground_color() {
		return background_color;
	}


	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getContents_color() {
		return contents_color;
	}


	public void setContents_color(String contents_color) {
		this.contents_color = contents_color;
	}


	public String getLanguages() {
		return languages;
	}


	public void setLanguages(String languages) {
		this.languages = languages;
	}


	public String getBlog_open_yn() {
		return blog_open_yn;
	}


	public void setBlog_open_yn(String blog_open_yn) {
		this.blog_open_yn = blog_open_yn;
	}


	public String getDiary_open_yn() {
		return diary_open_yn;
	}


	public void setDiary_open_yn(String diary_open_yn) {
		this.diary_open_yn = diary_open_yn;
	}


	public String getPlace_open_yn() {
		return place_open_yn;
	}


	public void setPlace_open_yn(String place_open_yn) {
		this.place_open_yn = place_open_yn;
	}


	public String getReview_open_yn() {
		return review_open_yn;
	}


	public void setReview_open_yn(String review_open_yn) {
		this.review_open_yn = review_open_yn;
	}


	public String getMusic_open_yn() {
		return music_open_yn;
	}


	public void setMusic_open_yn(String music_open_yn) {
		this.music_open_yn = music_open_yn;
	}


	public String getMovie_open_yn() {
		return movie_open_yn;
	}


	public void setMovie_open_yn(String movie_open_yn) {
		this.movie_open_yn = movie_open_yn;
	}


	public String getNews_open_yn() {
		return news_open_yn;
	}


	public void setNews_open_yn(String news_open_yn) {
		this.news_open_yn = news_open_yn;
	}


	@Override
	public String toString() {
		return "Blog [blog_id=" + blog_id + ", title=" + title + ", writer_id=" + writer_id
				+ ", contents=" + contents + ", background=" + background
				+ ", color=" + color + ", background_color=" + background_color
				+ ", languages=" + languages
				+ ", blog_open_yn=" + blog_open_yn
				+ ", diary_open_yn=" + diary_open_yn
				+ ", place_open_yn=" + place_open_yn
				+ ", review_open_yn=" + review_open_yn
				+ ", music_open_yn=" + music_open_yn
				+ ", movie_open_yn=" + movie_open_yn
				+ ", news_open_yn=" + news_open_yn+ "]";
	}
	

}
