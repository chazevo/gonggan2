package com.kh.gonggan.movie.model.vo;

public class Movie {
	private int post_id;
	private int movie_num;
	private String movie_info;
	private String title;
	private String link;
	private String image;
	private String subtitle;
	private String pubDate;
	private String director;
	private String actor;
	
	public Movie(){}

	public Movie(int post_id, int movie_num, String movie_info, String title, String link, String image,
			String subtitle, String pubDate, String director, String actor) {
		super();
		this.post_id = post_id;
		this.movie_num = movie_num;
		this.movie_info = movie_info;
		this.title = title;
		this.link = link;
		this.image = image;
		this.subtitle = subtitle;
		this.pubDate = pubDate;
		this.director = director;
		this.actor = actor;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getMovie_num() {
		return movie_num;
	}

	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}

	public String getMovie_info() {
		return movie_info;
	}

	public void setMovie_info(String movie_info) {
		this.movie_info = movie_info;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getpubDate() {
		return pubDate;
	}

	public void setpubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	@Override
	public String toString() {
		return "Movie [post_id=" + post_id + ", movie_num=" + movie_num + ", movie_info=" + movie_info + ", title="
				+ title + ", link=" + link + ", image=" + image + ", subtitle=" + subtitle + ", pubDate=" + pubDate
				+ ", director=" + director + ", actor=" + actor + "]";
	}
	
	

}