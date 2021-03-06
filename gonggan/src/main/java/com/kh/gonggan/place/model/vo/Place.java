package com.kh.gonggan.place.model.vo;

public class Place {
	private int post_id;
	private int place_num;
	private String place_content;
	private String place_name;
	
	public Place(){}

	public Place(int post_id, int place_num, String place_content, String place_name) {
		super();
		this.post_id = post_id;
		this.place_num = place_num;
		this.place_content = place_content;
		this.place_name = place_name;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getPlace_num() {
		return place_num;
	}

	public void setPlace_num(int place_num) {
		this.place_num = place_num;
	}

	public String getPlace_content() {
		return place_content;
	}

	public void setPlace_content(String place_content) {
		this.place_content = place_content;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	
	
}

