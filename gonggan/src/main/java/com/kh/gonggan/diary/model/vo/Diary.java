package com.kh.gonggan.diary.model.vo;

public class Diary {
	
	private int post_id;
	private int diary_num;
	private String diary_content;
	private String title;
	private String bg_image;

	public Diary(){}

	public Diary(int post_id, int diary_num, String diary_content, String title) {
		super();
		this.post_id = post_id;
		this.diary_num = diary_num;
		this.diary_content = diary_content;
		this.title = title;
	}

	public Diary(int post_id, int diary_num, String diary_content, String title, String bg_image) {
		super();
		this.post_id = post_id;
		this.diary_num = diary_num;
		this.diary_content = diary_content;
		this.bg_image = bg_image;
	}
	
	
	
	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getDiary_num() {
		return diary_num;
	}

	public void setDiary_num(int diary_num) {
		this.diary_num = diary_num;
	}

	public String getDiary_content() {
		return diary_content;
	}

	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBg_image() {
		return bg_image;
	}

	public void setBg_image(String bg_image) {
		this.bg_image = bg_image;
	}

	@Override
	public String toString() {
		return "Diary [post_id=" + post_id + ", diary_num=" + diary_num + ", diary_content=" + diary_content + "]";
	}
}