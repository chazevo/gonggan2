package com.kh.gonggan.diary.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.gonggan.diary.model.vo.Diary;
import com.kh.gonggan.news.model.vo.News;

@Repository("diaryDao")
public class DiaryDao {
   @Autowired
   private SqlSessionTemplate sqlSession;
   
   public DiaryDao(){}
   
   public List<Diary> selectAll_index2(){
      return (List<Diary>) sqlSession.selectList("diarymapper.diarylist_index2");
   }
   public Diary diaryDetail(int postId) {
	   return (Diary) sqlSession.selectList("diarymapper.diarydetail");
   }
}