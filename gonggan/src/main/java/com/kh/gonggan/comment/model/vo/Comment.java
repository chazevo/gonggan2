package com.kh.gonggan.comment.model.vo;

import java.sql.Date;

public class Comment {
	
	private int post_id;
	private int comment_num;
	private String writer_id;
	private String comment_content;
	private Date comment_date;
	private String comment_check;
	
	public Comment() {
		super();
	}
	public Comment(int post_id, int comment_num, String writer_id, String comment_content, Date comment_date, String comment_check) {
		this.post_id = post_id;
		this.comment_num = comment_num;
		this.writer_id = writer_id;
		this.comment_content = comment_content;
		this.comment_date = comment_date;
		this.comment_check = comment_check;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getComment_check() {
		return comment_check;
	}
	public void setComment_check(String comment_check) {
		this.comment_check = comment_check;
	}
	
	
	
}

