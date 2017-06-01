package com.kh.gonggan.music.model.service;

import java.util.List;

import com.kh.gonggan.music.model.vo.Music;

public interface MusicService {
	List<Music> selectAll(int rownum, int rownum2);
	List<Music> selectAll_index2();
	Music musicDetail(int postId);
}