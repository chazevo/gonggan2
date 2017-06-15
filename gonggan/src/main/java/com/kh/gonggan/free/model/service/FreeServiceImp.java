package com.kh.gonggan.free.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.free.model.dao.FreeDao;
import com.kh.gonggan.free.model.vo.Free;
import com.kh.gonggan.place.model.service.PlaceService;

@Service("freeService")
public class FreeServiceImp implements FreeService {

	@Autowired
	FreeDao freeDao;
	
	@Override
	public List<Free> selectAll(int rownum, int rownum2) {
		return freeDao.selectAll(rownum, rownum2);
	}
	
	@Override
	public List<Free> selectAll_index2() {
		return freeDao.selectAll_index2();
   }
	
	@Override
	public List<Free> selectAll_myhome(String writer_id) {
		return freeDao.selectAll_myhome(writer_id);
	}
	
	@Override
	public Free freeDetail(int postId) {
		return freeDao.freeDetail(postId);
	}
	
}
