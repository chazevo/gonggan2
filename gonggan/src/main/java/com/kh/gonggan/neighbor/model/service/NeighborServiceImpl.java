package com.kh.gonggan.neighbor.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.gonggan.neighbor.model.dao.NeighborDao;
import com.kh.gonggan.neighbor.model.vo.Neighbor;

@Service("neighborService")
public class NeighborServiceImpl implements NeighborService{
	@Autowired
	NeighborDao neighborDao;

	@Override
	public List<Neighbor> selectNeighborList(String loggedinUser) {
		return neighborDao.selectNeighborList(loggedinUser); 
	}
}
