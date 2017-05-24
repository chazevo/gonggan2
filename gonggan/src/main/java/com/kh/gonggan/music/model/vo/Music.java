package com.kh.gonggan.music.model.vo;

public class Music {

	private String videoId;
	private String title;
	private String thumbnail;
	
	public Music(){}
	
	public Music(String videoId, String title, String thumbnail) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.thumbnail = thumbnail;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
