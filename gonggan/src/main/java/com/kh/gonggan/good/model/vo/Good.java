package com.kh.gonggan.good.model.vo;

public class Good {
	private int post_id;
	private String member_id;
	private String good_check;
	
	public Good(){}

	public Good(int post_id, String member_id, String good_check) {
		super();
		this.post_id = post_id;
		this.member_id = member_id;
		this.good_check = good_check;
	}

	public Good(int post_id, String member_id) {
		super();
		this.post_id = post_id;
		this.member_id = member_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getGood_check() {
		return good_check;
	}

	public void setGood_check(String good_check) {
		this.good_check = good_check;
	}

	@Override
	public String toString() {
		return "Good [post_id=" + post_id + ", member_id=" + member_id + ", good_check=" + good_check + "]";
	}

	
}
