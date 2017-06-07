package com.kh.gonggan.free.model.vo;

public class Free {
	private int post_id;
	private int post_num;
	private String free_content;
	
	public Free(){}
	
	public Free(int post_id, int post_num, String free_content) {
		super();
		this.post_id = post_id;
		this.post_num = post_num;
		this.free_content = free_content;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getPost_num() {
		return post_num;
	}

	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}

	public String getFree_content() {
		return free_content;
	}

	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}

	@Override
	public String toString() {
		return "Free [post_id=" + post_id + ", post_num=" + post_num + ", free_content=" + free_content + "]";
	}
	
}
