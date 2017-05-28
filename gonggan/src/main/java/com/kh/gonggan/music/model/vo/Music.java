package com.kh.gonggan.music.model.vo;

public class Music {
	private int post_id;
	private int music_num;
	private String music_info;
	private String title;
	private String singer;
	private String music_content;
	public Music() {
		super();
	}
	public Music(int post_id, int music_num, String music_info, String title, String singer, String music_content) {
		super();
		this.post_id = post_id;
		this.music_num = music_num;
		this.music_info = music_info;
		this.title = title;
		this.singer = singer;
		this.music_content = music_content;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getMusic_num() {
		return music_num;
	}
	public void setMusic_num(int music_num) {
		this.music_num = music_num;
	}
	public String getMusic_info() {
		return music_info;
	}
	public void setMusic_info(String music_info) {
		this.music_info = music_info;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getMusic_content() {
		return music_content;
	}
	public void setMusic_content(String music_content) {
		this.music_content = music_content;
	}
	@Override
	public String toString() {
		return "Music [post_id=" + post_id + ", music_num=" + music_num + ", music_info=" + music_info + ", title="
				+ title + ", singer=" + singer + ", music_content=" + music_content + "]";
	}

	/*private String videoId;
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
	}*/
	
}
