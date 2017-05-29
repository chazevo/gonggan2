package com.kh.gonggan.music.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.music.model.vo.Music;


@Repository("musicDao")
public class MusicDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public MusicDao(){}
	
	public List<Music> selectAll(int rownum, int rownum2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rownum", rownum);
		map.put("rownum2", rownum2);
		return (List<Music>) sqlSession.selectList("musicmapper.musiclist", map);
	}
	
	public List<Music> selectAll_index2(){
		return (List<Music>) sqlSession.selectList("musicmapper.musiclist_index2");
	}
	

	public Music musicDetail(int postId) {
		return (Music) sqlSession.selectList("musicmapper.musicdetail", postId);
	}
	
}