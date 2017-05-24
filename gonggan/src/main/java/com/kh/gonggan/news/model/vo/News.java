package com.kh.gonggan.news.model.vo;

public class News {
	
	private String title;
	private String originallink;
	private String description;
	private String pubDate;
	
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
