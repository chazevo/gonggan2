package com.kh.gonggan.blog.model.vo;

public class Blog {
	private String title;
	private String writer_id;
	private String contents;
	private String background;
	private String color;
	private String languages;
	private String blog_open_yn;
	private int blog_id;
	

	public Blog(){}


	public Blog(String title, String writer_id, String contents, String background, String color, String languages,
			String blog_open_yn, int blog_id) {
		super();
		this.title = title;
		this.writer_id = writer_id;
		this.contents = contents;
		this.background = background;
		this.color = color;
		this.languages = languages;
		this.blog_open_yn = blog_open_yn;
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


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
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


	public int getBlog_id() {
		return blog_id;
	}


	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}


	@Override
	public String toString() {
		return "Blog [title=" + title + ", writer_id=" + writer_id + ", contents=" + contents + ", background="
				+ background + ", color=" + color + ", languages=" + languages + ", blog_open_yn=" + blog_open_yn
				+ ", blog_id=" + blog_id + "]";
	}
	

}
