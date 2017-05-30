package com.kh.gonggan.diary.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.diary.model.dao.DiaryDao;
import com.kh.gonggan.diary.model.vo.Diary;
@Service("diaryService")
public class DiaryServiceImpl implements DiaryService{
   @Autowired
   DiaryDao diaryDao;
   
   @Override
   public List<Diary> selectAll_index2(){
      return diaryDao.selectAll_index2();
   }
   
   @Override
   public Diary diaryDetail(int postId) {
	      return diaryDao.diaryDetail(postId);
   }
}