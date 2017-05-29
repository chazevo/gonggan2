package com.kh.gonggan.music.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.music.model.vo.Music;


@Repository("musicDao")
public class MusicDao {
   
   @Autowired
   private SqlSessionTemplate sqlSession;
   
   public MusicDao(){}
   
   public List<Music> selectAll_index2(){
   
      return (List<Music>) sqlSession.selectList("musicmapper.musiclist_index2");
   }
   

}