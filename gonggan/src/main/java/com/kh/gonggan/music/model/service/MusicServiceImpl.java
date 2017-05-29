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
   public List<Music> selectAll_index2(){
      return musicDao.selectAll_index2();
   }
   

}