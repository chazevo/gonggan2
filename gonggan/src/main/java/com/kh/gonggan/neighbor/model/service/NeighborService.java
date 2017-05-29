package com.kh.gonggan.neighbor.model.service;

import java.util.List;

import com.kh.gonggan.neighbor.model.vo.Neighbor;

public interface NeighborService {
	List<Neighbor> selectNeighborList(String loggedinUser);
}
