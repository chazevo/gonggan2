package com.kh.gonggan.free.model.vo;

public class Free {
	private int post_id;
	private int free_num;
	private String free_content;
	
	public Free(){}
	
	public Free(int post_id, int free_num, String free_content) {
		super();
		this.post_id = post_id;
		this.free_num = free_num;
		this.free_content = free_content;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getFree_num() {
		return free_num;
	}

	public void setFree_num(int free_num) {
		this.free_num = free_num;
	}

	public String getFree_content() {
		return free_content;
	}

	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}

	
}
