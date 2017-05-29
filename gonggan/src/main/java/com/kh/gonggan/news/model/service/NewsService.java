package com.kh.gonggan.news.model.service;

import java.util.List;

import com.kh.gonggan.news.model.vo.News;

public interface NewsService {
	
	List<News> selectAll(int rownum, int rownum2);
	List<News> selectAll_index2();
	News newsDetail(int postId);
}