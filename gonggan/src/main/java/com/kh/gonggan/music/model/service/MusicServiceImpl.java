package com.kh.gonggan.music.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.music.model.dao.MusicDao;
import com.kh.gonggan.music.model.vo.Music;

@Service("musicService")
public class MusicServiceImpl implements MusicService{
	
	@Autowired
	MusicDao musicDao;
	
	@Override
	public List<Music> selectAll(int rownum, int rownum2) {
		return musicDao.selectAll(rownum, rownum2);
	}
	
	@Override
	public List<Music> selectAll_index2() {
		return musicDao.selectAll_index2();
	}
	
	@Override
	public List<Music> selectAll_myhome(String writer_id) {
		return musicDao.selectAll_myhome(writer_id);
	}
	
	@Override
	public Music musicDetail(int postId) {
		return musicDao.musicDetail(postId);
	}

}