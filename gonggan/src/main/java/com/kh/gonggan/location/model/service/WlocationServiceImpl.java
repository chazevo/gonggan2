package com.kh.gonggan.location.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.location.model.service.WlocationService;
import com.kh.gonggan.location.model.dao.WlocationDao;
import com.kh.gonggan.location.model.vo.Wlocation;

@Service("WlocationService")
public class WlocationServiceImpl implements WlocationService {
	@Autowired
	WlocationDao wlocationDao;
	
	@Override
	public List<Wlocation> selectWlocationList() {
		return wlocationDao.selectWlocationList();
	}
	
}
