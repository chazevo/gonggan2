package com.kh.gonggan.place.model.service;

import java.util.List;

import com.kh.gonggan.place.model.vo.Place;

public interface PlaceService {
	
	List<Place> selectAll(int rownum, int rownum2);
	List<Place> selectAll_index2();
	List<Place> selectAll_myhome(String writer_id);
	Place placeDetail(int postId);
}