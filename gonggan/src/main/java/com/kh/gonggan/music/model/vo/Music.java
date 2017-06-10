package com.kh.gonggan.music.model.vo;

public class Music {

	private String videoId;
	private String title;
	private String thumbnail_default;
	private String thumbnail_high;
	private int post_id;
	private int music_num;
	private String music_info;
	private String singer;

	private String music_content;
	

	public Music(){}
	
	public Music(String videoId, String title, String thumbnail_default, String thumbnail_high) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.thumbnail_default = thumbnail_default;
		this.thumbnail_high = thumbnail_high;
	}

	public Music(String videoId, String title, String thumbnail_default, String thumbnail_high,
			int post_id, int music_num, String music_info,
			String singer, String music_content) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.thumbnail_default = thumbnail_default;
		this.thumbnail_high = thumbnail_high;
		this.post_id = post_id;
		this.music_num = music_num;
		this.music_info = music_info;
		this.singer = singer;
		this.music_content = music_content;
	}
	
	@Override
	public String toString() {
		return "Music [videoId=" + videoId + ", title=" + title
				+ ", thumbnail_default=" + thumbnail_default
				+ ", thumbnail_high=" + thumbnail_high
				+ ", post_id=" + post_id
				+ ", music_num=" + music_num + ", music_info=" + music_info + ", singer=" + singer + ", music_content="
				+ music_content + "]";
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

	public String getThumbnail_default() {
		return thumbnail_default;
	}

	public void setThumbnail_default(String thumbnail_default) {
		this.thumbnail_default = thumbnail_default;
	}

	public String getThumbnail_high() {
		return thumbnail_high;
	}

	public void setThumbnail_high(String thumbnail_high) {
		this.thumbnail_high = thumbnail_high;
	}
	
}
