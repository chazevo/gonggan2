package com.kh.gonggan.news.model.vo;

public class News {
	
	private String title;
	private String originallink;
	private String description;
	private String pubDate;
	private int post_id;
	private int news_num;
	private String news_info;
	private String news_link;
	private String news_content;
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getNews_num() {
		return news_num;
	}

	public void setNews_num(int news_num) {
		this.news_num = news_num;
	}

	public String getNews_info() {
		return news_info;
	}

	public void setNews_info(String news_info) {
		this.news_info = news_info;
	}

	public String getNews_link() {
		return news_link;
	}

	public void setNews_link(String news_link) {
		this.news_link = news_link;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public News(String title, String originallink, String description, String pubDate, int post_id, int news_num,
			String news_info, String news_link, String news_content) {
		super();
		this.title = title;
		this.originallink = originallink;
		this.description = description;
		this.pubDate = pubDate;
		this.post_id = post_id;
		this.news_num = news_num;
		this.news_info = news_info;
		this.news_link = news_link;
		this.news_content = news_content;
	}

	public News(){}
	
	public News(String title, String originallink, String description, String pubDate) {
		super();
		this.title = title;
		this.originallink = originallink;
		this.description = description;
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginallink() {
		return originallink;
	}

	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", originallink=" + originallink + ", description=" + description + ", pubDate="
				+ pubDate + "]";
	}
	

}
