package com.kh.gonggan.post.model.vo;

import java.util.Date;
import java.util.List;

import com.kh.gonggan.movie.model.vo.Movie;
import com.kh.gonggan.news.model.vo.News;

public class Post {

	private int postId;
	private String writerId;
	private String category;
	private String sharYn;
	private String openYn;
	private int goodCnt;
	private String photoPath;
	private Date postDate;
	
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSharYn() {
		return sharYn;
	}
	public void setSharYn(String sharYn) {
		this.sharYn = sharYn;
	}
	public String getOpenYn() {
		return openYn;
	}
	public void setOpenYn(String openYn) {
		this.openYn = openYn;
	}
	public int getGoodCnt() {
		return goodCnt;
	}
	public void setGoodCnt(int goodCnt) {
		this.goodCnt = goodCnt;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

}
