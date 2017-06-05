package com.kh.gonggan.book.model.vo;

public class BestSeller {
	
	private String title;
	private String author;
	private String description;
	private String pubDate;
	private String coverSmallUrl;
	private String coverLargeUrl;
	private String link;
	private int rank;
	
	public BestSeller() {
	}
	public BestSeller(String title, String author, String description, String pubDate, String coverSmallUrl,
			String coverLargeUrl, String link, int rank) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.pubDate = pubDate;
		this.coverSmallUrl = coverSmallUrl;
		this.coverLargeUrl = coverLargeUrl;
		this.link = link;
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getCoverSmallUrl() {
		return coverSmallUrl;
	}
	public void setCoverSmallUrl(String coverSmallUrl) {
		this.coverSmallUrl = coverSmallUrl;
	}
	public String getCoverLargeUrl() {
		return coverLargeUrl;
	}
	public void setCoverLargeUrl(String coverLargeUrl) {
		this.coverLargeUrl = coverLargeUrl;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	
}
