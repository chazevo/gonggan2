package com.kh.gonggan.diary.model.service;

import java.util.List;

import com.kh.gonggan.diary.model.vo.Diary;

public interface DiaryService {
	List<Diary> selectAll_index2();
	List<Diary> selectAll_myhome(String writer_id);
	Diary diaryDetail(int postId);
}