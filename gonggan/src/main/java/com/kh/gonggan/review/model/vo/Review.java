package com.kh.gonggan.review.model.vo;

public class Review {
	private int post_id;
	private int review_num;
	private String review_content;
	public Review(){}
	public Review(int post_id, int review_num, String review_content) {
		super();
		this.post_id = post_id;
		this.review_num = review_num;
		this.review_content = review_content;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	@Override
	public String toString() {
		return "Review [post_id=" + post_id + ", review_num=" + review_num + ", review_content=" + review_content + "]";
	}
}
