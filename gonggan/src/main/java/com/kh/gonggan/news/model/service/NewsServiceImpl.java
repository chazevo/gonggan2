package com.kh.gonggan.news.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.news.model.dao.NewsDao;
import com.kh.gonggan.news.model.vo.News;

@Service("newsService")
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsDao newsDao;
	
	@Override
	public List<News> selectAll(int rownum, int rownum2) {
		return newsDao.selectAll(rownum, rownum2);
	}
	
	@Override
	public List<News> selectAll_index2() {
		return newsDao.selectAll_index2();
   }

	@Override
	public News newsDetail(int postId) {
		return newsDao.newsDetail(postId);
	}
	
}