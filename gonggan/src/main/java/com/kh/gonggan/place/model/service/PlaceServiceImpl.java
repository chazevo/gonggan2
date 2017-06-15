package com.kh.gonggan.place.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.place.model.dao.PlaceDao;
import com.kh.gonggan.place.model.vo.Place;

@Service("placeService")
public class PlaceServiceImpl implements PlaceService{
	@Autowired
	PlaceDao placeDao;
	
	@Override
	public List<Place> selectAll(int rownum, int rownum2) {
		return placeDao.selectAll(rownum, rownum2);
	}
	
	@Override
	public List<Place> selectAll_index2() {
		return placeDao.selectAll_index2();
   }
	
	@Override
	public List<Place> selectAll_myhome(String writer_id) {
		return placeDao.selectAll_myhome(writer_id);
	}
	
	@Override
	public Place placeDetail(int postId) {
		return placeDao.newsDetail(postId);
	}
	
}